package yaBJ.SharedUtils.PM;

import java.util.ArrayList;
import java.util.HashMap;

public class Variables {
    HashMap<String, Integer> ints = new HashMap<>();
    HashMap<String, Double> floats = new HashMap<>();
    HashMap<String, Character> chars = new HashMap<>();
    HashMap<String, String> strings = new HashMap<>();
    HashMap<String, Boolean> booleans = new HashMap<>();
    String[] rest = new String[0];
    public int Int(String key){ return ints.get(key); }
    public double Float(String key){ return floats.get(key); }
    public String String(String key){ return strings.get(key); }
    public boolean Boolean(String key){ return booleans.get(key); }
    public String[] Rest(){ return rest; }
}
