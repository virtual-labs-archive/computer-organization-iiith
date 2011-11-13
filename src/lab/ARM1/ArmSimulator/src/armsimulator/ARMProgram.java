package armsimulator;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.FileReader;
import java.util.*;

import armsimulator.BASFS.* ;
import armsimulator.BASFS.Error;

public class ARMProgram {
        private Error assembleTimeError ;
	private String filename;
	private ArrayList sourceList;
	private ArrayList commentRemovedSource;
	private ArrayList sourceOfDataSection ; 
	private ArrayList sourceOfTextSection ;
        private ArrayList<Integer> DataSectionLineNo;      // For Generating Error msg
	private ArrayList<Integer> TextSectionLineNo;      // For Generating Error msg
	private Register Reg;
	private Memory Mem;
	private ArrayList<Instruction> AllInstructions ; 
	

	public ARMProgram(String source) throws IOException{
                assembleTimeError = new Error();
		AllInstructions=new ArrayList<Instruction>();
		Reg=new Register();
		Mem=new Memory();		
		readSource(source);
		removeComment();
//		PrintSource();
//		PrintCommentRemovedSource();
		splitDataAndTextSection();
		//printDataAndTextSections();
		
		Error err = processDataSection() ;
                if(!err.isOk()){
			err.printErrorMsg();
			assembleTimeError = err ;
                        return ;
		}
                //System.out.println("!!yes!!");
		//printDataAndTextSections();
                //System.out.println("!!yes!!");
		err = processTextSection();
                if(!err.isOk()){
			err.printErrorMsg();
                        assembleTimeError = err ;
			return ;
		}
                //printDataAndTextSections();
                //RunAllInstructions();
	}
        public Error getAssembleTimeError(){
            return assembleTimeError ;
        }
	public void runCurrentInstruction(){
		int currentPC=Reg.getPC();
		int startPC=Instruction.startAddressOfInst;
		int index=(currentPC-startPC)/4;
		if(index>=AllInstructions.size()){
			//
		}else{
			Instruction instr=AllInstructions.get(index);
                        instr.runSingleInstruction();

                }
        }
	private void RunAllInstructions() throws IOException {
		int PClimit=Register.getPC()+AllInstructions.size()*4;
		
		int startPC=Register.getPC();
		int currentPC;
		
		while(true){
			currentPC=Reg.getPC();
//			System.out.println("CURRENT PC : "+Register.getPC());
			if(currentPC >= PClimit){
				// all instructions have been executed 
				break;
			}
			int index=(currentPC-startPC)/4;
			
			Instruction instr=AllInstructions.get(index);
			instr.runSingleInstruction();
		//	Register.printAllReg();
		}
		
		for(int i=0;i<10;i++){
			int addr=4*i+Mem.startAddressOfDynamicData;
			System.out.println(" Fibonacci["+i+"] = "+Mem.readDynamicMemory(addr));
		}
	}


        public ArrayList<String> getsourceOfTextSection(){
            ArrayList<String> sourceLinesToDisplay = new ArrayList<String>();

            for(int i=0 ; i<AllInstructions.size();i++){
                sourceLinesToDisplay.add(AllInstructions.get(i).getSourceString());
            }

            return sourceLinesToDisplay ;
        }

	private void PrintCommentRemovedSource() {
		System.out.println("\n**********  PRINTING COMMENT REMOVED SOURCE FILE  ******************");
		for(int i=0;i<commentRemovedSource.size();i++)
			System.out.println(commentRemovedSource.get(i));
	}

	private void PrintSource() {
		System.out.println("**********  PRINTING SOURCE FILE  ******************");
		for(int i=0;i<sourceList.size();i++)
			System.out.println(sourceList.get(i));
	}

	public String getSourceLine(int lineNo) {
		if ((lineNo >= 1) && (lineNo <= sourceList.size()))
			return (String) sourceList.get(lineNo - 1);
		else
			return null;
	}

	
	public void readSource(String source) {
		this.sourceList = new ArrayList<String>();
		String[] tempSource = source.split("\n");
		try {
                    for(int i=0;i<tempSource.length;i++){
                        sourceList.add(tempSource[i]);
                    }
		} catch (Exception e) {

		}
		return;
	}
	
	public void removeComment(){
		commentRemovedSource=new ArrayList<String>();
		for(int i=0;i<sourceList.size();i++){
			String s=removeCommentFromAString((String)sourceList.get(i));
			s=MyLibrary.removeBeginningAndEndingSpace(s);
			if(!s.equals("")){
				commentRemovedSource.add(s);
			}
		}
	}

	private String removeCommentFromAString(String str) {
		int ind=str.indexOf('@');
		if(ind!=-1){
			str=str.substring(0,ind);
		}
		ind=str.indexOf(';');
		if(ind!=-1){
			str=str.substring(0,ind);
		}
		
		return str;
	}
	
	/********************************************************* 
	 * CODE TO SPLIT DATA AND TEXT SECTION INTO 2 SUBARRAYS OF 
	 * STRINGS CONTAINING DATA AND TEXT SECTION SAPERATELY 
	 * *******************************************************/
	
	public void splitDataAndTextSection(){
		sourceOfDataSection=new ArrayList<String>();
		sourceOfTextSection=new ArrayList<String>();
                DataSectionLineNo  = new ArrayList<Integer>() ;
		TextSectionLineNo  = new ArrayList<Integer>() ; 
		int DATA=0 , TEXT=1;
		int flag=DATA;
		for(int i=0;i<commentRemovedSource.size();i++){
			String str=(String) commentRemovedSource.get(i);
			str=str.toLowerCase();
			if(str.startsWith(".text")){
				flag=TEXT;
			}else if(str.startsWith(".data")){
				flag=DATA;
			}else{
				if(flag==DATA){
					sourceOfDataSection.add(str);
                                        DataSectionLineNo.add(i+1);
				}else{
					if(str.startsWith(".arm") || str.startsWith(".global")){
						continue;
					}
					sourceOfTextSection.add(str);
                                        //System.out.println(str);
                                        TextSectionLineNo.add(i+1);
				}
			}
		}
	}
	
	public void printDataAndTextSections(){
		System.out.println("\n\nPRINTING DATA SECTION\n\n");
		for(int i=0;i<sourceOfDataSection.size();i++){
			System.out.println(sourceOfDataSection.get(i));
		}
		
		System.out.println("\n\nPRINTING TEXT SECTION\n\n");
		for(int i=0;i<sourceOfTextSection.size();i++){
			System.out.println(sourceOfTextSection.get(i));
		}
	}
        

	/*************************************************
	 * CODE TO SPLIT DATA AND TEXT SECTION ENDS HERE 
	 * ***********************************************/
	
	
	/*********************************************************
	 * PROCESS THE DATA SECTION AND ALLOCATE THE MEMORY
	 * TO ALL THE DIRECTIVES (currently .space and .word only)
	 *********************************************************/
	
	private Error processDataSection(){
            
                Error err = new Error();
		for(int i=0;i<sourceOfDataSection.size();i++){
			String str=(String) sourceOfDataSection.get(i);
			if(doesStartWithLabel(str)){
			
				String labelName=getlabelName(str);
			
				Mem.setBeginingAddress(labelName);
                                //printDataAndTextSections();
                                //System.out.println("YESS!!!\n");
				err = processDirectives(str.substring(labelName.length()+1) ,  DataSectionLineNo.get(i));
                                //printDataAndTextSections();
                        }else {
                            //System.out.println("NO\n");
				err = processDirectives(str ,  DataSectionLineNo.get(i));
			}
                        if(!err.isOk()){
				break;
			}
		}
                //printDataAndTextSections();
                return err;
	}
	private boolean doesStartWithLabel(String str){
		for(int i=0;i<str.length();i++){
			if(MyLibrary.isSpaceChar(str.charAt(i))){
				return false;
			}else if(str.charAt(i)==':'){
				return true;
			}
		}
		return false;
	}
	private String getlabelName(String str){
		int ind=-1;
		for(int i=0;i<str.length();i++){
			if(MyLibrary.isSpaceChar(str.charAt(i))){
				break;
			}else if(str.charAt(i)==':'){
				ind=i ; break;
			}
		}
		if(ind==-1)return "";
		return str.substring(0, ind);
	}
	private Error processDirectives(String str, int sourceLineNo) {
           // printDataAndTextSections();
		str=MyLibrary.removeBeginningAndEndingSpace(str);
//		System.out.println("PROCESSING DIRECTIVE --> "+str);
		Error err = new Error();
                if(str.equals("")){
			return err;
		}
		if(str.startsWith(".space")){
                    //System.out.println("space");
			str=str.substring(6);
			str=MyLibrary.removeBeginningAndEndingSpace(str);
                        if(!MyLibrary.isValidNumber(str)){
                            err = new Error("Invalid Numaric Constant in Line Number "+sourceLineNo, Error.ASSEMBLE_TIME_ERROR);
                            return err;
                        }
                        int numBytes;
                        try{
			numBytes=MyLibrary.fromStringToInt(str);
                    }catch(Exception e){
                                        err = new Error("Invalid constant at line "+sourceLineNo,Error.ASSEMBLE_TIME_ERROR);
                                        return err;
                                    }
                    if (numBytes > (Memory.currentStackAddress - Memory.currentDynamicDataAddress)) {
                            err = new Error("Segmentation Fault ! Unable To Allocate "+numBytes+" Bytes !" , Error.ASSEMBLE_TIME_ERROR);
                            return err ;
                        }
			int numOfWords=(numBytes/4)+(numBytes%4==0?0:1);
			for(int i=0;i<numOfWords;i++){
				Mem.addDataInDynamicMemory(0);
			}
		}else if(str.startsWith(".word")){
			str=str.substring(5);
			str=MyLibrary.removeBeginningAndEndingSpace(str);
			if(str.indexOf(':')!=-1){ // value : separated
                            //System.out.println("first");
				String A[]=str.split("[ ]*:[ ]*");
				if(A.length<2){
                                    err = new Error("Syntax Error @ line " +sourceLineNo, Error.ASSEMBLE_TIME_ERROR);
                                    return err ;
                                }
                                if(!MyLibrary.isValidNumber(A[0])){
                                    err = new Error("Invalid Numaric Constant in Line Number "+sourceLineNo , Error.ASSEMBLE_TIME_ERROR);
                                    return err;
                                }
                                int value, noOfWords;
                                try{
				value=MyLibrary.fromStringToInt(A[0]);
				noOfWords=MyLibrary.fromStringToInt(A[1]);
                            }catch(Exception e){
                                        err = new Error("Invalid constant at line "+sourceLineNo,Error.ASSEMBLE_TIME_ERROR);
                                        return err;
                                    }
                                if(noOfWords*4 > (Memory.currentStackAddress - Memory.currentDynamicDataAddress)){
                                    err = new Error("Segmentation Fault ! Unable To Allocate "+noOfWords+" Words !" , Error.ASSEMBLE_TIME_ERROR);
                                    return err ;
                                }

				for(int i=0;i<noOfWords;i++){
					Mem.addDataInDynamicMemory(value);
				}

			}else if(str.indexOf(',')!=-1){ // values are comma separated
                           // System.out.println("second");
				String A[]=str.split("[ ]*,[ ]*");
                                int noOfWords = A.length;
                                //System.out.println(noOfWords);
                                if(noOfWords*4 > (Memory.currentStackAddress - Memory.currentDynamicDataAddress)){
                                    err = new Error("Segmentation Fault ! Unable To Allocate "+noOfWords+" Words !" , Error.ASSEMBLE_TIME_ERROR);
                                    return err ;
                                }
				for(int i=0;i<A.length;i++){
                                    //System.out.println("Ting: "+A[i]);
                                        if(!MyLibrary.isValidNumber(A[i]) ){
                                            err = new Error("Invalid Numaric Constant in Line Number "+sourceLineNo , Error.ASSEMBLE_TIME_ERROR);
					return err;
                                        }
                                        try{
					Mem.addDataInDynamicMemory(MyLibrary.fromStringToInt(A[i]));
                                    }catch(Exception e){
                                        err = new Error("Invalid constant at line "+sourceLineNo,Error.ASSEMBLE_TIME_ERROR);
                                        return err;
                                    }
                                        }
			}else { // only one value
                                 if(!MyLibrary.isValidNumber(str) && str!=""){
                                    err = new Error("Invalid Numaric Constant in Line Number "+sourceLineNo, Error.ASSEMBLE_TIME_ERROR);
                                    return err;
                                }
                                 //System.out.println(MyLibrary.fromStringToInt(str));
                                if(str!=""){
                                    //System.out.println("STring is :"+str);
                                    try{
                                    int res = MyLibrary.fromStringToInt(str);
                                    Mem.addDataInDynamicMemory(res);
                                    }
                                    catch(Exception e){
                                        err = new Error("Invalid constant at line "+sourceLineNo,Error.ASSEMBLE_TIME_ERROR);
                                        return err;
                                    }
                                }
			//printDataAndTextSections();
			}
                        return err;
		}
                return err;
	}
	/**********************************************************
	 * DATA SECTION PROCESSING ENDS HERE
	 *********************************************************/
	
	
	
	/*************************************************************
	 * CODE FOR PROCESSING TEXT SECTION , PROCESSING TEXT SECTION
	 * INCLUDES PARSING EACH AND EVERY LINE OF SOURCE CODE AND 
	 * STORING CORRESPONDING Instruction OBJECT INTO AllInstruction 
	 *************************************************************/
	private Error processTextSection(){
                Error err = new Error();
		int startAddress=Instruction.startAddressOfInst;
		int currentAddr=startAddress;
                //System.out.println("in text section");
                //printDataAndTextSections();
                //System.out.println(sourceOfTextSection.size());
                for(int i=0;i<sourceOfTextSection.size();i++){
                    //System.out.println(sourceOfTextSection.size());
			String str=(String) sourceOfTextSection.get(i);
                         str=MyLibrary.removeBeginningAndEndingSpace(str);
                         //System.out.println("String: "+str);
                        if(str.equals("")){
                            //System.out.println("empty");
                            continue;
                        }
			if(doesStartWithLabel(str)){
                            //System.out.println("up here" +sourceOfTextSection.size());

				String labelName=getlabelName(str);
				Instruction.addressOfLabels.put(labelName, currentAddr);
				str=str.substring(labelName.length()+1);
				str=MyLibrary.removeBeginningAndEndingSpace(str);
                                if(!str.equals("")){
                                    Instruction instr = new Instruction();
                                //System.out.println(str + " "+TextSectionLineNo.get(i));
				err = instr.parsInstruction(str, TextSectionLineNo.get(i));
                                AllInstructions.add(instr);
				currentAddr+=4;
                                }
			}
                        else {
                            // System.out.println("in here" +sourceOfTextSection.size());
				
				Instruction instr = new Instruction();
				err = instr.parsInstruction(str, TextSectionLineNo.get(i));
                                AllInstructions.add(instr);
				currentAddr+=4;
			}
                         if(!err.isOk())
                             return err;
		}
		//printDataAndTextSections();
                //System.out.println("here");
		err = FillAlllabelNamesWithTheirAddress();
                return err;
	}

	private Error FillAlllabelNamesWithTheirAddress() {
            //System.out.println(AllInstructions.size());
            Error err = new Error();
		for(int i=0;i<AllInstructions.size();i++){
			Instruction inst=AllInstructions.get(i);
			err = inst.fillLabelNameWithAddress();
                        if(!err.isOk())
                            return err;
			AllInstructions.set(i, inst);
		}
            return err;
	}
	
	private void printAllInstruction(){
		for(int i=0;i<AllInstructions.size();i++){
			Instruction instr=AllInstructions.get(i);
			instr.printInstruction();
		}
	}
}