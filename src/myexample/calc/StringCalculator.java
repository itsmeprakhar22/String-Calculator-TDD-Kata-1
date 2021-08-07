package myexample.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StringCalculator {

	public int Add(String text) {
		int sum=0;
		String[] numbers = tokenize(text);
		List<Integer> negativeNumbers = filterNegatives(numbers);
		if(negativeNumbers.size()>0) {
			throw new RuntimeException("negatives not allowed " + negativeNumbers);
		}
		for(int i=0;i<numbers.length;i++) {
			int element = Integer.parseInt(numbers[i]);
			sum = sum + element;
		}
		return sum;
	}
	
	private static List<Integer> filterNegatives(String[] numbers) {
		List<Integer> list = new ArrayList<Integer>();
		for(String e: numbers) {
			int number = Integer.parseInt(e);
			if(number<0)
				list.add(number);
		}
		return list;
	}
	
	private static String[] tokenize(String text) {
		if(text.isEmpty())
			return new String[] {"0"};
		if(text.startsWith("//")) {
			return splitCustomDelimiter(text);
		}
		
		else {
			String[] tokens = text.split(",|\n");
			return tokens;
		}
	}
	
	private static String[] splitCustomDelimiter(String input) {
		String[] headerAndNumberSequence = input.split("\n", 2);
		String delimiter = parseDelimiter(headerAndNumberSequence[0]);
		return headerAndNumberSequence[1].split(delimiter);
	}
	
	private static String parseDelimiter(String header) {
		String delimiter = header.substring(2);
		if (delimiter.startsWith("[")) {
			delimiter = delimiter.substring(1, delimiter.length() - 1);
		}
		return Stream.of(delimiter.split("]\\["))
				.map(Pattern::quote)
				.collect(Collectors.joining("|"));
	}

}
