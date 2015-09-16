package mipsparser_new.BMSFS;

import java.util.ArrayList;
import java.util.HashMap;
import mipsparser_new.BMSFS.Register;

public class Memory {
   public static int startAddressOfStack;
   public static int currentStackAddress;
   public static ArrayList dynamicData;
   public static int startAddressOfDynamicData;
   public static int currentDynamicDataAddress;
   public static HashMap localSymbolTable;

   public Memory() {
      startAddressOfStack = Register.getRegValue("$sp");
      currentStackAddress = startAddressOfStack;
      startAddressOfDynamicData = 268435456;
      currentDynamicDataAddress = startAddressOfDynamicData;
      int dynamicDataSize = 4 * (startAddressOfStack - startAddressOfDynamicData + 1);
      dynamicData = new ArrayList(dynamicDataSize);

      for(int i = 0; i < dynamicDataSize; ++i) {
         dynamicData.add(Integer.valueOf(0));
      }

      localSymbolTable = new HashMap();
   }

   public static void setBeginingAddressOfWord(String VariableName) {
      if(currentDynamicDataAddress % 4 != 0) {
         currentDynamicDataAddress = (currentDynamicDataAddress / 4 + 1) * 4;
      }

      localSymbolTable.put(VariableName, Integer.valueOf(currentDynamicDataAddress));
   }

   public static void setBeginingAddressOfHalfWord(String VariableName) {
      if(currentDynamicDataAddress % 2 != 0) {
         currentDynamicDataAddress = (currentDynamicDataAddress / 2 + 1) * 2;
      }

      localSymbolTable.put(VariableName, Integer.valueOf(currentDynamicDataAddress));
   }

   public static void setBeginingAddressOfByte(String VariableName) {
      localSymbolTable.put(VariableName, Integer.valueOf(currentDynamicDataAddress));
   }

   public static void addWordInDynamicMemory(int value) {
      while(currentDynamicDataAddress % 4 != 0 && currentDynamicDataAddress < currentStackAddress) {
         dynamicData.set(currentDynamicDataAddress++, Integer.valueOf(0));
      }

      if(currentDynamicDataAddress + 4 < currentStackAddress) {
         storeWord(currentDynamicDataAddress, value);
         currentDynamicDataAddress += 4;
      }

   }

   public static void addHalfWordInDynamicMemory(int value) {
      while(currentDynamicDataAddress % 2 != 0 && currentDynamicDataAddress < currentStackAddress) {
         dynamicData.set(currentDynamicDataAddress++, Integer.valueOf(0));
      }

      if(currentDynamicDataAddress + 4 < currentStackAddress) {
         storeHalfWord(currentDynamicDataAddress, value);
         currentDynamicDataAddress += 2;
      }

   }

   public static void addByteInDynamicMemory(int value) {
      if(currentDynamicDataAddress < currentStackAddress) {
         storeByte(currentDynamicDataAddress, value);
         ++currentDynamicDataAddress;
      }

   }

   public static int readWord(int addr) {
      int ret = 0;
      if(addr >= startAddressOfDynamicData && addr < startAddressOfStack && addr % 4 == 0) {
         int ind = addr - startAddressOfDynamicData;

         for(int i = 0; i < 4; ++i) {
            int singleByte = ((Integer)dynamicData.get(ind + i)).intValue() & 255;
            ret |= singleByte << 8 * i;
         }
      }

      return ret;
   }

   public static int readHalfWord(int addr) {
      int ret = 0;
      if(addr >= startAddressOfDynamicData && addr < startAddressOfStack && addr % 2 == 0) {
         int ind = addr - startAddressOfDynamicData;

         for(int i = 0; i < 2; ++i) {
            int singleByte = ((Integer)dynamicData.get(ind + i)).intValue() & 255;
            ret |= singleByte << 8 * i;
         }
      }

      return ret << 16 >> 16;
   }

   public static int readByte(int addr) {
      int ret = 0;
      if(addr >= startAddressOfDynamicData && addr < startAddressOfStack) {
         int ind = addr - startAddressOfDynamicData;
         ret = ((Integer)dynamicData.get(ind)).intValue() & 255;
      }

      return ret << 24 >> 24;
   }

   public static void storeWord(int addr, int value) {
      if(addr >= startAddressOfDynamicData && addr < startAddressOfStack && addr % 4 == 0) {
         int ind = addr - startAddressOfDynamicData;

         for(int i = 0; i < 4; ++i) {
            dynamicData.set(ind + i, Integer.valueOf(value >> 8 * i & 255));
         }
      }

   }

   public static void storeHalfWord(int addr, int value) {
      if(addr >= startAddressOfDynamicData && addr < startAddressOfStack && addr % 2 == 0) {
         int ind = addr - startAddressOfDynamicData;

         for(int i = 0; i < 2; ++i) {
            dynamicData.set(ind + i, Integer.valueOf(value >> 8 * i & 255));
         }
      }

   }

   public static void storeByte(int addr, int value) {
      if(addr >= startAddressOfDynamicData && addr < startAddressOfStack) {
         int ind = addr - startAddressOfDynamicData;
         dynamicData.set(ind, Integer.valueOf(value & 255));
      }

   }

   public static void storeWord(String VariableName, int disp, int value) {
      int startAddress = ((Integer)localSymbolTable.get(VariableName)).intValue();
      int addr = startAddress + disp;
      storeWord(addr, value);
   }

   public static void storeWord(String VariableName, int value) {
      storeWord(VariableName, 0, value);
   }

   public static void storeHalfWord(String VariableName, int disp, int value) {
      int startAddress = ((Integer)localSymbolTable.get(VariableName)).intValue();
      int addr = startAddress + disp;
      storeHalfWord(addr, value);
   }

   public static void storeHalfWord(String VariableName, int value) {
      storeHalfWord(VariableName, 0, value);
   }

   public static void storeByte(String VariableName, int disp, int value) {
      int startAddress = ((Integer)localSymbolTable.get(VariableName)).intValue();
      int addr = startAddress + disp;
      storeByte(addr, value);
   }

   public static void storeByte(String VariableName, int value) {
      storeByte(VariableName, 0, value);
   }

   public static int readWord(String VariableName, int disp) {
      int startAddress = ((Integer)localSymbolTable.get(VariableName)).intValue();
      int addr = startAddress + disp;
      return readWord(addr);
   }

   public static int readWord(String VariableName) {
      return readWord(VariableName, 0);
   }

   public static int readHalfWord(String VariableName, int disp) {
      int startAddress = ((Integer)localSymbolTable.get(VariableName)).intValue();
      int addr = startAddress + disp;
      return readHalfWord(addr);
   }

   public static int readHalfWord(String VariableName) {
      return readHalfWord(VariableName, 0);
   }

   public static int readByte(String VariableName, int disp) {
      int startAddress = ((Integer)localSymbolTable.get(VariableName)).intValue();
      int addr = startAddress + disp;
      return readByte(addr);
   }

   public static int readByte(String VariableName) {
      return readByte(VariableName, 0);
   }
}
