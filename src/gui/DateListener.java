package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class DateListener implements ActionListener{
    
    
    private String type;
    private JFrame appMainWindow;
    private ArrayList<String> educationAndTraining;
    private ArrayList<String> furtherCourses;



    public  DateListener(JFrame appMainWindow,String type,ArrayList<String> educationAndTraining,ArrayList<String> furtherCourses) {
        this.appMainWindow=appMainWindow;
        this.type=type;
        this.furtherCourses=furtherCourses;
        this.educationAndTraining=educationAndTraining;
        
    
    }
    
    
    
    public void actionPerformed(ActionEvent e) {
        ChronologicalJFrameWindow chronoobj = new ChronologicalJFrameWindow(appMainWindow);
        JFrame dateFrame = chronoobj.createFrame(type, 500, 250, 600, 400, false);
 //       chronoobj.onExit(dateFrame, true);
        
        JTextField textField1 = new JTextField();
        createTextField(textField1, dateFrame, 117, 237, 319, 20);
        
        JTextField textField2 = new JTextField();
        createTextField(textField2, dateFrame, 117, 268, 319, 20);
        
        JTextField textField3 = new JTextField();
        createTextField(textField3, dateFrame, 117, 299, 319, 20);
        
        JTextField dateField = new JTextField();
        createTextField(dateField, dateFrame, 117, 330, 319, 20);
        
        JLabel label1 = new JLabel("New label");
        createLabel(label1, dateFrame, 10, 240, 97, 14);
        
        JLabel label2 = new JLabel("Establishment");
        createLabel(label2, dateFrame, 10, 271, 97, 14);
        
        JLabel label3 = new JLabel("Location");
        createLabel(label3, dateFrame, 10, 302, 97, 14);
        
        JLabel label4 = new JLabel("Date");
        createLabel(label4, dateFrame, 10, 333, 97, 14);
        
        if (type.equals("Education And Training")){
            label1.setText("Qualification");

        } else if (type.equals("Further Courses")){
            label1.setText("Course");

        }
        
        JScrollPane listScroll = new JScrollPane();
        
        DefaultListModel<String> mainList = new DefaultListModel<>();
        
        JList<String> list = new JList<String>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(2);
        list.setBounds(10, 11, 564, 201);
        
        dateFrame.getContentPane().add(list);
        
        listScroll.setViewportView(list);
        listScroll.setBounds(10, 11, 564, 201);
        
        dateFrame.getContentPane().add(listScroll);
        
        JButton remove = new JButton("Remove Selected");
        remove.setBounds(446, 236, 128, 35);
        dateFrame.getContentPane().add(remove);
        
        JButton edit = new JButton("Edit Selected");
        edit.setBounds(446, 275, 128, 35);
        dateFrame.getContentPane().add(edit);
        
        JButton add = new JButton("Add");
        add.setBounds(446, 314, 128, 35);
        dateFrame.getContentPane().add(add);
        
        chronoobj.clearDateList();
        chronoobj.sortList(type);
        if (type.equals("Education And Training")){
            for (String temp : educationAndTraining){
                mainList.addElement(temp);
                chronoobj.fillDateList(temp, 3);
            }
        } else if (type.equals("Further Courses")){
            for (String temp : furtherCourses){
                mainList.addElement(temp);
                chronoobj.fillDateList(temp, 3);
            }
        }
        list.setModel(mainList);
        
        
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                if ((!textField1.getText().equals("")) &&
                         (!textField2.getText().equals("")) && 
                          (!textField3.getText().equals("")) &&
                           (!dateField.getText().equals(""))){

                    if (!chronoobj.checkDateList(dateField)){
                        JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
                    }
                    
                    if (type.equals("Education And Training")){
                        educationAndTraining.add((textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));
                        chronoobj.addEducationAndTraining((textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));
                        mainList.addElement(textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText());
                        list.setModel(mainList);
                        
                    } else if (type.equals("Further Courses")){
                        furtherCourses.add((textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));
                        chronoobj.addFurtherCourses((textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText()));
                        mainList.addElement(textField1.getText() + ", " + textField2.getText() + ", " + textField3.getText() + ", " + dateField.getText());
                        list.setModel(mainList);
                    
                    } 
                } else {
                    JOptionPane.showMessageDialog(null, "One or more Entries are missing.");
                }
            }
            
        });

        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() != -1){
                     if (type.equals("Education And Training")){
                        educationAndTraining.remove(list.getSelectedValue());
                        chronoobj.removeEducationAndTraining(list.getSelectedValue());
                    } else if (type.equals("Further Courses")){
                        furtherCourses.remove(list.getSelectedValue());
                        chronoobj.removeFurtherCourses(list.getSelectedValue());
                    }
                    
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
                    if (!textField1.getText().equals(" ") && !textField2.getText().equals(" ") 
                            && !textField3.getText().equals(" ") &&!dateField.getText().equals(" ") ){
                        
                        if (!chronoobj.checkDateList(dateField)){
                            JOptionPane.showMessageDialog(null, "You have entered a wrong Date.");
                        }
                        
                        if (type.equals("Education And Training")){
                            int i = 0;
                            String tempList = list.getSelectedValue() ;
                            for (String temp : educationAndTraining){
                                if (!temp.equals(null)){
                                    if (tempList.equals(temp) ){
                                        educationAndTraining.set(i, textField1.getText() + ", " + textField2.getText()
                                          + ", " + textField3.getText() + ", " + dateField.getText() );
                                        
                                        chronoobj.setEducationAndTraining(i, textField1.getText() + ", " + textField2.getText()
                                          + ", " + textField3.getText() + ", " + dateField.getText());
                                        
                                        mainList.set(list.getSelectedIndex(), educationAndTraining.get(i));
                                        list.setModel(mainList);
                                    }
                                    i++;    
                                }
                            }
                        } else if (type.equals("Further Courses")){
                            int i = 0;
                            String tempList = list.getSelectedValue() ;
                            for (String temp : furtherCourses){
                                if (tempList.equals(temp) ){
                                    furtherCourses.set(i, textField1.getText() + ", " + textField2.getText()
                                      + ", " + textField3.getText() + ", " + dateField.getText() );
                                    
                                    chronoobj.setFurtherCourses(i,textField1.getText() + ", " + textField2.getText()
                                      + ", " + textField3.getText() + ", " + dateField.getText());
                                    
                                    mainList.set(list.getSelectedIndex(), furtherCourses.get(i));
                                    list.setModel(mainList);
                                }
                                i++;    
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Don't leave an input field blank.");
                        }
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
                    textField1.setText(listSelectedSplit[0]);
                    textField2.setText(listSelectedSplit[1]);
                    textField3.setText(listSelectedSplit[2]);
                    dateField.setText(listSelectedSplit[3]);
                    
                }
            }
        };
        list.addMouseListener(mouseListen);
        
        dateFrame.setVisible(true);
    }

    public void createLabel(JLabel label, JFrame df, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        df.getContentPane().add(label);
    }
    
    
    
    public void createTextField(JTextField tf, JFrame df, int x, int y, int width, int height) {
        tf.setBounds(x, y, width, height);
        df.getContentPane().add(tf);
        tf.setColumns(10);
    }
    
    
}

