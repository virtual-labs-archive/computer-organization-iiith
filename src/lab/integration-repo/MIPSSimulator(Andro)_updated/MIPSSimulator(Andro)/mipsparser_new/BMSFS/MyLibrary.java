package mipsparser_new.BMSFS;

public class MyLibrary {
   public static String removeBeginningAndEndingSpace(String s) {
      int ind1 = 0;
      int ind2 = s.length() - 1;

      for(int n = s.length(); ind1 < n && isSpaceChar(s.charAt(ind1)); ++ind1) {
         ;
      }

      while(ind2 >= 0 && isSpaceChar(s.charAt(ind2))) {
         --ind2;
      }

      return ind2 < ind1?"":s.substring(ind1, ind2 + 1);
   }

   public static boolean isSpaceChar(char ch) {
      return ch == 32 || ch == 9 || ch == 10 || ch == 13 || Character.isWhitespace(ch);
   }

   public static int HexStringToDecInt(String str) {
      byte sign = 1;
      if(str.charAt(0) == 45) {
         sign = -1;
         str = str.substring(3);
      } else {
         str = str.substring(2);
      }

      return sign * Integer.parseInt(str, 16);
   }

   public static int fromStringToInt(String str) {
      str = removeBeginningAndEndingSpace(str);
      if(str.startsWith("#")) {
         str = str.substring(1);
      }

      return !str.startsWith("0x") && !str.startsWith("-0x")?Integer.parseInt(str):HexStringToDecInt(str);
   }

   public static boolean isValidNumber(String str) {
      str = removeBeginningAndEndingSpace(str);
      int len = str.length();
      if(len == 0) {
         return false;
      } else {
         if(str.startsWith("#")) {
            str = str.substring(1);
         }

         if(str.charAt(0) == 43 || str.charAt(0) == 45) {
            str = str.substring(1);
         }

         if(str.startsWith("0x")) {
            str = str.substring(2);
         }

         len = str.length();

         for(int i = 0; i < len; ++i) {
            if(!Character.isDigit(str.charAt(i))) {
               return false;
            }
         }

         return true;
      }
   }
}
