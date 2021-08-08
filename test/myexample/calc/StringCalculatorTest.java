package myexample.calc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StringCalculatorTest {

	@Test
	@Order(1)
	void atFirstTestGetMethodCount() {
		//Test: Throw exception on negative
		StringCalculator testObj = new StringCalculator();
		
		//First call to Add()
		testObj.Add("1,2");
		int calledCount = StringCalculator.GetCalledCount();

		assertTrue((calledCount==1));
		
		//Second call to Add()
		testObj.Add("4,5,6");
		calledCount = StringCalculator.GetCalledCount();
		assertTrue((calledCount==2));
	}

	@Test
	void testAdd() {
		StringCalculator testObj = new StringCalculator();
		//Test 1: Empty string -- 0
		int resultEmpty = testObj.Add("");
		assertTrue((resultEmpty==0));
		
		//Test 2: "1,2" -- 3
		resultEmpty = testObj.Add("1,2");
		assertTrue((resultEmpty==3));
		
		//Test 3: Single digit "4" -- 4
		resultEmpty = testObj.Add("4");
		assertTrue((resultEmpty==4));
						
		
		
		//Test 8: Multiple delim. [***]\n1***2***3” == 6
		resultEmpty = testObj.Add("//[*][%]\n1*2%3");
		assertTrue((resultEmpty==6));
		
		//Test 9:  handle multiple delimiters with length longer than one char 
		resultEmpty = testObj.Add("//[**][%%]\n4**2%%3");
		assertTrue((resultEmpty==9));
		
	}

	@Test
	void shouldAddUnknownAmountOfNumbers() {
		StringCalculator testObj = new StringCalculator();
		int resultEmpty = testObj.Add("1,2,3,4,5");
		assertTrue((resultEmpty==15));
	}
	
	@Test
	void shouldHandleNewLine() {
		StringCalculator testObj = new StringCalculator();
		int resultEmpty = testObj.Add("4\n2,3");
		assertTrue((resultEmpty==9));
	}
	
	@Test
	void shouldSupportDifferentDelimiter() {
		//Test: Support different delimiters “//;\n1;2;3” == 6
		StringCalculator testObj = new StringCalculator();
		int resultEmpty = testObj.Add("//;\n1;2;3");
		assertTrue((resultEmpty==6));
	}
	
	@Test
	void shouldThrowExceptionOnNegatives() {
		//Test: Throw exception on negative
		StringCalculator testObj = new StringCalculator();
		int resultEmpty = 0;
		try {
			resultEmpty = testObj.Add("1,-2,-3");
			fail("Exception expected");
		}
		catch(RuntimeException e) {
			assertEquals("negatives not allowed [-2, -3]", e.getMessage());
		}
	}

	@Test
	void shouldIgnoreGreaterThan1000() {
		StringCalculator testObj = new StringCalculator();
		int resultEmpty = testObj.Add("2,1002");
		assertTrue((resultEmpty==2));
	}

	@Test
	void testMultipleDelimiter() {
		StringCalculator testObj = new StringCalculator();
		//Test: Multiple delimiter [***]\n1***2***3” == 6
		int resultEmpty = testObj.Add("//[***]\n1***2***3");
		assertTrue((resultEmpty==6));
	}
}
