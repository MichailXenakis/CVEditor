package outputManagePackage;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.Test;

import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import inputManagePackage.InputSystemLatex;

public class JUnitTestOutputToLatex {
	int i = 0;
    InputSystemLatex ltxObj = new InputSystemLatex();
    
	@Test
	public void test() throws ParseException, FileNotFoundException {
		
		ltxObj.addCvDataFromFile(0,"outputFiles//KostaPapadopoulos.tex");
		OutputSystemLatex.saveApplicantInfoToLatex("outputFiles", 0);
		
		Scanner testOutputStream = new Scanner(new FileInputStream("outputFiles//KostaPapadopoulos.tex"));
		for(int j=0; j<5; j++){
			testOutputStream.nextLine();
		}
		if (testOutputStream.nextLine().equals("Name: Kosta Papadopoulos\\\\")){	//Check correct name output
			i++;
		}
		if (testOutputStream.nextLine().equals("Address: Dwdwnis 5\\\\")){	//Check correct Address output
			i++;
		}
		for(int j=0; j<5; j++){
			testOutputStream.nextLine();
		}
		if (testOutputStream.nextLine().equals("This is my professional profile.\\\\")){	//Check correct Professional output
			i++;
		}
		for(int j=0; j<13; j++){
			testOutputStream.nextLine();
		}
		if (testOutputStream.nextLine().equals("\\subsection{Skills and Experience on Maths}")){	//Check correct BulletList output
			i++;
		}
		testOutputStream.nextLine();
		if (testOutputStream.nextLine().equals("\\item Arithmetic")){	//Check correct Arithmetic output
			i++;
		}
		assertEquals(i,5);
	}
}