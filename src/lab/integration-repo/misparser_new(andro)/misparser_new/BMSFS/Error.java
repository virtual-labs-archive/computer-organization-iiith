package mipsparser_new.BMSFS;

public class Error {
   public static final int NO_ERROR = 0;
   public static final int ASSEMBLE_TIME_ERROR = 1;
   public static final int RUN_TIME_ERROR = 2;
   public static final int RUN_TIME_WARNING = 3;
   private String errorMsg;
   private int errorType;

   public Error() {
      this.errorMsg = "";
      this.errorType = 0;
   }

   public Error(String msg, int type) {
      this.errorMsg = msg;
      this.errorType = type;
   }

   public boolean isOk() {
      return this.errorType == 0 || this.errorType == 3;
   }

   public boolean isWarning() {
      return this.errorType == 3;
   }

   public void printErrorMsg() {
   }

   public String getErrorMsg() {
      return this.errorMsg;
   }

   public int getErrorType() {
      return this.errorType;
   }

   public String getErrorTypeString() {
      return this.errorType == 0?"NO_ERROR":(this.errorType == 1?"ASSEMBLE_TIME_ERROR":(this.errorType == 2?"RUN_TIME_ERROR":(this.errorType == 3?"RUN_TIME_WARNING":"")));
   }
}
