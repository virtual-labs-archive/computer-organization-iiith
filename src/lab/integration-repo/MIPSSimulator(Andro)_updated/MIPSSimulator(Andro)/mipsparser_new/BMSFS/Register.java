package mipsparser_new.BMSFS;

public class Register {
   public static int[] Reg = new int[35];

   public Register() {
      for(int i = 0; i < Reg.length; ++i) {
         Reg[i] = 0;
      }

      initializePC();
      this.initializeSP();
   }

   private void initializeSP() {
      updateRegister("$sp", 269484032);
   }

   public static void updateRegister(int regNo, int value) {
      if(regNo != 0) {
         if(regNo > 0 && regNo < Reg.length) {
            Reg[regNo] = value;
         }

      }
   }

   public static void updateRegister(String regStr, int value) {
      int regNo = getRegisterNumberFromString(regStr);
      if(regNo > 0 && regNo < Reg.length) {
         Reg[regNo] = value;
      }

   }

   public static int getRegValue(int regNo) {
      return regNo >= 0 && regNo < Reg.length?Reg[regNo]:-1;
   }

   public static void setRegValue(int regNo, int value) {
      if(regNo >= 0 && regNo < Reg.length) {
         Reg[regNo] = value;
      }

   }

   public static int getRegValue(String reg) {
      int regNo = getRegisterNumberFromString(reg);
      return regNo >= 0 && regNo < Reg.length?Reg[regNo]:-1;
   }

   public static int getPC() {
      return Reg[34];
   }

   public static void updatePcRelative(int relativeShift) {
      Reg[34] += relativeShift;
   }

   public static void updatePcAbsolute(int newPCAddress) {
      Reg[34] = newPCAddress;
   }

   public static void initializePC() {
      updatePcAbsolute(67108864);
   }

   public static void initializePC(int startAddr) {
      updatePcAbsolute(startAddr);
   }

   public static void incrementPCToNextInstruction() {
      updatePcRelative(4);
   }

   public static int getHI() {
      return Reg[32];
   }

   public static void printAllReg() {
      System.out.println("\nPRINTING ALL REGISTERS");

      for(int i = 0; i < Reg.length; ++i) {
         System.out.println(i + " " + Reg[i]);
      }

   }

   public static void updateHI(int newValue) {
      Reg[32] = newValue;
   }

   public static int getLO() {
      return Reg[33];
   }

   public static void updateLO(int newValue) {
      Reg[33] = newValue;
   }

   public static int getRegisterNumberFromString(String str) {
      return !str.equalsIgnoreCase("$0") && !str.equalsIgnoreCase("$zero")?(!str.equalsIgnoreCase("$1") && !str.equalsIgnoreCase("$at")?(!str.equalsIgnoreCase("$2") && !str.equalsIgnoreCase("$v0")?(!str.equalsIgnoreCase("$3") && !str.equalsIgnoreCase("$v1")?(!str.equalsIgnoreCase("$4") && !str.equalsIgnoreCase("$a0")?(!str.equalsIgnoreCase("$5") && !str.equalsIgnoreCase("$a1")?(!str.equalsIgnoreCase("$6") && !str.equalsIgnoreCase("$a2")?(!str.equalsIgnoreCase("$7") && !str.equalsIgnoreCase("$a3")?(!str.equalsIgnoreCase("$8") && !str.equalsIgnoreCase("$t0")?(!str.equalsIgnoreCase("$9") && !str.equalsIgnoreCase("$t1")?(!str.equalsIgnoreCase("$10") && !str.equalsIgnoreCase("$t2")?(!str.equalsIgnoreCase("$11") && !str.equalsIgnoreCase("$t3")?(!str.equalsIgnoreCase("$12") && !str.equalsIgnoreCase("$t4")?(!str.equalsIgnoreCase("$13") && !str.equalsIgnoreCase("$t5")?(!str.equalsIgnoreCase("$14") && !str.equalsIgnoreCase("$t6")?(!str.equalsIgnoreCase("$15") && !str.equalsIgnoreCase("$t7")?(!str.equalsIgnoreCase("$16") && !str.equalsIgnoreCase("$s0")?(!str.equalsIgnoreCase("$17") && !str.equalsIgnoreCase("$s1")?(!str.equalsIgnoreCase("$18") && !str.equalsIgnoreCase("$s2")?(!str.equalsIgnoreCase("$19") && !str.equalsIgnoreCase("$s3")?(!str.equalsIgnoreCase("$20") && !str.equalsIgnoreCase("$s4")?(!str.equalsIgnoreCase("$21") && !str.equalsIgnoreCase("$s5")?(!str.equalsIgnoreCase("$22") && !str.equalsIgnoreCase("$s6")?(!str.equalsIgnoreCase("$23") && !str.equalsIgnoreCase("$s7")?(!str.equalsIgnoreCase("$24") && !str.equalsIgnoreCase("$t8")?(!str.equalsIgnoreCase("$25") && !str.equalsIgnoreCase("$t9")?(!str.equalsIgnoreCase("$26") && !str.equalsIgnoreCase("$k0")?(!str.equalsIgnoreCase("$27") && !str.equalsIgnoreCase("$k1")?(!str.equalsIgnoreCase("$28") && !str.equalsIgnoreCase("$gp")?(!str.equalsIgnoreCase("$29") && !str.equalsIgnoreCase("$sp")?(!str.equalsIgnoreCase("$30") && !str.equalsIgnoreCase("$fp")?(!str.equalsIgnoreCase("$31") && !str.equalsIgnoreCase("$ra")?(str.equalsIgnoreCase("$hi")?32:(str.equalsIgnoreCase("$lo")?33:-1)):31):30):29):28):27):26):25):24):23):22):21):20):19):18):17):16):15):14):13):12):11):10):9):8):7):6):5):4):3):2):1):0;
   }
}
