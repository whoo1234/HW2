import java.util.ArrayList;
import java.util.Scanner;

public class AccountBook {
	private int selectmenu;
	private boolean done;
	private int nowindexnum;
	private ArrayList<String[]> listMember = new ArrayList<String[]>();
	private int total = 0;
	Scanner scan = new Scanner(System.in);
	Scanner scan2 = new Scanner(System.in);

	public void actAccountBook() {
		done = true;
		while (done) {
			showMenu();
			actMenu();
		}
	}

	public void showMenu() {
		System.out.println("=========================================");
		System.out.println("������ �޴��� ������ �ּ���.\n");
		System.out.println("1. ��� ����");
		System.out.println("2. �߰�");
		System.out.println("3. ����");
		System.out.println("4. ����");
		System.out.println("5. ���ư���");
		System.out.println("=========================================");
		selectmenu = scan.nextInt();
		System.out.println("-----------------------------------------");
	}

	public void actMenu() {
		switch (selectmenu) {
		case 1:
			showList();
			break;
		case 2:
			addList();
			break;
		case 3:
			modifyMember();
			break;
		case 4:
			deleteMember();
			break;
		case 5:
			backMain();
			break;
		default:
			System.out.println("�ٽ� ������.");
			System.out.println();
			break;
		}
	}

	public void showList() {
		System.out.println("��ȣ\t����\t�̸�\t��¥\t�ݾ�");
		System.out.println("-----------------------------------------");
		printMember();
		System.out.println("-----------------------------------------");
		System.out.println("�� �ݾ� :" + total);
	}

	public void printMember() {
		String arraymem[] = new String[5];
		for (int i = 0; i < listMember.size(); i++) {
			arraymem = listMember.get(i);
			for (int j = 0; j < 5; j++)
				System.out.print(arraymem[j] + "\t");
			System.out.println();
		}
	}

	public int addList() {
		int add;
		String budget[] = new String[5];
		nowindexnum = listMember.size();

		System.out.println("\n1. ����");
		System.out.println("2. ����");
		add = scan.nextInt();
		if (add == 1) {
			budget = inputSupplement(nowindexnum, budget);
			supplementBudget(budget);
		} else if (add == 2) {
			budget = inputSubtract(nowindexnum, budget);
			subtractBudget(budget);
		}
		return listMember.size();
	}

	public String[] inputSupplement(int nowindexnum, String[] budget) {
		budget[0] = String.valueOf(nowindexnum);
		budget[1] = "����";
		System.out.print("�̸� :");
		budget[2] = scan2.nextLine();
		System.out.print("��¥(ex.170118) :");
		budget[3] = scan.next();
		System.out.print("�ݾ� :");
		budget[4] = scan.next();
		return budget;
	}

	public String[] inputSubtract(int nowindexnum, String[] budget) {
		budget[0] = String.valueOf(nowindexnum);
		budget[1] = "����";
		System.out.print("�̸� :");
		budget[2] = scan2.nextLine();
		System.out.print("��¥(ex.170118) :");
		budget[3] = scan.next();
		System.out.print("�ݾ� :");
		budget[4] = scan.next();
		return budget;
	}

	public int supplementBudget(String[] budget) {
		listMember.add(budget);
		total += Integer.parseInt(budget[4]);
		return listMember.size();
	}

	public int subtractBudget(String[] budget) {
		listMember.add(budget);
		total -= Integer.parseInt(budget[4]);

		return listMember.size();
	}

	public String[] modifyMember() {
		int indexnumber;
		int selectedNumber;
		int originalBudget;
		String modifymember[] = new String[5];
		String modifiedmember[] = new String[5];

		System.out.println("\n������ �׸��� ��ȣ�� �Է��ϼ���.");
		indexnumber = scan.nextInt();
		modifymember = listMember.get(indexnumber);
		originalBudget = Integer.parseInt(modifymember[4]);
		System.out.println("\n1. ����");
		System.out.println("2. ����");
		selectedNumber = scan.nextInt();

		if (selectedNumber == 1) {
			total -= originalBudget;
			modifymember = inputSupplement(indexnumber, modifymember);
		} else if (selectedNumber == 2) {
			total += originalBudget;
			modifymember = inputSubtract(indexnumber, modifymember);
		}

		modifiedmember = modify(modifymember, indexnumber);
		return modifiedmember;
	}

	public String[] modify(String[] modifymember, int indexnumber) {
		if (modifymember[1] == "����") {
			total += Integer.parseInt(modifymember[4]);
			listMember.set(indexnumber, modifymember);
		} else if (modifymember[1] == "����") {
			total += Integer.parseInt(modifymember[4]);
			listMember.set(indexnumber, modifymember);
		}
		return listMember.get(indexnumber);
	}

	public void deleteMember() {
		int indexnumber;

		System.out.println("\n������ �׸��� ��ȣ�� �Է��ϼ���.");
		indexnumber = scan.nextInt();
		delete(indexnumber);
	}

	public boolean delete(int indexnumber) {
		int originalListSize = listMember.size();
		String removemember[] = new String[5];

		removemember = listMember.get(indexnumber);

		if (removemember[1] == "����") {
			total -= Integer.parseInt(removemember[4]);
			listMember.remove(indexnumber);
		} else if (removemember[1] == "����") {
			total += Integer.parseInt(removemember[4]);
			listMember.remove(indexnumber);
		}
		
		for (int i = 0; i < listMember.size(); i++) {
			removemember = listMember.get(i);
			removemember[0] = String.valueOf(i);
		}
		System.out.println("�����Ǿ����ϴ�.");

		if (listMember.size() == originalListSize)
			return false;
		else
			return true;
	}

	public void backMain() {
		System.out.println("�������� ���ư��ϴ�.");
		done = false;
	}
}