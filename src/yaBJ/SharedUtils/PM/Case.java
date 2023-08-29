package yaBJ.SharedUtils.PM;

import yaBJ.SharedUtils.TUI.SU2Conf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class Case {
    public static ResourceBundle i18n = ResourceBundle.getBundle("I18n");
    ArrayList<Pattern> patterns;
    OnMatch lambda;
    String description;
    public static Case of(String pattern, OnMatch lambda){ return of(pattern, "", lambda); }
    public static Case of(String pattern, String description, OnMatch lambda){
        Case obj = new Case();
        obj.patterns = Parser.parse(Lexer.tokenize(pattern));
        obj.lambda = lambda;
        obj.description = description;
        return obj;
    }
    public boolean tryMatch(String[] data){
        if(patterns.get(patterns.size()-1) instanceof RestPattern)
            if(data.length > patterns.size()){
                String[] temp = new String[patterns.size()];
                for(int i = 0; i < patterns.size()-1; i++) temp[i] = data[i];
                temp[patterns.size()-1] = String.join(" ", Arrays.copyOfRange(data, patterns.size()-1, data.length));
                data = temp;
            }
        if(data.length != patterns.size()) return false;
        Variables vars = new Variables();
        for(int i = 0; i < data.length; i++){
            if(!patterns.get(i).match(data[i], vars)) return false;
        } this.lambda.exec(vars);
        return true;
    }

    public static void match(String value, Case... cases){
        String[] list = value.split(String.join("", SU2Conf.matchSeparator));
        for (Case c : cases) if (c.tryMatch(list)) return;
        for (Case c : cases) c.generateHelp();
    }

    public String generateHelp(){
        String s = "\t";
        StringJoiner sj = new StringJoiner(SU2Conf.matchSeparator[1]);
        for(Pattern p : patterns) sj.add(HelpGen.generateUsageUnit(p));
        s += sj.toString();
        s += (description.equals("")? "" : " \u2014 "+description) +"\n";
        for(int i = 0; i < patterns.size(); i++){
            String restrictions = HelpGen.generateValidValuesUnit(patterns.get(i));
            if(restrictions.equals("")) continue;
            s += "\t\t"+i+". "+restrictions+"\n";
        } return s;
    }
}
