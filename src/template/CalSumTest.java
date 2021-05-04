package template;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalSumTest {
    Calculator Calculator;
    String numFilePath;
    
    @Before
    public void setup() {
        this.Calculator = new Calculator();
        this.numFilePath = "C:\\Users\\user\\Desktop\\numbers.txt";
    }
    
    @Test
    public void calSumTest() throws IOException{
        Calculator calculator = new Calculator();
        System.out.println(calculator.calSum(numFilePath));
    }
    
    @Test
    public void calMulTest() throws IOException{
        Calculator calculator = new Calculator();
        System.out.println(calculator.calMultiply(numFilePath));
    }
    
}
