package outputManagePackage;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.junit.Test;

import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import dataManagePackage.Section;
import inputManagePackage.InputSystemTxt;

public class JUnitTestOutputMerge {
	int i = 0;
	@Test
	public void test() throws ParseException, FileNotFoundException {
		
		CVTemplate testcvtemplate1 = new CVTemplate("GeorgeK", "Dodonis 5", "2651092344", "2651394364","george@gmail.com");
		CVTemplate testcvtemplate2 = new CVTemplate("GeorgeK", "Dodonis 5", "2651092344", "2651394364","george@gmail.com");
		
		DefaultListModel<String> modelFirstTest = new DefaultListModel<>();
		modelFirstTest.addElement("Professional Profile");
		modelFirstTest.addElement("Additional Information");								//needed list selection in order to check sections
		testcvtemplate1.addSectionObj(0, new Section("Professional Profile:"));
		testcvtemplate1.addSectionObj(1, new Section("Additional Information:"));
		JList<String> firstTestCVSectionsJList = new JList<>( modelFirstTest );
		
		DefaultListModel<String> modelSecondTest = new DefaultListModel<>();
		modelSecondTest.addElement("Education and Training");
		modelSecondTest.addElement("Interests");
		testcvtemplate2.addSectionObj(0, new Section("Education and Training:"));
		testcvtemplate2.addSectionObj(1, new Section("Interests:"));
		JList<String> secondTestCVSectionsJList = new JList<>( modelSecondTest );
		
		OutputMerge.mergeTwoCv(testcvtemplate1,testcvtemplate2,firstTestCVSectionsJList,secondTestCVSectionsJList,0);
		
		CVTemplate testcvtemplatemerge = Database.getCvtemplateFromArrayList(0);
		if(testcvtemplatemerge.getApplicantName().equals("GeorgeK")){	// check merge name
			i++;
		}
		if(testcvtemplatemerge.getApplicantEmail().equals("george@gmail.com")){  // check merge email (general information)
			i++;
		}	
																		
		System.out.println(testcvtemplatemerge.getNumberOfSectionObj());	
		assertEquals(i,2);
	}
}