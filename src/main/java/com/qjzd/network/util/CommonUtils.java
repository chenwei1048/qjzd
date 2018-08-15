package com.qjzd.network.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {
    /**
     * 把有数字组成的字符串转换为集合
     *
     * @param s
     * @return
     */
    public static List<Long> toLongList(String s) {
        if (s.contains(",")) {
            List<Long> sList = new ArrayList<Long>();
            String[] sArry = s.split(",");
            for (int i = 0; i < sArry.length; i++) {
                sList.add(Long.parseLong(sArry[i]));
            }
            return sList;
        }
        return null;
    }

    /**
     * 把有数字组成的字符串转换为集合
     *
     * @param s
     * @return
     */
    public static List<Integer> toIntegerList(String s) {
        if (s.contains(",")) {
            List<Integer> sList = new ArrayList<Integer>();
            String[] sArry = s.split(",");
            for (int i = 0; i < sArry.length; i++) {
                sList.add(Integer.parseInt(sArry[i]));
            }
            return sList;
        }
        return null;
    }

    /**
     * 判断是否是数字类型
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        boolean flag = true;
/*        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                flag = false;
                break;
            }
        }*/
        //Boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
        Boolean strResult = str.matches("-?[0-9]+");
        flag = strResult == true;
        return flag;
    }

    public static boolean isDouble(String str) {
        try{
            Double.parseDouble(str);
        }catch (Exception e){
            return false;
        }
        String s[] = str.split("\\.");

        if(s.length!=2){
            return false;
        }

        return s[1].length() == 2 || s[1].length() == 4;
    }
    public static boolean isDouble2(String str) {
        try{
            Double.parseDouble(str);
        }catch (Exception e){
            return false;
        }
        String s[] = str.split("\\.");

        return s.length == 2;
    }
    //判断是否为数值类型
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1)
            return false;

        int i = 0;
        if (length > 1 && chars[0] == '-')
            i = 1;

        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0 || "null".equals(str);
    }

    public static boolean isNull(Integer numb) {
        return null == numb || numb == 0;
    }

    public static boolean isNull(Long numb) {
        return null == numb || numb == 0;
    }

    public static boolean isNull(Double numb) {
        return null == numb || numb == 0;
    }

    public static boolean isNull(Byte numb) {
        return null == numb;
    }

    public static boolean isNull(Object numb) {
        return null == numb || "".equals(numb.toString());
    }

    public static boolean isNull(List numb) {
        return null == numb || numb.size() <= 0;
    }


}
