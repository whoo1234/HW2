
import java.util.*;

public class CalculatorTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean endCalculator = true;
		int selectedMenu = 0;
		Calculator cal = new Calculator();
		
		while (endCalculator) {
			cal.printMenu();
			
			try {
				selectedMenu = scanner.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("1-3 ���� ������ �Է��ϼ���.");
				scanner.nextLine();
				continue;
			}

			switch (selectedMenu) {
			case 1:
				cal.calculateTwoNumbers();
				break;
			case 2:
				cal.convertUnits();
				break;
			case 3:
				System.out.println("���⸦ �����մϴ�.");
				endCalculator = false;
				break;
			default:
				System.out.println("1-3 ���� ������ �Է��ϼ���.");
			}
			System.out.println();
		}
	}
}

class Calculator {
	private Scanner scanner;
	private int convertDirection = 0;
	private double input = 0.0;

	public void printMenu() {
		System.out.println("1. ��Ģ ����");
		System.out.println("2. ���� ��ȯ");
		System.out.println("3. ����");
		System.out.print("�޴��� �����ϼ��� >> ");
	}

	public void calculateTwoNumbers() {
		scanner = new Scanner(System.in);
		System.out.print("�� �Ǽ��� ��Ģ ������ ���� ������ �ΰ� �Է��ϼ���(ex. 3.2 * 4) >>");
		String input = scanner.nextLine();
		StringTokenizer st = new StringTokenizer(input);
		
		try {
			double num1 = Double.parseDouble(st.nextToken());
			String oper = st.nextToken();
			double num2 = Double.parseDouble(st.nextToken());
			double result = 0;
	
			switch (oper) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;
			case "*":
				result = num1 * num2;
				break;
			case "/":
				if (num2 == 0) {
					System.out.println("0���� ���� �� �����ϴ�. ������� 0���� ��µ˴ϴ�.");
					break;
				}
				result = num1 / num2;
				break;
			default:
				System.out.println("�Է��� �Ŀ� ������ �ֽ��ϴ�.");
			}
			System.out.printf("������� %.2f �Դϴ�.\n", result);
		}catch (Exception e) {
			System.out.println("�Է� �����Դϴ�.");
		}
	}

	public void convertUnits() {
		scanner = new Scanner(System.in);
		System.out.println("1. pound �� kg");
		System.out.println("2. inch �� cm");
		System.out.println("3. ��F �� ��C");
		System.out.print("��ȯ�� ���� ��ȣ�� �Է��ϼ��� >> ");
		int selectedUnit = 0;
		
		try {
			selectedUnit = scanner.nextInt();
		} catch(InputMismatchException e) {
			scanner.nextLine();
		}
		
		switch (selectedUnit) {
		case 1:
			convertBetweenPoundAndKg();
			break;
		case 2:
			convertBetweenInchAndCm();
			break;
		case 3:
			convertBetweenFahrenheitAndCelsius();
			break;
		default:
			System.out.println("1-3 ���� ������ �Է��ϼ���.");
		}
	}

	public void convertBetweenPoundAndKg() { 
		scanner = new Scanner(System.in);
		final double POUND_TO_KG = 0.453592;
		System.out.println("1. pound -> kg");
		System.out.println("2. kg -> pound");
		System.out.print("��ȯ ������ �����ϼ��� >> ");
		
		try {
			convertDirection = scanner.nextInt();
		} catch(InputMismatchException e) {
			scanner.nextLine();
		}
		
		switch(convertDirection) {
		case 1 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(pound) >> ");
			input = scanner.nextDouble();
			System.out.printf("%.2f kg\n", input * POUND_TO_KG);
			break;
		case 2 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(kg) >> ");
			input = scanner.nextDouble();
			System.out.printf("%.2f pound\n", input / POUND_TO_KG);
			break;
		default :
			System.out.println("1-2 ���� ������ �Է��ϼ���.");
		}
	}

	public void convertBetweenInchAndCm() {
		scanner = new Scanner(System.in);
		final double INCH_TO_CM = 2.54;
		System.out.println("1. inch -> cm");
		System.out.println("2. cm -> inch");
		System.out.print("��ȯ ������ �����ϼ��� >> ");
		
		try {
			convertDirection = scanner.nextInt();
		} catch(InputMismatchException e) {
			scanner.nextLine();
		}
		
		switch(convertDirection) {
		case 1 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(inch) >> ");
			input = scanner.nextDouble();
			System.out.printf("%.2f cm\n", input * INCH_TO_CM);
			break;
		case 2 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(cm) >> ");
			input = scanner.nextDouble();
			System.out.printf("%.2f inch\n", input / INCH_TO_CM );
			break;
		default :
			System.out.println("1-2 ���� ������ �Է��ϼ���.");
		}
	}

	public void convertBetweenFahrenheitAndCelsius() {
		scanner = new Scanner(System.in);
		System.out.println("1. ��F -> ��C");
		System.out.println("2. ��C -> ��F");
		System.out.print("��ȯ ������ �����ϼ��� >> ");
		
		try {
			convertDirection = scanner.nextInt();
		} catch(InputMismatchException e) {
			scanner.nextLine();
		}
		
		switch(convertDirection) {
		case 1 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(��F) >> ");
			input = scanner.nextDouble();
			System.out.println(fToC(input) + " ��C");
			break;
		case 2 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(��C) >> ");
			input = scanner.nextDouble();
			System.out.println(CToF(input) + " ��F");
			break;
		default :
			System.out.println("1-2 ���� ������ �Է��ϼ���.");
		}
	}

	private double fToC(double input) {
		return (input - 32) / 1.8;
	}

	private double CToF(double input) {
		return input * 1.8 + 32;
	}
}

