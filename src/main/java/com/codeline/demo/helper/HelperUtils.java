package com.codeline.demo.helper;

import java.util.List;

public class HelperUtils {

    public static <T> Boolean isNull (T str){
        return str==null;
    }
    public static <T> Boolean isNotNull(T str){
        return !isNull(str);
    }

    public static <T> Boolean isListEmpty(List<T> strList){
        return strList.isEmpty();
    }
    public static <T> Boolean isListNotEmpty(List<T> strList) {
        return !isListEmpty(strList);
    }
    public static <T> Boolean isNull(List<T> strList){
        return strList==null;
    }
    public static <T> Boolean isNotNull(List<T> strList){
        return !isNull(strList);
    }
    }
