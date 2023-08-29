package yaBJ.SharedUtils.IO;

public class Screen {
    public static String home(){ return "\033[H"; }
    public static String to(int row, int col){ return String.format("\033[%d;%df", row, col); }
    public static String up(int n){ return String.format("\033[%dA", n); }
    public static String down(int n){ return String.format("\033[%dB", n); }
    public static String right(int n){ return String.format("\033[%dC", n); }
    public static String left(int n){ return String.format("\033[%dD", n); }
    public static String clear(){ return "\033[2J"; }
    public static String clearLine(){ return "\033[2K"; }
}
