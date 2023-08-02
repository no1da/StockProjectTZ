package com.example.demo.models;

import java.time.LocalDate;

public class StockState {

    private int id;

    private int price;
    private LocalDate date;
    private Stock stockId;

    public StockState(int id, int price, LocalDate date) {
        this.id = id;
        this.price = price;
        this.date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
