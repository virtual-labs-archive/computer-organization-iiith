package mipsparser_new;

import java.io.IOException;
import java.util.ArrayList;
import mipsparser_new.BMSFS.Error;
import mipsparser_new.BMSFS.Instruction;
import mipsparser_new.BMSFS.Memory;
import mipsparser_new.BMSFS.MyLibrary;
import mipsparser_new.BMSFS.Register;

public class MIPSProgram {
   private String filename;
   private ArrayList sourceList;
   private ArrayList commentRemovedSource;
   private ArrayList sourceOfDataSection;
   public ArrayList sourceOfTextSection;
   private ArrayList DataSectionLineNo;
   private ArrayList TextSectionLineNo;
   private Register Reg = new Register();
   private Memory Mem = new Memory();
   private ArrayList AllInstructions = new ArrayList();
   private ArrayList CodeSection = new ArrayList();
   private Error assembleTimeError = new Error();

   public MIPSProgram(String source) throws IOException {
      this.readSource(source);
      this.removeComment();
      this.splitDataAndTextSection();
      Error err = this.processDataSection();
      if(!err.isOk()) {
         err.printErrorMsg();
         this.assembleTimeError = err;
      } else {
         err = this.processTextSection();
         if(!err.isOk()) {
            err.printErrorMsg();
            this.assembleTimeError = err;
         }
      }
   }

   public Error getAssembleTimeError() {
      return this.assembleTimeError;
   }

   public ArrayList getsourceOfTextSection() {
      ArrayList sourceLinesToDisplay = new ArrayList();

      for(int i = 0; i < this.AllInstructions.size(); ++i) {
         sourceLinesToDisplay.add(((Instruction)this.AllInstructions.get(i)).getSourceString());
      }

      return sourceLinesToDisplay;
   }

   public Error runCurrentInstruction() {
      Error err = new Error();
      Register var10000 = this.Reg;
      int currentPC = Register.getPC();
      int startPC = Instruction.startAddressOfInst;
      int index = (currentPC - startPC) / 4;
      if(index < this.AllInstructions.size()) {
         Instruction instr = (Instruction)this.AllInstructions.get(index);
         err = instr.runSingleInstruction();
      }

      return err;
   }

   private void RunAllInstructions() throws IOException {
      int PClimit = Register.getPC() + this.AllInstructions.size() * 4;
      int startPC = Instruction.startAddressOfInst;

      while(true) {
         Register var10000 = this.Reg;
         int currentPC = Register.getPC();
         if(currentPC >= PClimit) {
            break;
         }

         int index = (currentPC - startPC) / 4;
         Instruction instr = (Instruction)this.AllInstructions.get(index);
         Error err = instr.runSingleInstruction();
         if(!err.isOk()) {
            err.printErrorMsg();
            break;
         }
      }

   }

   private void printAllInstructions() {
      for(int i = 0; i < this.AllInstructions.size(); ++i) {
         System.out.println("\nPrinting " + i + "th instruction");
         System.out.println("mnemonic " + ((Instruction)this.AllInstructions.get(i)).getMnemonic());
         Instruction instr = (Instruction)this.AllInstructions.get(i);
         int NumOp = instr.getNumberOfOperands();
         int[] operands = instr.getOperands();
         boolean[] isImmediate = instr.getIsImmediate();
         int[] sign = instr.getSign();

         for(int j = 0; j < NumOp; ++j) {
            System.out.printf("%d %s %d\n", new Object[]{Integer.valueOf(operands[j]), isImmediate[j]?"Immediate":"Register", Integer.valueOf(sign[j])});
         }
      }

   }

   private void Testing() {
      System.out.println("\n\n*************** Testing *********************\n\n");
   }

   private void PrintCommentRemovedSource() {
      System.out.println("\n**********  PRINTING COMMENT REMOVED SOURCE FILE  ******************");

      for(int i = 0; i < this.commentRemovedSource.size(); ++i) {
         System.out.println(this.commentRemovedSource.get(i));
      }

   }

   private void PrintSource() {
      System.out.println("**********  PRINTING SOURCE FILE  ******************");

      for(int i = 0; i < this.sourceList.size(); ++i) {
         System.out.println(this.sourceList.get(i));
      }

   }

   public String getSourceLine(int lineNo) {
      return lineNo >= 1 && lineNo <= this.sourceList.size()?(String)this.sourceList.get(lineNo - 1):null;
   }

   public void readSource(String source) {
      this.sourceList = new ArrayList();
      String[] tempSource = source.split("\n");

      try {
         for(int e = 0; e < tempSource.length; ++e) {
            this.sourceList.add(tempSource[e]);
         }
      } catch (Exception var4) {
         ;
      }

   }

   public void removeComment() {
      this.commentRemovedSource = new ArrayList();

      for(int i = 0; i < this.sourceList.size(); ++i) {
         String s = this.removeCommentFromAString((String)this.sourceList.get(i));
         s = MyLibrary.removeBeginningAndEndingSpace(s);
         if(!s.equals("")) {
            this.commentRemovedSource.add(s);
         }
      }

   }

   private String removeCommentFromAString(String str) {
      int ind = str.indexOf("//");
      if(ind != -1) {
         str = str.substring(0, ind);
      }

      ind = str.indexOf("#");
      if(ind != -1) {
         str = str.substring(0, ind);
      }

      return str;
   }

   public void splitDataAndTextSection() {
      this.sourceOfDataSection = new ArrayList();
      this.sourceOfTextSection = new ArrayList();
      this.DataSectionLineNo = new ArrayList();
      this.TextSectionLineNo = new ArrayList();
      boolean DATA = false;
      boolean TEXT = true;
      boolean flag = DATA;

      for(int i = 0; i < this.commentRemovedSource.size(); ++i) {
         String str = (String)this.commentRemovedSource.get(i);
         str = str.toLowerCase();
         if(str.startsWith(".text")) {
            flag = TEXT;
         } else if(str.startsWith(".data")) {
            flag = DATA;
         } else if(flag == DATA) {
            this.sourceOfDataSection.add(str);
            this.DataSectionLineNo.add(Integer.valueOf(i + 1));
         } else {
            this.sourceOfTextSection.add(str);
            this.TextSectionLineNo.add(Integer.valueOf(i + 1));
         }
      }

   }

   public void printDataAndTextSections() {
      System.out.println("\n\nPRINTING TEXT SECTION\n\n");

      for(int i = 0; i < this.sourceOfTextSection.size(); ++i) {
         System.out.println(this.sourceOfTextSection.get(i));
      }

   }

   private boolean doesStartWithLevel(String str) {
      for(int i = 0; i < str.length(); ++i) {
         if(MyLibrary.isSpaceChar(str.charAt(i))) {
            return false;
         }

         if(str.charAt(i) == 58) {
            return true;
         }
      }

      return false;
   }

   private String getLevelName(String str) {
      int ind = -1;

      for(int i = 0; i < str.length() && !MyLibrary.isSpaceChar(str.charAt(i)); ++i) {
         if(str.charAt(i) == 58) {
            ind = i;
            break;
         }
      }

      return ind == -1?"":str.substring(0, ind);
   }

   private Error processDataSection() {
      Error err = new Error();

      for(int i = 0; i < this.sourceOfDataSection.size(); ++i) {
         String str = (String)this.sourceOfDataSection.get(i);
         if(this.doesStartWithLevel(str)) {
            String levelName = this.getLevelName(str);
            Memory var10000 = this.Mem;
            Memory.setBeginingAddressOfWord(levelName);
            err = this.processDirectives(str.substring(levelName.length() + 1), "\n\t" + str);
         } else {
            err = this.processDirectives(str, "\n\t" + str);
         }

         if(!err.isOk()) {
            break;
         }
      }

      return err;
   }

   private Error processSpaceDirective(String str, String sourceLineNo) {
      Error err = new Error();
      str = MyLibrary.removeBeginningAndEndingSpace(str);
      if(!MyLibrary.isValidNumber(str)) {
         err = new Error("Invalid Numaric Constant in Line : " + sourceLineNo, 1);
         return err;
      } else {
         int numBytes = MyLibrary.fromStringToInt(str);
         if(numBytes > Memory.currentStackAddress - Memory.currentDynamicDataAddress) {
            err = new Error("Segmentation Fault ! Unable To Allocate " + numBytes + " Bytes !", 1);
            return err;
         } else {
            for(int i = 0; i < numBytes; ++i) {
               Memory var10000 = this.Mem;
               Memory.addByteInDynamicMemory(0);
            }

            return err;
         }
      }
   }

   private Error processWordDirective(String str, String sourceLineNo) {
      Error err = new Error();
      str = MyLibrary.removeBeginningAndEndingSpace(str);
      Memory var10000;
      String[] A;
      int noOfWords;
      int i;
      if(str.indexOf(58) != -1) {
         A = str.split("[ ]*:[ ]*");
         if(A.length != 2) {
            err = new Error("Syntax Error @ line " + sourceLineNo, 1);
            return err;
         }

         if(!MyLibrary.isValidNumber(A[0]) || !MyLibrary.isValidNumber(A[0])) {
            err = new Error("Invalid Numaric Constant in Line : " + sourceLineNo, 1);
            return err;
         }

         noOfWords = MyLibrary.fromStringToInt(A[0]);
         i = MyLibrary.fromStringToInt(A[1]);
         if(i * 4 > Memory.currentStackAddress - Memory.currentDynamicDataAddress) {
            err = new Error("Segmentation Fault ! Unable To Allocate " + i + " Words !", 1);
            return err;
         }

         for(int i1 = 0; i1 < i; ++i1) {
            var10000 = this.Mem;
            Memory.addWordInDynamicMemory(noOfWords);
         }
      } else if(str.indexOf(44) != -1) {
         A = str.split("[ ]*,[ ]*");
         noOfWords = A.length;
         if(noOfWords * 4 > Memory.currentStackAddress - Memory.currentDynamicDataAddress) {
            err = new Error("Segmentation Fault ! Unable To Allocate " + noOfWords + " Words !", 1);
            return err;
         }

         for(i = 0; i < A.length; ++i) {
            if(!MyLibrary.isValidNumber(A[i])) {
               err = new Error("Invalid Numaric Constant in Line : " + sourceLineNo, 1);
               return err;
            }

            var10000 = this.Mem;
            Memory.addWordInDynamicMemory(MyLibrary.fromStringToInt(A[i]));
         }
      } else {
         if(!MyLibrary.isValidNumber(str)) {
            err = new Error("Invalid Numaric Constant in Line : " + sourceLineNo, 1);
            return err;
         }

         var10000 = this.Mem;
         Memory.addWordInDynamicMemory(MyLibrary.fromStringToInt(str));
      }

      return err;
   }

   private boolean isValidString(String str) {
      int len = str.length();
      if(len < 2) {
         return false;
      } else if(str.charAt(0) == 34 && str.charAt(len - 1) == 34) {
         for(int i = 1; i < len; ++i) {
            if(str.charAt(i) == 92) {
               if(i == len - 2) {
                  return false;
               }

               ++i;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private Error storeAllChars(String str) {
      Error err = new Error();
      int len = str.length();

      for(int i = 1; i < len - 1; ++i) {
         if(str.charAt(i) == 92) {
            ++i;
            if(str.charAt(i) == 98) {
               Memory.addByteInDynamicMemory(8);
            } else if(str.charAt(i) == 116) {
               Memory.addByteInDynamicMemory(9);
            } else if(str.charAt(i) == 110) {
               Memory.addByteInDynamicMemory(10);
            } else if(str.charAt(i) == 102) {
               Memory.addByteInDynamicMemory(12);
            } else if(str.charAt(i) == 114) {
               Memory.addByteInDynamicMemory(13);
            } else if(str.charAt(i) == 34) {
               Memory.addByteInDynamicMemory(34);
            } else if(str.charAt(i) == 39) {
               Memory.addByteInDynamicMemory(39);
            } else if(str.charAt(i) == 92) {
               Memory.addByteInDynamicMemory(92);
            }
         } else {
            Memory.addByteInDynamicMemory(str.charAt(i));
         }
      }

      return err;
   }

   private Error processAsciiDirective(String str, String sourceLineNo) {
      new Error();
      str = MyLibrary.removeBeginningAndEndingSpace(str);
      Error err;
      if(!this.isValidString(str)) {
         err = new Error("Invalid String Constant in line no " + sourceLineNo + " !", 1);
         return err;
      } else {
         err = this.storeAllChars(str);
         return err;
      }
   }

   private Error processAsciizDirective(String str, String sourceLineNo) {
      new Error();
      str = MyLibrary.removeBeginningAndEndingSpace(str);
      Error err;
      if(!this.isValidString(str)) {
         err = new Error("Invalid String Constant in line no " + sourceLineNo + " !", 1);
         return err;
      } else {
         err = this.storeAllChars(str);
         Memory.addByteInDynamicMemory(10);
         return err;
      }
   }

   private Error processDirectives(String str, String sourceLineNo) {
      str = MyLibrary.removeBeginningAndEndingSpace(str);
      Error err = new Error();
      if(str.equals("")) {
         return err;
      } else {
         if(str.startsWith(".space")) {
            str = str.substring(6);
            err = this.processSpaceDirective(str, sourceLineNo);
         } else if(str.startsWith(".word")) {
            str = str.substring(5);
            err = this.processWordDirective(str, sourceLineNo);
         } else if(str.startsWith(".asciiz")) {
            str = str.substring(7);
            err = this.processAsciizDirective(str, sourceLineNo);
         } else if(str.startsWith(".ascii")) {
            str = str.substring(6);
            err = this.processAsciiDirective(str, sourceLineNo);
         } else {
            err = new Error("Directive in line no " + sourceLineNo + " is not supported !", 1);
         }

         return err;
      }
   }

   private Error processTextSection() {
      new Error();
      int startAddress = Instruction.startAddressOfInst;
      int currentAddr = startAddress;

      Error err;
      for(int i = 0; i < this.sourceOfTextSection.size(); ++i) {
         String str = (String)this.sourceOfTextSection.get(i);
         str = MyLibrary.removeBeginningAndEndingSpace(str);
         if(!str.equals("")) {
            if(this.doesStartWithLevel(str)) {
               String instr = this.getLevelName(str);
               Instruction.addressOfLevels.put(instr, Integer.valueOf(currentAddr));
               str = str.substring(instr.length() + 1);
               str = MyLibrary.removeBeginningAndEndingSpace(str);
            }

            if(!str.equals("")) {
               Instruction var7 = new Instruction();
               this.CodeSection.add(str);
               err = var7.parsInstruction(str, "\n\t" + str);
               if(!err.isOk()) {
                  return err;
               }

               this.AllInstructions.add(var7);
               currentAddr += 4;
            }
         }
      }

      err = this.FillAllLevelNamesWithTheirAddress();
      return err;
   }

   private Error FillAllLevelNamesWithTheirAddress() {
      Error err = new Error();

      for(int i = 0; i < this.AllInstructions.size(); ++i) {
         Instruction inst = (Instruction)this.AllInstructions.get(i);
         err = inst.fillLevelNameWithAddress();
         if(!err.isOk()) {
            return err;
         }

         this.AllInstructions.set(i, inst);
      }

      return err;
   }
}
