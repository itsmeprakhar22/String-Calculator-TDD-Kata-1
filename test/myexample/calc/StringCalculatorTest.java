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
		
		//Test 5: 
		resultEmpty = testObj.Add("1\n2,3");
		assertTrue((resultEmpty==21));
	}

}
