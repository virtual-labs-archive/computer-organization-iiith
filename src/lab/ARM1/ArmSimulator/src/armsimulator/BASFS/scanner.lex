package scanner;
import java_cup.runtime.*;
import java.io.IOException;
import parser.ParserSym;


%%


%class Scanner


%unicode  
%line
%column
%public
//%final
// %abstract
 
%cupsym ParserSym
%cup

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return symbol(type, yytext());
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }
  private void error()
	throws IOException
	{
		throw new IOException("illegal text at line = "+yyline+", column = "+yycolumn+", text = '"+yytext()+"'");
	} 
%}



LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

Comment = {Comment1} | {Comment2}
Comment1   = "@"+[^*]
Comment2     = "#" + [^*]

Register = "r"+[:jdigit:]*
Immediate = 0 | [1-9][0-9]*

%state STRING

%%

/* keywords */
<YYINITIAL> "add"           { return symbol(ParserSym.ADD); }
<YYINITIAL> "adc"            { return symbol(ParserSym.ADC); }
<YYINITIAL> "sub"              { return symbol(ParserSym.SUB); }
<YYINITIAL> "mov"              { return symbol(ParserSym.MOV); }
<YYINITIAL> "mvn"              { return symbol(ParserSym.MVN); }
<YYINITIAL> "and"              { return symbol(ParserSym.AND); }
<YYINITIAL> "eor"              { return symbol(ParserSym.EOR); }
<YYINITIAL> "orr"              { return symbol(ParserSym.ORR); }
<YYINITIAL> "cmp"              { return symbol(ParserSym.CMP); }
<YYINITIAL> "lsl"              { return symbol(ParserSym.LSL); }
<YYINITIAL> "rsb"              { return symbol(ParserSym.RSB); }
<YYINITIAL> "bic"              { return symbol(ParserSym.BIC); }


<YYINITIAL> "b"              { return symbol(ParserSym.BRANCH); }
<YYINITIAL> "bl"              { return symbol(ParserSym.BRANCHL); }
<YYINITIAL> "ldr"              { return symbol(ParserSym.LOAD); }
<YYINITIAL> "str"              { return symbol(ParserSym.STORE); }
<YYINITIAL> "eq"              { return symbol(ParserSym.EQUAL); }
<YYINITIAL> "ne"              { return symbol(ParserSym.NOTEQUAL); }
<YYINITIAL> "gt"              { return symbol(ParserSym.GT); }
<YYINITIAL> "le"              { return symbol(ParserSym.LE); }
<YYINITIAL> "lt"              { return symbol(ParserSym.LT); }
<YYINITIAL> "ge"              { return symbol(ParserSym.GE); }


<YYINITIAL> {
  /* identifiers */ 
  {Register}                   { return symbol(ParserSym.Register); }
  
 
  /* literals */
  {Immediate}            { return symbol(ParserSym.Immediate,new Integer(yytext())); }
  \"                             { string.setLength(0); yybegin(STRING); }
  
  





  /* operators */
  ","                            { return symbol(ParserSym.COMMA); }
  "+"                            { return symbol(ParserSym.PLUS); }
  "-"							 { return symbol(ParserSym.MINUS);}
  
   ","							 { return symbol(ParserSym.COMMA);}
   
   
   /* "."					         { return symbol(ParserSym.DOT);}*/		 	
  

  /* comments */
  {Comment}                      { /* ignore */ }
 
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

<STRING> {
  \"                             { yybegin(YYINITIAL); 
                                   return symbol(ParserSym.STRING_LITERAL, 
                                   string.toString()); }
  [^\n\r\"\\]+                   { string.append( yytext() ); }
  \\t                            { string.append('\t'); }
  \\n                            { string.append('\n'); }

  \\r                            { string.append('\r'); }
  \\\"                           { string.append('\"'); }
  \\                             { string.append('\\'); }
}

/* error fallback */
.|\n                             { throw new Error("Illegal character <"+
                                                    yytext()+">"); }
                                                    
                                                    
                                                    





