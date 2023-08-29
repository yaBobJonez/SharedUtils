package yaBJ.SharedUtils.PM;

import java.util.ArrayList;

class Parser {
    static ArrayList<Token> tokens;
    static int pos;
    public static ArrayList<Pattern> parse(ArrayList<Token> tokens){
        Parser.tokens = tokens;
        Parser.pos = 0;
        ArrayList<Pattern> pats = new ArrayList<>();
        while(pos < tokens.size()){
            Pattern pat = disjunction();
            pats.add(pat);
            if(pat instanceof RestPattern) break;
            Parser.matches(TokenTypes.Comma);
        } return pats;
    }
    static Pattern disjunction(){
        Pattern left = conjunction();
        while(matches(TokenTypes.Or)){
            left = new BinOpPattern(TokenTypes.Or, left, conjunction());
        } return left;
    }
    static Pattern conjunction(){
        Pattern left = range();
        while(matches(TokenTypes.And)){
            left = new BinOpPattern(TokenTypes.And, left, range());
        } return left;
    }
    static Pattern range(){
        Pattern left = unary();
        while(true){
            if(matches(TokenTypes.Range)) left = new BinOpPattern(TokenTypes.Range, left, unary());
            else if(matches(TokenTypes.RangeROpen))
                left = new BinOpPattern(TokenTypes.And,
                        new UnOpPattern(TokenTypes.GtEq, left),
                        new UnOpPattern(TokenTypes.Lt, unary()));
            else break;
        } return left;
    }
    static Pattern unary(){
        if(matches(TokenTypes.Not)) return new UnOpPattern(TokenTypes.Not, unary());
        else if(matches(TokenTypes.Lt)) return new UnOpPattern(TokenTypes.Lt, primary());
        else if(matches(TokenTypes.LtEq)) return new UnOpPattern(TokenTypes.LtEq, primary());
        else if(matches(TokenTypes.Gt)) return new UnOpPattern(TokenTypes.Gt, primary());
        else if(matches(TokenTypes.GtEq)) return new UnOpPattern(TokenTypes.GtEq, primary());
        else if(matches(TokenTypes.Modulo)) return new UnOpPattern(TokenTypes.Modulo, primary());
        else return primary();
    }
    static Pattern primary(){
        Token curr = tokens.get(pos);
        if(matches(TokenTypes.String)) return new StringPattern(curr.value);
        else if(matches(TokenTypes.Int)) return new IntPattern(curr.value);
        else if(matches(TokenTypes.Float)) return new FloatPattern(curr.value);
        else if(matches(TokenTypes.Boolean)) return new BooleanPattern(curr.value);
        else if(matches(TokenTypes.ID)){
            String value = consume(TokenTypes.ID).value;
            Pattern constraints = matches(TokenTypes.Colon)? disjunction() : null;
            return new BindingPattern(curr.value, value, constraints);
        } else if(matches(TokenTypes.Rest)) return new RestPattern();
        else if(matches(TokenTypes.Lpar)){
            Pattern inside = disjunction();
            consume(TokenTypes.Rpar);
            return inside;
        } else throw new RuntimeException("Unexpected token "+curr.type);
    }
    static boolean matches(TokenTypes type){
        if(pos < tokens.size() && tokens.get(pos).type == type){ ++pos; return true; }
        else return false;
    }
    static Token consume(TokenTypes type){
        if(pos >= tokens.size()) throw new RuntimeException("Expected "+type);
        Token curr = tokens.get(pos++);
        if(curr.type != type) throw new RuntimeException("Expected "+type);
        return curr;
    }
}
