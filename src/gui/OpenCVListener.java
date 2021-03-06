package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import inputManagePackage.InputFactory;
import inputManagePackage.InputSystemLatex;
import inputManagePackage.InputSystemTxt;
import outputManagePackage.OutputSystemLatex;
import outputManagePackage.OutputSystemTxt;

public class OpenCVListener implements ActionListener {
    private JFrame cvMainWindow;
    static InputSystemTxt txtObj = new InputSystemTxt();
    static InputSystemLatex ltxObj = new InputSystemLatex();
    
    public OpenCVListener(JFrame cvMainWindow) {
        this.cvMainWindow = cvMainWindow;
    }
    //btnOpenACv.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFileChooser openCvFileChooser = new JFileChooser();
    
            FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "txt files (*.txt)", "txt");
    
            openCvFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
            //openCvFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
    
            openCvFileChooser.setFileFilter(xmlfilter);
            openCvFileChooser.setCurrentDirectory(new java.io.File("."));
            openCvFileChooser.setDialogTitle("Please select the CV file.");
    
    
    
            if(openCvFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    
    
                String openCvFileChooserPath = openCvFileChooser.getSelectedFile().toString();
                JOptionPane.showMessageDialog(null, openCvFileChooserPath, "Directory of chosen CV", JOptionPane.INFORMATION_MESSAGE);
    
                
                InputFactory inObj = new InputFactory();
                inObj.chooseFile(openCvFileChooserPath, cvMainWindow);

                CVTemplate cvtemplate = Database.getCvtemplateFromArrayList(Database.getCVtemplateArrayListSize()-1);
                System.out.println(cvtemplate.getApplicantName());
                System.out.println(cvtemplate.getApplicantAddress());
                System.out.println(cvtemplate.getApplicantHomeTelephone());
                System.out.println(cvtemplate.getApplicantWorkTelephone());
                System.out.println(cvtemplate.getApplicantEmail());
    
                for (int i=cvtemplate.getNumberOfSectionObj()-1; i>=0; i--){
    
                    System.out.println(cvtemplate.getSectionObjTitle(i));
                    if(cvtemplate.getSectionObjTitle(i).equals("Professional Profile:") ||
                            cvtemplate.getSectionObjTitle(i).equals("Core Strenghts:") ||
                            cvtemplate.getSectionObjTitle(i).equals("Additional Information:") ||
                            cvtemplate.getSectionObjTitle(i).equals("Interests:")){
                            System.out.println(cvtemplate.getSectionObj(i).getParagraph(0).getContents());
    
                    }else if(cvtemplate.getSectionObjTitle(i).equals("Career Summary:") ||
                            cvtemplate.getSectionObjTitle(i).equals("Education and Training:") ||
                            cvtemplate.getSectionObjTitle(i).equals("Further Courses:")){
                            int j=0;
                            for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
                                System.out.println(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents());
    
                            }
    
                    }else if(cvtemplate.getSectionObjTitle(i).equals("Skills and Experience:")){
    
                            for (int j=0;j<cvtemplate.getSectionObj(i).getNumberOfBulletLists();j++){
                                System.out.println(cvtemplate.getSectionObj(i).getBulletList(j).getTitle());
    
                                for(int k=0; k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k ++){
                                    System.out.println(cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
                                }
                            }
                    }else if(cvtemplate.getSectionObjTitle(i).equals("Professional Experience:")){
    
                        int j=0;
                        int k=0;
                        for (j=0;j<cvtemplate.getSectionObj(i).getSizeOfSingleBulletListItems();j++){
                            System.out.println(cvtemplate.getSectionObj(i).getBulletListItem(j).getContents());
                            for (k=0;k<cvtemplate.getSectionObj(i).getBulletList(j).getNumberOfItems();k++){
                                System.out.println(cvtemplate.getSectionObj(i).getBulletList(j).getBulletListItem(k).getContents());
    
                            }
    
                        }
                    }
    
                }
                System.out.println(InputSystemTxt.templateTypeSilentIdentifier(cvtemplate));
                if (InputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==0){
                    cvMainWindow.setVisible(false);
                    System.out.println("Opening funct");
                    FunctionalJFrameWindow.makeVisible(cvtemplate);
    
                }else if (InputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==1){
                    cvMainWindow.setVisible(false);
                    System.out.println("Opening chron");
                    ChronologicalJFrameWindow.makeVisible(cvtemplate);
    
                }else if (InputSystemTxt.templateTypeSilentIdentifier(cvtemplate)==2){
                    cvMainWindow.setVisible(false);
                    System.out.println("Opening comb");
                    CombinedJFrameWindow.makeVisible(cvtemplate);
                }
                OutputSystemTxt.saveApplicantInfoToTxt("outputFiles", 0);
                OutputSystemLatex.saveApplicantInfoToLatex("outputFiles", 0);
    
            }
    
        }

//});
    
}
