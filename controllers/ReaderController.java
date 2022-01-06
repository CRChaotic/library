package com.library.controllers;

import com.library.SimpleRouter;
import com.library.models.Book;
import com.library.MVC.Controller;
import com.library.MVC.View;
import com.library.models.Reader;
import com.library.models.User;
import com.library.views.ReaderView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReaderController extends Controller implements ActionListener, ListSelectionListener {
    private Reader model;
    private ReaderView view;
    private SimpleRouter router;
    public ReaderController(Reader model, View view, SimpleRouter router) {
        super(model, view);
        this.model = model;
        this.view = (ReaderView) view;
        this.router = router;
    }

    public List<Reader> queryReader(String...info) {
        List<Reader> readers = model.query(info);
        return readers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("command:"+command);
        switch (command){
            case "addReader" ->{
                int id = Integer.parseInt(view.fieldId.getText());
                String readerName = view.fieldReaderName.getText();
                String type = view.fieldType.getText();
                int maxDays = view.fieldMaxDays.getText().equals("")?1:Integer.parseInt(view.fieldMaxDays.getText());
                int maxAmounts = view.fieldMaxAmounts.getText().equals("")?1:Integer.parseInt(view.fieldMaxAmounts.getText());
                boolean returned = (Boolean) view.comboBoxReturned.getSelectedItem();
                List<User> user = new User().query("id="+id);
                if (user.size() > 0) {
                    Reader reader = new Reader(id, readerName, type,maxDays,maxAmounts);
                    reader.setReturned(returned);
                    reader.add();
                }else{
                    view.showMessage("Adding reader failed");
                }
                view.refresh();
            }
            case "deleteReader" ->{
                int[] selectedRows = view.table.getSelectedRows();
                int t = selectedRows[0];
                for (int row:selectedRows) {
                    int id = Integer.parseInt(view.table.getValueAt(row,0).toString());
                    Reader deletedReader = new Reader();
                    deletedReader.setId(id);
                    deletedReader.delete();
                }
                view.refresh();
            }
            case  "updateReader" ->{
                System.out.println("updateReader");
            }
            case  "queryReader" ->{
                view.tableModel.setRowCount(0);
                List<Reader> readers = queryReader();
                for (Reader reader: readers) {
                    System.out.println(reader.getId() + "\t" + reader.getReaderName());
                    view.tableModel.addRow(new Object[]{
                            reader.getId(),
                            reader.getReaderName(),
                            reader.getMaxBorrowedDays(),
                            reader.getMaxBorrowedAmounts(),
                            reader.getType(),
                            reader.getReturned(),
                    });
                }
                view.refresh();
            }
        }
        view.tableModel.setRowCount(0);
        List<Reader> readers = queryReader();
        for (Reader reader: readers) {
            System.out.println(reader.getId() + "\t" + reader.getReaderName());
            view.tableModel.addRow(new Object[]{
                    reader.getId(),
                    reader.getReaderName(),
                    reader.getMaxBorrowedDays(),
                    reader.getMaxBorrowedAmounts(),
                    reader.getType(),
                    reader.getReturned(),
            });
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println(view.table.getSelectedRow());
        int selectedRow = view.table.getSelectedRow();
        if (selectedRow != -1){
            int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow,0).toString());
            String readerName = (String) view.tableModel.getValueAt(selectedRow,1);
            int maxDays = Integer.parseInt(view.tableModel.getValueAt(selectedRow,2).toString());
            int maxAmounts = Integer.parseInt(view.tableModel.getValueAt(selectedRow,3).toString());
            String type = (String) view.tableModel.getValueAt(selectedRow,4);
            boolean returned = view.tableModel.getValueAt(selectedRow,5).equals("true");
            Reader reader = new Reader(id,readerName,type,maxDays,maxAmounts);
            reader.setReturned(returned);
            System.out.println("readerType:"+reader.getType());
            reader.update();
        }
    }
}
