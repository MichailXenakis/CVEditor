package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ChronoProfessionalExperienceListener implements ActionListener{
    //static HashMap<String, String> professionalExperience = new HashMap<String, String>();
    //static String  tempKey, tempStr;
    private JFrame appMainWindow;
    private String tempKey;
    private HashMap<String, String> professionalExperience;
    private String tempStr;
    public  ChronoProfessionalExperienceListener(JFrame appMainWindow,String tempKey,String tempStr,HashMap<String, String> professionalExperience) {
        this.appMainWindow=appMainWindow;
        this.tempKey=tempKey;
        this.tempStr=tempStr;
        this.professionalExperience=professionalExperience;
        
    }
    
    
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setBounds( 500, 250, 600, 451);
        frame.getContentPane().setLayout(null);
        ChronologicalJFrameWindow chronoobj = new ChronologicalJFrameWindow(appMainWindow);
 //       chronoobj.onExit(frame, true);
        frame.setResizable(false);
        
        JTextField companyField = new JTextField();
        createTextField(companyField, frame, 128, 213, 319, 20);
        
        JTextField jobField  = new JTextField();
        createTextField(jobField, frame, 128, 244, 319, 20);
        
        JTextField dateField = new JTextField();
        createTextField(dateField, frame, 128, 275, 319, 20);
    
        JLabel companyLabel = new JLabel("Company");
        createLabel(companyLabel, frame, 10, 213, 112, 14);
        
        JLabel jobTitleLabel = new JLabel("Job Title");
        createLabel(jobTitleLabel, frame, 10, 244, 112, 14);
            
        JLabel lblDate = new JLabel("Date");
        createLabel(lblDate, frame, 10, 275, 46, 14);
        
        JLabel listOfAchievementsLabel = new JLabel("List of Achievements: ");
        createLabel(listOfAchievementsLabel, frame, 10, 306, 120, 14);
        
        JLabel separateLabel = new JLabel("(seperate  by ', ')");
        createLabel(separateLabel, frame, 10, 323, 100, 14);
        
        JLabel separate2Label = new JLabel("(Comma + Space)");
        createLabel(separate2Label, frame, 10, 340, 100, 14);

        JLabel paragraphLabel = new JLabel("Paragraph");
        createLabel(paragraphLabel, frame, 10, 366, 84, 14);
        
        JTextArea listOfAchievementsArea= new JTextArea();
        listOfAchievementsArea.setBounds(128, 306, 318, 45);
        frame.getContentPane().add(listOfAchievementsArea);
        
        JTextArea paragraphArea = new JTextArea();
        paragraphArea.setBounds(129, 362, 318, 45);
        frame.getContentPane().add(paragraphArea);
        
        
        JScrollPane listScroll = new JScrollPane();
        
        DefaultListModel<String> mainList = new DefaultListModel<>();
        
        JList<String> list = new JList<String>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(2);
        list.setBounds(10, 11, 564, 191);
        
        frame.getContentPane().add(list);
        
        listScroll.setViewportView(list);
        listScroll.setBounds(10, 11, 564, 201);
        
        frame.getContentPane().add(listScroll);
        
        for (String key  : professionalExperience.keySet() ) {
            mainList.addElement(key + ", " + professionalExperience.get(key));
        }

        list.setModel(mainList);
        
        
        JButton remove = new JButton("Remove Selected");
        createButton(remove, frame, 462, 212, 112, 60);
        
        JButton edit = new JButton("Edit Selected");
        createButton(edit, frame, 462, 279, 112, 60);
        
        JButton add = new JButton("Add");
        createButton(add, frame, 462, 345, 112, 60);
        
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chronoobj.setTempKey(companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText());
                tempKey = (companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText());
                tempStr = ( listOfAchievementsArea.getText() + ", " + paragraphArea.getText() );
                chronoobj.setTempStr(listOfAchievementsArea.getText() + ", " + paragraphArea.getText() );
                
                if (companyField.getText().equals(" ") || jobField.getText().equals(" ") || dateField.getText().equals(" ") 
                        || listOfAchievementsArea.getText().equals(" ") || paragraphArea.getText().equals(" ") ){
                    JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
                } else if (professionalExperience.containsKey(tempKey)) {
                    JOptionPane.showMessageDialog(null, "Please change one of the values: Company, Job Title, Date");
                } else {
                    mainList.addElement(companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText()
                        + ", " + listOfAchievementsArea.getText()  + ", " + paragraphArea.getText() );  
                    
                    professionalExperience.put(tempKey , tempStr);
                    chronoobj.setProfessionalExp(tempKey,tempStr);
                    list.setModel(mainList);
                }
            }
        });
        
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tempListItem[];
                if (list.getSelectedIndex() != -1){
                    tempListItem = list.getSelectedValue().split(", ");
                    tempKey = (tempListItem[0] + ", " + tempListItem[1] + ", " + tempListItem[2]);
                    chronoobj.setTempKey(tempListItem[0] + ", " + tempListItem[1] + ", " + tempListItem[2]);
                    professionalExperience.remove(tempKey);
                    chronoobj.removeProfessionalExp(tempKey);
                    
                    mainList.remove(list.getSelectedIndex());
                    list.setModel(mainList);
                } else {
                    JOptionPane.showMessageDialog(null, "Select an item from the list, then press remove to remove it.");
                    
                }
            }
        });
        
        edit.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() != -1){
                    String[] tempArray = list.getSelectedValue().split(", ");
                    String tempListKey = (tempArray[0] + ", " + tempArray[1] + ", " + tempArray[2]);
                    tempKey = (companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText());
                    chronoobj.setTempKey(companyField.getText() + ", " + jobField.getText() + ", " + dateField.getText());
                    tempStr = (listOfAchievementsArea.getText() + ", " + paragraphArea.getText());
                    chronoobj.setTempStr(listOfAchievementsArea.getText() + ", " + paragraphArea.getText());
                    
                    if (companyField.getText().equals(" ") || jobField.getText().equals(" ") || dateField.getText().equals(" ") 
                            || listOfAchievementsArea.getText().equals(" ") || paragraphArea.getText().equals(" ") ){
                        JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
                    } else {
                        if (tempListKey.equals(tempKey)){
                            professionalExperience.put(tempKey , tempStr);
                            chronoobj.setProfessionalExp(tempKey,tempStr);
                        } else {
                            
                            professionalExperience.remove(tempListKey);
                            chronoobj.removeProfessionalExp(tempListKey);
                            professionalExperience.put(tempKey , tempStr);
                            chronoobj.setProfessionalExp(tempKey,tempStr);
                        }
                        mainList.set(list.getSelectedIndex(), (tempKey + ", " + tempStr) );
                        list.setModel(mainList);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No list item selected to edit.");
                }
            }
        });
        
        MouseListener mouseListen = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (list.getSelectedIndex() != -1){
                    
                    String[] listSelectedSplit = list.getSelectedValue().split(", "); 
                    
                    tempStr = listSelectedSplit[3];
                    chronoobj.setTempStr(listSelectedSplit[3]);
                    for (int i = 0; i < listSelectedSplit.length -1 ; i++){
                        if (i > 3){
                            tempStr = (tempStr + ", " + listSelectedSplit[i]);
                            chronoobj.setTempStr(tempStr + ", " + listSelectedSplit[i]);
                        }
                    }
                    companyField.setText(listSelectedSplit[0]);
                    jobField.setText(listSelectedSplit[1]);
                    dateField.setText(listSelectedSplit[2]);
                    listOfAchievementsArea.setText(tempStr);
                    paragraphArea.setText(listSelectedSplit[listSelectedSplit.length-1]);
                }
            }
        };
        
        list.addMouseListener(mouseListen);
        
        frame.setVisible(true);
    }
    
    public void createTextField(JTextField tf, JFrame frame, int x, int y, int width, int height) {
        tf.setBounds(x, y, width, height);
        frame.getContentPane().add(tf);
        tf.setColumns(10);
    }
    
    public void createLabel(JLabel label, JFrame frame, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        frame.getContentPane().add(label);
    }
    
    public void createButton(JButton btn, JFrame frame, int x, int y, int width, int height) {
        btn.setBounds(x, y, width, height);
        frame.getContentPane().add(btn);
    }
    
}
    
