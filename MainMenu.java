package se;
import java.util.Scanner;

public class MainMenu{
	static AccountBookTest accountBookTest = new AccountBookTest();
	static CalculatorTest calculatorTest = new CalculatorTest();
	public static int show_menu(){
		int n;
		System.out.println("1. Memo Manager");
		System.out.println("2. Calculator");
		System.out.println("3. Account Book");
		System.out.println("4. Quit");
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
				accountBookTest.main(args);
				break;
			case 3:
				calculatorTest.main(args);
				break;
			case 4:
				System.out.println("프로그램이 종료되었습니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다.");
		}
	}
}

