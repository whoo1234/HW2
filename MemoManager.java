package se;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class MemoManager extends JFrame implements ActionListener{
 JButton btnSave = new JButton("SAVE");
 JButton btnClear = new JButton("CLEAR");
 JTextArea Memo = new JTextArea("", 10, 40);
 JMenuItem mnuNew, mnuSave, mnuOpen, mnuExit, mnuDelete;
 
 public MemoManager() {
	  super("제목없음 ");
	  initLayout();
	  toolBarLayout();
	  setBounds(200, 200, 400, 350);
	  setVisible(true);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
 
 public void initLayout() {
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
  JMenu mnuFile = new JMenu("파일");
  
  mnuNew = new JMenuItem("새 메모");
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
  setJMenuBar(menuBar); 
  
  mnuNew.addActionListener(this);
  mnuSave.addActionListener(this);
  mnuOpen.addActionListener(this);
  mnuDelete.addActionListener(this);
  mnuExit.addActionListener(this);
 }
 
 public void exitPerformed(){
	 System.exit(0);
 }
 
 public void clearPerformed(){
	 Memo.setText("");
 }
 
 public void newPerformed(){
	 Memo.setText("");
	 setTitle("제목없음");
 }
 
 public void savePerformed(){
	FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
	dialog.setDirectory(".");
	dialog.setVisible(true);
	if(dialog.getFile() == null) return;
	String dfName = dialog.getDirectory() + dialog.getFile()+".txt";

	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
		writer.write(Memo.getText());
	    writer.close();
	    setTitle(dialog.getFile());
	   }catch (Exception e2) {
		   JOptionPane.showMessageDialog(this, "저장 오류");
	   }
 }
 
 public void openPerformed(){
	   FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD);
	   dialog.setDirectory(".");
	   dialog.setVisible(true);
	   
	   if(dialog.getFile() == null)
		   return;
	   
	   String dfName = dialog.getDirectory() + dialog.getFile();
 
	   try {
		   	BufferedReader reader = new BufferedReader(new FileReader(dfName));
		   	Memo.setText("");
		   	String line;
		   	while((line = reader.readLine()) != null) {
		   		Memo.append(line + "\n");
		   	}
		   	reader.close(); 
		   	setTitle(dialog.getFile());
	   } catch (Exception e2) {
		   JOptionPane.showMessageDialog(this, "열기 오류");
	   }
 }
 
 public void deletePerformed(){
	   FileDialog dialog = new FileDialog(this, "삭제", FileDialog.LOAD);
	   dialog.setDirectory("."); 
	   dialog.setVisible(true); 
	   
	   if(dialog.getFile() == null)
		   return;
	   
	   String dfName = dialog.getDirectory() + dialog.getFile();
	   
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
   if(e.getSource() == btnClear) {
	   	clearPerformed(); 
  }
   else if(e.getSource()==btnSave||e.getSource()==mnuSave){
	   savePerformed();
   }
   else if(e.getSource()==mnuNew){
	   newPerformed();
   }
   else if(e.getSource() == mnuOpen) { 
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