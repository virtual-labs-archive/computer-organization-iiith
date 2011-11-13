package armsimulator.BASFS ; 

public class Register{
	private static int []Reg = new int [16] ; // 16 registers in user mode 
											//13-sp , 14-lr , 15-pc
	private static int CPSR ; 
	
	public Register(){
		for(int i=0;i<Reg.length;i++){
			Reg[i]=0;
		}
		initializePC();
		initializeSP();
		initializeCPSR();
	}
	
	public Register(int startAddrOfPC , int startAddrOfStack){
		for(int i=0;i<Reg.length;i++){
			Reg[i]=0;
		}
		initializeCPSR();
		initializePC(startAddrOfPC);
		initializeSP(startAddrOfStack);
	}

	private void initializeCPSR(){
		
	}
	
	private void initializePC(){
		updatePCAbsolute(0x4000000);
	}
	private void initializeSP(){
		updateRegister("sp", 0x10100000);
	}
	private void initializePC(int startAddrOfPC){
		updatePCAbsolute(startAddrOfPC);
	}
	private void initializeSP(int startAddrOfStack){
		updateRegister("sp", startAddrOfStack);
	}
	
	public static int getPC(){
		return Reg[15];
	}
	public static void updatePCAbsolute(int targetAddress){
		Reg[15]=targetAddress;
	}
	public static void updatePCRelative(int relativeShift){
		updatePCAbsolute(getPC()+relativeShift);
	}
	public static void incrementPCToNextInstruction(){
		updatePCAbsolute(getPC()+4);
	}
	public static int getRegValue(int regNo){
		if(regNo>=0 && regNo<Reg.length){
			return Reg[regNo];
		}
		return -1; // error (-1 can be valid return value also)
	}
        public static void setRegValue(int regNo, int value){
            if(regNo>=0 && regNo<Reg.length){
                Reg[regNo] = value;
            }
        }
	public static void updateRegister(int regNo , int newValue){
		if(regNo>=0 && regNo<Reg.length){ // we don't allow direct writing to CPSR register  
			Reg[regNo]=newValue;
		}
	}
	
	public static void updateRegister(String regStr , int newValue){
		int regNo=getRegisterNumberFromString(regStr);
		updateRegister(regNo, newValue);
	}

	public static int getReg(int regNo){
		if(regNo>=0 && regNo<Reg.length){
			return Reg[regNo];
		}
		return 0; // error
	}
	public static int getReg(String regName){
		int regNo=getRegisterNumberFromString(regName);
		return getReg(regNo);
	}
	
	
	/************************	FUNCTIONS FOR CPSR START HERE  ************************/
	
	/* The following functions are used for CPSR register */
	public static boolean isSet(int no , int n){
		// n should be between 0 and 31 inclusive
		if(n>=0 && n<32){
			return (no&(1<<n))!=0;
		}	
		return false ; // error
	}
	public static boolean isClear(int no , int n){
		if(n>=0 && n<32){
			return !isSet(no, n);
		}
		return false ; // error
	}	
	
	public static boolean isNSet(){
		return isSet(CPSR, 31);
	}
	public static boolean isNClear(){
		return isClear(CPSR, 31);
	}
	public static boolean isZSet(){
		return isSet(CPSR, 30);
	}
	public static boolean isZClear(){
		return isClear(CPSR, 30);
	}
	public static boolean isCSet(){
		return isSet(CPSR, 29);
	}
	public static boolean isCClear(){
		return isClear(CPSR, 29);
	}
	public static boolean isVSet(){
		return isSet(CPSR, 28);
	}
	public static boolean isVClear(){
		return isClear(CPSR, 28);
	}
	
	private static int set(int no , int n){
		if(n>=0 && n<32){
			no=no|(1<<n);
		}
		return no;
	}
	private static int clear(int no ,int n){
		if(n>=0 && n<32){
			if(isSet(no,n)){
				no=no-(1<<n);
			}
		}
		return no;
	}
	
	public static void setN(){
		CPSR=set(CPSR,31);
	}
	public static void clearN(){
		CPSR=clear(CPSR,31);
	}
	public static void setZ(){
		CPSR=set(CPSR,30);
	}
	public static void clearZ(){
		CPSR=clear(CPSR,30);
	}
	public static void setC(){
		CPSR=set(CPSR,29);
	}
	public static void clearC(){
		CPSR=clear(CPSR,29);
	}
	public static void setV(){
		CPSR=set(CPSR,28);
	}
	public static void clearV(){
		CPSR=clear(CPSR,28);
	}
	
	/*********************	FUNCTIONS FOR CPSR END HERE  **************************/
	
	
	
	/*********************	FUNCTIONS FOR ARM CONDITION CODES START HERE **********/
	
	public static boolean isEQ(){
		return isZSet();
	}
	public static boolean isNE(){
		return isZClear();
	}
	public static boolean isCS(){
		return isCSet();
	}
	public static boolean isHS(){
		return isCSet();
	}
	public static boolean isCC(){
		return isCClear();
	}
	public static boolean isLO(){
		return isCClear();
	}
	public static boolean isMI(){
		return isNSet();
	}
	public static boolean isPL(){
		return isNClear();
	}
	public static boolean isVS(){
		return isVSet();
	}
	public static boolean isVC(){
		return isVClear();
	}
	public static boolean isHI(){
		return isCSet() && isZClear();
	}
	public static boolean isLS(){
		return isCClear() || isZSet();
	}
	public static boolean isGE(){
		// N equals V
		return (isNSet()&&isVSet()) || (isNClear()&&isVClear()) ; 
	}
	public static boolean isLT(){
		// N is not equal to V
		return (isNSet()&&isVClear()) || (isNClear()&&isVSet()) ; 
	}
	public static boolean isGT(){
		// Z clear and N equals V
		return isZClear() && isGE();
	}
	public static boolean isLE(){
		// Z set or N is not equal to v
		return isZClear() || isLT();
		
	}
	public static boolean isAL(){
		// ALways
		return true;
	}

	
	/*********************  FUNCTIONS FOR ARM CONDITION CODES END HERE   **********/ 
	
	public static int getRegisterNumberFromString(String str) {
		str = str.toLowerCase();
		if (str.equals("r0")) {
			return 0;
		} else if (str.equals("r1")) {
			return 1;
		} else if (str.equals("r2")) {
			return 2;
		} else if (str.equals("r3")) {
			return 3;
		} else if (str.equals("r4")) {
			return 4;
		} else if (str.equals("r5")) {
			return 5;
		} else if (str.equals("r6")) {
			return 6;
		} else if (str.equals("r7")) {
			return 7;
		} else if (str.equals("r8")) {
			return 8;
		} else if (str.equals("r9")) {
			return 9;
		} else if (str.equals("r10")) {
			return 10;
		} else if (str.equals("r11")) {
			return 11;
		} else if (str.equals("r12")) {
			return 12;
		} else if (str.equals("r13") || str.equals("sp")) {
			return 13;
		} else if (str.equals("r14") || str.equals("lr")) {
			return 14;
		} else if (str.equals("r15") || str.equals("pc")) {
			return 15;
		} else if (str.equals("r16")) {  // CPSR
			return 16;
		}
							
		return -1;
	}
	
	public static void printAllReg(){
		System.out.println("\nPRINTING ALL REGISTERS");
		for(int i=0;i<Reg.length;i++){
			System.out.println(i+" "+Reg[i]);
		}
	}

	public static boolean ConditionPassed(String cond) {
		if(cond.equals("eq")){
			return isEQ();
		}else if(cond.equals("ne")){
			return isNE();
		}else if(cond.equals("cs")){
			isCS();
		}else if(cond.equals("hs")){
			isHS();
		}else if(cond.equals("cc")){
			isCC();
		}else if(cond.equals("lo")){
			isLO();
		}else if(cond.equals("mi")){
			isMI();
		}else if(cond.equals("pl")){
			isPL();
		}else if(cond.equals("vs")){
			isVS();
		}else if(cond.equals("vc")){
			isVC();
		}else if(cond.equals("hi")){
			isHI();
		}else if(cond.equals("ls")){
			isLS();
		}else if(cond.equals("ge")){
			isGE();
		}else if(cond.equals("lt")){
			isLT();
		}else if(cond.equals("gt")){
			isGT();
		}else if(cond.equals("le")){
			isLE();
		}else if(cond.equals("al")){
			return true;
		}
		return false;
	}
	
}