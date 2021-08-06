package myexample.calc;

public class StringCalculator {

	public int Add(String text) {
		if(text.isEmpty())
			return 0;
		int sum=0;
		String[] numbers = text.split(",");
		
		for(int i=0;i<numbers.length;i++) {
			int element = Integer.parseInt(numbers[i]);
			sum = sum + element;
		}
		return sum;
	}
}
