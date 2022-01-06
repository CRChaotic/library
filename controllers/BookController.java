package com.library.controllers;

import com.library.MVC.Controller;
import com.library.SimpleRouter;
import com.library.models.Book;
import com.library.views.BookView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookController extends Controller implements ActionListener {
    private Book model;
    private BookView view;
    private SimpleRouter router;

    public BookController(Book model, BookView view, SimpleRouter router) {
        super(model, view);
        this.model = model;
        this.view = view;
        this.router = router;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
    }
}
