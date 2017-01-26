/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author macbook
 */
public class Window extends JFrame {
    private JLabel nameOfWindow;
    private JLabel singInRes;
    JButton button1;
    JButton button2;
    private JTextArea login;
    private JTextArea password;
    private JTextArea pass1;
    private JPanel textPanel;
    private JPanel labelPanel;
    private JPanel resultPanel;
    private JPanel buttonPanel;
    public Window(){
        setName("Authentication");
        setBounds(500, 250, 300, 200);
        nameOfWindow = new JLabel("Good");
        button1 = new JButton("");
        button2 = new JButton("");
        login = new JTextArea("login");
        password = new JTextArea("password");
        pass1 = new JTextArea("repeat password");
        labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(nameOfWindow); 
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button2);
        buttonPanel.add(button1);
        add(labelPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initListeners();
    }
    public Window(String nameOfWin, String nameOfB1, String nameOfB2){
        setName("Authentication");
        setBounds(500, 250, 300, 200);
        nameOfWindow = new JLabel(nameOfWin);
        singInRes = new JLabel("good");
        button1 = new JButton(nameOfB1);
        button2 = new JButton(nameOfB2);
        login = new JTextArea("login");
        password = new JTextArea("password");
        pass1 = new JTextArea("repeat password");
        labelPanel = new JPanel(new FlowLayout());
        labelPanel.add(nameOfWindow); 
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(button2);
        buttonPanel.add(button1);
        add(labelPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initListeners();
    }

    private void initListeners() {
     button2.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
        //Enter app = new Enter();
         //voron = voron + 1;   /* Добавляем одну ворону  */
         //updateCrowCounter(); /* Сообщаем аппликации, что количество ворон изменилось  */
       }
     });
    }
    public void setB1N( String name){
        button1.setText(name);
        //pack();
    }
    public void setB2N( String name){
        button2.setText(name);
        //pack();
    }
    public void setLN( String name){
        nameOfWindow.setText(name);
    }
    public void addSingInPanel(){
        textPanel = new JPanel(new BorderLayout());
        textPanel.add(login, BorderLayout.NORTH);
        textPanel.add(password, BorderLayout.SOUTH);
        add(textPanel);
    }
    public void addResultPanel(){
        resultPanel = new JPanel(new BorderLayout());
        resultPanel.add(singInRes, BorderLayout.NORTH);
        add(resultPanel);
    }
    public void addRegistratiosPanel(){
        textPanel = new JPanel(new BorderLayout());
        textPanel.add(login, BorderLayout.NORTH);
        textPanel.add(password, BorderLayout.CENTER);
        textPanel.add(pass1, BorderLayout.AFTER_LAST_LINE);
        add(textPanel);
    }
    public String getSetLog(){
        return login.getText();
    }
    public String getSetPass(){
        return password.getText();
    }
    public String getSetPass1(){
        return pass1.getText();
    }
    public void removeButtonPanel(){
        buttonPanel.removeAll();
    }
}

