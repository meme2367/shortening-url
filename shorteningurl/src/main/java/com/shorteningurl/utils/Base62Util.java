package com.shorteningurl.utils;

import org.springframework.stereotype.Component;

@Component
public class Base62Util {
    /**
     * Base62 Character Table
     */
    static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    /**
     * Base62 Encoding
     *
     * @return the base 62 string of an integer
     */
    public static String encoding(int value) {
        final StringBuilder sb = new StringBuilder();
        do {
            int i = value % 62;
            sb.append(BASE62[i]);
            value /= 62;
        } while (value > 0);
        return sb.toString();
    }


    /**
     * Returns the base 62 value of a string.
     *
     * @return the base 62 value of a string.
     */
    public static int decoding(String value) {
        int result=0;
        int power=1;
        for (int i = 0; i < value.length(); i++) {
            int digit = new String(BASE62).indexOf(value.charAt(i));
            result += digit * power;
            power *= 62;
        }
        return result;
    }


}

