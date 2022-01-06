package com.library.controllers;

import com.library.MVC.Controller;
import com.library.MVC.Model;
import com.library.MVC.View;
import com.library.SimpleRouter;
import com.library.views.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeController extends Controller implements ActionListener {
    private SimpleRouter router;
    private HomeView view;

    public HomeController(Model model, HomeView view, SimpleRouter router) {
        super(model, view);
        this.view = view;
        this.router = router;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(view.z){
            view.x.setText("It couldn't be done");
        }else{
            view.x.setText("CRAP too goddamn COLD!");
        }
        view.refresh();
    }
}
