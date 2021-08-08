package myexample.calc;

public class LaunchMain {
	
	public static void checkText(String text) {
		StringCalculator s = new StringCalculator();
		System.out.println(s.Add(text));
	}
	
	public static void main(String[] args) {

		String textEmpty = "";
		String textTwo = "1,2";
		checkText(textEmpty);
		checkText(textTwo);
		
	}
}
