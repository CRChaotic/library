package com.library.controllers;

import com.library.MVC.Controller;
import com.library.SimpleRouter;
import com.library.models.User;
import com.library.views.LoginView;
import com.library.views.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class LoginController extends Controller implements ActionListener {
    private User model;
    private LoginView view;
    private SimpleRouter router;

    public LoginController(User model, LoginView view, SimpleRouter router) {
        super(model, view);
        this.model = model;
        this.view = view;
        this.router = router;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "signIn" -> {
                System.out.println(view.fieldUsername.getText() + " " + new String(view.fieldPassword.getPassword()));
                String username = "'" + view.fieldUsername.getText() + "'";
                String password = "'" + new String(view.fieldPassword.getPassword()) + "'";
                List<User> user = model.query("username=" + username, "password=" + password);
                if (user.size() == 1) {
                    System.out.println("Login In!");
                    HashMap<String,String> d = new HashMap<>();
                    d.put("id", user.get(0).getId()+"");
                    d.put("username",view.fieldUsername.getText());
                    d.put("password",new String(view.fieldPassword.getPassword()));
                    router.switchView(new UserView(), d);
                } else {
                    view.showMessage("Username or password was wrong");
                    view.refresh();
                }
            }
            case "signUp" -> System.out.println("Sign up");
        }
    }
}
