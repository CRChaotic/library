package com.library.controllers;

import com.library.MVC.Controller;
import com.library.SimpleRouter;
import com.library.models.User;
import com.library.views.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController extends Controller implements ActionListener {
    private User model;
    private UserView view;
    private SimpleRouter router;

    public UserController(User model, UserView view, SimpleRouter router) {
        super(model,view);
        this.model = model;
        this.view = view;
        this.router = router;
    }

    public void setUserName(String name){
        model.setUsername(name);
        model.update();
    }

    public void setUserPassword(String password){
        model.setPassword(password);
        model.update();
    }

    public void addUser(User user){
        user.add();
    }

    public void deleteUser(User user){
        user.delete();
    }

    public void updateUser(User user){
        user.update();
    }

    public List<User> queryUser(String...info) {
        List<User> users = model.query(info);
        view.tableModel.setRowCount(0);
        for (User user: users) {
            System.out.println(user.getId() + "\t" + user.getUsername());
            view.tableModel.addRow(new Object[]{user.getId(),user.getUsername(),user.getAdmin()});
        }
        return users;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("command:"+command);
        String username = view.fieldUsername.getText().equals("")?"":view.fieldUsername.getText();
        String password = new String(view.fieldPassword.getPassword());
        boolean admin = (Boolean) view.comboBoxAdmin.getSelectedItem();
        switch (command) {
            case "addUser" -> {
                User newUser = new User(username, password);
                if (username.length() > 0 && password.length() >= 6) {
                    newUser.setAdmin(admin);
                    newUser.add();
                }else{
                    view.showMessage("Adding user failed");
                }
                queryUser("admin="+(admin?1:0));
                view.refresh();
            }
            case "queryUser" -> {
                if(!username.equals("")){
                    queryUser("username='"+username+"'","admin="+(admin?1:0));
                }else{
                    queryUser("admin="+(admin?1:0));
                }
                view.refresh();
            }
            case "deleteUser" -> {
                int[] selectedRows = view.table.getSelectedRows();
                if(selectedRows.length > 0){
                    for (int row:selectedRows) {
                        int id = Integer.parseInt(view.table.getValueAt(row,0).toString());
                        User deletedUser = new User();
                        deletedUser.setId(id);
                        deletedUser.delete();
                    }
                }else{
                    view.showMessage("Didn't select row");
                }
                queryUser("admin="+(admin?1:0));
                view.refresh();
            }
            case  "updateUser" ->{
                int selectedRow = view.table.getSelectedRow();
                User user = (User) new User().query("id="+router.getData().get("id")).get(0);
                user.setId(Integer.parseInt(router.getData().get("id")));
                user.setUsername(username);
                user.setPassword(password);
                user.setAdmin(admin);
                updateUser(user);
                System.out.println("Username:"+router.getData().get("username")+" Password:"+router.getData().get("password"));
                view.refresh();
                queryUser("admin="+(admin?1:0));
//                router.switchView(new ReaderView(), router.getData());
            }
        }

    }
}
