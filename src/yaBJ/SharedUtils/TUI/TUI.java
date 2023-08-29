package yaBJ.SharedUtils.TUI;

import yaBJ.SharedUtils.PM.Case;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TUI {
    List<Case> cases = new ArrayList<>();
    String help = Case.i18n.getString("Usage")+"\n";
    void printHelp(){ System.out.print(this.help); }
    public static void mainLoop(ValueFunction inputRetriever, Case... cases){
        TUI instance = new TUI();
        instance.cases.addAll(Arrays.asList(cases));
        boolean autohelp = SU2Conf.generateAutohelp;
        if(SU2Conf.generateAutoexit)
            instance.cases.add( Case.of(SU2Conf.exitOption, Case.i18n.getString("leaveTheProgram"), e -> System.exit(0)) );
        if(autohelp){
            instance.cases.add( Case.of("'help'", Case.i18n.getString("seeUsage"), e -> instance.printHelp()) );
            for(Case c : instance.cases) instance.help += c.generateHelp();
        } while(true){
            String[] input = inputRetriever.getInput().split(String.join("", SU2Conf.matchSeparator));
            boolean matched = false;
            for(Case c : instance.cases) if(c.tryMatch(input))
                { matched = true; break; }
            if(matched)
                if(SU2Conf.breakLoopOnMatch) break;
                else continue;
            if(SU2Conf.warnOnNoMatch)
                System.err.println(Case.i18n.getString(autohelp? "writeHelpForUsage" : "incorrectSyntax"));
            if(SU2Conf.breakLoopOnNoMatch) break;
        }
    }
}
