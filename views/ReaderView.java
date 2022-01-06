package com.library.views;

import com.library.MVC.View;
import com.library.controllers.ReaderController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReaderView extends JPanel implements View {
    public String title;
    public JPanel panelTop;
    public JPanel panelCenter;
    public JPanel panelBottom;
    public JLabel labelId;
    public JLabel labelReaderName;
    public JLabel labelType;
    public JLabel labelMaxDays;
    public JLabel labelMaxAmounts;
    public JLabel labelReturned;
    public JTextField fieldId;
    public JTextField fieldReaderName;
    public JTextField fieldType;
    public JTextField fieldMaxDays;
    public JTextField fieldMaxAmounts;
    public JComboBox<Boolean> comboBoxReturned;
    public JTable table;
    public DefaultTableModel tableModel;
    public JButton buttonAdd;
    public JButton buttonQuery;
    public JButton buttonDelete;
    public JButton buttonUpdate;

    public ReaderView(){
        title = "ReaderView";
        panelTop = new JPanel();
        panelCenter = new JPanel();
        panelBottom = new JPanel();

        labelId = new JLabel("id");
        labelReaderName = new JLabel("reader name");
        labelType = new JLabel("type");
        labelMaxDays = new JLabel("max days");
        labelMaxAmounts = new JLabel("max amounts");
        labelReturned = new JLabel("returned");
        fieldId = new JTextField(8);
        fieldReaderName = new JTextField(16);
        fieldType = new JTextField(16);
        fieldMaxDays = new JTextField(5);
        fieldMaxAmounts = new JTextField(5);

        comboBoxReturned = new JComboBox<>();
        comboBoxReturned.addItem(false);
        comboBoxReturned.addItem(true);

        buttonAdd = new JButton("ADD READER");
        buttonUpdate = new JButton("UPDATE READER");
        buttonDelete = new JButton("DELETE READER");
        buttonQuery = new JButton("QUERY READER");
        buttonAdd.setActionCommand("addReader");
        buttonDelete.setActionCommand("deleteReader");
        buttonUpdate.setActionCommand("updateReader");
        buttonQuery.setActionCommand("queryReader");

        panelTop.add(labelId);
        panelTop.add(fieldId);
        panelTop.add(labelReaderName);
        panelTop.add(fieldReaderName);
        panelTop.add(labelType);
        panelTop.add(fieldType);
        panelTop.add(labelMaxDays);
        panelTop.add(fieldMaxDays);
        panelTop.add(labelMaxAmounts);
        panelTop.add(fieldMaxAmounts);
        panelTop.add(labelReturned);
        panelTop.add(comboBoxReturned);
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"id","readerName", "maxBorrowedDays", "maxBorrowedAmounts","type","returned"}
        );
        table = new JTable(tableModel);
        table.getTableHeader().setFont(new Font("Serif", Font.ITALIC, 20));
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(20);
        JScrollPane scrollTable = new JScrollPane(table);
        panelCenter.add(scrollTable);
        panelCenter.setLayout(new GridLayout(1,1));

        panelBottom.add(buttonAdd);
        panelBottom.add(buttonDelete);
        panelBottom.add(buttonUpdate);
        panelBottom.add(buttonQuery);

        setLayout(new BorderLayout());
        add(panelTop, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        setSize(1200,600);
    }

    public void registerEventListeners(ReaderController controller){
        buttonAdd.addActionListener(controller);
        buttonDelete.addActionListener(controller);
        buttonUpdate.addActionListener(controller);
        buttonQuery.addActionListener(controller);
        table.getSelectionModel().addListSelectionListener(controller);
    }

    public void refresh(){
        fieldId.setText("");
        fieldReaderName.setText("");
        fieldType.setText("");
        fieldMaxDays.setText("");
        fieldMaxAmounts.setText("");
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }
}
