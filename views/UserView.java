package com.library.views;

import com.library.MVC.View;
import com.library.controllers.UserController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JPanel implements View {
    public String title;
    public JPanel panelTop;
    public JPanel panelCenter;
    public JPanel panelBottom;
    public JLabel labelUsername;
    public JLabel labelPassword;
    public JLabel labelAdmin;
    public JTextField fieldUsername;
    public JPasswordField fieldPassword;
    public JComboBox<Boolean> comboBoxAdmin;
    public JTable table;
    public DefaultTableModel tableModel;
    public JButton buttonAdd;
    public JButton buttonQuery;
    public JButton buttonDelete;
    public JButton buttonUpdate;

    public UserView(){
        title = "UserView";
        panelTop = new JPanel();
        panelCenter = new JPanel();
        panelBottom = new JPanel();
        labelUsername = new JLabel("username");
        labelPassword = new JLabel("password");
        labelAdmin = new JLabel("admin");
        fieldUsername = new JTextField(16);
        fieldPassword = new JPasswordField(16);
        comboBoxAdmin = new JComboBox<>();
        comboBoxAdmin.addItem(false);
        comboBoxAdmin.addItem(true);

        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"id","username","admin"});
        table = new JTable(tableModel);
        table.getTableHeader().setFont(new Font("Serif", Font.ITALIC, 20));
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(20);
        JScrollPane scrollTable = new JScrollPane(table);

        buttonAdd = new JButton("ADD USER");
        buttonUpdate = new JButton("UPDATE USER");
        buttonDelete = new JButton("DELETE USER");
        buttonQuery = new JButton("QUERY USER");
        buttonAdd.setActionCommand("addUser");
        buttonDelete.setActionCommand("deleteUser");
        buttonUpdate.setActionCommand("updateUser");
        buttonQuery.setActionCommand("queryUser");
        panelTop.add(labelUsername);
        panelTop.add(fieldUsername);
        panelTop.add(labelPassword);
        panelTop.add(fieldPassword);
        panelTop.add(labelAdmin);
        panelTop.add(comboBoxAdmin);
        panelCenter.add(scrollTable);
        panelBottom.add(buttonAdd);
        panelBottom.add(buttonDelete);
        panelBottom.add(buttonUpdate);
        panelBottom.add(buttonQuery);
        panelCenter.setLayout(new GridLayout(1,1));
        setLayout(new BorderLayout());
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        setSize(1200,600);
    }

    public void registerEventListeners(UserController controller){
        buttonAdd.addActionListener(controller);
        buttonDelete.addActionListener(controller);
        buttonUpdate.addActionListener(controller);
        buttonQuery.addActionListener(controller);
    }

    public void refresh(){
        fieldUsername.setText("");
        fieldPassword.setText("");
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }

    public void display(){
        setVisible(true);
    }

}
