package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class HotelResource {

    static List<Room> rooms = new ArrayList<Room>();
    public static final CustomerService CUSTOMER_SERVICE = CustomerService.getInstance();
    private static final ReservationService RESERVATION_SERVICE = ReservationService.getInstance();

    public static Customer getCustomer(String email) {
//        return CustomerService.getCustomer(email);
        CustomerService mySingleton = CustomerService.getInstance();
        return mySingleton.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) {
//        CustomerService.addCustomer(email, firstName, lastName);
        CustomerService mySingleton = CustomerService.getInstance();
        mySingleton.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber) {
        ReservationService mySingleton = ReservationService.getInstance();
        return mySingleton.getARoom(roomNumber);
        //        return ReservationService.getARoom(roomNumber);
    }


    public static Reservation bookARoom(String customerEmail, IRoom room, Date CheckInDate, Date CheckOutDate) {
//        ReservationService.reserveARoom(CustomerService.getCustomer(customerEmail), room, CheckInDate, CheckOutDate);
//        Reservation reservation = new Reservation(CustomerService.getCustomer(customerEmail), room, CheckInDate, CheckOutDate);
//        ReservationService.reserveARoom(mySingleton.getCustomer(customerEmail), room, CheckInDate, CheckOutDate);

        RESERVATION_SERVICE.reserveARoom(CUSTOMER_SERVICE.getCustomer(customerEmail), room, CheckInDate, CheckOutDate);
        Reservation reservation = new Reservation(CUSTOMER_SERVICE.getCustomer(customerEmail), room, CheckInDate, CheckOutDate);
        return (reservation);
    }

    public static Collection<Reservation> getCustomerReservation(String customerEmail) {
        CustomerService mySingleton = CustomerService.getInstance();
        ReservationService mySingleton2 = ReservationService.getInstance();

        return mySingleton2.getCustomersReservation(mySingleton.getCustomer(customerEmail));


//        return ReservationService.getCustomersReservation(mySingleton.getCustomer(customerEmail));

        //        return ReservationService.getCustomersReservation(CustomerService.getCustomer(customerEmail));
    }


    public static Collection<IRoom> findARoom(Date CheckInDate, Date CheckOutDate) {
        ReservationService mySingleton = ReservationService.getInstance();
        return mySingleton.findRooms(CheckInDate, CheckOutDate);
//        return ReservationService.findRooms(CheckInDate, CheckOutDate);
    }
}