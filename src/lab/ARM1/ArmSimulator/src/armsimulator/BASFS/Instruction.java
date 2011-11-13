package armsimulator.BASFS;
import java.util.HashMap;

import armsimulator.BASFS.*;


public class Instruction{
	private static int preIndex=1 , postIndex=2 , preIndexAutoIndexing=3 , None=4;
	
	private String mnemonic;
	private String cond;
	boolean S;
	
	private int []operands;
	private boolean []isImmediate;
	private int []sign;
	private int numOperand;
	
	private String labelName; // if any instruction has a label 
	
	private String Shift; // type of shift operation used i.e. LSL , LSR .... 
	private String sourceString; // the string which was used to get this instruction
	/*for load store instruction only*/
	private int Index ; // preIndex , postIndex , preIndexAutoIndexing
	
	public static int startAddressOfInst=0x4000000;
	public static HashMap<String,Integer> addressOfLabels=new HashMap<String, Integer>();
	
	private boolean shifter_carry_out;
	
	public Instruction(){
		cond="";
		S=false;
		shifter_carry_out=true;
		
		Index=0;
		Shift="";
		labelName="";
		numOperand=0;
		
		operands=new int [4];
		isImmediate=new boolean [4];
		sign=new int [4];
		for(int i=0;i<4;i++){
			isImmediate[i]=false;
			sign[i]=1;
		}
		
	}
	
	public Error parsInstruction(String str, int lineno){
                Error err = new Error();
		sourceString = str;
                //System.out.println("before parse the string is "+str);
                str=parseMnemonic(err, str);
		//System.out.println(mnemonic);
		if(mnemonic.equals("ldr") || mnemonic.equals("str")){
			err = ParseLoadStoreInstruction(str, lineno);
		}else if(mnemonic.equals("bl") || mnemonic.equals("b")){
			err = ParseBranchInstruction(str, lineno);
		}else{
                    //System.out.println("basicinstruction");
			err = ParseBasicInstruction( str, lineno);
		}
                //System.out.println("My label: " +labelName);
		return err;
	}

      public String getSourceString() {
        return sourceString;
      }
	
	private Error ParseBranchInstruction(String str, int lineno) {
                Error err = new Error();
		str=MyLibrary.removeBeginningAndEndingSpace(str);
		// str will be a label
                if(str==""){
                    err = new Error("Argument missing in branch instruction at line "+lineno, Error.ASSEMBLE_TIME_ERROR);
                }
		labelName=str;
                return err;
	}

	private Error ParseLoadStoreInstruction(String str, int lineno) {
		Error err = new Error();
                int ind=str.indexOf(',');
		if(ind!=-1){ // Extracting Rd
			String Rd=str.substring(0,ind);
			Rd=MyLibrary.removeBeginningAndEndingSpace(Rd);
			operands[numOperand++]=Register.getRegisterNumberFromString(Rd);
			
			str=str.substring(ind+1);
			str=MyLibrary.removeBeginningAndEndingSpace(str);
		}else {
			err = new Error("Invalid load instruction at lineno "+lineno, Error.ASSEMBLE_TIME_ERROR);
			return err;
		}
		
		if(str.indexOf('[')==-1){ // current LDR/STR instruction contains a label
                    //System.out.println("label is :" +str);
                    if(str.startsWith("="))
                        str = str.substring(1);
                    if(Memory.localSymbolTable.get(str)==null){
                        err = new Error("Invalid memory address at lineno "+lineno, Error.ASSEMBLE_TIME_ERROR);
                        return err;
                    }
                        //System.out.println("NULL\n");
			operands[numOperand]=Memory.localSymbolTable.get(str);
                        //System.out.println(operands[numOperand]);
			isImmediate[numOperand++]=true;
			Index=None;
			return err;
		}else{
			if(str.endsWith("]")){ // preIndex
				Index=preIndex;
				str=str.substring(1,str.length()-1);
				str=MyLibrary.removeBeginningAndEndingSpace(str);
				
				err = parseOperandsLS(str, "preIndex", lineno);
				
			}else if(str.endsWith("]!")){ //preIndex AutoIndexing
				Index=preIndexAutoIndexing;
				str=str.substring(1,str.length()-2);
				str=MyLibrary.removeBeginningAndEndingSpace(str);
				
				err = parseOperandsLS(str, "preAuto", lineno);
				
			}else{ // post Indexing 
				Index=postIndex;
				int indexOfFirstComma=str.indexOf(',');
				String Rn=str.substring(0, indexOfFirstComma); // String will be like -> [<Rn>]
				Rn=MyLibrary.removeBeginningAndEndingSpace(Rn);
				Rn=Rn.substring(1, Rn.length()-1);
				
				err = fillOperand(Rn, lineno);
                                if(!err.isOk())
                                    return err;
				
				str=str.substring(indexOfFirstComma+1);
				
				err = parseOperandsLS(str, "post", lineno);
			}
		}
                return err;
	}
	private Error parseOperandsLS(String str, String index, int lineno) {
		Error err = new Error();
                String A[]=str.split(",");
                if(index == "post"){
                    if(A.length!=1){
                        err = new Error("Too many arguments in load/store instruction at lineno " +lineno , Error.ASSEMBLE_TIME_ERROR);
                        return err;
                    }
                }
                if(index == "preindex" || index == "preAuto")
                    if(A.length>2){
                        err = new Error("Too many arguments in load/store instruction at lineno "+lineno, Error.ASSEMBLE_TIME_ERROR);
                        return err;
                    }
		for(int i=0;i<A.length;i++){
			String temp=A[i];
			temp=MyLibrary.removeBeginningAndEndingSpace(temp);
			if(i==A.length-1 && isShiftOperation(temp)){ // it can be a shift instruction
                            err = new Error("Shift Operations not permitted in load/store. Line no: "+lineno, Error.ASSEMBLE_TIME_ERROR);
			//fillShiftOperationInfo(temp);
			}else{
				err = fillOperand(temp, 0);
			}
		}
                return err;
	}
        private boolean isRegister(String str){
            String temp = str;
            if(temp.startsWith("+") || temp.startsWith("-"))
                temp = temp.substring(1);
            int x = Register.getRegisterNumberFromString(temp);
            if(x==-1){
                return false;
            }
            return true;
        }
	private Error parseOperands(String str, int lineno, int noOfArgs) {
		Error err = new Error();
                String A[]=str.split(",");
                if(A.length<noOfArgs){
                    err = new Error("Too few arguments at line no: "+lineno, Error.ASSEMBLE_TIME_ERROR);
                 return err;
                

                }
                if(A.length>(noOfArgs+1)){
                 err = new Error("Too many arguments at line no: "+lineno, Error.ASSEMBLE_TIME_ERROR);
                 return err;
                }
                if(A.length==noOfArgs+1){
                    if(!isShiftOperation(A[A.length-1])){
                        err = new Error("Too many arguments at line no: "+lineno, Error.ASSEMBLE_TIME_ERROR);
                     return err;

                    }
                }
                boolean shiftop = false, gonein = false;
                if(A.length == 4 && noOfArgs == 3){
                    //System.out.println("mint");
                    shiftop = true;
                    gonein = false;
            }
		for(int i=0;i<A.length;i++){
			String temp=A[i];
			temp=MyLibrary.removeBeginningAndEndingSpace(temp);
                        if(i<(noOfArgs-1)){
                            if(!isRegister(temp)){
                                err = new Error("Operand "+i+" should be a register. Line no "+lineno, Error.ASSEMBLE_TIME_ERROR);
                                return err;
                            }
                        }
			if(i==A.length-1 && isShiftOperation(temp)){ // it can be a shift instruction
				//System.out.println("unmint");
                                err = fillShiftOperationInfo(temp, lineno);
                                gonein = true;
			}else{
				err = fillOperand(temp, lineno);
			}
		}
                
                return err;
	}

	private Error fillShiftOperationInfo(String str, int lineno) {
		Error err = new Error();
                str=MyLibrary.removeBeginningAndEndingSpace(str);
		if(str.startsWith("lsl")){
			Shift="lsl";
		}else if(str.startsWith("lsr")){
			Shift="lsr";
		}else if(str.startsWith("asl")){
			Shift="asl";
		}else if(str.startsWith("asr")){
			Shift="asr";
		}else{
                        err =  new Error("Invalid shift operation at lineno "+ lineno, Error.ASSEMBLE_TIME_ERROR );
			return err;
			// not supported in this version , error
		}
		str=str.substring(3);
		err = fillOperand(str, lineno);
                return err;
	}

	private boolean isShiftOperation(String str) {
		return str.contains("lsl") || str.contains("lsr") || str.contains("asl") || str.contains("asr")
		       || str.contains("ror") || str.contains("rrx") ; 
	}

	/*************************************************************
	 * str may be in one of these form ->
	 * 1. Rn
	 * 2. +/-Rn
	 * 3. #+/-immediateValue
	 * 
	 * This function fills operands[] , sign[] , and isImmediate[] 
	 * and increments numOperand by one 
	 *************************************************************/
	
	private Error fillOperand(String str, int lineno) {
		str=MyLibrary.removeBeginningAndEndingSpace(str);
                Error err = new Error();
		if(str.equals("")){
                    err = new Error("Invalid operand. Line no "+lineno, Error.ASSEMBLE_TIME_ERROR);
			return err;
			// error
		}
		
		sign[numOperand]=1;
		if(isImmediateValue(str)){ 
			isImmediate[numOperand]=true;
			
			str=str.substring(1); // immediate values start with '#'
			str=MyLibrary.removeBeginningAndEndingSpace(str);
			if(str.startsWith("+")){
				str=str.substring(1);
				str=MyLibrary.removeBeginningAndEndingSpace(str);
			}else if(str.startsWith("-")){
				sign[numOperand]=-1;
				str=str.substring(1);
				str=MyLibrary.removeBeginningAndEndingSpace(str);
			}
                        try{
			operands[numOperand++]=MyLibrary.fromStringToInt(str);
                        }
                        catch (Exception e){
                            err = new Error("Invalid immediate constant at line no "+lineno, Error.ASSEMBLE_TIME_ERROR);
                            return err;
                        }
		
		
		}else{
			if(str.startsWith("+")){
				str=str.substring(1);
				str=MyLibrary.removeBeginningAndEndingSpace(str);
			}else if(str.startsWith("-")){
				sign[numOperand]=-1;
				str=str.substring(1);
				str=MyLibrary.removeBeginningAndEndingSpace(str);
			}
			int x =Register.getRegisterNumberFromString(str);
                        if(x==-1){
                            err = new Error("Invalid Operand! Line no " + lineno, Error.ASSEMBLE_TIME_ERROR);
                            return err;
                        }
                        operands[numOperand++] = x;
		}
                return err;
	}

	private boolean isImmediateValue(String str) {
		return str.startsWith("#");
	}

        boolean istwoarg(String str){
            str = str.toLowerCase();
            if(str.equals("mov") || str.equals("clz") || str.equals("tst") || str.equals("teq") || str.equals("cmp") ||str.equals("cmn") ){
                return true;
            }
            return false;
        }
	private Error ParseBasicInstruction( String str, int lineno) {
             Error err = new Error();
             String A[]=str.split(",");
             int noOfargs = 3;
             if(istwoarg(mnemonic)){
                noOfargs = 2;
             }
             if(mnemonic.equals("mla"))
                 noOfargs =4;
             //System.out.println(mnemonic);
             err = parseOperands(str, lineno, noOfargs);
             return err;
	}

	/*********************************************
	 * @param str   Full instruction as a string 
	 * extracts menmonic<cond><s> from the string 
	 * and returns the remaining string 
	 *********************************************/
	
	String parseMnemonic(Error err, String str){
		
//		System.out.println("Inside ParseMnemonic , String is -> "+str);
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<str.length();i++){
			if(Character.isSpace(str.charAt(i))){
				break;
			}
			sb.append(str.charAt(i));
		}
		
		String temp=sb.toString();
		
		str=str.substring(temp.length());
		str=MyLibrary.removeBeginningAndEndingSpace(str);
		
		if(temp.startsWith("adc")){
			mnemonic="adc";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("add")){
                    //System.out.println("Okk");
			mnemonic="add";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("and")){
			mnemonic="and";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("bic")){
			mnemonic="bic";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("clz")){
			mnemonic="clz";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("cmn")){
			mnemonic="cmn";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("cmp")){
			mnemonic="cmp";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("eor")){
			mnemonic="eor";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("ldr")){
			mnemonic="ldr";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("mla")){
			mnemonic="mla";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("mov")){
			mnemonic="mov";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("mul")){
			mnemonic="mul";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("mvn")){
			mnemonic="mvn";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("orr")){
			mnemonic="orr";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("rsb")){
			mnemonic="rsb";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("rsc")){
			mnemonic="rsc";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("sbc")){
			mnemonic="sbc";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("str")){
			mnemonic="str";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("sub")){
			mnemonic="sub";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("swp")){
			mnemonic="swp";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("teq")){
			mnemonic="teq";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("tst")){
			mnemonic="tst";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("bl")){
                        if(temp.startsWith("ble"))
                            mnemonic = "b";
                        else
                            mnemonic="bl";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else if(temp.startsWith("b")){
			mnemonic="b";
			temp=temp.substring(mnemonic.length());
			setCondAndS(temp);
		}else {
                        err = new Error("instruction not supported in this version", Error.ASSEMBLE_TIME_ERROR);
		}
//		System.out.println("Mnemonic ->"+ mnemonic+"<- cond ->"+cond+"<- S ->"+S+"<-\n");
		
		return str;
	}
	
	private void setCondAndS(String str){
		if(str.equals("")){
			return ;
		}
		
		if(str.equals("eq")){
			cond="eq";
			str=str.substring(2);
		}else if(str.equals("ne")){
			cond="ne";
			str=str.substring(2);
		}else if(str.equals("cs")){
			cond="cs";
			str=str.substring(2);
		}else if(str.equals("hs")){
			cond="hs";
			str=str.substring(2);
		}else if(str.equals("cc")){
			cond="cc";
			str=str.substring(2);
		}else if(str.equals("lo")){
			cond="lo";
			str=str.substring(2);
		}else if(str.equals("mi")){
			cond="mi";
			str=str.substring(2);
		}else if(str.equals("pl")){
			cond="pl";
			str=str.substring(2);
		}else if(str.equals("vs")){
			cond="vs";
			str=str.substring(2);
		}else if(str.equals("vc")){
			cond="vc";
			str=str.substring(2);
		}else if(str.equals("hi")){
			cond="hi";
			str=str.substring(2);
		}else if(str.equals("ls")){
			cond="ls";
			str=str.substring(2);
		}else if(str.equals("ge")){
			cond="ge";
			str=str.substring(2);
		}else if(str.equals("lt")){
			cond="lt";
			str=str.substring(2);
		}else if(str.equals("gt")){
			cond="gt";
			str=str.substring(2);
		}else if(str.equals("le")){
			cond="le";
			str=str.substring(2);
		}else if(str.equals("al")){
			cond="al";
			str=str.substring(2);
		}
		
		if(str.equals("s")){
			S=true;
		}
	}
	
	public Error fillLabelNameWithAddress() {
            Error err = new Error();
            //System.out.println("Label :"+labelName);
		if(labelName.equals("")){
                    //err = new Error("Empty Label", Error.ASSEMBLE_TIME_ERROR);
			return err;
		}
                isImmediate[numOperand]=true;
                if(addressOfLabels.get(labelName)==null)
                    err = new Error("Invalid Label -> "+labelName, Error.ASSEMBLE_TIME_ERROR);
                else
                    operands[numOperand++]=addressOfLabels.get(labelName);
                  return err;
	}
	
	
	public void printInstruction(){
		System.out.println("\nmnemonic: "+mnemonic+"<"+cond+">"+"<"+(S?"s":"")+">");
		for(int i=0;i<numOperand;i++){
			System.out.println("operand["+i+"]="+operands[i]+"  sign["+i+"]="+sign[i]+" isImmediate["+i+"]="+isImmediate[i]);
		}
		System.out.println("label : "+labelName);
		System.out.println("Shift : "+Shift);
		System.out.println("Index : "+Index);
		System.out.println("");
	}

	private int getShifterOperand(int ind){
		int ret=getOperand(ind);
		
		shifter_carry_out=Register.isCSet();
		
		if(!Shift.equals("") && ind<numOperand-1){
			int sft=getOperand(ind+1);
			if(Shift.equals("lsl") || Shift.equals("asl")){
				int Rm=ret;
				ret=ret<<sft;
				
				if (isImmediate[ind + 1]) {
					if(sft==0){
						shifter_carry_out=Register.isCSet();
					}else{
						shifter_carry_out=Register.isSet(Rm, 32-sft);
					}
					
				} else {

					if (sft == 0) {
						shifter_carry_out = Register.isCSet();
					} else if (sft < 32) {
						shifter_carry_out = Register.isSet(Rm, 32 - sft);
					} else if (sft == 32) {
						shifter_carry_out = Register.isSet(Rm, 0);
					} else { // sft>32
						shifter_carry_out = false;
					}

				}

			}else if(Shift.equals("lsr")){
				// must zero-fill, so use ">>>" instead of ">>".
				int Rm=ret;
				ret=ret>>>sft;
				
				if (isImmediate[ind + 1]) {
					if (sft == 0) {
						shifter_carry_out = Register.isSet(Rm, 31);
					} else {
						shifter_carry_out = Register.isSet(Rm, sft - 1);
					}
				} else {
					if (sft == 0) {
						shifter_carry_out = Register.isCSet();
					} else if (sft < 32) {
						shifter_carry_out = Register.isSet(Rm, sft-1);
					} else if (sft == 32) {
						shifter_carry_out = Register.isSet(Rm, 31);
					} else { // sft>32
						shifter_carry_out = false;
					}
				}
				
			}else if(Shift.equals("asr")){
				int Rm=ret;
				ret=ret>>sft;
				
				if(isImmediate[ind+1]){
					if(sft==0){
						shifter_carry_out=Register.isSet(Rm, 31);
						
					}else{
						shifter_carry_out=Register.isSet(Rm, sft-1 );
					}
					
				}else{
					if(sft==0){
						shifter_carry_out = Register.isCSet();
					}else if(sft<32){
						shifter_carry_out = Register.isSet(Rm, sft-1);
					}else{ 
						shifter_carry_out = Register.isSet(Rm, 31);
					}
				}
			}
		}
		return ret;
	}
	
	private int getOperand(int ind) {
		int ret=isImmediate[ind]?operands[ind]:Register.getReg(operands[ind]);
		return ret*sign[ind];
	}
        static int count_leading_zeros(int src)
        {
        for (int i = 31; i >= 0; i--) {
            if ((src & ((int)1 << i)) != 0) {
               return 31-i;
            }
        }
         return 32;
        }
        boolean conditioncheck(){
            if(cond.equals(("")))
                return true;
            else{
                if(Register.ConditionPassed(cond))
                    return true;
                else
                    return false;
            }
        }
	public void runSingleInstruction() {
		if(!conditioncheck()){
                    Register.incrementPCToNextInstruction();
                    return ;
                }
		/*if(!cond.equals("")){
			if(!Register.ConditionPassed(cond)){
				Register.incrementPCToNextInstruction();
				return ;
			}
		}*/
		
		if(mnemonic.equals("adc")){
			int add1 = getOperand(1) + (Register.isCSet()?1:0);
			int add2 = getShifterOperand(2);
			int sum = add1 + add2;
			Register.updateRegister(operands[0], sum);
			if (S) {
				// overflow on A+B detected when A and B have same sign and A+B
				// has other sign.
				updateNFlag(sum);
				updateZFlag(sum);
				if ((add1 >= 0 && add2 >= 0 && sum < 0)
						|| (add1 < 0 && add2 < 0 && sum >= 0)) {
					Register.setC();
					Register.setV();
				} else {
					Register.clearC();
					Register.clearV();
				}
			}

			Register.incrementPCToNextInstruction();

			
			
			
		}else if(mnemonic.equals("add")){
			int add1 = getOperand(1) ;
			int add2 = getShifterOperand(2);
			int sum = add1 + add2;
			Register.updateRegister(operands[0], sum);
			if (S) {
				// overflow on A+B detected when A and B have same sign and A+B
				// has other sign.
				updateNFlag(sum);
				updateZFlag(sum);
				if ((add1 >= 0 && add2 >= 0 && sum < 0)
						|| (add1 < 0 && add2 < 0 && sum >= 0)) {
					Register.setC();
					Register.setV();
				} else {
					Register.clearC();
					Register.clearV();
				}
			}

			Register.incrementPCToNextInstruction();

			
			
		}else if(mnemonic.equals("and")){
			int o1 = getOperand(1) ;
			int o2 = getShifterOperand(2);
			int res = o1 & o2;
			Register.updateRegister(operands[0], res);
			if(S){
                                updateNFlag(res);
				updateZFlag(res);
			}
			
			Register.incrementPCToNextInstruction();
			
		}else if(mnemonic.equals("bic")){ // x
                    int o1 = getOperand(1) ;
			int o2 = getShifterOperand(2);
			int res = o1 & (~o2);
			Register.updateRegister(operands[0], res);
			if(S){
                                updateNFlag(res);
				updateZFlag(res);
			}

			Register.incrementPCToNextInstruction();
			
		}else if(mnemonic.equals("clz")){
                    int o1 = getOperand(1);
                    int res = count_leading_zeros(o1);
                    Register.updateRegister(operands[0], res);
                    Register.incrementPCToNextInstruction();
		}else if(mnemonic.equals("cmn")){
			int add1 = getOperand(0);
			int add2 = getShifterOperand(1);
			int alu_out = add1 + add2;

			// overflow on A+B detected when A and B have same sign and A+B
			// has other sign.
			updateNFlag(alu_out);
			updateZFlag(alu_out);
			if ((add1 >= 0 && add2 >= 0 && alu_out < 0)
					|| (add1 < 0 && add2 < 0 && alu_out >= 0)) {
				Register.setC();
				Register.setV();
			} else {
				Register.clearC();
				Register.clearV();
			}

			Register.incrementPCToNextInstruction();

			
			
		}else if(mnemonic.equals("cmp")){
			int o1 = getOperand(0);
			int o2 = getShifterOperand(1);
			
		//	System.out.println("Comparing "+o1+" and "+o2);
			
			int alu_out=o1-o2;
			updateNFlag(alu_out);
			updateZFlag(alu_out);
			
			boolean cflag=!BorrowFrom(o1, o2);
			if(cflag){
				Register.setC();
			}else{
				Register.clearC();
			}
			
			Register.incrementPCToNextInstruction();
			
			
			
		}else if(mnemonic.equals("eor")){ // x
			int o1 = getOperand(1) ;
			int o2 = getShifterOperand(2);
			int res = o1 ^ o2;
			Register.updateRegister(operands[0], res);
			if(S){
                                updateNFlag(res);
				updateZFlag(res);
			}

			Register.incrementPCToNextInstruction();

			
			
			
		}else if(mnemonic.equals("ldr")){
//			System.out.println("Inside ldr :) Index = "+Index);
			
			if(Index==preIndex){
				int addr=getOperand(1);
				if(numOperand>2){
					addr=addr+getShifterOperand(2);
				}
				Register.updateRegister(operands[0], Memory.readDynamicMemory(addr));
				
				
			}else if(Index==preIndexAutoIndexing){
				int addr=getOperand(1);
				if(numOperand>2){
					addr=addr+getShifterOperand(2);
				}
				Register.updateRegister(operands[0], Memory.readDynamicMemory(addr));
				
				Register.updateRegister(operands[1], addr);
				
			}else if(Index==postIndex){
				Register.updateRegister(operands[0], Memory.readDynamicMemory( getOperand(1)));
				if(numOperand>2){
					Register.updateRegister(operands[1], getOperand(1)+getShifterOperand(2));
				}
				
			}else{ // no Index , label was used 
			//	Register.updateRegister(operands[0], Memory.readDynamicMemory(getOperand(1)));
				Register.updateRegister(operands[0], getOperand(1));
			}
			
			
			Register.incrementPCToNextInstruction();
			
		}else if(mnemonic.equals("mov")){  // TO DO
			Register.updateRegister(operands[0], getShifterOperand(1));
			
			if(S){
				int value=Register.getReg(operands[0]);
				updateNFlag(value);
				updateZFlag(value);
				updateCFlag(shifter_carry_out);
				// V unaffected
			}
			
			Register.incrementPCToNextInstruction();
			
			
		}else if(mnemonic.equals("mul")){  // TO DO 
			int op1=getOperand(1);
			int op2=getShifterOperand(2);
			
			long product = op1 * op2;
			int Rd_value= (int) ((product << 32) >> 32);
			
			Register.updateRegister(operands[0], Rd_value);
			
			if(S){
				updateNFlag(Rd_value);
				updateZFlag(Rd_value);
				// C and V flags are unaffected 
			}
                        Register.incrementPCToNextInstruction();
			
		}else if(mnemonic.equals("mla")){  // x
                    int op1=getOperand(1);
                    int op2=getOperand(2);
                    int op3 = getShifterOperand(3);
                    int res = op1*op2+op3;
                    Register.updateRegister(operands[0],res);

		}else if(mnemonic.equals("mvn")){  // x
			
		}else if(mnemonic.equals("orr")){
                    int o1 = getOperand(1) ;
			int o2 = getShifterOperand(2);
			int res = o1 | o2;
			Register.updateRegister(operands[0], res);
			if(S){
                                updateNFlag(res);
				updateZFlag(res);
			}

			Register.incrementPCToNextInstruction();
			
		}else if(mnemonic.equals("rsb")){
                    int sub1=getOperand(1);
			int sub2=getShifterOperand(2);
                        int sub=sub2-sub1;
                        Register.updateRegister(operands[0], sub);
			if (S) {
				updateNFlag(sub);
				updateZFlag(sub);
				boolean cflag = !BorrowFrom(sub2, sub1);
				updateCFlag(cflag);

				boolean vflag = OverFlowFromSub(sub2, sub1);
				updateVFlag(vflag);
			}

			Register.incrementPCToNextInstruction();
			
		}else if(mnemonic.equals("rsc")){
                    int sub1 = getOperand(1);
			int sub2 = getShifterOperand(2)  - (Register.isCSet()?0:1);
			int sub = sub2 - sub1;
			Register.updateRegister(operands[0], sub);
			if (S) {
				updateNFlag(sub);
				updateZFlag(sub);
				boolean cflag = !BorrowFrom(sub2, sub1);
				updateCFlag(cflag);

				boolean vflag = OverFlowFromSub(sub2, sub1);
				updateVFlag(vflag);
			}


			Register.incrementPCToNextInstruction();



			
		}else if(mnemonic.equals("sbc")){
                    int sub1 = getOperand(1) - (Register.isCSet()?0:1);
			int sub2 = getShifterOperand(2);
			int sub = sub1 - sub2;
			Register.updateRegister(operands[0], sub);
			if (S) {
				updateNFlag(sub);
				updateZFlag(sub);
				boolean cflag = !BorrowFrom(sub1, sub2);
				updateCFlag(cflag);

				boolean vflag = OverFlowFromSub(sub1, sub2);
				updateVFlag(vflag);
			}


			Register.incrementPCToNextInstruction();


		
		}else if(mnemonic.equals("str")){  
			if(Index==preIndex){
				int addr=getOperand(1);
				if(numOperand>2){
					addr=addr+getShifterOperand(2);
				}
				Memory.updateDynamicMemory(addr, getOperand(0));
			
				
				
			}else if(Index==preIndexAutoIndexing){
				int addr=getOperand(1);
				if(numOperand>2){
					addr=addr+getShifterOperand(2);
				}
				Memory.updateDynamicMemory(addr, getOperand(0));
			
				
				Register.updateRegister(operands[1], addr);
				
			}else if(Index==postIndex){
				Memory.updateDynamicMemory(getOperand(1), getOperand(0));
			
				if(numOperand>2){
					Register.updateRegister(operands[1], getOperand(1)+getShifterOperand(2));
				}
				
			}else{ // no Index , label was used 
				Memory.updateDynamicMemory(getOperand(1), getOperand(0));
			
			}
			
			
			Register.incrementPCToNextInstruction();
			
			
			
		}else if(mnemonic.equals("sub")){ 
			//System.out.println("YESSSSS!!!");
			int sub1=getOperand(1);
			int sub2=getShifterOperand(2);
                        int sub=sub1-sub2;
                        Register.updateRegister(operands[0], sub);
			if (S) {
				updateNFlag(sub);
				updateZFlag(sub);
				boolean cflag = !BorrowFrom(sub1, sub2);
				updateCFlag(cflag);

				boolean vflag = OverFlowFromSub(sub1, sub2);
				updateVFlag(vflag);
			}
			
			Register.incrementPCToNextInstruction();
			
		}else if(mnemonic.equals("swp")){  // x
			
		}else if(mnemonic.equals("teq")){
                    int o1 = getOperand(0) ;
			int o2 = getShifterOperand(1);
			int res = o1 ^ o2;
                                updateNFlag(res);
				updateZFlag(res);

			Register.incrementPCToNextInstruction();

			
		}else if(mnemonic.equals("tst")){
                        int o1 = getOperand(0) ;
			int o2 = getShifterOperand(1);
			int res = o1 & o2;
                                updateNFlag(res);
				updateZFlag(res);

			Register.incrementPCToNextInstruction();

			
		}else if(mnemonic.equals("bl")){
			Register.updateRegister("lr", Register.getPC()+4);
			Register.updatePCAbsolute(getOperand(0));
			
			
			
		}else if(mnemonic.equals("b")){
			Register.updatePCAbsolute(getOperand(0));
	
			
		}else {
                   // instruction not supported in this version 
		}
	}

	private void updateCFlag(boolean cflag) {
		if(cflag){
			Register.setC();
		}else{
			Register.clearC();
		}
	}

	private void updateVFlag(boolean vflag) {
		if(vflag){
			Register.setV();
		}else{
			Register.clearV();
		}
	}

	private boolean BorrowFrom(int a , int b){
		long A = a & 0xffffffffL ; 
		long B = b & 0xffffffffL ;
		return A<B ;
	}
	
	private boolean OverFlowFromSub(int sub1, int sub2){
		
		int dif = sub1 - sub2;
        // overflow on A-B detected when A and B have opposite signs and A-B has B's sign
           if ((sub1 >= 0 && sub2 < 0 && dif < 0)
              || (sub1 < 0 && sub2 >= 0 && dif >= 0))
           {
        	   return true;
           }else{
           		return false;
           }
	}
	
	private void updateZFlag(int val) {
		if(val==0){
			Register.setZ();
		}else{
			Register.clearZ();
		}
	}

	private void updateNFlag(int val) {
		if(val<0){
			Register.setN();
		}else{
			Register.clearN();
		}
	}
	
}