package se;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class MemoManager extends JFrame implements ActionListener{
 JButton btnSave = new JButton("SAVE");
 JButton btnClear = new JButton("CLEAR");
 JTextArea Memo = new JTextArea("", 10, 40);
 // textArea를 빈칸으로 출력, 사이즈는 10x40로 생성하고 변수 이름을 Memo라고 할당
 
 JMenuItem mnuNew, mnuSave, mnuOpen, mnuExit, mnuDelete;
 // 주 메뉴들...
 
 public MemoManager() {
	  super("제목없음 ");
	  //새로운 창을 화면에 띄우고, 타이틀을 "제목없음"으로 설정한다.
	  // 초기 버튼, 툴바(메뉴)의 레이아웃, 메모장 창의 크기, 속성 등을 설정한다.
	  initLayout();
	  toolBarLayout();
	  setBounds(200, 200, 400, 350);
	  setVisible(true);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
 
 public void initLayout() {
  //버튼과 textArea의 레이아웃 설정
  JPanel panel = new JPanel();
  panel.add(btnSave);
  panel.add(btnClear);
  add("South", panel);
  add("Center", Memo);
  btnSave.addActionListener(this);
  btnClear.addActionListener(this);
 }
 
 public void toolBarLayout() {       
  JMenuBar menuBar = new JMenuBar();
  // 툴바의 메뉴들 설정함
  // 툴바를 메모장 창(프레임)에 보이도록 설정함
  // 툴바의 액션리스너 호출
  JMenu mnuFile = new JMenu("파일");     // 주메뉴
  mnuNew = new JMenuItem("새 메모");     // 부메뉴
  mnuOpen  = new JMenuItem("열기");
  mnuSave = new JMenuItem("다른이름으로 저장");
  mnuDelete = new JMenuItem("삭제");
  mnuExit = new JMenuItem("종료");
  mnuFile.add(mnuNew);
  mnuFile.add(mnuOpen);
  mnuFile.add(mnuSave);
  mnuFile.add(mnuDelete);
  mnuFile.add(mnuExit);
  menuBar.add(mnuFile);
  setJMenuBar(menuBar);        // Frame에 메뉴바 장착
  
  mnuNew.addActionListener(this);      // 메뉴에 리스너 장착
  mnuSave.addActionListener(this);
  mnuOpen.addActionListener(this);
  mnuDelete.addActionListener(this);
  mnuExit.addActionListener(this);
 }
 public void exitPerformed(){
	 System.exit(0);
 }
 public void clearPerformed(){
	 int start = Memo.getSelectionStart();
	 int end = Memo.getSelectionEnd();
	 Memo.replaceRange("", start, end);   // 공백으로 치환하는 법 replaceRange("", 시작위치, 끝위치)
 }
 public void savePerformed(){
	// 파일 작업을 위한 경로명 및 파일명 얻기
	FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
	dialog.setDirectory(".");   // .은 지금폴더
	dialog.setVisible(true);   // 박스는 그냥 틀이고
	if(dialog.getFile() == null) return; // 이걸빼면 취소를 해도 저장이됨
	String dfName = dialog.getDirectory() + dialog.getFile()+".txt";  // 경로명 파일명
	// System.out.println(dfName);
	// 실제 저장은 여기에서
	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
		writer.write(Memo.getText());
	    writer.close();
	    setTitle(dialog.getFile() + " - 메모장" );
	   }catch (Exception e2) {
		   JOptionPane.showMessageDialog(this, "저장 오류");
	   }
 }
 
 public void openPerformed(){ // 열기 및 수정
	   FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD);
	   dialog.setDirectory(".");   // .은 지금폴더
	   dialog.setVisible(true);   // 박스는 그냥 틀이고
	   
	   if(dialog.getFile() == null)
		   return;
	   
	   String dfName = dialog.getDirectory() + dialog.getFile();  // 경로명 파일명
 
	   try {
		   	BufferedReader reader = new BufferedReader(new FileReader(dfName));
		   	Memo.setText("");
		   	String line;
		   	while((line = reader.readLine()) != null) {     // 읽어온 문서의 줄이 없어지면
		   		Memo.append(line + "\n");       // txtMemo에 추가
		   	}
		   	reader.close(); 
		   	setTitle(dialog.getFile() + " - 메모장" );
	   } catch (Exception e2) {
		   JOptionPane.showMessageDialog(this, "열기 오류");
	   }
 }
 public void deletePerformed(){
	   FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD);
	   dialog.setDirectory("."); 
	   dialog.setVisible(true); 
	   
	   if(dialog.getFile() == null)
		   return;
	   
	   String dfName = dialog.getDirectory() + dialog.getFile();  // 경로명 파일명
	   
	   try {
		   File file = new File(dfName);
		   if(file.exists()){
			   if(file.delete()){
				   System.out.println("파일삭제 성공");
				   JOptionPane.showMessageDialog(this, "파일삭제 성공");
			   }
			   else{
				   JOptionPane.showMessageDialog(this, "파일삭제 실패");
			   }
		   }else{
			   JOptionPane.showMessageDialog(this, "파일을 찾지 못했습니다.");
		   } 
	   }catch (Exception e2) {
		  JOptionPane.showMessageDialog(this, "삭제 오류");
	   }
 }
 
 @Override
 public void actionPerformed(ActionEvent e) {   
   // 메뉴아이템 실행 구현 부분
   if(e.getSource() == btnClear) {
	   	clearPerformed(); // 삭제할 부분 드래그해야
  }
   else if(e.getSource()==btnSave||e.getSource()==mnuSave){
	   savePerformed();
   }
   else if(e.getSource() == mnuOpen) { // 열기 및 수정
	   openPerformed();
   }
   else if(e.getSource() == mnuDelete){
	   deletePerformed();
   }
   else if(e.getSource()==mnuExit){
	   exitPerformed();
   }
 }
 
 public static void main(String args[]){
	 new MemoManager();
 }
}