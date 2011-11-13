package armsimulator.BASFS;
import java.util.*;

public class Memory{

	public static int startAddressOfStack;
	public static int currentStackAddress; // stack is full uptill this pint 
	
	public static ArrayList<Integer> dynamicData; // dynamic data grows upWard
	public static int startAddressOfDynamicData;
	public static int currentDynamicDataAddress; // dynamic data is filled to the memory address before this address .
	                               // is we have to allocate memory then we can allocate memory starting
	                               // from this address .
	
	public static HashMap<String,Integer > localSymbolTable=new HashMap<String, Integer>(); // contains level(variable) names and their start address
	
	public Memory(){

		startAddressOfStack=Register.getReg("sp");  // it should be 0x10100000
		currentStackAddress=startAddressOfStack;
		
		dynamicData=new ArrayList<Integer>();
		startAddressOfDynamicData=0x10000000 ;
		currentDynamicDataAddress=startAddressOfDynamicData;
	
	}
	
	
	public static void setBeginingAddress(String VariableName){
		localSymbolTable.put(VariableName, currentDynamicDataAddress);
	}
	
	public static void addDataInDynamicMemory(int value){
		dynamicData.add(value);
		currentDynamicDataAddress+=4;
	}
	
	public static int readDynamicMemory(int address){
		if(address<startAddressOfDynamicData) return 0;
		int index=(address-startAddressOfDynamicData)/4;
		int len=dynamicData.size();
		if(index>=len) return 0;
		return dynamicData.get(index);
	}

        public static int readWord(int addr){
            return readDynamicMemory(addr);
        }

	public static int readDynamicMemory(String VariableName , int disp /*should be multiple of 4*/){
		int startAddress=localSymbolTable.get(VariableName);
		int address=startAddress+disp ; 
		return readDynamicMemory(address);
	}
	
	public static int readDynamicMemory(String VariableName){
		return readDynamicMemory(VariableName, 0);
	}
	public static void storeWord(int address , int value){
            updateDynamicMemory(address, value);
	}
	public static void updateDynamicMemory(int address , int value){
		if(address<startAddressOfDynamicData) return ;
		int index=(address-startAddressOfDynamicData)/4;
		int len=dynamicData.size();
//		if(index>=len) return ;
		while(index>=len){
			dynamicData.add(0);
			len++;
		}
		dynamicData.set(index, value);
	}
	
	public static void updateDynamicMemory(String VariableName , int disp , int value){
		int startAddress=localSymbolTable.get(VariableName);
		int address=startAddress+disp ; 
		updateDynamicMemory(address, value);
	}
	
	public static void updateDynamicMemory(String VariableName  , int value){
		updateDynamicMemory(VariableName, 0,value);
	}
	
	public String fromDecToHex(int val){
		String ret="0x";
		int Mask=15; // (1111) in binary
		for(int i=0;i<8;i++){
			int v=Mask&(val>>(4*(7-i)));
			ret+=HexStringOfDecValue(v);
		}
		return ret;
	}
	
	private String HexStringOfDecValue(int v) {
		char ch=(char) ((v<10)?(v+'0'):(v-10+'A'));
		return Character.toString(ch);
	}

	public int fromHexToDec(String str){
		// the string should always start with 0x
		int sign=1;
		if(str.charAt(0)=='-'){
			sign=-1;
			str=str.substring(3);
		}else {
			str=str.substring(2);
		}
		return sign*Integer.parseInt(str,16);
	}

	private int decValueOfHexChar(char ch) {
		if(ch>='0' && ch<='9') return ch-'0';
		return ch-'A'+10;
	}

	public static void printDynamicMemory() {
		for(int i=0;i<dynamicData.size();i++){
			System.out.println(Integer.toString(startAddressOfDynamicData+4*i)+" "+Integer.toString(dynamicData.get(i)));
		}
	}
	
}