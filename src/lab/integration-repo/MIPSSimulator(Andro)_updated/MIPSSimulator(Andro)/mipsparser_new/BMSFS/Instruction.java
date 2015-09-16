package mipsparser_new.BMSFS;

import java.util.HashMap;
import mipsparser_new.BMSFS.Error;
import mipsparser_new.BMSFS.Memory;
import mipsparser_new.BMSFS.MyLibrary;
import mipsparser_new.BMSFS.Register;

public class Instruction {
   private String mnemonic;
   private int numOperands;
   private int[] operands;
   boolean[] isImmediate;
   private int[] sign;
   private String machineCode;
   private String levelName;
   private String sourceString;
   public static int startAddressOfInst = 67108864;
   public static HashMap addressOfLevels = new HashMap();

   public Instruction() {
      this.operands = new int[4];
      this.isImmediate = new boolean[4];
      this.sign = new int[4];
      this.levelName = "";
   }

   public Instruction(String _mnemonic) {
      this.mnemonic = _mnemonic;
      this.operands = new int[4];
      this.isImmediate = new boolean[4];
      this.sign = new int[4];
      this.levelName = "";
   }

   public Instruction(String _mnemonic, int _numOperands, int[] _operands, String _machineCode) {
      this.mnemonic = _mnemonic;
      this.numOperands = _numOperands;
      this.operands = _operands;
      this.machineCode = _machineCode;
      this.operands = new int[4];
      this.isImmediate = new boolean[4];
      this.sign = new int[4];
      this.levelName = "";
   }

   public String getName() {
      return this.mnemonic;
   }

   public int getNumberOfOperands() {
      return this.numOperands;
   }

   public int[] getOperands() {
      return this.operands;
   }

   public String getMachineCode() {
      return this.machineCode;
   }

   public String getSourceString() {
      return this.sourceString;
   }

   public static boolean isRegister(String str) {
      return str.startsWith("$");
   }

   public static boolean isImmediate(String str) {
      if(str.equals("")) {
         return false;
      } else {
         if(str.charAt(0) == 45) {
            str = str.substring(1);
         }

         int i;
         char ch;
         if(!str.startsWith("0x")) {
            for(i = 0; i < str.length(); ++i) {
               ch = str.charAt(i);
               if(ch < 48 || ch > 57) {
                  return false;
               }
            }
         } else {
            for(i = 2; i < str.length(); ++i) {
               ch = str.charAt(i);
               if((ch < 48 || ch > 57) && (ch < 65 || ch > 70)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public Error parseLoadStoreInstr(String str, String lineNoInSource) {
      Error err = new Error();

      for(int A = 0; A < 4; ++A) {
         this.isImmediate[A] = false;
         this.sign[A] = 1;
      }

      str = str.replaceAll(" ", "");
      String[] var8 = str.split(",");
      this.operands[0] = Register.getRegisterNumberFromString(var8[0]);
      if(this.operands[0] == -1) {
         err = new Error("Syntax Error @ Line " + lineNoInSource, 1);
         return err;
      } else {
         this.numOperands = 1;
         str = var8[1];
         int ind1;
         int ind2;
         String reg;
         if(str.contains("+")) {
            var8 = str.split("+");
            if(!Memory.localSymbolTable.containsKey(var8[0])) {
               err = new Error("Undefined label name : " + var8[0] + " Or Syntax error @ line " + lineNoInSource, 1);
               return err;
            }

            this.operands[this.numOperands] = ((Integer)Memory.localSymbolTable.get(var8[0])).intValue();
            this.isImmediate[this.numOperands++] = true;
            str = var8[1];
            if(str.contains("(")) {
               ind1 = str.indexOf("(");
               ind2 = str.indexOf(")");
               if(ind2 == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               reg = str.substring(ind1 + 1, ind2);
               this.operands[this.numOperands++] = Register.getRegisterNumberFromString(reg);
               if(this.operands[this.numOperands - 1] == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               str = str.replaceAll("\\(.*\\)", "");
            }

            if(str.equals("")) {
               return err;
            }

            if(isRegister(str)) {
               this.operands[this.numOperands] = Register.getRegisterNumberFromString(str);
               if(this.operands[this.numOperands] == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               this.isImmediate[this.numOperands++] = false;
            } else {
               if(!isImmediate(str)) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               this.operands[this.numOperands] = MyLibrary.fromStringToInt(str);
               this.isImmediate[this.numOperands++] = true;
            }
         } else if(str.contains("-")) {
            var8 = str.split("-");
            if(!Memory.localSymbolTable.containsKey(var8[0])) {
               err = new Error("Undefined label name : " + var8[0] + " Or Syntax error @ line " + lineNoInSource, 1);
               return err;
            }

            this.operands[this.numOperands] = ((Integer)Memory.localSymbolTable.get(var8[0])).intValue();
            this.isImmediate[this.numOperands++] = true;
            str = var8[1];
            if(str.contains("(")) {
               ind1 = str.indexOf("(");
               ind2 = str.indexOf(")");
               if(ind2 == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               reg = str.substring(ind1 + 1, ind2);
               this.sign[this.numOperands] = -1;
               this.operands[this.numOperands++] = Register.getRegisterNumberFromString(reg);
               if(this.operands[this.numOperands - 1] == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               str = str.replaceAll("\\(.*\\)", "");
            }

            if(str.equals("")) {
               return err;
            }

            if(isRegister(str)) {
               this.operands[this.numOperands] = Register.getRegisterNumberFromString(str);
               if(this.operands[this.numOperands] == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               this.sign[this.numOperands] = -1;
               this.isImmediate[this.numOperands++] = false;
            } else {
               if(!isImmediate(str)) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               this.operands[this.numOperands] = MyLibrary.fromStringToInt(str);
               this.sign[this.numOperands] = -1;
               this.isImmediate[this.numOperands++] = true;
            }
         } else if(Memory.localSymbolTable.containsKey(str)) {
            this.operands[this.numOperands] = ((Integer)Memory.localSymbolTable.get(str)).intValue();
            this.isImmediate[this.numOperands++] = true;
         } else {
            if(str.contains("(")) {
               ind1 = str.indexOf("(");
               ind2 = str.indexOf(")");
               if(ind2 == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               reg = str.substring(ind1 + 1, ind2);
               this.operands[this.numOperands++] = Register.getRegisterNumberFromString(reg);
               if(this.operands[this.numOperands - 1] == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               str = str.replaceAll("\\(.*\\)", "");
            }

            if(str.equals("")) {
               return err;
            }

            if(isRegister(str)) {
               this.operands[this.numOperands] = Register.getRegisterNumberFromString(str);
               if(this.operands[this.numOperands] == -1) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               this.isImmediate[this.numOperands++] = false;
            } else {
               if(!isImmediate(str)) {
                  err = new Error("Syntax error @ line : " + lineNoInSource, 1);
                  return err;
               }

               this.operands[this.numOperands] = MyLibrary.fromStringToInt(str);
               this.isImmediate[this.numOperands++] = true;
            }
         }

         return err;
      }
   }

   public Error parseBasicInstrucction(String str, String lineNoInSource) {
      Error err = new Error();

      for(int A = 0; A < 4; ++A) {
         this.sign[A] = 1;
      }

      this.numOperands = 0;
      this.levelName = "";
      String[] var6 = str.split(",");

      for(int i = 0; i < var6.length; ++i) {
         if(isRegister(var6[i])) {
            this.operands[i] = Register.getRegisterNumberFromString(var6[i]);
            if(this.operands[i] == -1) {
               err = new Error("Syntax Error @ Line " + lineNoInSource, 1);
               return err;
            }

            this.isImmediate[i] = false;
            ++this.numOperands;
         } else if(isImmediate(var6[i])) {
            this.operands[i] = MyLibrary.fromStringToInt(var6[i]);
            this.isImmediate[i] = true;
            ++this.numOperands;
         } else {
            this.levelName = var6[i];
         }
      }

      return err;
   }

   public Error parsInstruction(String str, String lineNoInSource) {
      new Error();
      str = MyLibrary.removeBeginningAndEndingSpace(str);
      this.sourceString = str;
      this.mnemonic = this.getMnemonicFromString(str);
      str = str.substring(this.mnemonic.length());
      str = str.replaceAll(" ", "");
      if(str.equals("")) {
         ;
      }

      Error err;
      if(this.mnemonic.equalsIgnoreCase("add")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("addi")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("addiu")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("addu")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("and")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("andi")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("b")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("beq")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("beqz")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bge")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bgez")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bgezal")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bgt")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bgtz")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("ble")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("blez")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("blt")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bltz")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bltzal")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bne")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("bnez")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("clo")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("clz")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("div")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("divu")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("j")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("jal")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("jalr")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("jr")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("la")) {
         err = this.parseLoadStoreInstr(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("lb")) {
         err = this.parseLoadStoreInstr(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("lh")) {
         err = this.parseLoadStoreInstr(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("li")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("lw")) {
         err = this.parseLoadStoreInstr(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("mfhi")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("mflo")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("mthi")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("mtlo")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("move")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("movn")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("movz")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("mul")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("mulo")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("mult")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("neg")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("nor")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("not")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("or")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("ori")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("sll")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("sllv")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("slt")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("slti")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("sltiu")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("sltu")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("srl")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("srlv")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("sb")) {
         err = this.parseLoadStoreInstr(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("seb")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("seh")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("sh")) {
         err = this.parseLoadStoreInstr(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("sub")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("sw")) {
         err = this.parseLoadStoreInstr(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("xor")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else if(this.mnemonic.equalsIgnoreCase("xori")) {
         err = this.parseBasicInstrucction(str, lineNoInSource);
      } else {
         err = new Error("Unsupported or invalid instruction : " + this.mnemonic, 1);
      }

      return err;
   }

   private String getMnemonicFromString(String str) {
      int ind = str.length();

      for(int i = 0; i < str.length(); ++i) {
         if(MyLibrary.isSpaceChar(str.charAt(i))) {
            ind = i;
            break;
         }
      }

      return str.substring(0, ind);
   }

   public Error fillLevelNameWithAddress() {
      Error err = new Error();
      if(this.levelName.equals("")) {
         return err;
      } else if(!addressOfLevels.containsKey(this.levelName)) {
         err = new Error("Undefined Label Name : " + this.levelName + " Or Syntax error ! ", 1);
         return err;
      } else {
         this.isImmediate[this.numOperands] = true;
         this.operands[this.numOperands++] = ((Integer)addressOfLevels.get(this.levelName)).intValue();
         return err;
      }
   }

   public String getMnemonic() {
      return this.mnemonic;
   }

   public boolean[] getIsImmediate() {
      return this.isImmediate;
   }

   public int[] getSign() {
      return this.sign;
   }

   public Error runSingleInstruction() {
      Error err = new Error();
      int oldPc = Register.getPC();
      int newPc;
      int i;
      int dif;
      if(this.mnemonic.equalsIgnoreCase("add")) {
         newPc = Register.getRegValue(this.operands[1]);
         i = this.isImmediate[2]?this.operands[2]:Register.getRegValue(this.operands[2]);
         if(this.isImmediate[2]) {
            if(i >= 65536) {
               err = new Error("Runtime Error: Immediate constant greater than (1<<16) in add instruction ", 2);
               return err;
            }

            i = i << 16 >> 16;
         }

         dif = newPc + i;
         if(newPc >= 0 && i >= 0 && dif < 0 || newPc < 0 && i < 0 && dif >= 0) {
            err = new Error("Overflow detected in add instruction , destination register didn\'t change", 3);
         } else {
            Register.updateRegister(this.operands[0], dif);
         }

         Register.incrementPCToNextInstruction();
      } else if(this.mnemonic.equalsIgnoreCase("addi")) {
         newPc = Register.getRegValue(this.operands[1]);
         i = this.operands[2];
         if(i >= 65536) {
            err = new Error("Runtime Error: Immediate constant greater than (1<<16) in add instruction ", 2);
            return err;
         }

         i = i << 16 >> 16;
         dif = newPc + i;
         if((newPc < 0 || i < 0 || dif >= 0) && (newPc >= 0 || i >= 0 || dif < 0)) {
            Register.updateRegister(this.operands[0], dif);
         } else {
            err = new Error("Overflow detected in addi instruction , destination register didn\'t change", 3);
         }

         Register.incrementPCToNextInstruction();
      } else if(this.mnemonic.equalsIgnoreCase("addiu")) {
         newPc = Register.getRegValue(this.operands[1]);
         i = this.operands[2];
         if(i >= 65536) {
            err = new Error("Runtime Error: Immediate constant greater than (1<<16) in add instruction ", 2);
            return err;
         }

         i = i << 16 >> 16;
         dif = newPc + i;
         Register.updateRegister(this.operands[0], dif);
         Register.incrementPCToNextInstruction();
      } else if(this.mnemonic.equalsIgnoreCase("addu")) {
         newPc = Register.getRegValue(this.operands[1]);
         i = this.isImmediate[2]?this.operands[2]:Register.getRegValue(this.operands[2]);
         if(this.isImmediate[2]) {
            if(i >= 65536) {
               err = new Error("Runtime Error: Immediate constant greater than (1<<16) in add instruction ", 2);
               return err;
            }

            i = i << 16 >> 16;
         }

         dif = newPc + i;
         Register.updateRegister(this.operands[0], dif);
         Register.incrementPCToNextInstruction();
      } else if(this.mnemonic.equalsIgnoreCase("and")) {
         Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) & Register.getRegValue(this.operands[2]));
         Register.incrementPCToNextInstruction();
      } else if(this.mnemonic.equalsIgnoreCase("andi")) {
         Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) & this.operands[2] & '\uffff');
         Register.incrementPCToNextInstruction();
      } else if(this.mnemonic.equalsIgnoreCase("b")) {
         Register.updatePcAbsolute(this.operands[0]);
         newPc = Register.getPC();
      } else if(this.mnemonic.equalsIgnoreCase("beq")) {
         if(Register.getRegValue(this.operands[0]) == Register.getRegValue(this.operands[1])) {
            Register.updatePcAbsolute(this.operands[2]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("beqz")) {
         if(Register.getRegValue(this.operands[0]) == 0) {
            Register.updatePcAbsolute(this.operands[1]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bge")) {
         if(Register.getRegValue(this.operands[0]) >= Register.getRegValue(this.operands[1])) {
            Register.updatePcAbsolute(this.operands[2]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bgez")) {
         if(Register.getRegValue(this.operands[0]) >= 0) {
            Register.updatePcAbsolute(this.operands[1]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bgezal")) {
         if(Register.getRegValue(this.operands[0]) >= 0) {
            Register.updateRegister("$ra", Register.getPC() + 4);
            Register.updatePcAbsolute(this.operands[1]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bgt")) {
         newPc = this.isImmediate[0]?this.operands[0]:Register.getRegValue(this.operands[0]);
         i = this.isImmediate[1]?this.operands[1]:Register.getRegValue(this.operands[1]);
         if(newPc > i) {
            Register.updatePcAbsolute(this.operands[2]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bgtz")) {
         if(Register.getRegValue(this.operands[0]) > 0) {
            Register.updatePcAbsolute(this.operands[1]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("ble")) {
         if(Register.getRegValue(this.operands[0]) <= Register.getRegValue(this.operands[1])) {
            Register.updatePcAbsolute(this.operands[2]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("blez")) {
         if(Register.getRegValue(this.operands[0]) <= 0) {
            Register.updatePcAbsolute(this.operands[1]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("blt")) {
         if(Register.getRegValue(this.operands[0]) < Register.getRegValue(this.operands[1])) {
            Register.updatePcAbsolute(this.operands[2]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bltz")) {
         if(Register.getRegValue(this.operands[0]) < 0) {
            Register.updatePcAbsolute(this.operands[1]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bltzal")) {
         if(Register.getRegValue(this.operands[0]) < 0) {
            Register.updateRegister("ra", Register.getPC() + 4);
            Register.updatePcAbsolute(this.operands[1]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bne")) {
         if(Register.getRegValue(this.operands[0]) != Register.getRegValue(this.operands[1])) {
            Register.updatePcAbsolute(this.operands[2]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("bnez")) {
         if(Register.getRegValue(this.operands[0]) != 0) {
            Register.updatePcAbsolute(this.operands[1]);
         } else {
            Register.incrementPCToNextInstruction();
         }
      } else if(this.mnemonic.equalsIgnoreCase("clo")) {
         newPc = Register.getRegValue(this.operands[1]);
         i = 32;

         for(dif = 31; dif >= 0; --dif) {
            if((1 << dif & newPc) == 0) {
               i = 31 - dif;
               break;
            }
         }

         Register.updateRegister(this.operands[0], i);
         Register.incrementPCToNextInstruction();
      } else if(this.mnemonic.equalsIgnoreCase("clz")) {
         newPc = Register.getRegValue(this.operands[1]);
         i = 32;

         for(dif = 31; dif >= 0; --dif) {
            if((1 << dif & newPc) != 0) {
               i = 31 - dif;
               break;
            }
         }

         Register.updateRegister(this.operands[0], i);
         Register.incrementPCToNextInstruction();
      } else if(this.mnemonic.equalsIgnoreCase("div")) {
         if(this.numOperands == 2) {
            if(Register.getRegValue(this.operands[1]) == 0) {
               err = new Error("Runtime Error : Division by 0 in div instruction", 2);
               return err;
            }

            Register.updateRegister("$hi", Register.getRegValue(this.operands[0]) % Register.getRegValue(this.operands[1]));
            Register.updateRegister("$lo", Register.getRegValue(this.operands[0]) / Register.getRegValue(this.operands[1]));
         } else if(this.numOperands == 3) {
            if(Register.getRegValue(this.operands[2]) == 0) {
               err = new Error("Runtime Error : Division by 0 in div instruction", 2);
               return err;
            }

            Register.updateRegister("$hi", Register.getRegValue(this.operands[1]) % Register.getRegValue(this.operands[2]));
            Register.updateRegister("$lo", Register.getRegValue(this.operands[1]) / Register.getRegValue(this.operands[2]));
            Register.updateRegister(this.operands[0], Register.getRegValue("$lo"));
         }

         Register.incrementPCToNextInstruction();
      } else {
         long product;
         long var11;
         long var12;
         if(this.mnemonic.equalsIgnoreCase("divu")) {
            var11 = (long)Register.getRegValue(this.operands[0]);
            var12 = (long)Register.getRegValue(this.operands[1]);
            if(var12 == 0L) {
               err = new Error("Runtime Error : Division by 0 in divu instruction", 2);
               return err;
            }

            var11 = var11 << 32 >>> 32;
            var12 = var12 << 32 >>> 32;
            product = var11 / var12;
            long hiValue = var11 % var12;
            product = product << 32 >> 32;
            hiValue = hiValue << 32 >> 32;
            Register.updateLO((int)product);
            Register.updateHI((int)hiValue);
            Register.incrementPCToNextInstruction();
         } else if(this.mnemonic.equalsIgnoreCase("j")) {
            Register.updatePcAbsolute(this.operands[0]);
         } else if(this.mnemonic.equalsIgnoreCase("jal")) {
            Register.updateRegister(31, Register.getPC() + 4);
            Register.updatePcAbsolute(this.operands[0]);
         } else if(this.mnemonic.equalsIgnoreCase("jalr")) {
            Register.updateRegister("$ra", Register.getPC() + 4);
            Register.updatePcAbsolute(Register.getRegValue(this.operands[0]));
         } else {
            if(this.mnemonic.equalsIgnoreCase("jr")) {
               Register.updatePcAbsolute(Register.getRegValue(this.operands[0]));
               return err;
            }

            if(this.mnemonic.equalsIgnoreCase("la")) {
               newPc = 0;

               for(i = 1; i < this.numOperands; ++i) {
                  newPc += this.isImmediate[i]?this.operands[i] * this.sign[i]:this.sign[i] * Register.getRegValue(this.operands[i]);
               }

               Register.updateRegister(this.operands[0], newPc);
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("lb")) {
               newPc = 0;

               for(i = 1; i < this.numOperands; ++i) {
                  newPc += this.isImmediate[i]?this.operands[i] * this.sign[i]:this.sign[i] * Register.getRegValue(this.operands[i]);
               }

               Register.updateRegister(this.operands[0], Memory.readByte(newPc));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("lh")) {
               newPc = 0;

               for(i = 1; i < this.numOperands; ++i) {
                  newPc += this.isImmediate[i]?this.operands[i] * this.sign[i]:this.sign[i] * Register.getRegValue(this.operands[i]);
               }

               Register.updateRegister(this.operands[0], Memory.readHalfWord(newPc));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("li")) {
               Register.updateRegister(this.operands[0], this.operands[1]);
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("lw")) {
               newPc = 0;

               for(i = 1; i < this.numOperands; ++i) {
                  newPc += this.isImmediate[i]?this.operands[i] * this.sign[i]:this.sign[i] * Register.getRegValue(this.operands[i]);
               }

               Register.updateRegister(this.operands[0], Memory.readWord(newPc));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("mfhi")) {
               Register.updateRegister(this.operands[0], Register.getHI());
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("mflo")) {
               Register.updateRegister(this.operands[0], Register.getLO());
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("mthi")) {
               Register.updateHI(Register.getRegValue(this.operands[0]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("mtlo")) {
               Register.updateLO(Register.getRegValue(this.operands[0]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("move")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("movn")) {
               if(Register.getRegValue(this.operands[2]) != 0) {
                  Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]));
               }

               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("movz")) {
               if(Register.getRegValue(this.operands[2]) == 0) {
                  Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]));
               }

               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("mul")) {
               if(this.numOperands == 2) {
                  var11 = (long)Register.getRegValue(this.operands[0]);
                  var12 = this.isImmediate[1]?(long)this.operands[1]:(long)Register.getRegValue(this.operands[1]);
                  if(this.isImmediate[1]) {
                     var12 = var12 << 16 >> 16;
                  }

                  product = var11 * var12;
                  Register.updateRegister("$hi", (int)(product >> 32));
                  Register.updateRegister("$lo", (int)(product << 32 >> 32));
               } else if(this.numOperands == 3) {
                  var11 = (long)Register.getRegValue(this.operands[1]);
                  var12 = this.isImmediate[2]?(long)this.operands[2]:(long)Register.getRegValue(this.operands[2]);
                  if(this.isImmediate[2]) {
                     var12 = var12 << 16 >> 16;
                  }

                  product = var11 * var12;
                  Register.updateRegister(this.operands[0], (int)(product << 32 >> 32));
                  Register.updateRegister("$hi", (int)(product >> 32));
                  Register.updateRegister("$lo", (int)(product << 32 >> 32));
               }

               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("mulo")) {
               var11 = (long)Register.getRegValue(this.operands[1]);
               var12 = this.isImmediate[2]?(long)this.operands[2]:(long)Register.getRegValue(this.operands[2]);
               if(this.isImmediate[2]) {
                  var12 = var12 << 16 >> 16;
               }

               product = var11 * var12;
               Register.updateRegister(this.operands[0], (int)(product << 32 >> 32));
               Register.updateRegister("$hi", (int)(product >> 32));
               Register.updateRegister("$lo", (int)(product << 32 >> 32));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("mult")) {
               var11 = (long)Register.getRegValue(this.operands[0]);
               var12 = (long)Register.getRegValue(this.operands[1]);
               product = var11 * var12;
               Register.updateRegister("$hi", (int)(product >> 32));
               Register.updateRegister("$lo", (int)(product << 32 >> 32));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("neg")) {
               Register.updateRegister(this.operands[0], -Register.getRegValue(this.operands[1]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("nor")) {
               Register.updateRegister(this.operands[0], ~(Register.getRegValue(this.operands[1]) | Register.getRegValue(this.operands[2])));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("not")) {
               Register.updateRegister(this.operands[0], ~Register.getRegValue(this.operands[1]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("or")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) | Register.getRegValue(this.operands[2]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("ori")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) | this.operands[2] & '\uffff');
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("sb")) {
               newPc = 0;

               for(i = 1; i < this.numOperands; ++i) {
                  newPc += this.isImmediate[i]?this.operands[i] * this.sign[i]:this.sign[i] * Register.getRegValue(this.operands[i]);
               }

               Memory.storeByte(newPc, Register.getRegValue(this.operands[0]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("seb")) {
               newPc = Register.getRegValue(1);
               Register.updateRegister(this.operands[0], (newPc & 255) << 24 >> 24);
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("seh")) {
               newPc = Register.getRegValue(1);
               Register.updateRegister(this.operands[0], (newPc & '\uffff') << 16 >> 16);
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("sh")) {
               newPc = 0;

               for(i = 1; i < this.numOperands; ++i) {
                  newPc += this.isImmediate[i]?this.operands[i] * this.sign[i]:this.sign[i] * Register.getRegValue(this.operands[i]);
               }

               Memory.storeHalfWord(newPc, Register.getRegValue(this.operands[0]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("sll")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) << this.operands[2]);
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("sllv")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) << (Register.getRegValue(this.operands[2]) & 31));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("slt")) {
               newPc = Register.getRegValue(this.operands[1]);
               i = Register.getRegValue(this.operands[2]);
               if(newPc < i) {
                  Register.updateRegister(this.operands[0], 1);
               } else {
                  Register.updateRegister(this.operands[0], 0);
               }

               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("slti")) {
               newPc = Register.getRegValue(this.operands[1]);
               i = this.operands[2];
               if(newPc < i) {
                  Register.updateRegister(this.operands[0], 1);
               } else {
                  Register.updateRegister(this.operands[0], 0);
               }

               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("sltiu")) {
               var11 = (long)Register.getRegValue(this.operands[1]);
               var12 = (long)this.operands[2];
               var11 = var11 << 32 >>> 32;
               var12 = var12 << 32 >>> 32;
               if(var11 < var12) {
                  Register.updateRegister(this.operands[0], 1);
               } else {
                  Register.updateRegister(this.operands[0], 0);
               }

               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("sltu")) {
               var11 = (long)Register.getRegValue(this.operands[1]);
               var12 = (long)Register.getRegValue(this.operands[2]);
               var11 = var11 << 32 >>> 32;
               var12 = var12 << 32 >>> 32;
               if(var11 < var12) {
                  Register.updateRegister(this.operands[0], 1);
               } else {
                  Register.updateRegister(this.operands[0], 0);
               }

               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("srl")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) >>> this.operands[2]);
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("srlv")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) >>> (Register.getRegValue(this.operands[2]) & 31));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("sub")) {
               newPc = Register.getRegValue(this.operands[1]);
               if(this.isImmediate[2]) {
                  i = this.operands[2];
               } else {
                  i = Register.getRegValue(this.operands[2]);
               }

               dif = newPc - i;
               if((newPc < 0 || i >= 0 || dif >= 0) && (newPc >= 0 || i < 0 || dif < 0)) {
                  Register.updateRegister(this.operands[0], dif);
               }

               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("subu")) {
               newPc = Register.getRegValue(this.operands[1]);
               if(this.isImmediate[2]) {
                  i = this.operands[2];
               } else {
                  i = Register.getRegValue(this.operands[2]);
               }

               dif = newPc - i;
               Register.updateRegister(this.operands[0], dif);
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("sw")) {
               newPc = 0;

               for(i = 1; i < this.numOperands; ++i) {
                  newPc += this.isImmediate[i]?this.operands[i] * this.sign[i]:this.sign[i] * Register.getRegValue(this.operands[i]);
               }

               Memory.storeWord(newPc, Register.getRegValue(this.operands[0]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("xor")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) ^ Register.getRegValue(this.operands[2]));
               Register.incrementPCToNextInstruction();
            } else if(this.mnemonic.equalsIgnoreCase("xori")) {
               Register.updateRegister(this.operands[0], Register.getRegValue(this.operands[1]) ^ this.operands[2] & '\uffff');
               Register.incrementPCToNextInstruction();
            } else {
               err = new Error("Instruction " + this.mnemonic + " Not Supported ! ", 2);
            }
         }
      }

      newPc = Register.getPC();
      if(oldPc == newPc) {
         err = new Error("Infinite loop detected !", 2);
      }

      return err;
   }
}
