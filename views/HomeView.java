package com.library.views;

import com.library.MVC.View;
import com.library.controllers.HomeController;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel implements View {
    public String title;
    private JPanel panel;
    public JLabel x;
    public JButton y;
    public JButton y2;
    public boolean z;

    public HomeView(){
        title = "Home";
        panel = new JPanel();
        x = new JLabel("CRAP too goddamn COLD!");
        y = new JButton("SMASH");
        x.setFont(new Font("Serif", Font.BOLD, 50));
        panel.add(x);
        add(panel);
        add(y);
        setLayout(new GridLayout(2,1));
        setSize(1200,600);
        z = false;
    }

    public void registerEventListeners(HomeController controller){
        y.addActionListener(controller);
    }

    public void refresh(){
        z = !z;
    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }
}
