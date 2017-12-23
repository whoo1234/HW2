package se;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountBook {
	private int selectmenu;
	private boolean done;
	private int nowindexnum;
	private ArrayList<String[]> listMember = new ArrayList<String[]>();
	Scanner scan = new Scanner(System.in);
	Scanner scan2 = new Scanner(System.in);
	
	int lastIndex;

	public void actAccountBook() {
		done = true;
		while (done) {
			showMenu();
			actMenu();
		}
	}

	public void showMenu() {
		System.out.println("=========================================");
		System.out.println("수행할 메뉴를 선택해 주세요.\n");
		System.out.println("1. 기록 보기");
		System.out.println("2. 추가");
		System.out.println("3. 수정");
		System.out.println("4. 삭제");
		System.out.println("5. 돌아가기");
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
			System.out.println("다시 고르세요.");
			System.out.println();
			break;
		}
	}

	public void showList() {
		System.out.println("번호\t종류\t이름\t날짜\t금액");
		System.out.println("-----------------------------------------");
		printMember();
		System.out.println("-----------------------------------------");
		System.out.println("총 금액 :" + printTotal());
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
	
	public int printTotal(){
		int total = 0;
		String[] me = new String[5];
		
		for(int i=0; i<listMember.size(); i++){
			me = listMember.get(i);
			if(me[1] == "수입"){
				total += Integer.parseInt(me[4]);
			}
			else if(me[1] == "지출"){
				total -= Integer.parseInt(me[4]);
			}
		}
		return total;
	}

	public int addList() {
		int add;
		String budget[] = new String[5];
		nowindexnum = listMember.size();

		System.out.println("\n1. 수입");
		System.out.println("2. 지출");
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
		budget[1] = "수입";
		System.out.print("이름 :");
		budget[2] = scan2.nextLine();
		System.out.print("날짜(ex.170118):");
		budget[3] = scan.next();
		System.out.print("금액 :");
		budget[4] = scan.next();
		return budget;
	}

	public String[] inputSubtract(int nowindexnum, String[] budget) {
		budget[0] = String.valueOf(nowindexnum);
		budget[1] = "지출";
		System.out.print("이름 :");
		budget[2] = scan2.nextLine();
		System.out.print("날짜(ex.170118) :");
		budget[3] = scan.next();
		System.out.print("금액 :");
		budget[4] = scan.next();
		return budget;
	}

	public String[] supplementBudget(String[] budget) {
		listMember.add(budget);
		lastIndex = listMember.size()-1;
		return listMember.get(lastIndex);
	}

	public String[] subtractBudget(String[] budget) {
		listMember.add(budget);
		lastIndex = listMember.size()-1;
		return listMember.get(lastIndex);
	}

	public void modifyMember() {
		int indexnumber;
		int selectedNumber;
		String modifymember[] = new String[5];

		System.out.println("\n수정할 항목의 번호를 입력하세요.");
		try{
		indexnumber = scan.nextInt();
		modifymember = listMember.get(indexnumber);
		System.out.println("\n1. 수입");
		System.out.println("2. 지출");
		selectedNumber = scan.nextInt();
		
		if (selectedNumber == 1) {
			modifymember = inputSupplement(indexnumber, modifymember);
		}
		else if (selectedNumber == 2){
			modifymember = inputSubtract(indexnumber, modifymember);
		}
		
		listMember.set(indexnumber, modifymember);
		}catch(IndexOutOfBoundsException e){
			System.out.println("저장된 데이터가 없어서 수정할 수 없습니다.");
			System.out.println("먼저 데이터를 입력해주세요. ^^");
		}
	}

	public void deleteMember() {
		int indexnumber;

		System.out.println("\n삭제할 항목의 번호를 입력하세요.");
		try{
			indexnumber = scan.nextInt();
			delete(indexnumber);
		}catch(IndexOutOfBoundsException e){
			System.out.println("저장된 데이터가 없어서 삭제할 수 없습니다.");
			System.out.println("먼저 데이터를 입력해주세요.");
		}		
	}

	public String[] delete(int indexnumber) {
		String removemember[] = new String[5];
		String nullarray[] = new String[5];
		
		try{
		removemember = listMember.get(indexnumber);
		
		
		if (removemember[1] == "수입") 
			listMember.remove(indexnumber);
		else if (removemember[1] == "지출")
			listMember.remove(indexnumber);
		
		for (int i = 0; i < listMember.size(); i++) {
			removemember = listMember.get(i);
			removemember[0] = String.valueOf(i);
		}
		System.out.println("삭제되었습니다.");
		
		lastIndex = listMember.size()-1;
		if(lastIndex>=0)
			return listMember.get(lastIndex);
		}catch(IndexOutOfBoundsException e){
			System.out.println("저장된 데이터가 없어서 삭제할 수 없습니다.");
			System.out.println("먼저 데이터를 입력해주세요.");
		}
		return nullarray;
	}

	public void backMain() {
		System.out.println("메인으로 돌아갑니다.");
		done = false;
	}
}
