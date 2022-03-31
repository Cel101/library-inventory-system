/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colonmarcelfinalexam;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author marce
 */
public class Book extends Item {

    private String author;
    private int pages;

    /**
     *
     * @param title String for Book title
     * @param id Integer for Book ID
     * @param daysDue Due date as LocalType object
     * @param avail Boolean for availability
     * @param author String for author name
     * @param pages Integer for amount of pages
     */
    public Book(String title, int id, LocalDate daysDue, boolean avail, String author, int pages) {
        super(title, id, daysDue, avail);
        this.author = author;
        this.pages = pages;
    }

    /**
     * Description: Method to get author name
     *
     * @return Returns author name as String type
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Description: Method to get the amount of pages
     *
     * @return Number of integer type for amount of pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * Description: Method to display Book information
     *
     * @return String type with Book information
     */
    @Override
    public String display() {
        String display = "";
        String display2 = "";
        if (isAvail() == false) {
            display2 = " Due back by:" + displayDue();
        }
        display = "ID:" + getId() + " Title:" + getTitle() + " Author:" + getAuthor() + " Pages:" + getPages() + " Availablity:" + isAvail() + display2;
        return display;
    }

}
