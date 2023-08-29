package yaBJ.SharedUtils.IO;

public class Graph {
    public static String circle(double x, double y, double r, double S, double c){
        r = Math.abs(r); S = Math.abs(S); c = Math.abs(c);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("             #######            \n"));
        sb.append(String.format("        ###          ###        \n"));
        sb.append(String.format("    ###                  ###    \n"));
        sb.append(String.format("  ##     %-11s        ##  \n"      , Double.isNaN(S)? "" : String.format("%.2f", S)));
        sb.append(String.format(" #                            # \n"));
        sb.append(String.format("#                 %11s  #\n"       , Double.isNaN(r)? "" : String.format("%.2f", r)));
        sb.append(String.format("#               O-------r------#\n"));
        if(Double.isNaN(x) || Double.isNaN(y))
            sb.append(String.format("#                              #\n"));
        else
            sb.append(String.format("#   %12s;%-12s  #\n"           , String.format("(%.2f", x), String.format("%.2f)", y)));
        sb.append(String.format(" #                            # \n"));
        sb.append(String.format("  ##                        ##  \n"));
        sb.append(String.format("    ###                  ###    \n"));
        sb.append(String.format("        ###          ### %s\n"     , Double.isNaN(c)? "" : String.format("%.2f", c)));
        sb.append(String.format("            ########            \n"));
        return sb.toString();
    }
    public static String ellipse(double cx, double cy, double f1x, double f1y, double f2x, double f2y,
                                 double major, double minor, double d2f, double area, double perim){
        major = Math.abs(major); minor = Math.abs(minor); d2f = Math.abs(d2f); area = Math.abs(area); perim = Math.abs(perim);
        if(f1x==f2x && f1y==f2y || major==minor || d2f==0.0)
            return Graph.circle(cx, cy, major, area, perim);
        if(minor > major)
            { double t = minor; minor = major; major = t; }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("                       #############                       \n"));
        sb.append(String.format("                #####        |        #####                \n"));
        sb.append(String.format("           ####              |              ####           \n"));
        sb.append(String.format("       ###                   m %-12s      ###       \n"       , Double.isNaN(minor)? "" : String.format("%.2f", minor)));
        sb.append(String.format("   ###                       |                       ###   \n"));
        sb.append(String.format(" ##             %-12s |         %12s     ## \n"               ,
                Double.isNaN(d2f)? "" : String.format("%.2f", d2f),
                Double.isNaN(major)? "" : String.format("%.2f", major) ));
        sb.append(String.format("#             f-------d------O==============f======M======#\n"));
        String focus1 = Double.isNaN(f1x)||Double.isNaN(f1y)? "               " : String.format("%7s;%-7s", String.format("(%.2f", f1x), String.format("%.2f)", f1y));
        String focus2 = Double.isNaN(f2x)||Double.isNaN(f2y)? "               " : String.format("%7s;%-7s", String.format("(%.2f", f2x), String.format("%.2f)", f2y));
        String center = Double.isNaN(cx)||Double.isNaN(cy)? "               " : String.format("%7s;%-7s", String.format("(%.2f", cx), String.format("%.2f)", cy));
        sb.append(String.format(" ##    %s%s%s    ## \n", focus1, center, focus2));
        sb.append(String.format("   ###                                               ###   \n"));
        sb.append(String.format("       ###             %-13s             ###       \n"        , Double.isNaN(area)? "" : String.format("%.2f", area)));
        sb.append(String.format("           ####                             ####           \n"));
        sb.append(String.format("                #####                 #####  %-12s  \n"       , Double.isNaN(perim)? "" : String.format("%.2f", perim)));
        sb.append(String.format("                       #############                       \n"));
        return sb.toString();
    } public static String oval(double cx, double cy, double f1x, double f1y, double f2x, double f2y,
                                   double major, double minor, double d2f, double area, double perim){
        return ellipse(cx, cy, f1x, f1y, f2x, f2y, major, minor, d2f, area, perim);
    }
    public static String triangle(double x1, double y1, double x2, double y2, double x3, double y3,
                                  double a, double b, double c, double A, double B, double C, double area){
        a = Math.abs(a); b = Math.abs(b); c = Math.abs(c); A = Math.abs(A); B = Math.abs(B); C = Math.abs(C);
        String pointA = Double.isNaN(x1)||Double.isNaN(y1)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x1), String.format("%.2f)", y1));
        String pointB = Double.isNaN(x2)||Double.isNaN(y2)? " ".repeat(27) : String.format("%13s;%-13s", String.format("(%.2f", x2), String.format("%.2f)", y2));
        String pointC = Double.isNaN(x3)||Double.isNaN(y3)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x3), String.format("%.2f)", y3));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("        %s                                   \n"                         , pointB));
        sb.append(String.format("                    #B##                                              \n"));
        sb.append(String.format("                   #%6s##                                          \n"   , Double.isNaN(B)? "" : String.format("%.1f\u00b0", B)));
        sb.append(String.format("                  #           ##                                      \n"));
        sb.append(String.format("                 #                ##                                  \n"));
        sb.append(String.format("  %13s a                     #b %-13s                \n"                 ,
                Double.isNaN(a)? "" : String.format("%.2f", a),
                Double.isNaN(b)? "" : String.format("%.2f", b) ));
        sb.append(String.format("               #                          ##                          \n"));
        sb.append(String.format("              #       %-13s           ##                      \n"        , Double.isNaN(area)? "" : String.format("%.2f", area)));
        sb.append(String.format("             #                                    ##                  \n"));
        sb.append(String.format("            # %-6s                           %6s ##              \n"     ,
                Double.isNaN(A)? "" : String.format("%.1f\u00b0", A),
                Double.isNaN(C)? "" : String.format("%.1f\u00b0", C) ));
        sb.append(String.format("           A######################c#######################C           \n"));
        sb.append(String.format("%s      %-13s     %s\n"                                                  , pointA, Double.isNaN(c)? "" : String.format("%.2f", c), pointC));
        return sb.toString();
    }
    public static String trapezium(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4,
                                   double a, double b, double c, double d, double A, double B, double C, double D, double midseg, double area){
        a = Math.abs(a); b = Math.abs(b); c = Math.abs(c); d = Math.abs(d);
        A = Math.abs(A); B = Math.abs(B); C = Math.abs(C); D = Math.abs(D); midseg = Math.abs(midseg); area = Math.abs(area);
        String pointA = Double.isNaN(x1)||Double.isNaN(y1)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x1), String.format("%.2f)", y1));
        String pointB = Double.isNaN(x2)||Double.isNaN(y2)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x2), String.format("%.2f)", y2));
        String pointC = Double.isNaN(x3)||Double.isNaN(y3)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x3), String.format("%.2f)", y3));
        String pointD = Double.isNaN(x4)||Double.isNaN(y4)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x4), String.format("%.2f)", y4));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("         %s%-11s%s                  \n"                                                 , pointB, Double.isNaN(a)? "" : String.format("%.2f", a), pointC));
        sb.append(String.format("                    B################a################C                              \n"));
        sb.append(String.format("                   # %-6s                      %6s #                            \n"     ,
                Double.isNaN(B)? "" : String.format("%.1f\u00b0", B), Double.isNaN(C)? "" : String.format("%.1f\u00b0", C) ));
        sb.append(String.format("                  #                                       #                          \n"));
        sb.append(String.format("                 #                                          #                        \n"));
        sb.append(String.format("                c----------------------m----------------------d                      \n"));
        sb.append(String.format("   %11s #                %-13s                   # %-11s        \n"                     ,
                Double.isNaN(c)? "" : String.format("%.2f", c),
                Double.isNaN(midseg)? "" : String.format("%.2f", midseg),
                Double.isNaN(d)? "" : String.format("%.2f", d) ));
        sb.append(String.format("              #                                                   #                  \n"));
        sb.append(String.format("             #                   %-13s                      #                \n"        , Double.isNaN(area)? "" : String.format("%.2f", area)));
        sb.append(String.format("            # %-6s                                           %6s #              \n"     ,
                Double.isNaN(A)? "" : String.format("%.1f\u00b0", A), Double.isNaN(D)? "" : String.format("%.1f\u00b0", D) ));
        sb.append(String.format("           A#############################b##############################D            \n"));
        sb.append(String.format("%s             %-11s              %s\n"                                                 , pointA, Double.isNaN(b)? "" : String.format("%.2f", b), pointD));
        return sb.toString();
    } public static String trapezoid(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4,
                                     double a, double b, double c, double d, double A, double B, double C, double D, double midseg, double area){
        return trapezium(x1, y1, x2, y2, x3, y3, x4, y4, a, b, c, d, A, B, C, D, midseg, area);
    }
    public static String parallelogram(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4,
                                       double a, double b, double A, double B, double area){
        a = Math.abs(a); b = Math.abs(b); A = Math.abs(A); B = Math.abs(B); area =  Math.abs(area);
        if(A==90 || B==90)
            return rectangle(x1, y1, x2, y2, x3, y3, x4, y4, a, b, area);
        String pointA = Double.isNaN(x1)||Double.isNaN(y1)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x1), String.format("%.2f)", y1));
        String pointB = Double.isNaN(x2)||Double.isNaN(y2)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x2), String.format("%.2f)", y2));
        String pointC = Double.isNaN(x3)||Double.isNaN(y3)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x3), String.format("%.2f)", y3));
        String pointD = Double.isNaN(x4)||Double.isNaN(y4)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x4), String.format("%.2f)", y4));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("        %s           %s\n"                                          , pointB, pointC));
        sb.append(String.format("                   B#################################C           \n"));
        sb.append(String.format("                  # %-6s                          #            \n"  , Double.isNaN(B)? "" : String.format("%.1f\u00b0", B)));
        sb.append(String.format("                 #                                 #             \n"));
        sb.append(String.format("                #                                 #              \n"));
        sb.append(String.format("   %11s b          %-11s            #               \n"             ,
                Double.isNaN(b)? "" : String.format("%.2f", b), Double.isNaN(area)? "" : String.format("%.2f", area) ));
        sb.append(String.format("              #                                 #                \n"));
        sb.append(String.format("             #                                 #                 \n"));
        sb.append(String.format("            # %-6s                          #                  \n"  , Double.isNaN(A)? "" : String.format("%.1f\u00b0", A)));
        sb.append(String.format("           A################a################D                   \n"));
        sb.append(String.format("%s%-11s%s        \n"                                                , pointA, Double.isNaN(a)? "" : String.format("%.2f", a), pointD));
        return sb.toString();
    }
    public static String rhombus(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4,
                                 double a, double A, double B, double area){
        a = Math.abs(a); A = Math.abs(A); B = Math.abs(B); area =  Math.abs(area);
        if(A==90 || B==90)
            return square(x1, y1, x2, y2, x3, y3, x4, y4, a, area);
        String pointA = Double.isNaN(x1)||Double.isNaN(y1)? "" : String.format("(%.2f;%.2f)", x1, y1);
        String pointB = Double.isNaN(x2)||Double.isNaN(y2)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x2), String.format("%.2f)", y2));
        String pointC = Double.isNaN(x3)||Double.isNaN(y3)? "" : String.format("(%.2f;%.2f)", x3, y3);
        String pointD = Double.isNaN(x4)||Double.isNaN(y4)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x4), String.format("%.2f)", y4));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("                           %s                           \n"                     , pointB));
        sb.append(String.format("                                    ##B##                                    \n"));
        sb.append(String.format("                    %11s a## %6s###                                \n"          ,
                Double.isNaN(a)? "" : String.format("%.2f", a), Double.isNaN(B)? "" : String.format("%.1f\u00b0", B) ));
        sb.append(String.format("                            ###               ###                            \n"));
        sb.append(String.format("%23s A## %-6s %-11s    ##C %-23s\n"                                             ,
                pointA, Double.isNaN(A)? "" : String.format("%.1f\u00b0", A), Double.isNaN(area)? "" : String.format("%.2f", area), pointC ));
        sb.append(String.format("                            ###               ###                            \n"));
        sb.append(String.format("                                ###       ###                                \n"));
        sb.append(String.format("                                    ##D##                                    \n"));
        sb.append(String.format("                           %s                           \n"                     , pointD));
        return sb.toString();
    }
    public static String rectangle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4,
                                   double a, double b, double area){
        a = Math.abs(a); b = Math.abs(b); area =  Math.abs(area);
        if(a == b)
            return square(x1, y1, x2, y2, x3, y3, x4, y4, a, area);
        String pointA = Double.isNaN(x1)||Double.isNaN(y1)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x1), String.format("%.2f)", y1));
        String pointB = Double.isNaN(x2)||Double.isNaN(y2)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x2), String.format("%.2f)", y2));
        String pointC = Double.isNaN(x3)||Double.isNaN(y3)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x3), String.format("%.2f)", y3));
        String pointD = Double.isNaN(x4)||Double.isNaN(y4)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x4), String.format("%.2f)", y4));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" %s           %s\n"                                          , pointB, pointC));
        sb.append(String.format("            B#################################C           \n"));
        sb.append(String.format("            #                                 #           \n"));
        sb.append(String.format("            #                                 #           \n"));
        sb.append(String.format("%11s b           %-11s           #           \n"             ,
                Double.isNaN(b)? "" : String.format("%.2f", b), Double.isNaN(area)? "" : String.format("%.2f", area) ));
        sb.append(String.format("            #                                 #           \n"));
        sb.append(String.format("            #                                 #           \n"));
        sb.append(String.format("            A################a################D           \n"));
        sb.append(String.format(" %s%-11s%s\n"                                                , pointA, Double.isNaN(a)? "" : String.format("%.2f", a), pointD));
        return sb.toString();
    }
    public static String square(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double a, double area){
        a = Math.abs(a); area =  Math.abs(area);
        String pointA = Double.isNaN(x1)||Double.isNaN(y1)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x1), String.format("%.2f)", y1));
        String pointB = Double.isNaN(x2)||Double.isNaN(y2)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x2), String.format("%.2f)", y2));
        String pointC = Double.isNaN(x3)||Double.isNaN(y3)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x3), String.format("%.2f)", y3));
        String pointD = Double.isNaN(x4)||Double.isNaN(y4)? " ".repeat(23) : String.format("%11s;%-11s", String.format("(%.2f", x4), String.format("%.2f)", y4));
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" %s%s\n"                                          , pointB, pointC));
        sb.append(String.format("            B######################C           \n"));
        sb.append(String.format("            #                      #           \n"));
        sb.append(String.format("            #                      #           \n"));
        sb.append(String.format("%11s a      %-11s     #           \n"             ,
                Double.isNaN(a)? "" : String.format("%.2f", a), Double.isNaN(area)? "" : String.format("%.2f", area) ));
        sb.append(String.format("            #                      #           \n"));
        sb.append(String.format("            #                      #           \n"));
        sb.append(String.format("            #                      #           \n"));
        sb.append(String.format("            A######################D           \n"));
        sb.append(String.format(" %s%s\n"                                          , pointA, pointD));
        return sb.toString();
    }
}
