package com.library;

import com.library.controllers.*;
import com.library.models.Book;
import com.library.models.Reader;
import com.library.models.User;
import com.library.views.*;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        UserView userView = new UserView();
        ReaderView readerView = new ReaderView();
        BookView bookView = new BookView();
        HomeView homeView = new HomeView();
        SimpleRouter  router = new SimpleRouter(loginView,userView,readerView,bookView,homeView);
        loginView.registerEventListeners(new LoginController(new User(),loginView,router));
        userView.registerEventListeners(new UserController(new User(),userView, router));
        readerView.registerEventListeners(new ReaderController(new Reader(), readerView, router));
        bookView.registerEventListeners(new BookController(new Book(), bookView, router));
        homeView.registerEventListeners(new HomeController(new Book(),homeView, router));
        router.display();
//        User user1 = new User("Moron", "123456");
//        User user2 = new User("Richard", "55555");
//        user1.add();
//        user2.add();
//        user1.delete();
//        user1.setUsername("new");
//        user1.update();
//        List<Book> books = new Book().query("bookName='fuck'");
//        Book b = books.get(0);
//        b.setBookName("fuck2");
//        b.update();
//        b.delete();
    }
}
