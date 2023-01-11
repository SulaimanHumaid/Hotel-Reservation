package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    public static final CustomerService CUSTOMER_SERVICE = CustomerService.getInstance();
    private static final ReservationService RESERVATION_SERVICE = ReservationService.getInstance();

    public Customer getCustomer(String email) {
        return CUSTOMER_SERVICE.getCustomer(email);
//        return CustomerService.getCustomer(email);
    }

    public static void addRoom(List<IRoom> rooms) {

        for (IRoom room : rooms) {
//            ReservationService.addRoom(room);
            RESERVATION_SERVICE.addRoom(room);
        }
    }

    public static Collection<IRoom> getAllRooms() {
        return ReservationService.roomsList;
    }

    public Collection<Customer> getAllCustomers() {
        return CUSTOMER_SERVICE.getAllCustomers();
    }

    public static void displayAllReservation() {
        RESERVATION_SERVICE.printAllReservation();
    }

}
