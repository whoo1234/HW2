import java.util.Scanner;
import java.lang.System.*;

public class MainMenu{
	static AccountBookProgram accountBookTest = new AccountBookProgram();
	static CalculatorProgram calculatorTest = new CalculatorProgram();
	public static int show_menu(){
		int n;
		System.out.println("1. Memo Manager");
		System.out.println("2. Calculator");
		System.out.println("3. Account Book");
		System.out.println("4. Quit");
		System.out.print(">> ");
		Scanner scan= new Scanner(System.in);
		n = scan.nextInt();
		return n;
	}

	public static void main(String args[]){
		int n=show_menu();
		switch(n){
			case 1:
				MemoManager memoManager = new MemoManager();
				break;
			case 2:
				calculatorTest.main(args);
				break;
			case 3:
				accountBookTest.main(args);
				break;
			case 4:
				System.out.println("Quit");
				return;
			default:
				System.out.println("You entered the wrong input.\nPlease restart this program.");
		}
	}
}
