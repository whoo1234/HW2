import java.util.*;

class AccountBook {
	private int selectmenu;
	private boolean done;
	private int nowindexnum;
	private ArrayList<String[]> listMember = new ArrayList<String[]>();
	private int total = 0;
	Scanner scan = new Scanner(System.in);

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
		if (selectmenu == 1)
			showList();
		else if (selectmenu == 2)
			addList();
		else if (selectmenu == 3)
			modifyMember();
		else if (selectmenu == 4)
			deleteMember();
		else if (selectmenu == 5)
			backMain();

	}

	public void showList() {
		String arraymem[] = new String[5];

		System.out.println("��ȣ\t����\t�̸�\t��¥\t�ݾ�");
		System.out.println("-----------------------------------------");
		for (int i = 0; i < listMember.size(); i++) {
			arraymem = listMember.get(i);
			for (int j = 0; j < 5; j++)
				System.out.print(arraymem[j] + "\t");
			System.out.println();
		}

		System.out.println("-----------------------------------------");
		System.out.println("�� �ݾ� :" + total);
	}

	public void addList() {
		int add;
		nowindexnum = listMember.size();

		System.out.println("\n1. ����");
		System.out.println("2. ����");

		add = scan.nextInt();

		if (add == 1)
			supplementBudget(nowindexnum);
		else if (add == 2)
			subtractBudget(nowindexnum);
	}

	public void supplementBudget(int nowindexnum) {
		String budget[] = new String[5];

		budget[0] = String.valueOf(nowindexnum);
		budget[1] = "����";
		System.out.print("�̸� :");
		budget[2] = scan.next();
		System.out.print("��¥(ex.170118) :");
		budget[3] = scan.next();
		System.out.print("�ݾ� :");
		budget[4] = scan.next();

		listMember.add(budget);
		total += Integer.parseInt(budget[4]);
	}

	public void subtractBudget(int nowindexnum) {
		String budget[] = new String[5];

		budget[0] = String.valueOf(nowindexnum);
		budget[1] = "����";
		System.out.print("�̸� :");
		budget[2] = scan.next();
		System.out.print("��¥(ex.170118) :");
		budget[3] = scan.next();
		System.out.print("�ݾ� :");
		budget[4] = scan.next();

		listMember.add(budget);
		total -= Integer.parseInt(budget[4]);
	}

	public void modifyMember() {
		int indexnumber;
		String modifymember[] = new String[5];

		System.out.println("\n������ �׸��� ��ȣ�� �Է��ϼ���.");
		indexnumber = scan.nextInt();
		modifymember = listMember.get(indexnumber);

		if (modifymember[1] == "����") {
			String budget[] = new String[5];

			budget[0] = String.valueOf(indexnumber);
			budget[1] = "����";
			System.out.print("�̸� :");
			budget[2] = scan.next();
			System.out.print("��¥(ex.170118) :");
			budget[3] = scan.next();
			System.out.print("�ݾ� :");
			budget[4] = scan.next();

			total -= Integer.parseInt(modifymember[4]);
			total += Integer.parseInt(budget[4]);

			listMember.set(indexnumber, budget);
		} else if (modifymember[1] == "����") {
			String budget[] = new String[5];

			budget[0] = String.valueOf(indexnumber);
			budget[1] = "����";
			System.out.print("�̸� :");
			budget[2] = scan.next();
			System.out.print("��¥(ex.170118) :");
			budget[3] = scan.next();
			System.out.print("�ݾ� :");
			budget[4] = scan.next();

			total += Integer.parseInt(modifymember[4]);
			total -= Integer.parseInt(budget[4]);

			listMember.set(indexnumber, budget);
		}
	}

	public void deleteMember() {
		int indexnumber;
		String removemember[] = new String[5];

		System.out.println("\n������ �׸��� ��ȣ�� �Է��ϼ���.");
		indexnumber = scan.nextInt();
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
	}

	public void backMain() {
		System.out.println("�������� ���ư��ϴ�.");
		done = false;
	}

}

public class AccountBookTestProgram {
	static AccountBook ac = new AccountBook();

	public static void main(String[] args) {
		ac.actAccountBook();
	}

}
