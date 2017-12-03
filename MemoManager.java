import java.awt.Color;
import java.awt.FileDialog;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import jaa.awt.event.MouseEvent;
import java.io.*;
import javax.swing.*;
import java.io.Scanner;

public class MemoManager extends JFrame implements ActionListener{
 
	public MemoManager() {
  	//새로운 창을 화면에 띄우고, 타이틀을 "제목없음"으로 설정
  	// 초기 버튼, 툴바(메뉴)의 레이아웃, 메모장 창의 크기, 속성 등을 설정
	}
 
	public void initLayout() {
	  //버튼과 textArea의 레이아웃 설정
	 }
 
	 public void toolBarLayout() {       
	 // 툴바의 메뉴들 설정함
	  // 툴바를 메모장 창(프레임)에 보이도록 설정
	  // 툴바의 액션리스너 호출
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {   
	   // 메뉴아이템 실행 구현 부분
	   // clear버튼 눌렀을 때 -> 공백으로 치환
	   // save버튼(혹은 툴바의 다른 이름으로 저장)
	   // 툴바의 Exit
  	 // 파일 열기(Open)
 	}
 
	public static void main(String args[]){
	 new MemoManager();
	 // 메모장을 프로그램을 실행
	}
}

