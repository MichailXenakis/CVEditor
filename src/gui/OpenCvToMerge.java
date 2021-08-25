package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import dataManagePackage.CVTemplate;
import dataManagePackage.Database;
import inputManagePackage.InputSystemLatex;
import inputManagePackage.InputSystemTxt;

public class OpenCvToMerge implements ActionListener{
    private String path;
    private JFrame appMainWindow;
    private int x;
    private JList<String> cvSectionsJList;
    private DefaultListModel<String> model;
    private CVTemplate cvTemplate;
    
    private JScrollPane scrollPanelCv;
    
    private JButton btnCompare;
    private JButton btnOpenCv;
    private CompareOrMergeTwoCVsJFrameWindow CompareObj;
    private InputSystemTxt txtObj = new InputSystemTxt();
    private InputSystemLatex texObj = new InputSystemLatex();

    public OpenCvToMerge(CompareOrMergeTwoCVsJFrameWindow CompareObj, JFrame appMainWindow, String path,JList<String> cvSectionsJList, DefaultListModel<String> model,CVTemplate cvTemplate ,JScrollPane scrollPanelCV, 
              JButton btnCompare,JButton btnOpenCv, int x) {
        
        this.CompareObj=CompareObj;
        this.path=path;
        this.appMainWindow=appMainWindow;
        this.cvSectionsJList=cvSectionsJList;
        this.model=model;
        this.cvTemplate=cvTemplate;
        
        this.scrollPanelCv=scrollPanelCV;
        
        this.btnCompare=btnCompare;
        this.btnOpenCv=btnOpenCv;
        this.x=x;
    }
    
    public void actionPerformed(ActionEvent e) {
        JFileChooser openFirstCvFileChooser = new JFileChooser();
        //CompareOrMergeTwoCVsJFrameWindow CompareObj= new CompareOrMergeTwoCVsJFrameWindow(appMainWindow);
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter( "txt files (*.txt)", "txt");
        
        openFirstCvFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("tex files (*.tex)", "tex"));
        
        openFirstCvFileChooser.setFileFilter(xmlfilter);
        
        openFirstCvFileChooser.setCurrentDirectory(new java.io.File("."));
        openFirstCvFileChooser.setDialogTitle("Please select the CV file.");
        
        
        
        if(openFirstCvFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            
            
           path = openFirstCvFileChooser.getSelectedFile().toString();
           CompareObj.setPath(openFirstCvFileChooser.getSelectedFile().toString(),x);
            JOptionPane.showMessageDialog(null, path, "Directory of chosen CV", JOptionPane.INFORMATION_MESSAGE);
            
            if(path.substring(path.length()-3).equals("txt")){
                try {
                    txtObj.addCvDataFromFile(0,path);
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }else if(path.substring(path.length()-3).equals("tex")){
                try {
                    texObj.addCvDataFromFile(0,path);
                } catch (ParseException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Invalid input file type. Txt or Tex allowed.");
                
                CompareObj.makeVisible();
            }

            
            if (CompareObj.checkIfSameFiles()!=1){
            
                cvSectionsJList = new JList<>( model );
                CompareObj.setcvSectionsJList(cvSectionsJList,x);
                
                cvTemplate = Database.getCvtemplateFromArrayList(CompareObj.getCheckCounter());
                CompareObj.setCvTemplate(cvTemplate,x);
                for (int i=cvTemplate.getNumberOfSectionObj()-1; i>=0; i--){
                    model.addElement(cvTemplate.getSectionObjTitle(i).substring(0,cvTemplate.getSectionObjTitle(i).length()-1));
                    CompareObj.addmodel(model,x);
                }
                cvSectionsJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                cvSectionsJList.setForeground(Color.DARK_GRAY);
                cvSectionsJList.setFont(new Font("Tahoma", Font.BOLD, 15));
                scrollPanelCv.setViewportView(cvSectionsJList);
                //checkCounter++;
                CompareObj.setCheckCounter(CompareObj.getCheckCounter()+1);
                //getopenCounter++;
                CompareObj.setCounter(CompareObj.getCounter(x)+1, x);
            }
            
            if(CompareObj.getCheckCounter()>=2) btnCompare.setEnabled(true);
            
        }
        
        
        if (CompareObj.getCounter(x)==1) btnOpenCv.setEnabled(false);
        CompareObj.makeVisible();

        
    }

}
