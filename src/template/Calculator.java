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
        // �ݹ� �Լ��� ����, �Լ��� ����
        return lineReadTemplate(filePath, sumCallback, 0);
    }
    
    public Integer calMultiply(String filePath) throws IOException{
        LineCallback<Integer> multiplyCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);
            }
        };
        // �ݹ� �Լ��� ����, �Լ��� ����
        return lineReadTemplate(filePath, multiplyCallback, 1);
    }
    
    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            // FileReader ��ü�� �̿��Ͽ� Ư�� ���� Path�� ������ �о�´�.
            // BufferedReader�� �̿��Ͽ� ������ 1�پ� �д´�.
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
    
    // fileReadTemplate ���ø��� �ٸ� ����, 1���� line�� �о�� ������ �ݹ� �޼ҵ带 �����Ѵٴ� ���̴�. (���ø� ����� Ȯ�� �� �ݹ� �޼ҵ��� ���� ���)
    public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        try {
            // FileReader ��ü�� �̿��Ͽ� Ư�� ���� Path�� ������ �о�´�.
            // BufferedReader�� �̿��Ͽ� ������ 1�پ� �д´�.
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
