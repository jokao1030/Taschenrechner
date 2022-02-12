import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for the calculator
 * 
 * @author Chin-Wen, Kao
 * @version 1.0
 * @since 18.06.2021
 */


class TaschenrechnerTest {
	   
	@Test
	@DisplayName("Valid Input: Operation of empty sets")
	void testEmptySet() {
		ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor1));
		Taschenrechner.calculate("[]-[]");
	    assertEquals("Result from minus: []", outputStreamCaptor1.toString().trim());
	    
	    ByteArrayOutputStream outputStreamCaptor2 = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outputStreamCaptor2));
	    Taschenrechner.calculate("[ ]*[89,0,90,91,123445,12]");
	    assertEquals("Result from intersect: []", outputStreamCaptor2.toString().trim());
	}
	

	@Test
	@DisplayName("Valid Input: Operation of non-empty sets")
    public void testNonEmptySet() {
		ByteArrayOutputStream outputStreamCaptor3 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor3));
		Taschenrechner.calculate("[100,10002988,8912,764,900,1092,2048,32,91,89,3131]*[89,34,990, 91,2048,100,8989999,72,6001,901,763,101]");
	    assertEquals("Result from intersect: [100, 89, 2048, 91]", outputStreamCaptor3.toString().trim());
	    
	    ByteArrayOutputStream outputStreamCaptor4 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor4));
		Taschenrechner.calculate("[12,345,67,890,0,6]+[89 ,0 ,90 ,91 ,123445 ,12]");
	    assertEquals("Result from union: [0, 12, 67, 89, 345, 123445, 6, 90, 91, 890]", outputStreamCaptor4.toString().trim());
		
	}
	
	@Test
	@DisplayName("Invalid Input: No valid operation character")
    public void testOperationCharacter() {
		ByteArrayOutputStream outputStreamCaptor5 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor5));
		Taschenrechner.calculate("[9,1,23,4,56,7,9000]");
	    assertEquals("There is no valid operation character!", outputStreamCaptor5.toString().trim());
	    
	    ByteArrayOutputStream outputStreamCaptor6 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor6));
		Taschenrechner.calculate("[1,2,3,0,99,7]/[89,0,90,91,123445,12]");
	    assertEquals("There is no valid operation character!", outputStreamCaptor6.toString().trim());
	    
	    ByteArrayOutputStream outputStreamCaptor7 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor7));
		Taschenrechner.calculate("[ ][ ]");
	    assertEquals("There is no valid operation character!", outputStreamCaptor7.toString().trim());
	    
	    ByteArrayOutputStream outputStreamCaptor8 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor8));
		Taschenrechner.calculate(" ");
	    assertEquals("There is no valid operation character!", outputStreamCaptor8.toString().trim());
    }
	
	@Test
	@DisplayName("Invalid Input: Bad form")
    public void testBadForm() {
		ByteArrayOutputStream outputStreamCaptor9 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor9));
		Taschenrechner.calculate("[4 5 67]+[2,909,0,71]");
		assertEquals("Comma or space problem?!", outputStreamCaptor9.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor10 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor10));
		Taschenrechner.calculate("[1,2,23,567,901]+[ 6 8 9 0 ]");
		assertEquals("Comma or space problem?!", outputStreamCaptor10.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor11 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor11));
		Taschenrechner.calculate("[20,213,10,1111]+ 3,700,564,89,250]");
		assertEquals("Please reform and try it again!", outputStreamCaptor11.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor12 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor12));
		Taschenrechner.calculate("-[56,98]");
		assertEquals("Please reform and try it again!", outputStreamCaptor12.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor13 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor13));
		Taschenrechner.calculate("23,99,78+[90,79]");
		assertEquals("Please reform and try it again!", outputStreamCaptor13.toString().trim());
        
        
    }

	@Test
	@DisplayName("Invalid Input: Invalid element in sets")
    public void testElementinSet() {
		ByteArrayOutputStream outputStreamCaptor14 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor14));
		Taschenrechner.calculate("[9,1,23,4,56,7,9000]+[2,909,000,71]");
		assertEquals("Please reform and try it again!", outputStreamCaptor14.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor15 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor15));
		Taschenrechner.calculate("[1,2,-23,567,901]+[2,909,89]");
		assertEquals("Please reform and try it again!", outputStreamCaptor15.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor16 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor16));
		Taschenrechner.calculate("[20,213,10,1111]+[0.3,700,564,89,250]");
		assertEquals("Please reform and try it again!", outputStreamCaptor16.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor17 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor17));
		Taschenrechner.calculate("[d,4,7,c,24]+[d,c]");
		assertEquals("Please reform and try it again!", outputStreamCaptor17.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor18 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor18));
		Taschenrechner.calculate("[d,4,7,@,24]+[d,)]");
		assertEquals("Please reform and try it again!", outputStreamCaptor18.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor19 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor19));
		Taschenrechner.calculate("[,]+[1,2]");
		assertEquals("Comma or space problem?!", outputStreamCaptor19.toString().trim());
		
		ByteArrayOutputStream outputStreamCaptor20 = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStreamCaptor20));
		Taschenrechner.calculate("[1 ,2,4,!]+[29,30,91]");
		assertEquals("Please reform and try it again!", outputStreamCaptor20.toString().trim());
		
       
    }
	
	
}
