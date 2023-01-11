package menus;

import api.HotelResource;
import model.IRoom;
import service.CustomerService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static menus.AdminMenu.adminMenu;

public class MainMenu {

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {


        Scanner scanner = new Scanner(System.in);
        boolean er = true;
        boolean testing = true;
        boolean finished = true;
        int choice = 0;
        while (finished) {
            try {
                do {
                    System.out.println("-----------------------------------------------------");
                    System.out.println("Main Menu");
                    System.out.println("1.Find and Reserve a room");
                    System.out.println("2.see my reservation");
                    System.out.println("3.create an account");
                    System.out.println("4.admin");
                    System.out.println("5.exit");
                    System.out.println("-----------------------------------------------------");
                    String acc;
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            findAndReserve();
                            mainMenu();
                            break;
                        case 2:
                            seeReservation();
                            break;
                        case 3:
                            account();
                            mainMenu();
                            break;

                        case 4:
                            adminMenu();
                            break;
                        case 5:
                            System.out.println("finished");
                            finished = false;
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                } while (testing);
            } catch (Exception e) {
                System.out.println("Invalid Input");
                System.out.println(e.getLocalizedMessage());
            }
        }
    }


    public static void findAndReserve() {
        CustomerService mySingleton = CustomerService.getInstance();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        Date checkInDate, checkOutDate;
        try {
            System.out.println("enter check in date: (mm/dd/yyyy)");
            checkInDate = dateFormat.parse(scanner.next());
            System.out.println("enter check out date: (mm/dd/yyyy)");
            checkOutDate = dateFormat.parse(scanner.next());


            if (HotelResource.findARoom(checkInDate, checkOutDate).isEmpty()) {         //print if no rooms are available
                Calendar checkInDateIncrement = Calendar.getInstance();
                Calendar checkOutDateIncrement = Calendar.getInstance();
                checkInDateIncrement.setTime(checkInDate);
                checkOutDateIncrement.setTime(checkOutDate);
                checkInDateIncrement.add(Calendar.DATE, 7);
                checkOutDateIncrement.add(Calendar.DATE, 7);


                if (HotelResource.findARoom(checkInDateIncrement.getTime(), checkOutDateIncrement.getTime()).isEmpty()) {
                    System.out.println("unfortunately there are no rooms available.");
                    mainMenu();
                } else {
                    checkInDate = checkInDateIncrement.getTime();
                    checkOutDate = checkOutDateIncrement.getTime();

                    System.out.println("unfortunately there are no rooms available for the given dates, we recommend the following dates: ");
                    System.out.println("Check In: " + checkInDateIncrement.getTime());
                    System.out.println("Check Out: " + checkOutDateIncrement.getTime());
                    for (IRoom room : HotelResource.findARoom(checkInDateIncrement.getTime(), checkOutDateIncrement.getTime())) {
                        System.out.println(room);
                    }
                }
            } else {
                for (IRoom room : HotelResource.findARoom(checkInDate, checkOutDate)) {
                    System.out.println(room);
                }
            }
            System.out.println("would you like to book a room? y/n");

            if (yesOrNo(scanner.next())) {
                mainMenu();
            }

            System.out.println("do you have an account?  y/n");
            if (yesOrNo(scanner.next())) {
                System.out.println("please make one from the main menu ");
                mainMenu();
            }
            System.out.println("please enter your email: ");
            String email = scanner.next();

//            if (!CustomerService.checkCustomerEmail(email)) {               //check RegEx
//                mainMenu();
//            }
            if (!mySingleton.checkCustomerEmail(email)) { //check
                mainMenu();
            }
            if (HotelResource.getCustomer(email) == null) {                 // check if customer is registered
                System.out.println("no account is registered with the given email, please create one from the main menu");
                mainMenu();
            }
            System.out.println("enter room number you would like to book: ");
            String roomNum = scanner.next();
            if (HotelResource.getRoom(roomNum) == null) {
                System.out.println("room not found");
                mainMenu();
            } else {
                HotelResource.bookARoom(email, HotelResource.getRoom(roomNum), checkInDate, checkOutDate);
                System.out.println("room booked successfully");
            }
        } catch (Exception e) {
            System.out.println("wrong input");

        }
    }


    public static void seeReservation() {
        CustomerService mySingleton = CustomerService.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your email: ");
        String email = scanner.nextLine();
//        if (!CustomerService.checkCustomerEmail(email))
        if (!mySingleton.checkCustomerEmail(email)) {
            System.out.println("enter an email with the given format:  (mm/dd/yyyy)");//check RegEx
            mainMenu();
        }
        if (HotelResource.getCustomer(email) == null) {                 // check if customer is registered
            System.out.println("no account is registered with the given email, please create one from the main menu");
            mainMenu();
        }

        if (HotelResource.getCustomerReservation(email).isEmpty()) {
            System.out.println("sorry you don't have any reservations");
        } else {
            System.out.println(HotelResource.getCustomerReservation(email));
        }
    }

    public static void account() {
        CustomerService mySingleton = CustomerService.getInstance();
        String email, fname, lname;
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your email: (example@gmail.com)");
        email = scanner.nextLine();

//        if (CustomerService.checkCustomerEmail(email))
        if (mySingleton.checkCustomerEmail(email)) {
            System.out.println("please enter your firstName: ");
            fname = scanner.nextLine();
            System.out.println("please enter your lastName: ");
            lname = scanner.nextLine();
            HotelResource.createACustomer(email, fname, lname);
            mainMenu();
        } else {
            System.out.println("please enter a valid email");
            account();
        }
    }

    public static boolean yesOrNo(String s) {
        if (s.charAt(0) == 'y' || s.charAt(0) == 'Y') {
            return false;
        } else return true;
    }

}