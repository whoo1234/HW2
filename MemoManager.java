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
 
	public String returnFileName(String dfName){
		String fileName=dfName.substring(dfName.lastIndexOf("\\")+1, dfName.length());
		return fileName;
	}
	
	public boolean confirmDelete(){
		int result=JOptionPane.showConfirmDialog(this,"파일을 삭제하시겠습니까?","삭제 재확인", JOptionPane.YES_NO_OPTION);
		
		if(result==JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}
	
	public String returnContents(){
		return Memo.getText();
	}

	public void openWindow(String windowTitle){
		FileDialog dialog;
	
		if(windowTitle.equals("저장")){
			dialog = new FileDialog(this, windowTitle, FileDialog.SAVE);
			dialog.setDirectory("."); 
			dialog.setVisible(true); 

			if(dialog.getFile() == null)
				return;
			
			String dfName = dialog.getDirectory()+dialog.getFile();
			String contents = returnContents();
			
			savePerformed(dfName, contents);
		}
		
		else if(windowTitle.equals("열기")){
			dialog = new FileDialog(this, windowTitle, FileDialog.LOAD);
			dialog.setDirectory("."); 
			dialog.setVisible(true); 
		   
			if(dialog.getFile() == null)
				return;
			
			String dfName = dialog.getDirectory()+dialog.getFile();
			openPerformed(dfName);
		}
		
		else{
			dialog = new FileDialog(this, windowTitle, FileDialog.LOAD);
			System.out.println(dialog);
			dialog.setDirectory("."); 
			dialog.setVisible(true); 
		   
			if(dialog.getFile() == null)
				return;
			
			String dfName = dialog.getDirectory()+dialog.getFile();
			deletePerformed(dfName);
		}
		
	}
	
	public boolean savePerformed(String dfName, String contents){
		
		if(!isFileTxt(dfName)){
			JOptionPane.showMessageDialog(this, "메모장은 텍스트파일만 읽고 쓸 수 있습니다.\n확장자(.txt)를 꼭 붙여서 저장해주세요.");
			return false;
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
			writer.write(contents);
			writer.close();
			setTitle(returnFileName(dfName));
			System.out.println(Memo.getText());
			return true;
		}catch (Exception e2) {
		   JOptionPane.showMessageDialog(this, "저장 오류");
	   }
		return false;
	}
	
	public void openPerformed(String dfName){
	   
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
		   	setTitle(returnFileName(dfName));
	   	} catch (Exception e2) {
	   		JOptionPane.showMessageDialog(this, "열기 오류");
	   	}
	}
	
	public boolean deletePerformed(String dfName){
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
				JOptionPane.showMessageDialog(this, returnFileName(dfName)+" 파일을 찾을 수 없습니다.");
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
   
   		else if(e.getSource() == btnSave||e.getSource() == mnuSave){
	   		openWindow("저장");
   		}
   	
   		else if(e.getSource()==mnuNew){
	   		newPerformed();
   		}
   
   		else if(e.getSource() == mnuOpen) { 
   			openWindow("열기");   		}
   
   		else if(e.getSource() == mnuDelete){
	   		openWindow("삭제");
   		}
   
   		else if(e.getSource() == mnuExit){
	   		exitPerformed();
   		}
 	}
 	public static void main(String args[]){
 		new MemoManager();
 	}
 }
