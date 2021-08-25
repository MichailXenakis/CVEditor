package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dataManagePackage.CVTemplate;

public class CreateCVListener implements ActionListener {
    private JFrame cvMainWindow;
    private JCheckBox chckbxChronological;
    private JCheckBox chckbxFunctional;
    private JCheckBox chckbxCombined;
    
    
    public CreateCVListener(JFrame cvMainWindow,JCheckBox chckbxChronological,JCheckBox chckbxFunctional,JCheckBox chckbxCombined) {
        this.cvMainWindow = cvMainWindow;
        this.chckbxChronological = chckbxChronological;
        this.chckbxFunctional = chckbxFunctional;
        this.chckbxCombined = chckbxCombined;
    }
    public void actionPerformed(ActionEvent e) {
        if ( chckbxChronological.isSelected()) {
            cvMainWindow.setVisible(false);
            CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
            ChronologicalJFrameWindow.makeVisible(cvtemplate);

        } else if ( chckbxFunctional.isSelected() ){

            cvMainWindow.setVisible(false);
            CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
            FunctionalJFrameWindow.makeVisible(cvtemplate);


        } else if (chckbxCombined.isSelected() ) {
            cvMainWindow.setVisible(false);
            CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
            CombinedJFrameWindow.makeVisible(cvtemplate);

        } else {
            JOptionPane.showMessageDialog(null, "Please select a CV first. :)");
        }

    }
}
