package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dataManagePackage.CVTemplate;

public class AddFieldListener implements ActionListener{
    private CVTemplate cvtemplate2;
    private CVTemplate cvtemplate1;
    private String field;
    private CompareTwoCVsJFrameWindow compareObj;
    public AddFieldListener(CompareTwoCVsJFrameWindow compareObj, String field) {
        this.cvtemplate1=cvtemplate1;
        this.cvtemplate2=cvtemplate2;
        this.compareObj=compareObj;
        this.field=field;
    }
    public void actionPerformed(ActionEvent e) {
        
        ComparisonJFrameWindow window = new ComparisonJFrameWindow(compareObj.getCvTemplate(1), compareObj.getCvTemplate(2), field);
        window.frame.setVisible(true);

        
    }
}
