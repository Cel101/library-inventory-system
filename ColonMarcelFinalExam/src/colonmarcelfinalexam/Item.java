/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colonmarcelfinalexam;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author Marcel Colon Date: 4/23/2020 Version: 1.0
 */
public class Item {

    private int id;
    private String title;
    private LocalDate daysDue;
    private boolean avail;

    private static int nextID = 1;

    /**
     * Description: Constructor for item
     *
     * @param title a String type for the title
     * @param id a integer type for the item ID
     * @param daysDue a LocalDate object for the due date
     * @param avail a Boolean type for availability
     */
    public Item(String title, int id, LocalDate daysDue, boolean avail) {
        this.title = title;
        this.id = id + nextID;
        ++nextID;
        this.daysDue = daysDue;
        this.avail = avail;
    }

    /**
     * Description: Method to get item ID
     *
     * @return Integer type for item ID
     */
    public int getId() {
        return id;
    }

    /**
     * Description: Method to get item title
     *
     * @return String type of the items title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Description: Method to get item availability
     *
     * @return Boolean type for item availability
     */
    public boolean isAvail() {
        return avail;
    }

    /**
     * Description: Method to get the due date
     *
     * @return Object of LocalDate type showing due date
     */
    public LocalDate getDaysDue() {
        return daysDue;
    }

    /**
     * Description: Method to format due date
     *
     * @return String type with formatted date
     */
    public String displayDue() {
        String formattedDate = daysDue.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return formattedDate;
    }

    /**
     * Description: Method to set availability
     *
     * @param avail Boolean type to set availability
     */
    public void setAvail(boolean avail) {
        this.avail = avail;
    }

    /**
     * Description: Method to set due date
     *
     * @param daysDue Due date of object type LocalDate
     */
    public void setDaysDue(LocalDate daysDue) {
        this.daysDue = daysDue;
    }

    /**
     * Description: Method to display information about the object
     *
     * @return A String type with item information
     */
    public String display() {
        String display = "ID: " + getId() + " Title: " + getTitle() + " Availablity: " + isAvail();
        return display;
    }

}//End Item Class
