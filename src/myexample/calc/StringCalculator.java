package myexample.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StringCalculator {
	private static int count = 0;

	public int Add(String text) {
		count++;
		
		//Split the input to tokens
		String[] tokens = tokenize(text);
		
		//Convert tokens to list of integers
		List<Integer> numbers = convertStringToList(tokens);
		
		//Check if negative number will throw an exception “negatives not allowed” - 
		checkAllPositiveNumbers(numbers);
		
		int sum = sumOfList(numbers);
		
		return sum;
	}
	
	public static int GetCalledCount() {
		return count;
	}
	
	private int sumOfList(List<Integer> numbers) {
		int sum=0;
		for(int number: numbers) {
			if(number<=1000)
			sum = sum + number;
		}
		return sum;
	}

	//Converts a list of number string to list of integers
	private List<Integer> convertStringToList(String[] tokens) {
		List<Integer> convertedList = new ArrayList<Integer>();
		
		for(int i=0;i<tokens.length;i++) {
			int element = Integer.parseInt(tokens[i]);
			convertedList.add(element);
		}
		return convertedList;
	}

	private void checkAllPositiveNumbers(List<Integer> numbers) throws RuntimeException {
		List<Integer> negativeNumbers = filterNegatives(numbers);
		if(negativeNumbers.size()>0) {
			//If there are multiple negatives, show all of them in the exception message 
			throw new RuntimeException("negatives not allowed " + negativeNumbers);
		}
	}
	
	//Filter negative integers in a list of integers
	private static List<Integer> filterNegatives(List<Integer> numbers) {
		List<Integer> list = new ArrayList<Integer>();
		for(int number: numbers) {
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
