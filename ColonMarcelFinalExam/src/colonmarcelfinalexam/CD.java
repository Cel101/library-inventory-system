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
 * @author Marcel Colon Date: 4/28/2020 Version: 1.0
 */
public class CD extends Item {

    String artist;
    int tracks;

    /**
     *
     * @param title String for CD title
     * @param id Integer for CD ID
     * @param daysDue LocalDate for due date
     * @param avail Boolean for availability
     * @param artist String for artist name
     * @param tracks Integer for number of tracks
     */
    public CD(String title, int id, LocalDate daysDue, boolean avail, String artist, int tracks) {
        super(title, id, daysDue, avail);
        this.artist = artist;
        this.tracks = tracks;
    }

    /**
     * Description: Method to get CD artist
     *
     * @return String type with artist name
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Description: Method to get amount of tracks on CD
     *
     * @return Integer type with the amount of tracks
     */
    public int getTracks() {
        return tracks;
    }

    /**
     * Description: Method to display CD information
     *
     * @return String type with CD information
     */
    @Override
    public String display() {
        String display = "";
        String display2 = "";
        if (isAvail() == false) {
            display2 = " Due back by:" + displayDue();
        }
        display = "ID:" + getId() + " Title:" + getTitle() + " Artist:" + getArtist() + " Tracks:" + getTracks() + " Availablity:" + isAvail() + display2;
        return display;
    }
}
