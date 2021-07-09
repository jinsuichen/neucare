package cn.edu.neu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.OutputStream;


/**
 * 提供与json相关的方法
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许属性名称没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 设置输入时忽略在json字符串中存在但在java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 设置输出时包含属性的风格
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    //fixme 考虑到数组的存在，方法注释中“Bean”这个描述并不准确


    /**
     * 序列化，将对象转化为json字符串
     *
     * @param data 待序列化的Bean对象
     * @return 序列化字符串
     */
    public static String toJsonString(Object data) {
        if (data == null) {
            return null;
        }

        String json = null;
        try {
            json = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 反序列化，将json字符串转化为对象
     *
     * @param json json字符串
     * @param clazz Bean对象的类对象
     * @param <T> Bean对象的类
     * @return Bean对象
     */
    public static <T> T parse(String json, Class<T> clazz) {
        T t = null;
        try {
            t = mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * 将对象解析后写入输出流
     * @param data 待解析的对象
     * @param os 输出流
     */
    public static void writeDateToStream(Object data, OutputStream os){
        try {
            mapper.writeValue(os, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
