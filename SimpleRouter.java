package com.library;

import com.library.MVC.View;
import com.library.views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class SimpleRouter extends JFrame implements MouseListener, View{
    private View[] views;
    private HashMap<String, String> data;
    public JPanel panelBackground;
    public JMenuBar menuBar;
    public JMenu menuSwitchUser;
    public JMenu menuSwitchReader;
    public JMenu menuSwitchBook;
    public JMenu menuSwitchHome;

    public SimpleRouter(View... views) {

        menuBar = new JMenuBar();
        menuSwitchUser = new JMenu("USER");
        menuSwitchReader = new JMenu("READER");
        menuSwitchBook = new JMenu("BOOK");
        menuSwitchHome = new JMenu("HOME");
        menuBar.add(menuSwitchUser);
        menuBar.add(menuSwitchReader);
        menuBar.add(menuSwitchBook);
        menuBar.add(menuSwitchHome);
        menuSwitchUser.addMouseListener(this);
        menuSwitchReader.addMouseListener(this);
        menuSwitchBook.addMouseListener(this);
        menuSwitchHome.addMouseListener(this);

        this.views = views;
        LoginView loginView= (LoginView)views[0];
        UserView userView = (UserView)views[1];
        ReaderView readerView = (ReaderView)views[2];
        panelBackground = (JPanel) views[0];
        add(panelBackground);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle(loginView.title);
        setSize(panelBackground.getSize());
        setLocation((int)(screenSize.getWidth()-getWidth())/2,(int)(screenSize.getHeight()-getHeight())/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display(){
        setVisible(true);
    }

    public void switchView(View switchedView,HashMap<String,String> data){
        this.data = data;
        remove(panelBackground);
        UserView userView = (UserView)views[1];
        panelBackground = userView;
        add(panelBackground);
        setJMenuBar(menuBar);
        setTitle(userView.title);
        setSize(panelBackground.getSize());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int)(screenSize.getWidth()-getWidth())/2,(int)(screenSize.getHeight()-getHeight())/2);
        System.out.println("switch");
    }

    public HashMap<String, String> getData(){
        return this.data;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();

        if(obj.equals(menuSwitchUser)){
            remove(panelBackground);
            UserView userView = (UserView)views[1];
            panelBackground = userView;
            add(panelBackground);
            setTitle(userView.title);
        }else if(obj.equals(menuSwitchReader)){
            remove(panelBackground);
            ReaderView readerView = (ReaderView)views[2];
            panelBackground = readerView;
            add(panelBackground);
            setTitle(readerView.title);
        }else if(obj.equals(menuSwitchBook)){
            remove(panelBackground);
            BookView bookView = (BookView)views[3];
            panelBackground = bookView;
            add(panelBackground);
            setTitle(bookView.title);
        }else if(obj.equals(menuSwitchHome)){
            remove(panelBackground);
            HomeView homeView = (HomeView)views[4];
            panelBackground = homeView;
            add(panelBackground);
            setTitle(homeView.title);
        }
        SwingUtilities.updateComponentTreeUI(this);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
