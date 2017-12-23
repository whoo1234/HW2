import java.util.*;

public class CalculatorProgram {
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
	private final double POUND_TO_KG = 0.453592;
	private final double INCH_TO_CM = 2.54;

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
				result = add(num1, num2);
				break;
			case "-":
				result = sub(num1, num2);
				break;
			case "*":
				result = mul(num1, num2);
				break;
			case "/":
				result = div(num1, num2);
				break;
			default:
				System.out.println("�Է��� �Ŀ� ������ �ֽ��ϴ�.");
			}
			System.out.println("�������" + Math.floor(result*100)/100 + "�Դϴ�.");
		}catch (Exception e) {
			System.out.println("�Է� �����Դϴ�.");
		}
	}
	
	double add(double a, double b) {
		return a + b;
	}
	
	double sub(double a, double b) {
		return a - b;
	}
	
	double mul(double a, double b) {
		return a * b;
	}
	
	double div(double a, double b) {
		if (b == 0) {
			System.out.println("0���� ���� �� �����ϴ�. ������� 0���� ��µ˴ϴ�.");
			return 0;
		}
		return a / b;
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
			scanner.nextLine();
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
			try {
				input = scanner.nextDouble();
				System.out.println(Math.floor(convertPoundToKg(input)*100)/100 + " kg");
			} catch(InputMismatchException e) {
				System.out.println("�Է� �����Դϴ�.");
				scanner.nextLine();
			}
			break;
			
		case 2 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(kg) >> ");
			
			try {
				input = scanner.nextDouble();
				System.out.println(Math.floor(convertKgToPound(input)*100)/100 + " pound");
			} catch(InputMismatchException e) {
				System.out.println("�Է� �����Դϴ�.");
				scanner.nextLine();
			}
			break;
			
		default :
			System.out.println("1-2 ���� ������ �Է��ϼ���.");
		}
	}
	
	double convertPoundToKg(double input) {
		return input * POUND_TO_KG;
	}
	
	double convertKgToPound(double input) {
		return input / POUND_TO_KG;
	}

	public void convertBetweenInchAndCm() {
		scanner = new Scanner(System.in);
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
			
			try {
				input = scanner.nextDouble();
				System.out.println(Math.floor(convertInchToCm(input)*100)/100 + " cm");
			} catch(InputMismatchException e) {
				System.out.println("�Է� �����Դϴ�.");
				scanner.nextLine();
			}
			break;
			
		case 2 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(cm) >> ");
			
			try {
				input = scanner.nextDouble();
				System.out.println(Math.floor(convertCmToInch(input)*100)/100 + " inch");
			} catch(InputMismatchException e) {
				System.out.println("�Է� �����Դϴ�.");
				scanner.nextLine();
			}
			break;
		default :
			System.out.println("1-2 ���� ������ �Է��ϼ���.");
		}
	}
	
	double convertInchToCm(double input) {
		return input * INCH_TO_CM;
	}
	
	double convertCmToInch(double input) {
		return input / INCH_TO_CM ;
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
			try {
				input = scanner.nextDouble();
				System.out.println(Math.floor(convertFToC(input)*100)/100 + " ��C");
			} catch(InputMismatchException e) {
				System.out.println("�Է� �����Դϴ�.");
				scanner.nextLine();
			}
			break;
			
		case 2 :
			System.out.print("��ȯ�� ���� �Է��ϼ���(��C) >> ");
			
			try {
				input = scanner.nextDouble();
				System.out.println(Math.floor(convertCToF(input)*100)/100 + " ��F");
			} catch(InputMismatchException e) {
				System.out.println("�Է� �����Դϴ�.");
				scanner.nextLine();
			}
			break;
			
		default :
			System.out.println("1-2 ���� ������ �Է��ϼ���.");
		}
	}

	private double convertFToC(double input) {
		return (input - 32) / 1.8;
	}

	private double convertCToF(double input) {
		return input * 1.8 + 32;
	}
}