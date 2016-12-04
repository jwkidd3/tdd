package com.xyzcorp;

import java.time.LocalDate;

/**
 * Created by danno on 9/30/16.
 */
public class Book {
    private String name;
    private String title;
    private LocalDate checkoutDate;

    public Book(String name, String title, LocalDate checkoutDate) {

        this.name = name;
        this.title = title;
        this.checkoutDate = checkoutDate;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }
}
