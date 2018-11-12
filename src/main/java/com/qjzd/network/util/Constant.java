package com.qjzd.network.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:
 * @Description:
 * @Date Create on 15:39 2018/11/12
 * @MOdifyBy:
 * @parameter
 */
public class Constant {

    private int code;
    private String name;

    public final static Constant GYWM = new Constant(1,"关于我们");

    public static Map<Long,String> type = new HashMap<>();

    static {
        type.put((long)1,"关于我们");
    }
    private Constant( int code,String name ) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
