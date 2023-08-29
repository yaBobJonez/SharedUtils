package yaBJ.SharedUtils.I18N;

import java.util.HashMap;

public class CategoryRules {
    private static boolean in(double n, double from, double to){ return n >= from && n <= to; }
    static HashMap<String, NumberCategorizer> cardinal = new HashMap<>();
    static {
        cardinal.put("", n -> NumCat.OTHER);
        cardinal.put("en", n -> n==1? NumCat.ONE : NumCat.OTHER);
        cardinal.put("ru", n -> {
            if(n%1==0) {
                int i = (int) n;
                if (i%10==1 && i%100!=11) return NumCat.ONE;
                else if (in(i%10, 2, 4) && !in(i%100, 12, 14)) return NumCat.FEW;
                else return NumCat.MANY;
            } else return NumCat.OTHER;
        });
        cardinal.put("uk", cardinal.get("ru"));
    }

    static HashMap<String, NumberCategorizer> ordinal = new HashMap<>();
    static {
        ordinal.put("", n -> NumCat.OTHER);
        ordinal.put("en", n -> {
            int i = (int) n;
            if(i%10==1 && i%100!=11) return NumCat.ONE;
            else if(i%10==2 && i%100!=12) return NumCat.TWO;
            else if(i%10==3 && i%100!=13) return NumCat.FEW;
            else return NumCat.OTHER;
        });
        ordinal.put("ru", n -> NumCat.OTHER);
        ordinal.put("uk", n -> (n%10==3 && n%100!=13)? NumCat.FEW : NumCat.OTHER);
    }
}
