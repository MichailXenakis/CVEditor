package inputManagePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import dataManagePackage.BulletList;
import dataManagePackage.BulletListItem;
import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import dataManagePackage.Section;


public class InputSystemTxt extends InputSystem{
	
	Scanner inputStream = null;
	Scanner checkStream = null;
	int checkIfSection = 0;
	int notSkipLine = 0;
	protected CVTemplate cvtemplate;
	protected ArrayList<Section> inputCvTemplateSectionObj = new ArrayList<Section>();

	public InputSystemTxt(){
	    this.generalInfoEndTagLength = 0;
	}

	protected void skipLines(Scanner inputStream) {
	    // Nothing to skip in the case of Txt
	}
	
	public void addCvDataFromFile(int index, String filePath) throws ParseException{
		InputSystem inpObj = new InputSystemTxt();	
	    try {
	        inputStream = new Scanner(new FileInputStream(filePath));
	        checkStream = new Scanner(new FileInputStream(filePath));
	        cvtemplate = inpObj.createTemplateWithGeneralInfo(inputStream);
	    } catch(FileNotFoundException e){
	        System.out.println("Problem opening file.");
	        System.exit(0);
    } 

    /*		try{
			inputStream = new Scanner(new FileInputStream(filePath));
			checkStream = new Scanner(new FileInputStream(filePath));
		}catch(FileNotFoundException e){
			System.out.println("Problem opening file.");
			System.exit(0);
		}
*/		
		int i=0;
        while(checkStream.hasNextLine() && checkStream.nextLine().length()>0){
			i++;
			checkStream.nextLine();
		}
		
		if(i<4){ 
			JOptionPane.showMessageDialog(null, "Invalid input file. Reached eof while on General Info.");
			return;
		}
			
/*	
		String applicantName = inputStream.nextLine().substring(6);
		String applicantAddress = inputStream.nextLine().substring(9);
		String applicantHomeTelephone = inputStream.nextLine().substring(18);
		String applicantWorkTelephone = inputStream.nextLine().substring(18);
		String applicantEmail = inputStream.nextLine().substring(7);
		
		CVTemplate cvtemplate = new CVTemplate(applicantName, applicantAddress, applicantHomeTelephone, 
				applicantWorkTelephone, applicantEmail);
*/	
		int sectionCounter = 0;
		while(inputStream.hasNextLine()){
			if(notSkipLine==0){
				inputStream.nextLine();			
			}
			notSkipLine=0;
			inputCvAddSection(sectionCounter, cvtemplate);	 
		}
		
		inputStream.close();
		checkStream.close();
		
		templateTypeIdentifier(cvtemplate);
		Database.addCVtemplateToList(cvtemplate);
		
	}
	
	private final void inputCvAddSection(int sectionCounter, CVTemplate cvtemplate) throws ParseException{
		String checkNextLine=null; 
		
		if (inputStream.hasNextLine()){
			checkNextLine = inputStream.nextLine();
		}else {
		    checkNextLine = "exit";
		}
		
//		if(sectionRepetitionCheck(checkNextLine, cvtemplate)) return;
		
//		if(checkNextLine.equals("Professional Profile:")){
		if(isSingleParagraphSection(checkNextLine) == true) {
			cvtemplate.addSectionObj(sectionCounter , new Section(checkNextLine));
			cvtemplate.getSectionObj(sectionCounter).addParagraph(inputStream.nextLine().substring(1));
			
			
/*		}else if(checkNextLine.equals("Additional Information:")){
			
			
			cvtemplate.addSectionObj(sectionCounter , new Section("Additional Information:"));
			cvtemplate.getSectionObj(sectionCounter).addParagraph(inputStream.nextLine().substring(1));
			
			
		}else if(checkNextLine.equals("Interests:")){
			
			cvtemplate.addSectionObj(sectionCounter , new Section("Interests:"));
			cvtemplate.getSectionObj(sectionCounter).addParagraph(inputStream.nextLine().substring(1));
			
			
		}else if(checkNextLine.equals("Education and Training:")){
			
			cvtemplate.addSectionObj(sectionCounter , new Section("Education and Training:"));
			inputCvAddBulletListItem(sectionCounter , cvtemplate);
*/			
		}else if(isDoubleParagraphSection(checkNextLine) == true){
			
			cvtemplate.addSectionObj(sectionCounter , new Section(checkNextLine));
			inputCvAddBulletListItem(sectionCounter , cvtemplate);
			
//		}else if(checkNextLine.equals("Career Summary:")){
//			
//			cvtemplate.addSectionObj(sectionCounter , new Section("Career Summary:"));
//			inputCvAddBulletListItem(sectionCounter , cvtemplate);
//			
		}else if(checkNextLine.equals("Skills and Experience:")){
			
			cvtemplate.addSectionObj(sectionCounter , new Section("Skills and Experience:"));
			inputCvAddBulletList(sectionCounter , cvtemplate);
			
		}else if(checkNextLine.equals("Core Strengths:")){
			
			cvtemplate.addSectionObj(sectionCounter , new Section("Core Strengths:"));
			cvtemplate.getSectionObj(sectionCounter).addParagraph(inputStream.nextLine());
			
		}else if(checkNextLine.equals("Professional Experience:")){
			
			cvtemplate.addSectionObj(sectionCounter , new Section("Professional Experience:"));
			inputCvAddBulletListItemAndItemList(sectionCounter , cvtemplate);
		}	
//		}else{
//			    System.out.println("Invalid Input File "+checkNextLine);			
//		}		
		sectionCounter++;	
	}
	
	
	private static boolean isSingleParagraphSection(String checkNextLine) {
	    if (checkNextLine.equals("Professional Profile:") || checkNextLine.equals("Additional Information:") || checkNextLine.equals("Interests:")){
	        return true;
	    }
	    return false;
	}
	
	private static boolean isDoubleParagraphSection(String checkNextLine) {
	    if(checkNextLine.equals("Further Courses:") || checkNextLine.equals("Career Summary:") || checkNextLine.equals("Education and Training:")) {
	        return true;
	    }
	    return false;
	}
	
	private void inputCvAddBulletList(int sectionCounter, CVTemplate cvtemplate) throws ParseException{
		int listCounter = 0;
		String checkNextLine=inputStream.nextLine();
		
		while (checkNextLine.substring(0, 2).equals("  ")){
			
			cvtemplate.getSectionObj(sectionCounter).addBulletList(listCounter , new BulletList(checkNextLine.substring(2)));
			checkNextLine=inputStream.nextLine();
			
			
			while (checkNextLine.substring(0, 4).equals("    ")){
				cvtemplate.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(checkNextLine.substring(4)));
				checkNextLine=inputStream.nextLine();
				
				if(checkNextLine.isEmpty()) break;
			
			}
			
			if(checkNextLine.isEmpty()) break;
			listCounter++;
			
		}
		notSkipLine=1;
	}
	
	private void inputCvAddBulletListItem(int sectionCounter, CVTemplate cvtemplate) throws ParseException{
		String checkNextLine=inputStream.nextLine();
		while (checkNextLine.substring(0, 2).equals("  ")){
			
			cvtemplate.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(getDate(checkNextLine),getWithoutDate(checkNextLine.substring(2))));
			checkNextLine=inputStream.nextLine();
			
			if(checkNextLine.isEmpty()) break;
		}
		notSkipLine=1;
	}
	
	private void inputCvAddBulletListItemAndItemList(int sectionCounter, CVTemplate cvtemplate) throws ParseException{
			
			int listCounter = 0;
			String checkNextLine=inputStream.nextLine();
		
			while (checkNextLine.substring(0, 2).equals("  ")){
				
				cvtemplate.getSectionObj(sectionCounter).addBulletListItem(new BulletListItem(getDate(checkNextLine),getWithoutDate(checkNextLine.substring(2))));
				cvtemplate.getSectionObj(sectionCounter).addBulletList(listCounter, new BulletList(checkNextLine));
				int listItemCounter = 0;
				checkNextLine=inputStream.nextLine();
	            
				if(checkNextLine.substring(0, 4).equals("    ")){
					cvtemplate.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(checkNextLine.substring(4)));
					checkNextLine=inputStream.nextLine();
					listItemCounter++;
				
					if(checkNextLine.isEmpty()) break;	
				}else{
					System.out.println("Wrong Input Professional Experience");
					break;
				}
	            
				if(checkNextLine.substring(0, 4).equals("    ")){
				
					cvtemplate.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(checkNextLine.substring(4)));
					checkNextLine=inputStream.nextLine();
					listItemCounter++;
								
				}else{
					System.out.println("Wrong Input Professional Experience");
					break;
				}
	            if(checkNextLine.isEmpty()) break;

				while (checkNextLine.substring(0, 6).equals("      ")){
					
					cvtemplate.getSectionObj(sectionCounter).getBulletList(listCounter).addBulletListItem(new BulletListItem(checkNextLine.substring(6)));
					checkNextLine=inputStream.nextLine();
					listItemCounter++;
					if(checkNextLine.isEmpty()) break;
				
				}
				
		
			
			if(checkNextLine.isEmpty()) break;
			listCounter++;
		}
		notSkipLine=1;
	}
	
	private boolean sectionRepetitionCheck(String checkNextLine, CVTemplate cvtemplate){
		for(int i=0; i<cvtemplate.getNumberOfSectionObj(); i++){
			if( checkNextLine.equals(cvtemplate.getSectionObjTitle(i))){
				JOptionPane.showMessageDialog(null, "Repeated section of " + checkNextLine + ". Skipping.");
				if(checkNextLine.equals("Professional Profile:") || checkNextLine.equals("Additional Information:") || checkNextLine.equals("Interests:")){
					inputStream.nextLine();
					return true;
				}

			}
		}
		return false;
		
	}
	
	private Date getDate(String input) throws ParseException{
		Date date;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		date = sdf.parse(input.substring(input.length() - 10));
		
		return date;
		
	}
	
	private String getWithoutDate(String input){
		
		return input.substring(0, input.length() - 12);	
	}
	
	public static void templateTypeIdentifier(CVTemplate cvtemplate){
		int checkForFunctional=0;
		int checkForChronological=0;
		int checkForCombined=0;
		
		for (int i=0; i<cvtemplate.getNumberOfSectionObj(); i++){
			
			if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){
				checkForFunctional++;
				checkForCombined++;
			}else if(cvtemplate.getSectionObjTitle(i).equals("Career Summary:")){
				checkForFunctional++;
				
			}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
				checkForChronological++;
				checkForCombined++;
			}else if(cvtemplate.getSectionObjTitle(i).equals("Core Strengths:")){
				checkForChronological++;
			
			}
		
		}
		
		if(cvtemplate.getNumberOfSectionObj()<4) {
		    JOptionPane.showMessageDialog(null, "Less than 4 sections.");
	    }
		if(checkForChronological!=2 && checkForCombined!=2 && checkForFunctional==2){ 
			JOptionPane.showMessageDialog(null, "Functional CV identified.");
		}else if(checkForChronological==2 && checkForCombined!=2 && checkForFunctional!=2){ 
			JOptionPane.showMessageDialog(null, "Chronological CV identified.");
		}else if(checkForChronological!=2 && checkForCombined==2 && checkForFunctional!=2){ 
			JOptionPane.showMessageDialog(null, "Combined CV identified.");
		}else{
			JOptionPane.showMessageDialog(null, "CV type not identified.");
		}
		
	}
	
	public static int templateTypeSilentIdentifier(CVTemplate cvtemplate){
		int checkForFunctional=0;
		int checkForChronological=0;
		int checkForCombined=0;
		
		for (int i=0; i<cvtemplate.getNumberOfSectionObj(); i++){
			
			if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){
				checkForFunctional++;
				checkForCombined++;
			}else if(cvtemplate.getSectionObjTitle(i).equals("Career Summary:")){
				checkForFunctional++;
				
			}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
				checkForChronological++;
				checkForCombined++;
			}else if(cvtemplate.getSectionObjTitle(i).equals("Core Strengths:")){
				checkForChronological++;
			
			}
		
		}
		
		if(checkForChronological!=2 && checkForCombined!=2 && checkForFunctional==2){ 
			return 0;
		}else if(checkForChronological==2 && checkForCombined!=2 && checkForFunctional!=2){ 
			return 1;
		}else if(checkForChronological!=2 && checkForCombined==2 && checkForFunctional!=2){ 
			return 2;
		}else{
			return -1;
		}
		
		
	}
	
	public int checkIfCVFile(String filePath){
		
		Scanner fileStream = null;
		try {
			fileStream = new Scanner(new FileInputStream(filePath));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    int i=0;
		while(fileStream.hasNextLine() && fileStream.nextLine().length()>0){
			i++;
			fileStream.nextLine();
		}
		
		if(i<4){ 
			JOptionPane.showMessageDialog(null, "Invalid input file. Not a CV file.");
			return 1;
		}
		return 0;
	}
	
}
