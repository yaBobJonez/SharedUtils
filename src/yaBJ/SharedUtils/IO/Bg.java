package yaBJ.SharedUtils.IO;

public class Bg {
    public static final String NONE = "\033[49m";
    public static final String BLACK = "\033[40m";
    public static final String RED = "\033[41m";
    public static final String GREEN = "\033[42m";
    public static final String YELLOW = "\033[43m";
    public static final String BLUE = "\033[44m";
    public static final String MAGENTA = "\033[45m";
    public static final String CYAN = "\033[46m";
    public static final String WHITE = "\033[47m";

    public static String black(String text){ return Bg.BLACK + text + Bg.NONE; }
    public static String red(String text){ return Bg.RED + text + Bg.NONE; }
    public static String green(String text){ return Bg.GREEN + text + Bg.NONE; }
    public static String yellow(String text){ return Bg.YELLOW + text + Bg.NONE; }
    public static String blue(String text){ return Bg.BLUE + text + Bg.NONE; }
    public static String magenta(String text){ return Bg.MAGENTA + text + Bg.NONE; }
    public static String cyan(String text){ return Bg.CYAN + text + Bg.NONE; }
    public static String white(String text){ return Bg.WHITE + text + Bg.NONE; }
    public static String color(String hex, String text){
        int red = Integer.valueOf(hex.substring(0, 2), 16);
        int green = Integer.valueOf(hex.substring(2, 4), 16);
        int blue = Integer.valueOf(hex.substring(4, 6), 16);
        return String.format("\033[48;2;%d;%d;%dm%s%s", red, green, blue, text, Bg.NONE);
    }
}
