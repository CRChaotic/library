package com.library.models;

import com.library.MVC.Model;

public class Book extends Model {
    private String id;
    private String bookName;
    private String type;
    private String author;
    private String translator;
    private String publisher;
    private String publishedDate;
    private float price;
    private int stock;

    public Book(){
        super(
                "book",
                new String[]{"id","bookName", "type", "author", "translator", "publisher", "publishedDate", "price", "stock"},
                "id"
        );
    }
    public Book(String id, String bookName, String type, String author, String translator, String publisher, String publishedDate, float price) {
        this();
        this.id = id;
        this.bookName = bookName;
        this.type = type;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
        this.stock = 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
