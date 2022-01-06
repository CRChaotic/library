package com.library.views;

import com.library.MVC.View;
import com.library.controllers.LoginController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginView extends JPanel implements View {
    private HashMap<String, Object> data;
    public String title;
    public JLabel labelUsername;
    public JLabel labelPassword;
    public JTextField fieldUsername;
    public JPasswordField fieldPassword;
    public JButton buttonLogin;
    public JButton buttonRegister;

    public LoginView(){
        title = "LOGIN";
        labelUsername = new JLabel("username");
        labelPassword = new JLabel("password");
        fieldUsername = new JTextField(16);
        fieldPassword = new JPasswordField(16);
        buttonLogin = new JButton("SIGN IN");
        buttonLogin.setActionCommand("signIn");
        buttonRegister = new JButton("SIGN UP");
        buttonRegister.setActionCommand("signUp");
        add(labelUsername);
        add(fieldUsername);
        add(labelPassword);
        add(fieldPassword);
        add(buttonLogin);
        add(buttonRegister);
        setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
        setBorder(new EmptyBorder(new Insets(20, 20, 10, 20)));
        setSize(300,200);
    }
    public void registerEventListeners(LoginController controller){
        buttonLogin.addActionListener(controller);
        buttonRegister.addActionListener(controller);
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }

    public void refresh(){
        fieldPassword.setText("");
    }

}
