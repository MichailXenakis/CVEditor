package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;


import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import inputManagePackage.*;
import outputManagePackage.OutputSystemLatex;
import outputManagePackage.OutputSystemTxt;

//import inputManagePackage.InputSystem;
//import outputManagePackage.OutputSystemLatex;
//import outputManagePackage.OutputSystemTxt;
//import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.*;

public class CVMainJFrameWindow{

    static InputSystemTxt txtObj = new InputSystemTxt();
    static InputSystemLatex ltxObj = new InputSystemLatex();
	/*
	 * 		Creates the main Window and calls the event Listener of the checkboxes and "Create CV" button.
	 */
	static JFrame cvMainWindow;
	
	
	public static void main(String[] args) {
	 	ChronologicalJFrameWindow ChronologicalJFrameWindow = new ChronologicalJFrameWindow(cvMainWindow);
		FunctionalJFrameWindow FunctionalJFrameWindow = new FunctionalJFrameWindow(cvMainWindow);
		CombinedJFrameWindow CombinedJFrameWindow = new CombinedJFrameWindow(cvMainWindow);
		CompareOrMergeTwoCVsJFrameWindow CompareTwoCVsJFrameWindow = new CompareOrMergeTwoCVsJFrameWindow(cvMainWindow);

		cvMainWindow = createFrame();
	//	onExit(cvMainWindow, false);
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(500, 500, 500, 500));
		panel.setSize(756, 568);
		//panel.setBackground(Color.RED);
		cvMainWindow.getContentPane().add(panel);
		panel.setLayout(null);

		JButton createCv = new JButton("Create a CV");
	    createButton(panel, createCv, 562, 203, 129, 49, AbstractButton.CENTER, AbstractButton.LEADING, "Click This Button to create a new CV");

		JLabel lblCheck = new JLabel("Hello. Please check what kind of CV you want to create and click on \"Create a CV\".");
		lblCheck.setForeground(Color.WHITE);
		lblCheck.setHorizontalAlignment(SwingConstants.CENTER);
		createLabel(panel, lblCheck, 10, 11, 712, 49);

		JPanel panel1 = new JPanel();
        createPanel(panel, panel1, 0, 0, 795, 56, Color.DARK_GRAY);


		JCheckBox chckbxChronological = new JCheckBox("Chronological");
		createCheckBox(chckbxChronological, panel, "Chronological", 20, 127, 167, 23);

		JCheckBox chckbxFunctional = new JCheckBox("Functional");
		createCheckBox(chckbxFunctional, panel, "Functional", 20, 70, 167, 23);

		JCheckBox chckbxCombined = new JCheckBox("Combined");
		createCheckBox(chckbxCombined, panel, "Combined", 20, 190, 167, 23);

		JLabel labelChronological = new JLabel("Create a Chronological CV");
		createLabel(panel, labelChronological, 30, 157, 211, 14);

		JLabel labelFunctional = new JLabel("Create a Functional CV");
		createLabel(panel, labelFunctional, 30, 100, 129, 14);

		JLabel labelCombined = new JLabel("Create a Combined CV");
		createLabel(panel, labelCombined, 30, 220, 129, 14);



		JLabel lblYouCanSave = new JLabel("Here, you can edit an existing CV in a number of ways.");
		lblYouCanSave.setForeground(Color.WHITE);
		lblYouCanSave.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouCanSave.setBackground(Color.DARK_GRAY);
		createLabel(panel, lblYouCanSave, 0, 303, 773, 69);

		JPanel panel2 = new JPanel();
		createPanel(panel, panel2, 0, 303, 808, 69, Color.DARK_GRAY);

		JButton btnOpenACv = new JButton("Open a CV");
		createButton(panel, btnOpenACv, 70, 407, 143, 76, SwingConstants.CENTER, SwingConstants.LEADING, "Click This Button to open an existing CV");

		JButton btnDeleteACv = new JButton("Delete a CV");
		createButton(panel, btnDeleteACv, 324, 407, 143, 76, SwingConstants.CENTER, SwingConstants.LEADING, "Click This Button to delete an existing CV");

		JButton btnCompareTwoCvs = new JButton("Compare two CV's");
		createButton(panel, btnCompareTwoCvs, 567, 407, 143, 76, SwingConstants.CENTER, SwingConstants.LEADING, "Click This Button to compare or merge two existing CV's");

		JPanel panel3 = new JPanel();
		createPanel(panel, panel3, 0, 494, 795, 86, Color.DARK_GRAY);

		eventListenerMainWindow(cvMainWindow, chckbxChronological, labelChronological, chckbxFunctional, labelFunctional,
								chckbxCombined, labelCombined, createCv, btnOpenACv, btnDeleteACv, btnCompareTwoCvs);
	}

	/*		
	 *         These Methods give values to the Frame, Buttons, Labels created in the main Function
	 */
	public static void createCheckBox(JCheckBox chckBox, JPanel panel, String checkBox, int x, int y, int width, int height) {
	    chckBox.setBounds(x, y, width, height);
	    panel.add(chckBox);
	}
	
	public static void createLabel(JPanel panel, JLabel label, int x, int y, int width, int height) {
	    label.setBounds(x, y, width, height);
	    panel.add(label);
	}
	
	public static void createButton(JPanel panel, JButton btn, int x, int y, int width, int height, int vTextPos, int hTextPos, String toolTip) {
	    btn.setVerticalTextPosition(vTextPos);
        btn.setToolTipText(toolTip);
        btn.setHorizontalTextPosition(hTextPos);
        btn.setBounds(x, y, width, height);
        panel.add(btn);
	}
	 
	public static void createPanel(JPanel panel, JPanel pnl, int x, int y, int width, int height, Color background) {
	    pnl.setBackground(background);
        pnl.setBounds(x, y, width, height);
        panel.add(pnl);
	}
	  
	public static JFrame createFrame(){
		JFrame cvMainWindow =new JFrame("Curriculum Vitae (CV) Creator");
		cvMainWindow.setResizable(false);
		cvMainWindow.setBounds(400, 150, 800, 600);
		cvMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cvMainWindow.setVisible(true);
		return cvMainWindow;
	}

	//---------------------------- EVENT LISTENERS-----------------------

	public static void eventListenerMainWindow(JFrame cvMainWindow, JCheckBox chckbxChronological, JLabel labelChronological,
												JCheckBox chckbxFunctional, JLabel labelFunctional, JCheckBox chckbxCombined,
												JLabel labelCombined, JButton createCV, JButton btnOpenACv, JButton btnDeleteACv,
												JButton btnCompareTwoCvs){

        
	    OpenCVListener opencv = new OpenCVListener(cvMainWindow);
        btnOpenACv.addActionListener(opencv);

        DeleteCVListener deletecv = new DeleteCVListener(cvMainWindow);
        btnDeleteACv.addActionListener(deletecv);


        btnCompareTwoCvs.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CompareOrMergeTwoCVsJFrameWindow.makeVisible();
            }

        });

        DisableOthers dsblothers = new DisableOthers(cvMainWindow, chckbxChronological, chckbxFunctional, chckbxCombined, labelFunctional, labelCombined, labelChronological);
        chckbxChronological.addActionListener(dsblothers);
        chckbxFunctional.addActionListener(dsblothers);
        chckbxCombined.addActionListener(dsblothers);

        CreateCVListener createcv = new CreateCVListener(cvMainWindow, chckbxChronological, chckbxFunctional, chckbxCombined);
        createCV.addActionListener(createcv);

    }

	    
	    
	    
	    
	public static void makeVisible(){
		cvMainWindow.setVisible(true);

	}
}