package yaBJ.SharedUtils.IO;

public class Fg {
    public static final String NONE = "\033[39m";
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    public static String black(String text){ return Fg.BLACK + text + Fg.NONE; }
    public static String red(String text){ return Fg.RED + text + Fg.NONE; }
    public static String green(String text){ return Fg.GREEN + text + Fg.NONE; }
    public static String yellow(String text){ return Fg.YELLOW + text + Fg.NONE; }
    public static String blue(String text){ return Fg.BLUE + text + Fg.NONE; }
    public static String magenta(String text){ return Fg.MAGENTA + text + Fg.NONE; }
    public static String cyan(String text){ return Fg.CYAN + text + Fg.NONE; }
    public static String white(String text){ return Fg.WHITE + text + Fg.NONE; }
    public static String color(String hex, String text){
        int red = Integer.valueOf(hex.substring(0, 2), 16);
        int green = Integer.valueOf(hex.substring(2, 4), 16);
        int blue = Integer.valueOf(hex.substring(4, 6), 16);
        return String.format("\033[38;2;%d;%d;%dm%s%s", red, green, blue, text, Fg.NONE);
    }
}
