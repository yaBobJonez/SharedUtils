package yaBJ.SharedUtils.PM;

public interface Pattern {
    boolean match(String other, Variables vars);
}

class StringPattern implements Pattern {
    String value;

    public StringPattern(String value) {
        this.value = value;
    }

    public boolean match(String other, Variables vars) {
        return other.equals(this.value);
    }
}

class IntPattern implements Pattern {
    int value;

    public IntPattern(String value) {
        this.value = Integer.parseInt(value);
    }

    public boolean match(String other, Variables vars) {
        try {
            return Integer.parseInt(other) == this.value;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class FloatPattern implements Pattern {
    double value;

    public FloatPattern(String value) {
        this.value = Double.parseDouble(value);
    }

    public boolean match(String other, Variables vars) {
        try {
            return Double.parseDouble(other) == this.value;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class BooleanPattern implements Pattern {
    boolean value;

    public BooleanPattern(String value) {
        this.value = value.equals("true");
    }

    public boolean match(String other, Variables vars) {
        return this.value ? other.matches("(true)|1|y") : other.matches("(false)|0|n");
    }
}

class BindingPattern implements Pattern {
    String type;
    String var;
    Pattern constraints;

    public BindingPattern(String t, String v, Pattern c) {
        this.type = t;
        this.var = v;
        this.constraints = c;
    }

    public boolean match(String other, Variables vars) {
        if (!this.typeCheck(other)) return false;
        if (this.constraints != null)
            if (!this.constraints.match(other, vars)) return false;
        if (this.type.equals("boolean")) vars.booleans.put(this.var, other.matches("(true)|1|y"));
        else if (this.type.equals("int")) vars.ints.put(this.var, Integer.parseInt(other));
        else if (this.type.equals("double") || this.type.equals("float"))
            vars.floats.put(this.var, Double.parseDouble(other));
        else if (this.type.equals("char")) vars.chars.put(this.var, other.charAt(0));
        else if (this.type.equals("String")) vars.strings.put(this.var, other);
        return true;
    }
    boolean typeCheck(String other){
        if (type.equals("boolean")) return other.matches("(true)|(false)|1|0|y|n");
        else if (type.equals("int")) return other.matches("\\d+");
        else if (type.equals("double") || type.equals("float")) return other.matches("\\d+(\\.\\d+)?");
        else if (type.equals("char")) return other.length() == 1;
        else if (type.equals("String")) return true;
        else return false;
    }
}

class RestPattern implements Pattern {
    public boolean match(String other, Variables vars) {
        vars.rest = other.split(" ");
        return true;
    }
}

class BinOpPattern implements Pattern {
    TokenTypes op;
    Pattern left, right;

    public BinOpPattern(TokenTypes op, Pattern left, Pattern right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public boolean match(String other, Variables vars) {
        if (op == TokenTypes.And) return left.match(other, vars) && right.match(other, vars);
        else if (op == TokenTypes.Or) return left.match(other, vars) || right.match(other, vars);
        else if (op == TokenTypes.Range) {
            try {
                double val = Double.parseDouble(other);
                double left, right;
                if (this.left instanceof FloatPattern p) left = p.value;
                else if (this.left instanceof IntPattern p) left = p.value;
                else return false;
                if (this.right instanceof FloatPattern p) right = p.value;
                else if (this.right instanceof IntPattern p) right = p.value;
                else return false;
                return left <= val && val <= right;
            } catch(NumberFormatException e){ return false; }
        } else return false;
    }
}

class UnOpPattern implements Pattern {
    TokenTypes op;
    Pattern right;

    public UnOpPattern(TokenTypes op, Pattern right) {
        this.op = op;
        this.right = right;
    }

    public boolean match(String other, Variables vars) {
        if (op == TokenTypes.Not) return !right.match(other, vars);
        try {
            double val = Double.parseDouble(other);
            double r;
            if (right instanceof FloatPattern p) r = p.value;
            else if (right instanceof IntPattern p) r = p.value;
            else return false;
            if (op == TokenTypes.Lt) return val < r;
            else if (op == TokenTypes.LtEq) return val <= r;
            else if (op == TokenTypes.Gt) return val > r;
            else if (op == TokenTypes.GtEq) return val >= r;
            else if (op == TokenTypes.Modulo) return val % r == 0;
        } catch(NumberFormatException e){}
        return false;
    }
}
