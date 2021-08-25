package gui;

import java.awt.Color;

import javax.swing.JFrame;

import dataManagePackage.CVTemplate;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CompareTwoCVsJFrameWindow {
	
	static JFrame CompareTwoCVsJFrameWindow;
	private JFrame appMainWindow;
	static CVTemplate cvtemplate1, cvtemplate2;
	private JTextField txtSeeTheDifferences;
	private static JTextField txtProfessionalProfile1, txtProfessionalProfile2, txtSkillsAndExperience1, txtSkillsAndExperience2,
				txtCoreStrengths1, txtCoreStrenghts2, txtProfessionalExperience1, txtProfessionalExperience2, txtCareerSummary1,
				txtCareerSummary2, txtEducationAndTraining1, txtEducationAndTraining2, txtFurtherCourses1, txtFurtherCourses2,
				txtAdditionalInformation1, txtAdditionalInformation2, txtInterests1, txtInterests2;
	
	private static JButton btnProfessionalProfile, btnSkillsAndExperience, btnCoreStrengths, btnProfessionalExperience, 
				btnCareerSummary, btnEducationAndTraining, btnFurtherCourses, btnAdditionalInformation, btnInterests;
	
	public CompareTwoCVsJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		
		CompareTwoCVsJFrameWindow = new JFrame();
		CompareTwoCVsJFrameWindow.getContentPane().setBackground(Color.DARK_GRAY);
		CompareTwoCVsJFrameWindow.setTitle("Curriculum Vitae (CV) Compare or Merge");
		CompareTwoCVsJFrameWindow.setResizable(false);
		CompareTwoCVsJFrameWindow.setBounds(400, 150, 478, 570);
		CompareTwoCVsJFrameWindow.setLocationRelativeTo(null);
		CompareTwoCVsJFrameWindow.getContentPane().setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 500, 101, 30);
		CompareTwoCVsJFrameWindow.getContentPane().add(btnBack);
		
		btnProfessionalProfile = new JButton("Professional Profile");
		createButton(btnProfessionalProfile, 10, 384, 146, 23);
		
		btnSkillsAndExperience = new JButton("Skills and Experience");
		createButton(btnSkillsAndExperience, 166, 384, 146, 23);
		btnSkillsAndExperience.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnCoreStrengths = new JButton("Core Strengths");
		createButton(btnCoreStrengths, 322, 384, 146, 23);
		
		btnProfessionalExperience = new JButton("Professional Experience");
		createButton(btnProfessionalExperience, 10, 418, 146, 23);
		btnProfessionalExperience.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnCareerSummary = new JButton("Career Summary");
		createButton(btnCareerSummary, 166, 418, 146, 23);
		btnCareerSummary.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		btnEducationAndTraining = new JButton("Education and Training");
		createButton(btnEducationAndTraining, 322, 418, 146, 23);
		btnEducationAndTraining.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnFurtherCourses = new JButton("Further Courses");
		createButton(btnFurtherCourses, 10, 452, 146, 23);
		btnFurtherCourses.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnAdditionalInformation = new JButton("Additional Information");
		createButton(btnAdditionalInformation, 166, 452, 146, 23);
		btnAdditionalInformation.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		btnInterests = new JButton("Interests");
		createButton(btnInterests, 322, 452, 146, 23);
		
		txtSeeTheDifferences = new JTextField();
		txtSeeTheDifferences.setEditable(false);
		txtSeeTheDifferences.setBackground(Color.GRAY);
		txtSeeTheDifferences.setText("See the differences in:");
		txtSeeTheDifferences.setBounds(0, 350, 483, 23);
		CompareTwoCVsJFrameWindow.getContentPane().add(txtSeeTheDifferences);
		txtSeeTheDifferences.setColumns(10);
		
		JLabel lblFirstCv = new JLabel("First CV");
		lblFirstCv.setForeground(Color.WHITE);
		lblFirstCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFirstCv.setBounds(31, 11, 167, 43);
		CompareTwoCVsJFrameWindow.getContentPane().add(lblFirstCv);
		
		JLabel lblSecondCv = new JLabel("Second CV");
		lblSecondCv.setForeground(Color.WHITE);
		lblSecondCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblSecondCv.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSecondCv.setBounds(273, 11, 167, 43);
		CompareTwoCVsJFrameWindow.getContentPane().add(lblSecondCv);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(238, 0, 10, 350);
		CompareTwoCVsJFrameWindow.getContentPane().add(panel_1);
		
		txtProfessionalProfile1 = new JTextField();
		createTextField(txtProfessionalProfile1, "Professional Profile", SwingConstants.CENTER, 0, 57, 248, 20);
		
		txtProfessionalProfile2 = new JTextField();
	    createTextField(txtProfessionalProfile2, "Professional Profile", SwingConstants.CENTER, 238, 57, 245, 20);
		
		txtSkillsAndExperience1 = new JTextField();
		createTextField(txtSkillsAndExperience1, "Skills and Experience", SwingConstants.CENTER, 0, 88, 248, 20);
	
		txtSkillsAndExperience2 = new JTextField();
	    createTextField(txtSkillsAndExperience2, "Skills and Experience", SwingConstants.CENTER, 238, 88, 245, 20);
	      
		txtCoreStrengths1 = new JTextField();
		createTextField(txtCoreStrengths1, "Core Strengths", SwingConstants.CENTER, 0, 119, 248, 20);
		
		txtCoreStrenghts2 = new JTextField();
		createTextField(txtCoreStrenghts2, "Core Strengths", SwingConstants.CENTER, 238, 119, 248, 20);
		
		txtProfessionalExperience1 = new JTextField();
		createTextField(txtProfessionalExperience1, "Professional Experience", SwingConstants.CENTER, 0, 150, 248, 20);
		
		txtProfessionalExperience2 = new JTextField();
		createTextField(txtProfessionalExperience2, "Professional Experience", SwingConstants.CENTER, 238, 150, 248, 20);
		
		txtCareerSummary1 = new JTextField();
		createTextField(txtCareerSummary1, "Career Summary", SwingConstants.CENTER, 0, 181, 248, 20);
		
		txtCareerSummary2 = new JTextField();
		createTextField(txtCareerSummary2, "Career Summary", SwingConstants.CENTER, 238, 181, 248, 20);
		
		txtEducationAndTraining1 = new JTextField();
		createTextField(txtEducationAndTraining1, "Education and Training", SwingConstants.CENTER, 0, 212, 248, 20);
	
		txtEducationAndTraining2 = new JTextField();
		createTextField(txtEducationAndTraining2, "Education and Training", SwingConstants.CENTER, 235, 212, 248, 20);
		
		txtFurtherCourses1 = new JTextField();
		createTextField(txtFurtherCourses1, "Further Courses", SwingConstants.CENTER, 0, 243, 248, 20);
		
		txtFurtherCourses2 = new JTextField();
		createTextField(txtFurtherCourses2, "Further Courses", SwingConstants.CENTER, 238, 243, 248, 20);

		txtAdditionalInformation1 = new JTextField();
		createTextField(txtAdditionalInformation1, "Additional Information", SwingConstants.CENTER, 0, 274, 248, 20);
		
		txtAdditionalInformation2 = new JTextField();
        createTextField(txtAdditionalInformation2, "Additional Information", SwingConstants.CENTER, 238, 274, 248, 20);
		
		txtInterests1 = new JTextField();
		createTextField(txtInterests1, "Interests", SwingConstants.CENTER, 0, 305, 248, 20);

		txtInterests2 = new JTextField();
		createTextField(txtInterests2, "Interests", SwingConstants.CENTER, 238, 305, 248, 20);

		JPanel panel = new JPanel();
		panel.setBounds(0, 65, 483, 424);
		CompareTwoCVsJFrameWindow.getContentPane().add(panel);
		
		
		/* Listeners */
		
		btnBack.addActionListener(new ActionListener() {		
			
			public void actionPerformed(ActionEvent e) {
				
				
				CompareTwoCVsJFrameWindow.setVisible(false);
				CompareOrMergeTwoCVsJFrameWindow.makeVisible();
				
				
			}
		
		});
		
		AddFieldListener field1 = new AddFieldListener(this, "Professional Profile:");
		btnProfessionalProfile.addActionListener(field1);
		  
		AddFieldListener field2 = new AddFieldListener(this, "Additional Information:");
		btnAdditionalInformation.addActionListener(field2);
		 
		AddFieldListener field3 = new AddFieldListener(this, "Interests:");
		btnInterests.addActionListener(field3);
		  
		AddFieldListener field4 = new AddFieldListener(this, "Education and Training:");
		btnEducationAndTraining.addActionListener(field4);
		  
		AddFieldListener field5 = new AddFieldListener(this, "Further Courses:");
		btnFurtherCourses.addActionListener(field5);
		  
		AddFieldListener field6 = new AddFieldListener(this, "Skills and Experience:");
		btnSkillsAndExperience.addActionListener(field6);
		  
		AddFieldListener field7 = new AddFieldListener(this, "Professional Experience:");
		btnProfessionalExperience.addActionListener(field7);
		  
		AddFieldListener field8 = new AddFieldListener(this,  "Career Summary:");
		btnCareerSummary.addActionListener(field8);
		  
		AddFieldListener field9 = new AddFieldListener(this, "Core Strengths:");
		btnCoreStrengths.addActionListener(field9);
	}
	
	public void createButton(JButton btn, int x, int y, int width, int height) {
	    btn.setEnabled(false);
        btn.setBounds(x, y, width, height);
        CompareTwoCVsJFrameWindow.getContentPane().add(btn);
	}
	
	public void createTextField(JTextField textField, String text, int hTextPos, int x, int y, int width, int height) {
	    textField.setEnabled(false);
	    textField.setEditable(false);
	    textField.setText(text);
	    textField.setHorizontalAlignment(hTextPos);
	    textField.setColumns(10);
	    textField.setBounds(x, y, width, height);
        CompareTwoCVsJFrameWindow.getContentPane().add(textField);
	}
	
	
	public static void makeVisible(CVTemplate inputcvtemplate1, CVTemplate inputcvtemplate2){
		
		cvtemplate1 = inputcvtemplate1;
		cvtemplate2 = inputcvtemplate2;
		CompareTwoCVsJFrameWindow.setVisible(true);
		initializeTextFields();
		
	}
	
	public static void initializeTextFields(){
		
		for (int i=0; i<cvtemplate1.getNumberOfSectionObj();i++){
			if (cvtemplate1.getSectionObjTitle(i).equals("Professional Profile:")) txtProfessionalProfile1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Skills and Experience:")) txtSkillsAndExperience1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Career Summary:")) txtCareerSummary1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Education and Training:")) txtEducationAndTraining1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Professional Experience:")) txtProfessionalExperience1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Core Strengths:")) txtCoreStrengths1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Further Courses:")) txtFurtherCourses1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Additional Information:")) txtAdditionalInformation1.setEnabled(true);
			else if (cvtemplate1.getSectionObjTitle(i).equals("Interests:")) txtInterests1.setEnabled(true);
			
		}
		
		for (int i=0; i<cvtemplate2.getNumberOfSectionObj();i++){
			if (cvtemplate2.getSectionObjTitle(i).equals("Professional Profile:")) txtProfessionalProfile2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Skills and Experience:")) txtSkillsAndExperience2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Career Summary:")) txtCareerSummary2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Education and Training:")) txtEducationAndTraining2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Professional Experience:")) txtProfessionalExperience2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Core Strengths:")) txtCoreStrenghts2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Further Courses:")) txtFurtherCourses2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Additional Information:")) txtAdditionalInformation2.setEnabled(true);
			else if (cvtemplate2.getSectionObjTitle(i).equals("Interests:")) txtInterests2.setEnabled(true);
			
		}
		
		for (int i=0; i<cvtemplate1.getNumberOfSectionObj(); i++){
			for (int j=0; j<cvtemplate2.getNumberOfSectionObj();j++){
				if(cvtemplate1.getSectionObjTitle(i).equals(cvtemplate2.getSectionObjTitle(j))){
					if(cvtemplate1.getSectionObjTitle(i).equals("Professional Profile:")) btnProfessionalProfile.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Skills and Experience:")) btnSkillsAndExperience.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Career Summary:")) btnCareerSummary.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Education and Training:")) btnEducationAndTraining.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Professional Experience:")) btnProfessionalExperience.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Core Strengths:")) btnCoreStrengths.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Further Courses:")) btnFurtherCourses.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Additional Information:")) btnAdditionalInformation.setEnabled(true);
					else if(cvtemplate1.getSectionObjTitle(i).equals("Interests:")) btnInterests.setEnabled(true);
					
					
				}
				
				
			}
			
		}		
	}
	
	public CVTemplate getCvTemplate(int i) {
        if (i==1) {
            return cvtemplate1;
        }else{
            return cvtemplate2;
        }
    }
}
