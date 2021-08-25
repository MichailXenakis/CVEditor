package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import dataManagePackage.CVTemplate;
import outputManagePackage.OutputMerge;

public class MergeListener implements ActionListener{
    int outputType;
    private JList<String> secondCVSectionsJList;
    private JList<String> firstCVSectionsJList;
    private CVTemplate cvtemplate2;
    private CVTemplate cvtemplate1;
    private String openSecondCvFileChooserPath;
    private String openFirstCvFileChooserPath;
    private JCheckBox chckbxTex;
    private JCheckBox chckbxTxt;
    private CompareOrMergeTwoCVsJFrameWindow compareObj;
    public MergeListener(CompareOrMergeTwoCVsJFrameWindow compareObj, JCheckBox chckbxTxt,JCheckBox chckbxTex,String openFirstCvFileChooserPath, String openSecondCvFileChooserPath,CVTemplate cvtemplate1,CVTemplate cvtemplate2,
            JList<String> firstCVSectionsJList, JList<String> secondCVSectionsJList) {
        this.compareObj=compareObj;
        this.chckbxTxt=chckbxTxt;
        this.chckbxTex=chckbxTex;
        this.openFirstCvFileChooserPath=openFirstCvFileChooserPath;
        this.openSecondCvFileChooserPath=openSecondCvFileChooserPath;
        this.cvtemplate1=cvtemplate1;
        this.cvtemplate2=cvtemplate2;
        this.firstCVSectionsJList=firstCVSectionsJList;
        this.secondCVSectionsJList=secondCVSectionsJList;
    }
    public void actionPerformed(ActionEvent e) {
        if (chckbxTxt.isSelected() || chckbxTex.isSelected()){
            if(compareObj.getPath(1)!=null && compareObj.getPath(2)!=null){
                if (chckbxTxt.isSelected()) outputType=0;
                if (chckbxTex.isSelected()) outputType=1;
                if (chckbxTxt.isSelected() && chckbxTex.isSelected()) outputType=2;
                OutputMerge.mergeTwoCv(compareObj.getCvTemplate(1), compareObj.getCvTemplate(2), compareObj.getCvSectionsJList(1), compareObj.getCvSectionsJList(2), outputType);
                JOptionPane.showMessageDialog(null, "Your Two CVs have been succesfully merged! \n Output folder: /outputfiles");
            }else{
                JOptionPane.showMessageDialog(null, "Two CVs need to be selected in order to merge.");
                
            }   
        }else{
            JOptionPane.showMessageDialog(null, "Please select an output type first.");
            
        }   
        
        
        
        
    }
    
}
