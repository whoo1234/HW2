package se;
import java.util.Scanner;

public class MainMenu {
	static MemoManager memoManager = new MemoManager();
	static AccountBookTest accountBookTest = new AccountBookTest();
	static CalculatorTest calculatorTest = new CalculatorTest();
	public static int show_menu(){
		int n;
		/* 
		   1. 메모 관리 시스템
		   2. 계산기
		   3. 가계부
		   등과 같이 사용자에게 메뉴를 보여준다.
		*/
		Scanner scan = new Scanner(System.in); 
		n=scan.nextInt();
		return n; 
	}
	public static void main(String args[]){
		int n=show_menu();
		// 사용자로부터 메뉴를 입력받아 실행시킨다.
		switch(n){
		case 1:
			memoManager.main(args);
			//메모 프로그램 실행
			break;
		case 2:
			accountBookTest.main(args);
			//가계부 프로그램 실행
			break;
		case 3:
			calculatorTest.main(args);
			// 계산기 프로그램 실행
			break;
		default:
			// "잘못된 입력입니다"라는 메시지 출력
		}
	}
}



