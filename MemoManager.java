package se;
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
 	JButton btnSave = new JButton("SAVE");
	JButton btnClear = new JButton("CLEAR");
	JTextArea Memo = new JTextArea("",10,40);

	public MemoManager() {
		super("제목없음");
		initLayout();
		toolBarLayout();
		setBounds(200,200,400,350);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//새로운 창을 화면에 띄우고, 타이틀을 "제목없음"으로 설정
  	// 초기 버튼, 툴바(메뉴)의 레이아웃, 메모장 창의 크기, 속성 등을 설정
	}
 
	public void initLayout() {
	  //버튼과 textArea의 레이아웃 설정
	  JPanel paner = new JPanel();
	  panel.add(btnSave);
	  panel.add(btnClear);
	  add("South", panel);
	  add("Center", Memo);
	  btnSave.addActionListener(this);
	  btnClear.addActionListenr(this);
	 }
 
	 public void toolBarLayout() {    
	  JMenuBar menuBar = new JMenuBar();
	 // 툴바의 메뉴들 설정함
	  // 툴바를 메모장 창(프레임)에 보이도록 설정
	  // 툴바의 액션리스너 호출
	  JMenu mnuFile = new JMenu("파일");
	  mnuNew = new JMenuItem("새 메모");
	  mnuOpen = new JMenuItem("열기");
	  mnuSave = new JMenuItem("다른 이름으로 저장");
	  mnuExit = new JMenuItem("종료");
	  mnuFile.add(mnuNew);
	  mnuFile.add(mnuOpen);
	  mnuFile.add(mnuSave);
	  mnuFile.add(mnuExit);
	  menuBar.add(mnuFile);
	  setJMenuBar(menuBar);

	  mnuNew.addActionListener(this);
	  mnuSave.addActionListener(this);
	  mnuOpen.addActionListener(this);
	  mnuExit.addActionListener(this);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {   
	   // 메뉴아이템 실행 구현 부분
	   // clear버튼 눌렀을 때 -> 공백으로 치환
	   // save버튼(혹은 툴바의 다른 이름으로 저장)
	   // 툴바의 Exit
  	 // 파일 열기(Open)
	 if(e.getSource()==btnSave||e.getSource()==mnuSave){
		 FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
		 dialog.setDirectory(".");
		 dialog.setVisible(true);
		 if(dialog.getFile()==null)
			 return;
		 String dfName = dialog.getDirectory()+dialog.getFile();

		 try{
			 BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
			 writer.write(Memo.getText());
			 writer.close();
			 setTitle(dialog.getFile()+"-메모장");
		}catch(Exception e2){
			JOptionPane.showMessage.Dialog(this, "저장오류");
		}
 	}
	else if(e.getSource()==mnuExit){
		System.exit(0);
	}
	else if(e.getSource()==mnuOpen){
		FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD);
		dialog.setDirectory(".");
		dialog.setVisible(true);
		if(dialog.getFile()==null) return;
		String dfName = dialog.getDirectory()+dialog.getFile();

		try{
			BufferReader reader = new BufferReader ( new FileReader(dfName));
			Memo.setText("");
			String line;
			while((line=reader.readLine())!=null){
				Memo.append(line+"\n");
			}
			reader.close();
			
			setTitle(dialog.getFile()+"-메모장");
		}catch(Exception e2){
			JOptionPane.showMessageDialog(this, "열기 오류");
		}
	}
	}
 
	public static void main(String args[]){
	 new MemoManager();
	 // 메모장을 프로그램을 실행
	}
}

