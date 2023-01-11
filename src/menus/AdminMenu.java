package menus;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static menus.MainMenu.mainMenu;

public class AdminMenu {
    public static void main(String[] args) {
        adminMenu();
    }

    public static void adminMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("Admin Menu");
        System.out.println("1.see all customers");
        System.out.println("2.see all rooms");
        System.out.println("3.see al reservation");
        System.out.println("4.add a room");
        System.out.println("5.back to main menu");
        System.out.println("-----------------------------------------------------");
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid choice");
            adminMenu();
        }

        switch (choice) {
            case 1:
                allCustomer();
                adminMenu();
                break;
            case 2:
                allRooms();
                adminMenu();
                break;
            case 3:
                allReservation();
                adminMenu();
                break;
            case 4:
                addARoom();
                adminMenu();
                break;
            case 5:
                mainMenu();
                break;
            default:
                System.out.println("Invalid choice");
                adminMenu();
        }
    }


    public static void allCustomer() {
        CustomerService mySingleton = CustomerService.getInstance();
        ReservationService mySingleton2 = ReservationService.getInstance();

        for (Customer customer : mySingleton.getAllCustomers()) {
            System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getEmail());
        }
    }

    public static void allRooms() {

        for (IRoom room : AdminResource.getAllRooms()) {
            System.out.println("Rooms: " + room.getRoomNumber() + " " + room.getRoomType() + " " + room.getRoomPrice());
        }
    }

    public static void allReservation() {
        AdminResource.displayAllReservation();
    }

    public static void addARoom() {
        List<IRoom> rooms = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String roomNumber, input;
        double roomPrice;
        RoomType roomType = null;
        boolean anotherRoom = true;
        int roomTypeInt;

        while (anotherRoom) {
            System.out.println("Enter room number:");
            roomNumber = scanner.nextLine();
            System.out.println("Enter room price:");
            roomPrice = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter room type:  1 for single bed,   2 for double bed");
            roomTypeInt = Integer.parseInt(scanner.nextLine());

            if (roomTypeInt == 1 || roomTypeInt == 2) {
                if (roomTypeInt == 1) {
                    rooms.add(new Room(roomNumber, roomPrice, roomType.valueOf("SINGLE"), false));
                } else if (roomTypeInt == 2) {
                    rooms.add(new Room(roomNumber, roomPrice, roomType.valueOf("DOUBLE"), false));
                } else {
                    System.out.println("please enter a valid room type");
                }
            }

            System.out.println("Would you like to add another room? y/n");
            input = scanner.nextLine();
            switch (input) {
                case "Y":
                case "y":
                    anotherRoom = true;
                    break;
                case "N":
                case "n":
                    anotherRoom = false;
                    break;
                default:
                    System.out.println("Please enter  y/n");
            }
        }
        AdminResource.addRoom(rooms);
    }
}



