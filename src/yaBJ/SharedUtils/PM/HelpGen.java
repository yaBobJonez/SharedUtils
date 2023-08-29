package yaBJ.SharedUtils.PM;

public class HelpGen {
    static String generateUsageUnit(Pattern p){
        String help = "";
        if(p instanceof StringPattern v) help += v.value;
        else if(p instanceof IntPattern v) help += v.value;
        else if(p instanceof FloatPattern v) help += v.value;
        else if(p instanceof BooleanPattern v) help += v.value;
        else if(p instanceof BindingPattern v) help += "<"+v.var+">";
        else if(p instanceof RestPattern v) help += "...";
        else if(p instanceof BinOpPattern v){
            if(v.op == TokenTypes.Or) help += generateUsageUnit(v.left)+"|"+generateUsageUnit(v.right);
            else if(v.op == TokenTypes.And) help += generateUsageUnit(v.left);
            else if(v.op == TokenTypes.Range) help += "<(float)>";
        } else if(p instanceof UnOpPattern v){
            if(v.op == TokenTypes.Not) help += "\u2612"+generateUsageUnit(v.right);
            else if(v.op == TokenTypes.Lt || v.op == TokenTypes.LtEq
                || v.op == TokenTypes.Gt || v.op == TokenTypes.GtEq) help += "<(float)>";
            else if(v.op == TokenTypes.Modulo) help += "<(int)>";
        } return help;
    }
    static String generateValidValuesUnit(Pattern p){
        if(p instanceof BindingPattern v){
            String cots = "";
            if(v.constraints != null){
                cots = ", "+generateValidValuesUnit(v.constraints);
                if(cots.equals(", ")) cots += generateRepresentation(v.constraints);
            } return "<"+v.var+">: " + v.type + cots;
        } else if(p instanceof BinOpPattern v){
            if(v.op == TokenTypes.And){
                if(v.left instanceof BinOpPattern)
                    return generateValidValuesUnit(v.left)+", "+generateRepresentation(v.right);
                else return generateRepresentation(v.left)+", "+generateRepresentation(v.right);
            } else if(v.op == TokenTypes.Or){
                String left = generateValidValuesUnit(v.left);
                String right = generateValidValuesUnit(v.right);
                return left.equals("")||right.equals("")? "" : left+" | "+right;
            } else if(v.op == TokenTypes.Range) return generateRepresentation(v);
        } else if(p instanceof UnOpPattern v){
            String part = "<("+Case.i18n.getString("value")+")>";
            if(v.op == TokenTypes.Lt) part += " < ";
            else if(v.op == TokenTypes.LtEq) part += " \u2264 ";
            else if(v.op == TokenTypes.Gt) part += " > ";
            else if(v.op == TokenTypes.GtEq) part += " \u2265 ";
            else if(v.op == TokenTypes.Modulo) part += " : ";
            else return "";
            return part + generateRepresentation(v.right);
        } return "";
    }
    static String generateRepresentation(Pattern p){
        if(p instanceof StringPattern v) return v.value;
        else if(p instanceof IntPattern v) return String.valueOf(v.value);
        else if(p instanceof FloatPattern v) return String.valueOf(v.value);
        else if(p instanceof BooleanPattern v) return String.valueOf(v.value);
        else if(p instanceof BindingPattern v) return v.type+" "+v.var;
        else if(p instanceof RestPattern v) return  "...";
        else if(p instanceof BinOpPattern v){
            String part = generateRepresentation(v.left);
            if(v.op == TokenTypes.Or) part += " | ";
            else if(v.op == TokenTypes.And) part += " & ";
            else if(v.op == TokenTypes.Range) part += "\u2013";
            return part + generateRepresentation(v.right);
        } else if(p instanceof UnOpPattern v){
            String right = generateRepresentation(v.right);
            if(v.op == TokenTypes.Not){
                if( right.equals(Case.i18n.getString("even")) )
                    return Case.i18n.getString("odd");
                else return "\u2612 "+right;
            } else if(v.op == TokenTypes.Lt) return "< "+right;
            else if(v.op == TokenTypes.LtEq) return "\u2264 "+right;
            else if(v.op == TokenTypes.Gt) return "> "+right;
            else if(v.op == TokenTypes.GtEq) return "\u2265 "+right;
            else if(v.op == TokenTypes.Modulo){
                if(right.equals("2") || right.equals("2.0"))
                    return Case.i18n.getString("even");
                else return right+"k";
            } return right;
        } return "";
    }
}
