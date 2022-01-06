package com.library.models;

import com.library.MVC.Model;

public class Reader extends Model {
    private int id;
    private String readerName;
    private String type;
    private boolean returned;
    private int maxBorrowedDays;
    private int maxBorrowedAmounts;

    public Reader(){
        super("Reader",new String[]{"id","readerName", "type", "maxBorrowedDays", "maxBorrowedAmounts","returned"},"id");
    }

    public Reader(int id, String readerName, String type, int maxBorrowedDays, int maxBorrowedAmounts) {
        this();
        this.id = id;
        this.readerName = readerName;
        this.type = type;
        this.maxBorrowedDays = maxBorrowedDays;
        this.maxBorrowedAmounts = maxBorrowedAmounts;
        this.returned = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public int getMaxBorrowedDays() {
        return maxBorrowedDays;
    }

    public void setMaxBorrowedDays(int maxBorrowedDays) {
        this.maxBorrowedDays = maxBorrowedDays;
    }

    public int getMaxBorrowedAmounts() {
        return maxBorrowedAmounts;
    }

    public void setMaxBorrowedAmounts(int maxBorrowedAmounts) {
        this.maxBorrowedAmounts = maxBorrowedAmounts;
    }

}
