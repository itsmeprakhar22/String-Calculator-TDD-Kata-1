package myexample.calc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

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
		
		//Test 4: Unlimited numbers -- sum
		resultEmpty = testObj.Add("1,2,3,4,5,6");
		assertTrue((resultEmpty==21));
				
		//Test 7: Throw exception on negative
		try {
			resultEmpty = testObj.Add("1,-2,-3");
			fail("Exception expected");
		}
		catch(RuntimeException e) {
			assertEquals("negatives not allowed [-2, -3]", e.getMessage());
		}
		
		//Test 8: Multiple delim. [***]\n1***2***3” == 6
		resultEmpty = testObj.Add("//[***]\n1***2***3");
		assertTrue((resultEmpty==6));
		
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
}
