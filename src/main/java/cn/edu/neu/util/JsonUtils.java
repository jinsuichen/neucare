package cn.edu.neu.util;

import cn.edu.neu.pojo.Admin;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 提供与json相关的方法
 */
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 序列化对象（转json）
     * @param obj
     * @return java.lang.String
     */
    public static String serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.err.println("json序列化出错：" + obj);
            return null;
        }
    }

    /**
     * 反序列化（json转为Bean）
     * @param json
     * @param tClass
     * @return T
     */
    public static <T> T parse(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            System.err.println("json解析出错：" + json);
            return null;
        }
    }

    /**
     * 反序列化（json转List）
     * @param json
     * @param eClass
     * @return java.util.List<E>
     */
    public static <E> List<E> parseList(String json, Class<E> eClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException e) {
            System.err.println("json解析出错：" + json);
            return null;
        }
    }

    /**
     * 反序列化（json转Map）
     * @param json
     * @param kClass
     * @param vClass
     * @return java.util.Map<K,V>
     */
    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            System.err.println("json解析出错：" + json);
            return null;
        }
    }

    /**
     * json转复杂对象
     * @param json
     * @param type
     * @return T
     */
    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            System.err.println("json解析出错：" + json);
            return null;
        }
    }


/*    *//**
     * 从文件反序列化，将json字符流转化为对象
     * @param path 文件路径
     * @param
     *//*
    public static <T> List<T> parseFromFile(String path, Class<T[]> clazz) {

        String jsonString = null;

        File file = new File(JsonUtils.class.getClassLoader().getResource(path).getPath());
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            jsonString = sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        jsonString = sbf.toString();
        return parseFromString(jsonString, clazz);
    }*/
}
