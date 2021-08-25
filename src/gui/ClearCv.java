package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ClearCv implements ActionListener{

    private JButton btnCompare;
    private String openPath;
    private DefaultListModel<String> model;
    private int x;
    private JFrame appMainWindow;
    private JButton btnOpenCv;
    private CompareOrMergeTwoCVsJFrameWindow compareObj;

    public ClearCv(CompareOrMergeTwoCVsJFrameWindow compareObj, JFrame appMainWindow,  JButton btnOpenCv, JButton btnCompare,int x) {
        this.compareObj=compareObj;
        this.btnOpenCv=btnOpenCv;
        this.btnCompare=btnCompare;
        this.x=x;
        this.appMainWindow=appMainWindow;
    }
    
    public void actionPerformed(ActionEvent e) {
        //CompareOrMergeTwoCVsJFrameWindow compareObj = new CompareOrMergeTwoCVsJFrameWindow(appMainWindow);
        compareObj.setModel(x);
        compareObj.setPath(null,x);
        if(compareObj.getCounter(x)>=1){
            btnOpenCv.setEnabled(true);
            compareObj.setCounter(compareObj.getCounter(x)-1,x);
            compareObj.setCheckCounter(compareObj.getCheckCounter()-1);
        }
        
        if(compareObj.getCheckCounter()<2) btnCompare.setEnabled(false);
    }
}
