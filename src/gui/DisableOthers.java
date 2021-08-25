package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisableOthers implements ActionListener {
    private JFrame cvMainWindow;
    private JCheckBox chckbxChronological;
    private JCheckBox chckbxFunctional;
    private JCheckBox chckbxCombined;
    private JLabel labelFunctional;
    private JLabel labelCombined;
    private JLabel labelChronological;
    
    public DisableOthers(JFrame cvMainWindow,JCheckBox chckbxChronological,JCheckBox chckbxFunctional,JCheckBox chckbxCombined,JLabel labelFunctional,JLabel labelCombined,JLabel labelChronological) {
        this.cvMainWindow = cvMainWindow;
        this.chckbxChronological = chckbxChronological;
        this.chckbxFunctional = chckbxFunctional;
        this.chckbxCombined = chckbxCombined;
        this. labelFunctional =  labelFunctional;
        this.labelCombined = labelCombined;
        this.labelChronological = labelChronological;
    }
    public void actionPerformed(ActionEvent e) {

        if (chckbxChronological.isSelected()){
            chckbxFunctional.setEnabled(false);
            labelFunctional.setEnabled(false);
            chckbxCombined.setEnabled(false);
            labelCombined.setEnabled(false);
        }
        else if (chckbxFunctional.isSelected()){
            chckbxChronological.setEnabled(false);
            labelChronological.setEnabled(false);
            chckbxCombined.setEnabled(false);
            labelCombined.setEnabled(false);
        }
        else if (chckbxCombined.isSelected()){
            chckbxChronological.setEnabled(false);
            labelChronological.setEnabled(false);
            chckbxFunctional.setEnabled(false);
            labelFunctional.setEnabled(false);
        
        }else {
            chckbxFunctional.setEnabled(true);
            labelFunctional.setEnabled(true);
            chckbxCombined.setEnabled(true);
            labelCombined.setEnabled(true);
            chckbxChronological.setEnabled(true);
            labelChronological.setEnabled(true);
        }
    }

}
