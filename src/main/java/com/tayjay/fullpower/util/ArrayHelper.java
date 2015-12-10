package com.tayjay.fullpower.util;

import cpw.mods.fml.relauncher.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to help with arrays. <br>
 * Convert from Strings to various types of arrays. <br>
 * Convert arrays into Strings(For storing in NBTTags) <br>
 * Strings stored in form "[1,2,3]"
 * <br>
 * Created by tayjm_000 on 2015-11-07.
 */
public class ArrayHelper
{
    public static int[] stringToIntArray(String string)
    {
        String[] items = string.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {}
        }

        return results;
    }

    public static String arrayToString(int[] array)
    {
        String result = "[";
        String temp = "";
        for(int i =0;i<array.length;i++)
        {
            //temp = result;
            result = result+array[i];
            //result.concat(array[i]+"");
            if(i!=array.length-1)
            {
                result = result+",";
            }
        }
        result = result + "]";

        return result;
    }

    public static Double[] stringToDoubleArray(String string)
    {
        String[] items = string.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

        Double[] results = new Double[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Double.parseDouble(items[i]);
            } catch (NumberFormatException nfe) {};
        }
        return results;
    }

    public static long[] stringTolongArray(String string)
    {
        String[] items = string.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

        long[] results = new long[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {};
        }
        return results;
    }

}
