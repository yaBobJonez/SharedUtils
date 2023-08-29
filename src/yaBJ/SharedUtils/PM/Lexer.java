package yaBJ.SharedUtils.PM;

import java.util.ArrayList;

class Lexer {
    static String input;
    static int pos;
    public static ArrayList<Token> tokenize(String input){
        Lexer.input = input;
        Lexer.pos = 0;
        ArrayList<Token> tokens = new ArrayList<>();
        while(pos < input.length()){
            char c = at(0);
            if(Character.isWhitespace(c)) ++pos;
            else if(c == ','){ tokens.add(new Token(TokenTypes.Comma, "")); ++pos; }
            else if(c == '\''){ tokens.add(buildString()); ++pos; }
            else if("-0123456789".indexOf(c) != -1) tokens.add(buildNumber());
            else if(".&|!<=>%():".indexOf(c) != -1) tokens.add(buildOperator());
            else if(Character.isJavaIdentifierStart(c)) tokens.add(buildWord());
            else throw new RuntimeException("Unrecognized character "+input.charAt(pos));
        } return tokens;
    }
    static char at(int rel){
        if(pos+rel >= input.length()) return '\0';
        else return input.charAt(pos+rel);
    }
    static Token buildString(){
        String s = "";
        while(++pos < input.length() && at(0) != '\''){
            if(at(0) == '\\' && "\\'".indexOf(at(1)) != -1)
                s += input.charAt(++pos);
            else s += at(0);
        } return new Token(TokenTypes.String, s);
    }
    static Token buildNumber(){
        String s = String.valueOf(at(0)); ++pos;
        boolean isFloatingPoint = false;
        while("0123456789.".indexOf(at(0)) != -1){
            if(at(0) == '.')
                if("0123456789".indexOf(at(1)) == -1 || isFloatingPoint) break;
                else isFloatingPoint = true;
            s += input.charAt(pos++);
        } return new Token(isFloatingPoint? TokenTypes.Float : TokenTypes.Int, s);
    }
    static Token buildWord(){
        String s = "";
        while(Character.isJavaIdentifierPart(at(0)) && !Character.isIdentifierIgnorable(at(0)))
            s += input.charAt(pos++);
        if(s.equals("true") || s.equals("false")) return new Token(TokenTypes.Boolean, s);
        else return new Token(TokenTypes.ID, s);
    }
    static Token buildOperator(){
        String s = ""; s += input.charAt(pos++);
        if(s.equals("(")) return new Token(TokenTypes.Lpar, "");
        else if(s.equals(")")) return new Token(TokenTypes.Rpar, "");
        else if(s.equals(":")) return new Token(TokenTypes.Colon, "");
        else if(s.equals("&")) return new Token(TokenTypes.And, "");
        else if(s.equals("|")) return new Token(TokenTypes.Or, "");
        else if(s.equals("!")) return new Token(TokenTypes.Not, "");
        else if(s.equals("%")) return new Token(TokenTypes.Modulo, "");
        if (".=".indexOf(at(0)) == -1){
            if(s.equals("<")) return new Token(TokenTypes.Lt, "");
            else if(s.equals(">")) return new Token(TokenTypes.Gt, "");
            else throw new RuntimeException("Unrecognized operator "+s);
        } s += input.charAt(pos++);
        if(s.equals("<=")) return new Token(TokenTypes.LtEq, "");
        else if(s.equals(">=")) return new Token(TokenTypes.GtEq, "");
        if (".<".indexOf(at(0)) == -1){
            if(s.equals("..")) return new Token(TokenTypes.Range, "");
            else throw new RuntimeException("Unrecognized operator "+s);
        } s += input.charAt(pos++);
        if(s.equals("...")) return new Token(TokenTypes.Rest, "");
        else if(s.equals("..<")) return new Token(TokenTypes.RangeROpen, "");
        else throw new RuntimeException("Unrecognized operator "+s);
    }
}
