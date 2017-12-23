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
 public boolean isFileTxt(String fileName){
	 String ext=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
	 if(ext.equals("txt"))
		 return true;
	return false;
 }
 public boolean confirmDelete(){
	 int result=JOptionPane.showConfirmDialog(null,"파일을 삭제하시겠습니까?","삭제 재확인", JOptionPane.YES_NO_OPTION);
	 if(result==JOptionPane.YES_OPTION)
		 return true;
	 else
		 return false;
 }
 public FileDialog openWindow(String windowTitle){
	 FileDialog dialog;
	 if(windowTitle.equals("저장"))
		 dialog = new FileDialog(this, windowTitle, FileDialog.SAVE);
	 else
		 dialog = new FileDialog(this, windowTitle, FileDialog.LOAD);
	 dialog.setDirectory("."); 
	 dialog.setVisible(true); 
	   
	 if(dialog.getFile() == null)
		return null;
	 return dialog;
 }
 public void savePerformed(){
	FileDialog dialog=openWindow("저장");
	if(dialog==null) return;
	if(!isFileTxt(dialog.getFile())){
		JOptionPane.showMessageDialog(this, "메모장은 텍스트파일 전용입니다.\n확장자(.txt)를 꼭 붙여서 저장해주세요.");
		return;
	}
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
	   FileDialog dialog=openWindow("열기");
	   if(dialog==null) return;
	   String dfName = dialog.getDirectory() + dialog.getFile();
	   if(!isFileTxt(dfName)){
		   JOptionPane.showMessageDialog(this, "열수 없는 형태의 파일입니다.\n확장자가 .txt인 파일을 선택해 주세요.");
		   return;
	   }
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

 public boolean deletePerformed(FileDialog dialog){
	 if(dialog==null) return false;
	 String dfName = dialog.getDirectory() + dialog.getFile();
	   try {
		   File file = new File(dfName);
		   if(file.exists()){
			   if(confirmDelete()){
				   if(file.delete()){
					   JOptionPane.showMessageDialog(this, "파일이 삭제되었습니다.");
					   newPerformed();
					   return true;
				   }
				   else{
					   JOptionPane.showMessageDialog(this, "파일 삭제에 실패했습니다.");
				   }
			   }
		   }else{
			   JOptionPane.showMessageDialog(this, dialog.getFile()+" 파일을 찾을 수 없습니다.");
		   } 
	   }catch (Exception e2) {
		  JOptionPane.showMessageDialog(this, "삭제 오류");
	   }
	   return false;

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
	   deletePerformed(openWindow("삭제"));
   }
   else if(e.getSource()==mnuExit){
	   exitPerformed();
   }
 }
 
 	public static void main(String args[]){
 		new MemoManager();
 	}
 