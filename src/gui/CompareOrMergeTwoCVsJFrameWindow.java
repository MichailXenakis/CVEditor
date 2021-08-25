package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import inputManagePackage.InputSystemLatex;
import inputManagePackage.InputSystemTxt;
import outputManagePackage.OutputMerge;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class CompareOrMergeTwoCVsJFrameWindow {

	static JFrame CompareOrMergeTwoCVsJFrameWindow;
	private JFrame appMainWindow;
	private JList<String> firstCVSectionsJList, secondCVSectionsJList;
	String openFirstCvFileChooserPath, openSecondCvFileChooserPath;
	int checkCounter=0, openFirstCounter=0, openSecondCounter=0;
	DefaultListModel<String> modelFirst = new DefaultListModel<>();
	DefaultListModel<String> modelSecond = new DefaultListModel<>();
	CVTemplate cvtemplate1, cvtemplate2;
    InputSystemTxt txtObj = new InputSystemTxt();
    InputSystemLatex ltxObj = new InputSystemLatex();
	
	
	public CompareOrMergeTwoCVsJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		CompareTwoCVsJFrameWindow CompareTwoCVsJFrameWindow = new CompareTwoCVsJFrameWindow(appMainWindow);
		
		CompareOrMergeTwoCVsJFrameWindow = new JFrame();
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().setBackground(Color.DARK_GRAY);
		CompareOrMergeTwoCVsJFrameWindow.setTitle("Curriculum Vitae (CV) Compare or Merge");
		CompareOrMergeTwoCVsJFrameWindow.setResizable(false);
		CompareOrMergeTwoCVsJFrameWindow.setBounds(400, 150, 518, 538);
		CompareOrMergeTwoCVsJFrameWindow.setLocationRelativeTo(null);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().setLayout(null);
		
		JLabel lblFirstCv = new JLabel("First CV");
		lblFirstCv.setBackground(Color.BLACK);
		lblFirstCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstCv.setForeground(Color.DARK_GRAY);
		lblFirstCv.setBounds(10, 11, 233, 35);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(lblFirstCv);
		
		JLabel lblSecondCv = new JLabel("Second CV");
		lblSecondCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondCv.setForeground(Color.DARK_GRAY);
		lblSecondCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSecondCv.setBackground(Color.BLACK);
		lblSecondCv.setBounds(267, 11, 245, 35);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(lblSecondCv);
		
		JPanel panel = new JPanel();
		createPanel(panel,0, 0, 512, 54);
		
		JPanel panel2 = new JPanel();
		createPanel(panel2, 253, -3, 4, 415);
		
		JScrollPane scrollPaneFirstCV = new JScrollPane();
		scrollPaneFirstCV.setBounds(20, 65, 198, 255);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(scrollPaneFirstCV);
		
		
		JScrollPane scrollPaneSecondCV = new JScrollPane();
		scrollPaneSecondCV.setBounds(292, 65, 198, 255);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(scrollPaneSecondCV);
		
		
		JButton btnOpenFirstCv = new JButton("Open First CV");
		createButton(btnOpenFirstCv, 49, 331, 156, 29);
		
		JButton btnOpenSecondCv = new JButton("Open Second CV");
		createButton(btnOpenSecondCv, 302, 331, 156, 29);
		
		JButton btnBack = new JButton("Back");
		createButton(btnBack, 10, 469, 103, 35);
		
		JCheckBox chckbxTex = new JCheckBox("tex");
		chckbxTex.setBounds(323, 481, 60, 23);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(chckbxTex);
		
		JButton btnMerge = new JButton("Merge");
		createButton(btnMerge, 389, 467, 113, 38);
		btnMerge.setToolTipText("If in doubt, the second CV's data \n will be overwritten");
		
		JButton btnClearFirstCv = new JButton("Clear");
		createButton(btnClearFirstCv, 81, 371, 103, 23);
		
		JButton btnClearSecondCv = new JButton("Clear");
		createButton(btnClearSecondCv, 327, 371, 103, 23);
		
		JCheckBox chckbxTxt = new JCheckBox("txt");
		chckbxTxt.setBounds(323, 455, 60, 23);
		CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(chckbxTxt);
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.setEnabled(false);
		createButton(btnCompare, 181, 413, 156, 35);
		
		JPanel panel1 = new JPanel();
		createPanel(panel1, 502, 412, -504, 95);
		
		JPanel panel3 = new JPanel();
		createPanel(panel3, 0, 405, 512, 104);
		
		
		/* Listeners */
		
		btnCompare.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				CompareTwoCVsJFrameWindow.makeVisible(cvtemplate1, cvtemplate2);
			}
		
		});
		
		ClearCv clear1 = new ClearCv(this, appMainWindow, btnOpenFirstCv, btnCompare,1);
        btnClearFirstCv.addActionListener(clear1); 
        ClearCv clear2 = new ClearCv(this, appMainWindow, btnOpenSecondCv, btnCompare,2);
        btnClearSecondCv.addActionListener(clear2); 
 	
		btnBack.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				CompareOrMergeTwoCVsJFrameWindow.setVisible(false);
				CVMainJFrameWindow.makeVisible();
				
			}
		
		});
		
		OpenCvToMerge cv1 = new OpenCvToMerge(this, appMainWindow, openFirstCvFileChooserPath, firstCVSectionsJList,  modelFirst, cvtemplate1, scrollPaneFirstCV, 
                btnCompare, btnOpenFirstCv, 1);
		btnOpenFirstCv.addActionListener(cv1);
		OpenCvToMerge cv2 = new OpenCvToMerge(this, appMainWindow, openSecondCvFileChooserPath, secondCVSectionsJList,  modelSecond, cvtemplate2,  scrollPaneSecondCV, 
                btnCompare, btnOpenSecondCv, 2);
		btnOpenSecondCv.addActionListener(cv2);
		
		
		MergeListener merge= new MergeListener(this, chckbxTxt, chckbxTex, openFirstCvFileChooserPath, openSecondCvFileChooserPath, cvtemplate1, cvtemplate2,
	             firstCVSectionsJList,  secondCVSectionsJList); 
        btnMerge.addActionListener(merge);	
	}

	public int checkIfSameFiles(){
		
		if (openFirstCvFileChooserPath!=null && openSecondCvFileChooserPath!=null){
			String[] openFirstCvFileChooserPathParts = openFirstCvFileChooserPath.split("\\\\");
			String[] openSecondCvFileChooserPathParts = openSecondCvFileChooserPath.split("\\\\");
			String errorDialogText = "Error! Different CV file names. \n Proceed Anyway?";
			
			if (!openFirstCvFileChooserPathParts[openFirstCvFileChooserPathParts.length-1].equals(openSecondCvFileChooserPathParts[openSecondCvFileChooserPathParts.length-1])){
				int dialogResult = JOptionPane.showConfirmDialog (null, errorDialogText, "File Name Check", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.NO_OPTION){
					
					return 1;
					
				}
				
		
			}
			return 2;
		}
		return 0;
	}
	
	public void createButton(JButton btn, int x, int y, int width, int height) {
	    btn.setBounds(x, y, width, height);
        CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(btn);
	}
	
	public void createPanel(JPanel panel, int x, int y, int width, int height) {
	    panel.setBounds(x, y, width, height);
        CompareOrMergeTwoCVsJFrameWindow.getContentPane().add(panel);
	}
	
	public static void makeVisible(){	
		CompareOrMergeTwoCVsJFrameWindow.setVisible(true);		
	}
	
    public void setModel(int i) {
        if (i==1) {
            modelFirst.clear();
        }else{
            modelSecond.clear();
        }        
    }
    
    public void setCounter(int counter,int i) {
        if (i==1) {
            openFirstCounter=counter;
        }else{
            openSecondCounter=counter;
        }
    }
    
    public int getCounter(int i) {
        if (i==1) {
            return openFirstCounter;
        }else{
            return openSecondCounter;
        }
    }
    
    public void setCheckCounter(int checkC) {
        checkCounter=checkC;
        
    }
    
    public int getCheckCounter() {
        return checkCounter;
        
    }
    
    public void setPath(String path,int i) {
        if (i==1) {
            openFirstCvFileChooserPath=path;
        }else{
            openSecondCvFileChooserPath=path;
        }
    }
    
    public String getPath(int i) {
        if (i==1) {
            return openFirstCvFileChooserPath;
        }else{
            return openSecondCvFileChooserPath;
        }
    }
    
    public void setcvSectionsJList(JList<String>  list,int i) {
        if (i==1) {
            firstCVSectionsJList=list;
        }else{
            secondCVSectionsJList=list;
        }
    }
    
    public JList<String> getCvSectionsJList(int i){
        if (i==1) {
            return firstCVSectionsJList;
        }else{
            return secondCVSectionsJList;
        }
    }
    
    public void setCvTemplate(CVTemplate cvTemplate,int i) {
        if (i==1) {
            cvtemplate1=cvTemplate;
        }else{
            cvtemplate2=cvTemplate;
        }
    }
    
    public CVTemplate getCvTemplate(int i) {
        if (i==1) {
            return cvtemplate1;
        }else{
            return cvtemplate2;
        }
    }
    
    public void addmodel(DefaultListModel<String> model, int i) {
        if (i==1) {
            modelFirst=model;
        }else{
            modelSecond=model;
        }	
    }
}
