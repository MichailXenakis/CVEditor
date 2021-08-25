package gui;
import javax.swing.*;

import dataManagePackage.BulletList;
import dataManagePackage.BulletListItem;
import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import dataManagePackage.Section;
import outputManagePackage.OutputFactory;
import outputManagePackage.OutputSystemLatex;
import outputManagePackage.OutputSystemTxt;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Color;

public class ChronologicalJFrameWindow extends JDialog{
	
	static JFrame ChronologicalJFrameWindow;
	static JFrame GeneralInfo;
	private JFrame appMainWindow;
	
	static String[] generalInformation = new String[5];
	
	static ArrayList<String> educationAndTraining = new ArrayList<String>();
	static ArrayList<String> furtherCourses = new ArrayList<String>();
	static ArrayList<String> dateList = new ArrayList<String>();
	
	static HashMap<String, String> professionalExperience = new HashMap<String, String>();
	
	static CVTemplate cvtemplate;
	static CVMainJFrameWindow mainwindow;
	static String interests, additionalInformation, professionalProfile, coreStrengths;
	static String oldDate, newDate, tempKey, tempStr;
	
	public ChronologicalJFrameWindow(final JFrame appMainWindow) {
		this.appMainWindow = appMainWindow;
		
		ChronologicalJFrameWindow = new JFrame();
		ChronologicalJFrameWindow.getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		ChronologicalJFrameWindow.setTitle("Curriculum Vitae (CV) Editor");
		ChronologicalJFrameWindow.setResizable(false);
		ChronologicalJFrameWindow.setBounds(400, 150, 725, 350);
		ChronologicalJFrameWindow.setLocationRelativeTo(null);
		ChronologicalJFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChronologicalJFrameWindow.getContentPane().setLayout(null);
//		onExit(ChronologicalJFrameWindow, false);
		
		JButton btnGeneralInformation = new JButton("General Information");
		createButton(btnGeneralInformation, 10, 23, 154, 51);
		
		JButton btnProfessionalProfile = new JButton("Professional Profile");
		createButton(btnProfessionalProfile, 185, 23, 154, 51);
		
		JButton btnProfessionalExperience = new JButton("Professional Experience");
		createButton(btnProfessionalExperience, 368, 23, 154, 51);
		
		JButton btnCoreStrengths = new  JButton("Core Strengths");
		createButton(btnCoreStrengths, 550, 23, 154, 51);
		
		JButton btnEducationAndTraining = new JButton("Education And Training");
		createButton(btnEducationAndTraining, 10, 109, 154, 51);
		
		JButton btnFurtherCourses = new JButton("Further Courses");
		createButton(btnFurtherCourses, 187, 109, 152, 57);
		
		JButton btnAdditionalInformation = new JButton("Additional Info");
		createButton(btnAdditionalInformation, 360, 110, 154, 56);
		
		JButton btnInterests = new JButton("Interests");
		createButton(btnInterests, 550, 109, 152, 57);
		
		JButton btnCancel = new JButton("Cancel");
		createButton(btnCancel, 10, 279, 154, 31);
		
		JCheckBox chckbxTxt = new JCheckBox("txt");
		createCheckBox(chckbxTxt, 425, 258, 97, 23, SwingConstants.TRAILING);
		
		JCheckBox chckbxTex = new JCheckBox("tex");
		createCheckBox(chckbxTex, 425, 284, 97, 23, SwingConstants.TRAILING);
		JButton btnSave = new JButton("Save");
		createButton(btnSave, 552, 258, 152, 52);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 205, 719, 116);
		ChronologicalJFrameWindow.getContentPane().add(panel);
		
		
		eventListenerGeneralInformation(appMainWindow, btnGeneralInformation);
		eventListenerDate(btnEducationAndTraining, "Education And Training", appMainWindow);
		eventListenerDate(btnFurtherCourses, "Further Courses", appMainWindow);
		eventListener(btnProfessionalProfile,"Professional Profile", appMainWindow);
		eventListener(btnAdditionalInformation, "Additional Information", appMainWindow);
		eventListener(btnInterests,"Interests", appMainWindow);	
		eventListener(btnCoreStrengths, "Core Strengths", appMainWindow);
		eventListenerProfessionalExperience(btnProfessionalExperience, "Professional Experience", appMainWindow);
		/* Listeners */
		
		btnCancel.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				ChronologicalJFrameWindow.setVisible(false);
				CVMainJFrameWindow.makeVisible();
				
			}
		
		});
		
        ChooseFileType filetype= new ChooseFileType(chckbxTxt,chckbxTex,appMainWindow, this);
        btnSave.addActionListener(filetype);
	}
	public void createButton(JButton btn, int x, int y, int width, int height) {
	    btn.setBounds(x, y, width, height);
        ChronologicalJFrameWindow.getContentPane().add(btn);
    }
	
	public void createCheckBox(JCheckBox chck, int x, int y, int width, int height, int hTextPos ) { 
	    chck.setHorizontalAlignment(hTextPos);
	    chck.setBounds(x, y, width, height);
	    ChronologicalJFrameWindow.getContentPane().add(chck);
	}
	
	public static void eventListenerProfessionalExperience(JButton button, String type,JFrame appMainWindow){
        ChronoProfessionalExperienceListener profexp= new ChronoProfessionalExperienceListener(appMainWindow,tempKey,tempStr, professionalExperience);
        button.addActionListener(profexp);
    }
    
    
    public static void eventListener(JButton button, String type,JFrame appMainWindow){
        EventListener eventlistener= new  EventListener(type,appMainWindow);
        button.addActionListener(eventlistener);
    }
    
    public static void eventListenerDate(JButton btnDate, String type,JFrame appMainWindow){
        DateListener datelistener= new DateListener(appMainWindow, type, educationAndTraining, furtherCourses);
        btnDate.addActionListener(datelistener);
    }
    
    public static void eventListenerGeneralInformation(JFrame appMainWindow, JButton btnGeneralInformation){
        GeneralInfoListener generalinfo=new GeneralInfoListener(appMainWindow,generalInformation);
        btnGeneralInformation.addActionListener(generalinfo);		
	}

	public static JLabel createLabel(String labelNam, int x, int y, int width, int height, int fontPosition){
		JLabel label = new JLabel(labelNam);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(x,y,width,height);
		if (fontPosition == 0) {
			label.setHorizontalAlignment(SwingConstants.LEFT);
		}else if (fontPosition == 1) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}else {
			label.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		
		return label;
	}
	
	public static JFrame createFrame(String windowName, int x, int y, int width, int height, boolean exitOnClose){
		JFrame window = new JFrame(windowName);
		window.setBounds(x, y, width, height);
		window.setResizable(false);
		if (exitOnClose){
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else {
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		window.getContentPane().setLayout(null);

		return window;
	}
	
	public static void createFunctDescription(JFrame cvEditor, JLabel labelFunctionalDescription){
		labelFunctionalDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelFunctionalDescription.setBounds(10, 11, 764, 14);
		labelFunctionalDescription.setHorizontalAlignment(SwingConstants.CENTER);
		cvEditor.getContentPane().add(labelFunctionalDescription);
	}	
	
	public static JTextField createTextField(int x, int y, int width, int height){
		JTextField generalField = new JTextField();
		generalField.setBounds(x, y, width, height);
		generalField.setColumns(10);

		return generalField;
	}
	
	public static void sortList(String type){
		
		if (type.equals("Further Courses")){
			Collections.sort(furtherCourses);
		}else if (type.equals("Education And Training")){
			Collections.sort(educationAndTraining);
		}
		
	}
	
	public static void fillDateList(String date, int position){
		String[] temp = date.split(", ");
		dateList.add(temp[position]);
	}
	
	public static boolean checkDateList(JTextField dateField){
		boolean check = true;
		for (String tempDate: dateList){
			try {
				check = checkDate(tempDate, dateField.getText());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		dateList.add(dateField.getText());	
		return check;
	}
	
	public static boolean checkDate(String oldDate, String newDate) throws ParseException {
		Date date1, date2;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		
		date1 = sdf.parse(oldDate);
		date2 = sdf.parse(newDate);
		
		if(date1.compareTo(date2)>0){    //check if earlier first
			return false;
		}
		return true;
	}
	
	public static Date makeDate(String dateStr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sdf.parse(dateStr);
		
		return date;
	}
	
	
	public static void makeVisible(CVTemplate inputcvtemplate){
		
		cvtemplate = inputcvtemplate;
		if(cvtemplate.getApplicantName()!=null){
			initialize();
		}
		ChronologicalJFrameWindow.setVisible(true);
		
	}
	

	public static void initialize(){
		
		
		generalInformation[0]=cvtemplate.getApplicantName();
		generalInformation[1]=cvtemplate.getApplicantAddress();
		generalInformation[2]=cvtemplate.getApplicantHomeTelephone();
		generalInformation[3]=cvtemplate.getApplicantWorkTelephone();
		generalInformation[4]=cvtemplate.getApplicantEmail();
		
		for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
	    	
	    	if(cvtemplate.getSectionObjTitle(i).equals("Professional Profile:")){
	    		professionalProfile = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	} else if(cvtemplate.getSectionObjTitle(i).equals("Additional Information:")){
	    		additionalInformation = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	} else if(cvtemplate.getSectionObjTitle(i).equals("Interests:")){
	    		interests = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Core Strengths:")){
	    		coreStrengths = cvtemplate.getSectionObj(i).getParagraph(0).getContents();
	    		
	    	} else if(cvtemplate.getSectionObjTitle(i).equals("Education and Training:")){
	    		int j=0;
	    		for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
	    			
	    			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    			String reportDate = sdf.format(cvtemplate.getSectionObj(i).getBulletListItem(j).getDate());
	    			educationAndTraining.add(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+reportDate);
	    				
	    		}
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Further Courses:")){
    			int j=0;
    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
 
    				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    				String reportDate = sdf.format(cvtemplate.getSectionObj(i).getBulletListItem(j).getDate());
    				furtherCourses.add(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+", "+reportDate);
    				
    			}
    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
	    		String temp = " ";
	    		for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
	    			tempKey =  cvtemplate.getSectionObj(i).getBulletList(j).getTitle().substring(2);
	    			
    				for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
    					if ( k == 0){
    						// Paragraph imported from file
    						temp= cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents();
    					} else if (k == 2) {
    						// First Achievement from file
    						tempStr = cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents();
						} else if (k > 2) {
							// Rest of Achievements from file
							tempStr = (tempStr + ", " + cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents() );	
						}
				
    				}
	    			tempStr = (tempStr + ", " + temp); 
	    			professionalExperience.put(tempKey, tempStr);	
    			}
	    	}
	   
	    }
	}
	
	
	public static void saveCV(int output) throws ParseException{
		
		
		CVTemplate cvToSave = new CVTemplate(generalInformation[0], generalInformation[1], generalInformation[2], 
				  generalInformation[3], generalInformation[4]);

		
		for (int sectionCounter=0;sectionCounter<7;sectionCounter++) {	
			if (sectionCounter==0){
				cvToSave.addSectionObj(sectionCounter , new Section("Professional Profile:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(professionalProfile);
			
			} else if(sectionCounter==1){		
				cvToSave.addSectionObj(sectionCounter , new Section("Additional Information:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(additionalInformation);
		
			} else if(sectionCounter==2){
				cvToSave.addSectionObj(sectionCounter , new Section("Interests:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(interests);
				
			} else if(sectionCounter==2){
				cvToSave.addSectionObj(sectionCounter , new Section("Core Strengths:"));
				cvToSave.getSectionObj(sectionCounter).addParagraph(coreStrengths);
				
			} else if(sectionCounter==3){
				
				cvToSave.addSectionObj(sectionCounter , new Section("Education and Training:"));
				
				for (String count : educationAndTraining){
					String[] temp = count.split(", ");
					String content = temp[0] + ", " + temp[1] + ", " + temp[2];
					Date date = makeDate(temp[3]);
					
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, content));
				}
			} else if(sectionCounter==4){
				
				cvToSave.addSectionObj(sectionCounter , new Section("Further Courses:"));
				
				for (String count : furtherCourses){
					String[] temp = count.split(", ");
					String content = temp[0] + ", " + temp[1] + ", " + temp[2];
					Date date = makeDate(temp[3]);
					
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, content));
				}
			
			} else if(sectionCounter==5){
			
				cvToSave.addSectionObj(sectionCounter , new Section("Professional Experience:"));
				Date date;
				String withoutDate, paragraph;
				String listOfAchievements;
				
				int listCounter = 0;
				int i = 0;
				
				for (String key : professionalExperience.keySet()){
					String[] valueStr = professionalExperience.get(key).split(", ");
					String[] keyStr = key.split(", ");
					date = makeDate(keyStr[2]);					
					withoutDate = keyStr[0] + ", " + keyStr[1];
					paragraph = valueStr[valueStr.length - 1];
					
					cvToSave.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(date, withoutDate));
					cvToSave.getSectionObj(sectionCounter).addBulletList(listCounter, new BulletList(key));
					
					
					cvToSave.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(paragraph));
					
					
					listOfAchievements = ("List Of Achievements" + Integer.toString(i));
					
					cvToSave.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(listOfAchievements));
					
					
					for (String achievement: valueStr){
						if ( !achievement.equals(paragraph)){
							cvToSave.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(achievement));
							
						}
					}
					
					listCounter++;
					i++;
				}
		
			}	
	
		}
	
		Database.addCVtemplateToList(cvToSave); 
		OutputFactory outObj = new OutputFactory();
		outObj.chooseOutput(output);		
	}
	
	public void setProfessionalExp(String tempKey,String tempStr) {
        professionalExperience.put(tempKey , tempStr);
        
    }
    public void removeProfessionalExp(String tempkey) {
        professionalExperience.remove(tempkey);
    }
    public void setTempKey(String tempkey) {
        tempKey=tempkey;
    }
    
    public void setTempStr(String tempstr) {
        tempStr=tempstr;
    }
    public void clearDateList() {
        dateList.clear();
    }
    public void addEducationAndTraining(String str) {
        educationAndTraining.add(str);
    }
    public void addFurtherCourses(String str) {
        furtherCourses.add(str);
    }
    public void removeEducationAndTraining(String str) {
        educationAndTraining.remove(str);
    }
    public void removeFurtherCourses(String str) {
        furtherCourses.remove(str);
    }
    public void setFurtherCourses(int index,String str) {
        furtherCourses.set(index,str);
    }
    public void setEducationAndTraining(int index,String str) {
        educationAndTraining.set(index,str);
    }
    public void setGeneralInfo(int i,String str) {
        generalInformation[i] = str;
    }
}