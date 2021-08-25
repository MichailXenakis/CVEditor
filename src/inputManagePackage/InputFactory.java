package inputManagePackage;

import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InputFactory {
    public void chooseFile(String openCvFileChooserPath, JFrame cvMainWindow) {
        if(openCvFileChooserPath.substring(openCvFileChooserPath.length()-3).equals("txt")){
            try {
                InputSystemTxt txtObj = new InputSystemTxt();
                txtObj.addCvDataFromFile(0,openCvFileChooserPath);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }else if(openCvFileChooserPath.substring(openCvFileChooserPath.length()-3).equals("tex")){
            try {
                InputSystemLatex ltxObj = new InputSystemLatex();
                ltxObj.addCvDataFromFile(0, openCvFileChooserPath);
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Invalid input file type. Txt or Tex allowed.");

            cvMainWindow.setVisible(true);
        }

    }
}
