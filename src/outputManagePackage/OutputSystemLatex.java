package outputManagePackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import dataManagePackage.CVTemplate;
import dataManagePackage.Database;

public class OutputSystemLatex extends OutputSystem{
    static PrintWriter outputStream;
	
	public static void saveApplicantInfoToLatex(String savePath, int applicantIndex){
		OutputSystem outObj = new OutputSystemLatex();
		CVTemplate cvtemplate = Database.getCvtemplateFromArrayList(applicantIndex);
		
		outputStream = null;
		try
		{
			outputStream = new PrintWriter(new FileOutputStream
					(savePath+"//"+cvtemplate.getApplicantName()+".tex"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Problem opening: "+savePath+"//"+cvtemplate.getApplicantName()+".tex");
		}
		
		outputStream.println("\\documentclass[12pt]{article}");
		outputStream.println("\\begin{document}");
		outputStream.println("\\title{CV of "+cvtemplate.getApplicantName()+"}");
		outputStream.println("\\maketitle");
		outputStream.println("\\section{General Information}");
		
		outObj.printOutputStream(outputStream, cvtemplate);
		
		for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
	    	
	    	
			outputStream.println("\\section{"+cvtemplate.getSectionObjTitle(i)+"}");
	    	if(cvtemplate.getSectionObjTitle(i).equals("Professional Profile:") ||
	    			cvtemplate.getSectionObjTitle(i).equals("Core Strenghts:") ||
	    			cvtemplate.getSectionObjTitle(i).equals("Additional Information:") ||
	    			cvtemplate.getSectionObjTitle(i).equals("Interests:")){
	    		outputStream.println(cvtemplate.getSectionObj(i).getParagraph(0).getContents()+"\\"+"\\");
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Career Summary:") || 
	    			cvtemplate.getSectionObjTitle(i).equals("Education and Training:") ||
	    			cvtemplate.getSectionObjTitle(i).equals("Further Courses:")){
	    			int j=0;
	    			outputStream.println("\\begin{itemize}");
	    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
	    				outputStream.println("\\item "+cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+" "+cvtemplate.getSectionObj(i).getBulletListItem(j).getStringDate());
	    				
	    			}
	    			outputStream.println("\\end{itemize}");
	    		
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){

	    			for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
	    				outputStream.println("\\subsection{"+cvtemplate.getSectionObj(i).getBulletList(j).getTitle()+"}");
	    				outputStream.println("\\begin{itemize}");
	    				for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
	    					outputStream.println("\\item "+cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
	    				}
	    				outputStream.println("\\end{itemize}");
	    			}
	    	}else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
	    		
	    		int j=0;
	    		int k=0;
    			for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
    				outputStream.println("\\subsection{"+cvtemplate.getSectionObj(i).getBulletListItem(j).getContents()+" "+cvtemplate.getSectionObj(i).getBulletListItem(j).getStringDate()+"}");
    				outputStream.println("\\begin{itemize}");
    				for (k=0;k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
    					if(k==0 || k==1) outputStream.println("\\item "+cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
    					else outputStream.println(cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents()+"\\"+"\\");
    				}
    				outputStream.println("\\end{itemize}");
    			}
	    	}
	    	outputStream.println();
	    }
		
		outputStream.println("\\end{document}");
		outputStream.close();
	}
	
	
}
