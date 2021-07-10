package cn.edu.neu.util;

import java.io.*;

public class FileUtils {

    public static String toString(String path){
        File file = new File(JsonUtils.class.getClassLoader().getResource(path).getPath());
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                stringBuffer.append(tempStr);
            }
            reader.close();
            return stringBuffer.toString();
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
        return stringBuffer.toString();
    }

    public static void write(String content, String path) throws IOException {
        path = FileUtils.class.getClassLoader().getResource(path).getPath();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(content);
        }

    }
}
