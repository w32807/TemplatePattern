package template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator {

    public Integer calSum(String filePath) throws IOException{
        LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }
        };
        // 콜백 함수를 만들어서, 함수를 전달
        return lineReadTemplate(filePath, sumCallback, 0);
    }
    
    public Integer calMultiply(String filePath) throws IOException{
        LineCallback<Integer> multiplyCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);
            }
        };
        // 콜백 함수를 만들어서, 함수를 전달
        return lineReadTemplate(filePath, multiplyCallback, 1);
    }
    
    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            // FileReader 객체를 이용하여 특정 파일 Path의 파일을 읽어온다.
            // BufferedReader를 이용하여 파일을 1줄씩 읽는다.
            br = new BufferedReader(new FileReader(new File(filePath)));
            return callback.doSomethingWithReader(br);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(br != null) {
                br.close();
            }
        }
    }
    
    // fileReadTemplate 템플릿과 다른 점은, 1개의 line을 읽어올 때마다 콜백 메소드를 실행한다는 점이다. (템플릿 기능의 확장 및 콜백 메소드의 역할 축소)
    public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        try {
            // FileReader 객체를 이용하여 특정 파일 Path의 파일을 읽어온다.
            // BufferedReader를 이용하여 파일을 1줄씩 읽는다.
            br = new BufferedReader(new FileReader(new File(filePath)));
            T res = initVal;
            String line = null;
            while((line = br.readLine())!=null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(br != null) {
                br.close();
            }
        }
    }
}
