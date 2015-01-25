package com.nhf.io;

/**
 * Created by ZAKIR_224 on 2/24/14.
 */
public class IOUtil {

    public static String getString(Double aDouble){
        return aDouble+"";
    }

    public static double getDouble(String s){
        if(s.equals(""))
            return 0.0;

        return Double.valueOf(s);
    }

    public static int getInt(String s){
        if(s.equals(""))
            return 0;

        return Integer.valueOf(s);
    }

    public static String getString(int s){

        return s+"";
    }
}
