package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EventListener implements ActionListener{
    
    private JFrame appMainWindow;
    private String type;
    static String interests, additionalInformation, professionalProfile, coreStrengths;

    public EventListener(String type,JFrame appMainWindow) {
        this.type=type;
        this.appMainWindow=appMainWindow;
    }
    
    
    public void actionPerformed(ActionEvent e) {
        
        ChronologicalJFrameWindow createframe = new ChronologicalJFrameWindow(appMainWindow);
        JFrame frame = createframe.createFrame(type, 500, 250, 600, 400, false);
        ChronologicalJFrameWindow exit = new ChronologicalJFrameWindow(appMainWindow);
//        exit.onExit(frame, true);
        
        JLabel label = new JLabel("Input your " + type + " here.");
        label.setBounds(10, 10, 564, 20);
        frame.getContentPane().add(label);
        
        JScrollPane scrollArea = new JScrollPane();
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 40, 564, 250);
        frame.getContentPane().add(textArea);
        
        if (type.equals("Interests")){
            textArea.setText(interests);
        } else if (type.equals("Professional Profile")){
            textArea.setText(professionalProfile);
        } else if (type.equals("Additional Information")){
            textArea.setText(additionalInformation);
        } else if (type.equals("Core Strengths")){
            textArea.setText(coreStrengths);
        }
        
        scrollArea.setViewportView(textArea);
        scrollArea.setBounds(10, 40, 564, 250);
        frame.getContentPane().add(scrollArea);

        JButton saveInfo = new JButton("Save ");
        saveInfo.setBounds(420, 300, 153, 60);
        frame.getContentPane().add(saveInfo);
    
        saveInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (type.equals("Interests")){
                    interests = textArea.getText();
                } else if (type.equals("Professional Profile")) {
                    professionalProfile = textArea.getText();
                } else if (type.equals("Additional Information")){
                    additionalInformation = textArea.getText();
                } else if (type.equals("Core Strengths")){
                    coreStrengths = textArea.getText();
                }
            }
        });
        
        
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
