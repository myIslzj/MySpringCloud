package com.lzj.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultMap {

    /**
     * 写操作返回结果
     * @param code  结果码
     * @param msg  请求结果描述
     * @return
     */
    public static Map<String, Object> writeResultMap(int code, String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        return map;
    }

    /**
     * 读操作返回结果
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Map<String, Object> readResultMap(int code, String msg, Object data){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }

    /**
     * 分页读操作返回结果
     * @param code
     * @param msg
     * @param rows
     * @return
     */
    public static Map<String, Object> readPageResultMap(int code, String msg, int total, Object rows){
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("total", total);
        map.put("rows", rows);
        return map;
    }

}
