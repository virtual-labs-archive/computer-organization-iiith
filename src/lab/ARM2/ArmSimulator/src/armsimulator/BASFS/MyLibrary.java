package armsimulator.BASFS;

public class MyLibrary{
	
	public static int HexStringToDecInt(String str){ // string starts with 0x
            //System.out.println("in hexfunc string is "+str);
            int sign=1;
		if(str.charAt(0)=='-'){
			sign=-1;
			str=str.substring(3);
		}else {
                    if(str.charAt(0)=='+')
			str=str.substring(3);
                    else
                        str = str.substring(2);
		}
                //System.out.println(str);

		int temp =  sign*Integer.parseInt(str,16);
                return temp;
        }

	public static int fromStringToInt(String str){ // str can be in decimal or hexadecimal form
           // System.out.println("In func: "+str);
                
		str=removeBeginningAndEndingSpace(str);
		if(str.startsWith("#")){
			str=str.substring(1);
		}
            	if(str.startsWith("0x") || str.startsWith("-0x")){ // hexadecimal
			return HexStringToDecInt(str);
		}else{ // decimal
			return Integer.parseInt(str);
		}
            
	}
	public static String removeBeginningAndEndingSpace(String s) {
		int ind1=0 , ind2=s.length()-1 , n=s.length();
		while(ind1<n && isSpaceChar(s.charAt(ind1)))ind1++;
		while(ind2>=0 && isSpaceChar(s.charAt(ind2)))ind2--;
		if(ind2<ind1)return "";
		return s.substring(ind1, ind2+1);
	}
	
	public static boolean isSpaceChar(char ch) {
		return Character.isSpace(ch);
	}
        public static boolean isValidNumber(String str){
		str=removeBeginningAndEndingSpace(str);
		int len=str.length();
		if(len==0){
			return false;
		}
                boolean hex = false;
		if(str.startsWith("#")){
			str=str.substring(1);
		}
		if(str.charAt(0)=='+' || str.charAt(0)=='-'){
			str=str.substring(1);
		}
		if(str.startsWith("0x")){
                    hex = true;
			str=str.substring(2);
		}
		len=str.length();
		for(int i=0;i<len;i++){
                    if(hex == false){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
                    }
		}
		return true;
	}

}