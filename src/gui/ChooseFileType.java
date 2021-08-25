package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChooseFileType implements ActionListener{
	private JCheckBox chckbxTxt;
	private JCheckBox chckbxTex;
	private JFrame appMainWindow;
	private String cvType;
	private JDialog savecv;
	
	
	public ChooseFileType(JCheckBox chckbxTxt,JCheckBox chckbxTex,JFrame appMainWindow, JDialog savecv) {
		this.chckbxTxt=chckbxTxt;
		this.chckbxTex=chckbxTex;
		this.appMainWindow=appMainWindow;
		//this.cvType=cvType;
		this.savecv=savecv;
	}
	
	public void actionPerformed(ActionEvent e) {			
			if (chckbxTxt.isSelected() && chckbxTex.isSelected() ){
				save(0);
			}else if (chckbxTxt.isSelected()){
				save(1);
			}else if (chckbxTex.isSelected()){
				save(2);
			}else {
				JOptionPane.showMessageDialog(null, "Please select a type to output first, then save.");
			}
	}
	private void save(int x) {
		try {
			if(savecv instanceof ChronologicalJFrameWindow) {
				((ChronologicalJFrameWindow)savecv).saveCV(x);
			}else if(savecv instanceof CombinedJFrameWindow) {
				((CombinedJFrameWindow)savecv).saveCV(x);
			}else {
				((FunctionalJFrameWindow)savecv).saveCV(x);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}	
	}
}
