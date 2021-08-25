package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GeneralInfoListener implements ActionListener{

    private JFrame appMainWindow;
    private String[] generalInformation;

    public GeneralInfoListener(JFrame appMainWindow,String[] generalInformation) {
        this.appMainWindow=appMainWindow;
        this.generalInformation=generalInformation;
    }
    
    public void actionPerformed(ActionEvent e) {
        ChronologicalJFrameWindow chronoobj = new ChronologicalJFrameWindow(appMainWindow);
        JFrame generalInformationWindow = chronoobj.createFrame("General Information.", 600, 250, 400, 400, false);
        generalInformationWindow.getContentPane().setLayout(null);
 //       chronoobj.onExit(generalInformationWindow, true);
        
        JLabel labelName = chronoobj.createLabel("Name : ", 10, 10, 370, 20, 0);
        generalInformationWindow.getContentPane().add(labelName);
                        
        JTextField  nameField = chronoobj.createTextField(10, 30, 370, 20);     
        generalInformationWindow.getContentPane().add(nameField);
        
        JLabel labelAddress = chronoobj.createLabel("Address : ", 10, 60, 370, 20, 0);
        generalInformationWindow.getContentPane().add(labelAddress);
        
        JTextField  addressField = chronoobj.createTextField(10, 80, 370, 20);  
        generalInformationWindow.getContentPane().add(addressField);
        
        JLabel labelHome = chronoobj.createLabel("Home Telephone : ", 10, 110, 370, 20, 0);
        generalInformationWindow.getContentPane().add(labelHome);
        
        JTextField  homeField = chronoobj.createTextField(10, 130, 370, 20);
        generalInformationWindow.getContentPane().add(homeField);
        
        JLabel labelMobile = chronoobj.createLabel("Mobile : ", 10, 160, 370, 20, 0);
        generalInformationWindow.getContentPane().add(labelMobile);
        
        JTextField  mobileField = chronoobj.createTextField(10, 180, 370, 20);  
        generalInformationWindow.getContentPane().add(mobileField);
        
        JLabel labelEmail = chronoobj.createLabel("Email : ", 10, 210, 370, 20, 0);
        generalInformationWindow.getContentPane().add(labelEmail);
        
        JTextField  emailField = chronoobj.createTextField(10, 230, 370, 20);
        generalInformationWindow.getContentPane().add(emailField);
        
        
        nameField.setText(generalInformation[0]);
        addressField.setText(generalInformation[1]);
        homeField.setText(generalInformation[2]);
        mobileField.setText(generalInformation[3]);
        emailField.setText(generalInformation[4]);
        
        JButton save = new JButton("Save");
        save.setBounds(300, 300, 80 , 50);
        generalInformationWindow.getContentPane().add(save);
        
        save.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent e) {    
                generalInformation[0] = nameField.getText();
                chronoobj.setGeneralInfo(0, nameField.getText());
                generalInformation[1] = addressField.getText();
                chronoobj.setGeneralInfo(1, addressField.getText());
                generalInformation[2] = homeField.getText();
                chronoobj.setGeneralInfo(2, homeField.getText());
                generalInformation[3] = mobileField.getText();
                chronoobj.setGeneralInfo(3, mobileField.getText());
                generalInformation[4] = emailField.getText();
                chronoobj.setGeneralInfo(4, emailField.getText());
                
            }
            
        });
        
        generalInformationWindow.setVisible(true);
    }
    
}
