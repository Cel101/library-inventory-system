/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colonmarcelfinalexam;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Marcel Colon Date: 4/23/2020 Version: 1.0
 */
public class ColonMarcelFinalExam {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Variables
        Scanner input = new Scanner(System.in); //Create Scanner Object to take user input
        int userChoice = -1;                    //Integer to hold user menu selection
        String userTest;                        //String to hold user input pre-test
        int tracker = 0;                        //Integer used to place hold array search that gets no hits
        double lateFee;                         //Double used to place hold total late fee
        final long BOOK_DUE = 21;               //Contstant for length of book checkout in days
        final long CD_DUE = 14;                  //Constant for length of CD checkout in days
        final long DVD_DUE = 7;                  //Constant for length of DVD checkout in days
        final double DAY_LATE = .5;             //Constant for late charge per day
        final double CD_LATE = 2.0;             //Constant for lump charge on late CDs
        final double DVD_LATE = 5.0;            //Constant for lump charge of late DVDs

        ArrayList<Item> list = new ArrayList<Item>();  //Main database list

        //Program Start
        System.out.println("******NINER LIBRARY******");
        while (userChoice != 9) {
            menuDisplay();
            while (true) {
                System.out.print("Enter you selection: ");
                userTest = input.nextLine();
                try {
                    userChoice = Integer.parseInt(userTest);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: You must enter an integer from 1 to 9");
                }
            }//End Exception While

            if (userChoice <= 0 || userChoice > 9) {
                System.out.println("ERROR: You must enter an integer from 1 to 9");
            } //OPTION 1
            else if (userChoice == 1) {
                System.out.println("");
                System.out.println("******ADD A BOOK******");
                Book newItem = addBook();
                list.add(newItem);
                System.out.println("");
                System.out.println("***BOOK ADDED TO LIBRARY***");
                System.out.println("");
            } //Option 2
            else if (userChoice == 2) {
                System.out.println("");
                System.out.println("******ADD A CD******");
                CD newItem = addCD();
                list.add(newItem);
                System.out.println("");
                System.out.println("***CD ADDED TO LIBRARY***");
                System.out.println("");

            } //Option 3
            else if (userChoice == 3) {
                System.out.println("");
                System.out.println("******ADD A DVD******");
                DVD newItem = addDVD();
                list.add(newItem);
                System.out.println("");
                System.out.println("***DVD ADDED TO LIBRARY***");
                System.out.println("");

            } //Option 4
            else if (userChoice == 4) {
                if (list.size() == 0) {
                    System.out.println("");
                    System.out.println("No items currently in database");
                    tracker++;
                } else {
                    System.out.println("");
                    System.out.println("******DISPLAY AN ITEM******");
                    int idSearch = getId();
                    for (int i = 0; i < list.size(); ++i) {
                        if (list.get(i).getId() == idSearch) {
                            tracker++;
                            System.out.println("");
                            System.out.println(list.get(i).display());
                        }
                    }
                }//Else
                if (tracker == 0) {
                    System.out.println("");
                    System.out.println("No item found");
                }
                tracker = 0;
                System.out.println("");
            } //Option 5
            else if (userChoice == 5) {
                if (list.size() == 0) {
                    tracker++;
                    System.out.println("");
                    System.out.println("No items currently in database");
                    System.out.println("");
                } else {
                    int checkout;
                    System.out.println("");
                    System.out.println("******CHECKOUT AN ITEM******");
                    checkout = getId();
                    for (int i = 0; i < list.size(); ++i) {
                        if (list.get(i).getId() == checkout) {
                            tracker++;
                            if (list.get(i).isAvail() == false) {
                                System.out.println("That item is already checked out");
                            } else if (list.get(i) instanceof Book) {
                                list.get(i).setDaysDue(list.get(i).getDaysDue().plusDays(BOOK_DUE));
                                list.get(i).setAvail(false);
                                System.out.println("");
                                System.out.println("***Book Checked Out***");
                                System.out.println("");

                            } else if (list.get(i) instanceof CD) {
                                list.get(i).setDaysDue(list.get(i).getDaysDue().plusDays(CD_DUE));
                                list.get(i).setAvail(false);
                                System.out.println("");
                                System.out.println("***CD Checked Out***");
                                System.out.println("");

                            } else if (list.get(i) instanceof DVD) {
                                list.get(i).setDaysDue(list.get(i).getDaysDue().plusDays(DVD_DUE));
                                list.get(i).setAvail(false);
                                System.out.println("");
                                System.out.println("***DVD Checked Out***");
                                System.out.println("");

                            }
                        }
                    }//End Search Loop
                }
                if (tracker == 0) {
                    System.out.println("");
                    System.out.println("Item was not found");
                    System.out.println("");
                }
                tracker = 0;
            }//End option 5 
            //Option 6
            else if (userChoice == 6) {
                long dayDiff = 0;
                if (list.size() == 0) {
                    tracker++;
                    System.out.println("");
                    System.out.println("No items currently in database");
                    System.out.println("");
                } else {
                    System.out.println("");
                    System.out.println("******RETURN AN ITEM******");
                    int itemReturn = getId();
                    LocalDate returnDate;
                    for (int i = 0; i < list.size(); ++i) {
                        if (list.get(i).getId() == itemReturn) {
                            tracker++;
                            if (list.get(i).isAvail() == true) {
                                System.out.println("");
                                System.out.println("The item ID is not checked out");
                                System.out.println("");
                            } else if (list.get(i) instanceof Book) {   //If it is a Book
                                returnDate = getDate();
                                dayDiff = ChronoUnit.DAYS.between(list.get(i).getDaysDue(), returnDate);
                                if (dayDiff > 0) {
                                    System.out.println("");
                                    System.out.print("You have a late fee of ");
                                    lateFee = dayDiff * DAY_LATE;
                                    System.out.println("$" + lateFee);

                                }
                                list.get(i).setAvail(true);
                                list.get(i).setDaysDue(LocalDate.now());
                                System.out.println("");
                                System.out.println("******BOOK RETURNED******");
                                System.out.println("");

                            }//END BOOK
                            else if (list.get(i) instanceof CD) {
                                returnDate = getDate();
                                dayDiff = ChronoUnit.DAYS.between(list.get(i).getDaysDue(), returnDate);
                                if (dayDiff > 0) {
                                    System.out.println("");
                                    System.out.print("You have a late fee of ");
                                    lateFee = (dayDiff * DAY_LATE) + CD_LATE;
                                    System.out.println("$" + lateFee);
                                }
                                list.get(i).setAvail(true);
                                list.get(i).setDaysDue(LocalDate.now());
                                System.out.println("");
                                System.out.println("******CD RETURNED******");
                                System.out.println("");
                            } else if (list.get(i) instanceof DVD) {
                                returnDate = getDate();
                                dayDiff = ChronoUnit.DAYS.between(list.get(i).getDaysDue(), returnDate);
                                if (dayDiff > 0) {
                                    System.out.println("");
                                    System.out.print("You have a late fee of ");
                                    lateFee = (dayDiff * DAY_LATE) + DVD_LATE;
                                    System.out.println("$" + lateFee);
                                }
                                list.get(i).setAvail(true);
                                list.get(i).setDaysDue(LocalDate.now());
                                System.out.println("");
                                System.out.println("******DVD RETURNED******");
                                System.out.println("");
                            }
                        }//If found

                    }//End List Search
                }
                if (tracker == 0) {
                    System.out.println("");
                    System.out.println("No item found matching that ID");
                    System.out.println("");
                }

                tracker = 0;
            }//End Option 6
            //Option 7
            else if (userChoice == 7) {
                if (list.size() == 0) {
                    tracker++;
                    System.out.println("");
                    System.out.println("No items currently in database");
                    System.out.println("");
                } else {
                    userTest = getKeyword();
                    System.out.println("");
                    for (int i = 0; i < list.size(); ++i) {
                        if (list.get(i).getTitle().toLowerCase().contains(userTest.toLowerCase())) {
                            tracker++;
                            System.out.println("Found ID:" + list.get(i).getId() + " Title:" + list.get(i).getTitle());
                        }
                    }

                    System.out.println("");
                    System.out.println("***Search Complete***");
                    System.out.println("");
                }
                if (tracker == 0) {
                    System.out.println("");
                    System.out.println("No item found matching that ID");
                    System.out.println("");
                }
                tracker = 0;
            } //Option 8
            else if (userChoice == 8) {
                if (list.size() == 0) {
                    System.out.println("");
                    System.out.println("No items currently in database");
                    System.out.println("");
                    tracker++;
                } else {
                    int removeId = getId();
                    for (int i = 0; i < list.size(); ++i) {
                        if (list.get(i).getId() == removeId) {
                            tracker++;
                            list.remove(i);
                            System.out.println("");
                            System.out.println("Item successfully removed.");
                            System.out.println("");
                        }
                    }
                }
                if (tracker == 0) {
                    System.out.println("");
                    System.out.println("Item not removed: No item found");
                    System.out.println("");
                }
                tracker = 0;
                //option 9
            } else if (userChoice == 9) {
                System.out.println("");
                System.out.println("Closing Program");
            }

        }//End Program Loop

    }//End Main

    //Methods
    /**
     * Description: Method to display Menu Options to user
     */
    public static void menuDisplay() {
        System.out.println("***MENU***");
        System.out.println("1 - Add a new book");
        System.out.println("2 - Add a new CD");
        System.out.println("3 - Add a new DVD");
        System.out.println("4 - Display an item");
        System.out.println("5 - Check out an item");
        System.out.println("6 - Return an item");
        System.out.println("7 - Search for items with a keyword");
        System.out.println("8 - Remove an item");
        System.out.println("9 - Quit the program");
    }//End Menu Display

    /**
     * Description: Method to take user input for ID purposes
     *
     * @return ID number of type Integer
     */
    public static int getId() {
        Scanner input = new Scanner(System.in);
        int idSearch;
        String userTest;
        while (true) {
            System.out.print("Enter an Item ID: ");
            userTest = input.nextLine();
            try {
                idSearch = Integer.parseInt(userTest);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID must be a number");
            }
        }//End While True
        return idSearch;
    }

    /**
     * Description: Method to take user input and create a LocalDate object
     *
     * @return Date of type LocalDate
     */
    public static LocalDate getDate() {
        Scanner input = new Scanner(System.in);
        String userTest;
        int day;
        int month;
        int year;
        final int SET_YEAR = 2000;
        System.out.println("Please enter return date.");
        while (true) {
            System.out.print("Enter the month as a number: ");
            userTest = input.nextLine();
            try {
                month = Integer.parseInt(userTest);
                if (month < 1 || month > 12) {
                    throw new Exception("Out of bounds");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Month must be a number");
            } catch (Exception f) {
                System.out.println("The month must be a number from 1 to 12");
            }
        }//End While True

        while (true) {
            System.out.print("Enter the day: ");
            userTest = input.nextLine();
            try {
                day = Integer.parseInt(userTest);
                if (month == 2) {
                    if (day < 1 || day > 28) {
                        throw new Exception();
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day < 1 || day > 30) {
                        throw new Exception();
                    }
                } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day < 1 || day > 31) {
                        throw new Exception();
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: The day must be a number");
            } catch (Exception f) {
                System.out.println("ERROR: The day provided does not fall within range of given month");
            }
        }//END DAY

        while (true) {
            System.out.print("Enter the last two digits of the year: 20");
            userTest = input.nextLine();
            try {
                year = Integer.parseInt(userTest);
                if (year > 99) {
                    throw new Exception();
                } else if (year < 20) {
                    throw new Exception();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: The year must be a number from 20 to 99");
            } catch (Exception f) {
                System.out.println("ERROR: The year must be from 20 to 99");
            }
        }
        year = year + SET_YEAR;
        LocalDate returnDate = LocalDate.of(year, Month.of(month), day);
        return returnDate;

    }//End method

    /**
     * Description: Method to create book object
     *
     * @return An object of type Book
     */
    public static Book addBook() {
        Scanner input = new Scanner(System.in);
        String title = "";
        String author;
        String userTest;
        int pages = 0;
        System.out.print("What is the title: ");
        title = input.nextLine();
        System.out.print("What is the name of the author: ");
        author = input.nextLine();
        while (true) {
            System.out.print("How many pages: ");
            userTest = input.nextLine();
            try {
                pages = Integer.parseInt(userTest);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Page amount must be integer");
            }
        }//End while true
        LocalDate localDate = LocalDate.now();
        Book newBook = new Book(title, 1000, localDate, true, author, pages);
        return newBook;
    }

    /**
     * Description: Method to create CD Object
     *
     * @return Object of type CD
     */
    public static CD addCD() {
        Scanner input = new Scanner(System.in);
        String title = "";
        String artist;
        String userTest;
        int tracks = 0;
        System.out.print("What is the title: ");
        title = input.nextLine();
        System.out.print("What is the name of the artist: ");
        artist = input.nextLine();
        while (true) {
            System.out.print("How many tracks: ");
            userTest = input.nextLine();
            try {
                tracks = Integer.parseInt(userTest);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Track amount must be integer");
            }
        }//End while true
        LocalDate localDate = LocalDate.now();
        CD newCD = new CD(title, 1000, localDate, true, artist, tracks);
        return newCD;
    }

    /**
     * Description: Method to create DVD Object
     *
     * @return Object of type DVD
     */
    public static DVD addDVD() {
        Scanner input = new Scanner(System.in);
        String title = "";
        String director;
        String userTest;
        int length = 0;
        System.out.print("What is the title: ");
        title = input.nextLine();
        System.out.print("What is the name of the director: ");
        director = input.nextLine();
        while (true) {
            System.out.print("What is the length in minutes: ");
            userTest = input.nextLine();
            try {
                length = Integer.parseInt(userTest);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Length amount must be integer");
            }
        }//End while true
        LocalDate localDate = LocalDate.now();
        DVD newDVD = new DVD(title, 1000, localDate, true, director, length);
        return newDVD;
    }

    /**
     * Description: Method to get keyword from user
     *
     * @return Keyword of type String
     */
    public static String getKeyword() {
        Scanner input = new Scanner(System.in);
        String searchWord;
        System.out.print("Enter keyword to search for: ");
        searchWord = input.nextLine();
        return searchWord;
    }

}//End Class
