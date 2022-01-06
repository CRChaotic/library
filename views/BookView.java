package com.library.views;

import com.library.MVC.View;
import com.library.controllers.BookController;
import com.library.controllers.ReaderController;
import com.library.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookView extends JPanel implements View {
    public String title;
    public JLabel labelId;
    public JLabel labelBookName;
    public JLabel labelType;
    public JLabel labelAuthor;
    public JLabel labelTranslator;
    public JLabel labelPublisher;
    public JLabel labelPublishedDate;
    public JLabel labelPrice;
    public JLabel labelStock;
    public JTextField fieldId;
    public JTextField fieldBookName;
    public JTextField fieldType;
    public JTextField fieldAuthor;
    public JTextField fieldTranslator;
    public JTextField fieldPublisher;
    public JTextField fieldPublishedDate;
    public JTextField fieldPrice;
    public JTextField fieldStock;
    public JTable table;
    public DefaultTableModel tableModel;
    public JButton buttonAdd;
    public JButton buttonQuery;
    public JButton buttonDelete;
    public JButton buttonUpdate;
    public JPanel panelTop;
    public JPanel panelCenter;
    public JPanel panelBottom;

    public BookView(){
        title = "BookView";
        panelTop = new JPanel();
        panelCenter = new JPanel();
        panelBottom = new JPanel();

        labelId = new JLabel("id");
        labelBookName = new JLabel("book name");
        labelAuthor = new JLabel("author");
        labelType = new JLabel("type");
        labelPrice = new JLabel("price");
        labelStock = new JLabel("stock");
        labelTranslator = new JLabel("translator");
        labelPublisher = new JLabel("publisher");
        labelPublishedDate = new JLabel("publishedDate");
        fieldId = new JTextField(8);
        fieldBookName = new JTextField(16);
        fieldType = new JTextField(16);
        fieldAuthor = new JTextField(16);
        fieldTranslator = new JTextField(16);
        fieldPublisher = new JTextField(16);
        fieldPublishedDate = new JTextField(16);
        fieldPrice = new JTextField(5);
        fieldStock = new JTextField(5);

        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"id","book name", "author", "type", "price", "stock","translator", "publisher","publishedDate"}
        );
        table = new JTable(tableModel);
        table.getTableHeader().setFont(new Font("Serif", Font.ITALIC, 20));
        table.setFont(new Font("Serif", Font.PLAIN, 20));
        table.setRowHeight(20);

        buttonAdd = new JButton("ADD BOOK");
        buttonUpdate = new JButton("UPDATE BOOK");
        buttonDelete = new JButton("DELETE BOOK");
        buttonQuery = new JButton("QUERY BOOK");
        buttonAdd.setActionCommand("addBook");
        buttonDelete.setActionCommand("deleteBook");
        buttonUpdate.setActionCommand("updateBook");
        buttonQuery.setActionCommand("queryBook");

        panelTop.add(labelId);
        panelTop.add(fieldId);
        panelTop.add(labelBookName);
        panelTop.add(fieldBookName);
        panelTop.add(labelAuthor);
        panelTop.add(fieldAuthor);
        panelTop.add(labelType);
        panelTop.add(fieldType);
        panelTop.add(labelTranslator);
        panelTop.add(fieldTranslator);
        panelTop.add(labelPrice);
        panelTop.add(fieldPrice);
        panelTop.add(labelStock);
        panelTop.add(fieldStock);
        panelTop.add(labelPublisher);
        panelTop.add(fieldPublisher);
        panelTop.add(labelPublishedDate);
        panelTop.add(fieldPublishedDate);

        panelCenter.add(new JScrollPane(table));
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

    public void registerEventListeners(BookController controller){
        buttonAdd.addActionListener(controller);
        buttonDelete.addActionListener(controller);
        buttonUpdate.addActionListener(controller);
        buttonQuery.addActionListener(controller);
    }

    public void refresh(){
        fieldId.setText("");
        fieldBookName.setText("");
        fieldType.setText("");
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }
}
