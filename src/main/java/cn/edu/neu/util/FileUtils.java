package cn.edu.neu.util;

import java.io.*;

public class FileUtils {

    public static String toString(String path){
        File file = new File(JsonUtils.class.getClassLoader().getResource(path).getPath());
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        String jsonString;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
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
        return sbf.toString();
    }

    public static void write(String content, String path) throws IOException {
        path = FileUtils.class.getClassLoader().getResource(path).getPath();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(content);
        }

    }
}
