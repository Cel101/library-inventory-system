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
 * @author marce
 */
public class DVD extends Item {

    String director;
    int length;

    /**
     *
     * @param title String for DVD title
     * @param id Integer for DVD ID
     * @param daysDue LocalDate for DVD due date
     * @param avail Boolean for availability
     * @param director String for director name
     * @param length Integer for the length of DVD
     */
    public DVD(String title, int id, LocalDate daysDue, boolean avail, String director, int length) {
        super(title, id, daysDue, avail);
        this.director = director;
        this.length = length;
    }

    /**
     * Description: Method to get director
     *
     * @return String type with director name
     */
    public String getDirector() {
        return director;
    }

    /**
     * Description: Method to get DVD length
     *
     * @return Integer type for DVD length
     */
    public int getLength() {
        return length;
    }

    /**
     * Description: Method to display DVD information
     *
     * @return String type with DVD information
     */
    @Override
    public String display() {
        String display = "";
        String display2 = "";
        if (isAvail() == false) {
            display2 = " Due back by:" + displayDue();
        }
        display = "ID:" + getId() + " Title:" + getTitle() + " Director:" + getDirector() + " Length:" + getLength() + " Availablity:" + isAvail() + display2;
        return display;
    }

}
