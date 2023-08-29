package yaBJ.SharedUtils.IO;

public class Style {
    public static final String BOLD = "\033[1m";
    public static final String NODIMBOLD = "\033[22m";
    public static final String DIM = "\033[2m";
    public static final String ITALIC = "\033[3m";
    public static final String NOITALIC = "\033[23m";
    public static final String UNDERLINE = "\033[4m";
    public static final String NOUNDERLINE = "\033[24m";
    public static final String BLINK = "\033[5m";
    public static final String NOBLINK = "\033[25m";
    public static final String INVERSE = "\033[7m";
    public static final String NOINVERSE = "\033[27m";
    public static final String HIDDEN = "\033[8m";
    public static final String NOHIDDEN = "\033[28m";
    public static final String STRIKETHROUGH = "\033[9m";
    public static final String NOSTRIKETHROUGH = "\033[29m";

    public static String bold(String text){ return Style.BOLD + text + Style.NODIMBOLD; }
    public static String dim(String text){ return Style.DIM + text + Style.NODIMBOLD; }
    public static String italic(String text){ return Style.ITALIC + text + Style.NOITALIC; }
    public static String underline(String text){ return Style.UNDERLINE + text + Style.NOUNDERLINE; }
    public static String blink(String text){ return Style.BLINK + text + Style.NOBLINK; }
    public static String inverse(String text){ return Style.INVERSE + text + Style.NOINVERSE; }
    public static String hidden(String text){ return Style.HIDDEN + text + Style.NOHIDDEN; }
    public static String strikethrough(String text){ return Style.STRIKETHROUGH + text + Style.NOSTRIKETHROUGH; }
}
