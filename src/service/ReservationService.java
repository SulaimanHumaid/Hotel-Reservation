package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ReservationService {


    private static ReservationService INSTANCE;

    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService();
        }

        return INSTANCE;
    }

    private ReservationService() {
    }


    public static Collection<Reservation> reservationList = new ArrayList<Reservation>();
    public static Collection<IRoom> roomsList = new ArrayList<IRoom>();


    public void addRoom(IRoom room) {
        roomsList.add(room);
    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : roomsList) {
            if (room.getRoomNumber().equals(roomId)) {
                if (checkRoom(roomId)) {
                    return room;
                }
            }
        }
        return null;
    }


    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation res = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(res);
        return res;

    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> unavailableRooms = new ArrayList<IRoom>();
        Collection<IRoom> availableRooms = new ArrayList<IRoom>(roomsList);
        if (reservationList.isEmpty()) {
            return roomsList;
        } else {
            for (Reservation reservation : reservationList) {
                if (
                        reservation.getCheckInDate().after(checkInDate) && reservation.getCheckInDate().before(checkOutDate) ||
                                reservation.getCheckOutDate().after(checkInDate) && reservation.getCheckOutDate().before(checkOutDate) ||
                                reservation.getCheckInDate().after(checkInDate) && reservation.getCheckOutDate().before(checkOutDate) ||
                                reservation.getCheckInDate().equals(checkInDate) && reservation.getCheckOutDate().equals(checkOutDate) ||
                                reservation.getCheckInDate().before(checkInDate) && reservation.getCheckOutDate().after(checkOutDate) ||
                                reservation.getCheckInDate().equals(checkInDate) && reservation.getCheckOutDate().after(checkOutDate) ||
                                reservation.getCheckInDate().before(checkInDate) && reservation.getCheckOutDate().equals(checkOutDate)
                ) {
                    unavailableRooms.add(reservation.getRoom()); // unavailable rooms
                }
            }
        }
        for (IRoom room : roomsList) {
            for (IRoom room2 : unavailableRooms) {
                if (room2.getRoomNumber().equals(room.getRoomNumber())) {
                    availableRooms.remove(room);
                }
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> customerReservation = new ArrayList<Reservation>();
        for (Reservation reservation : reservationList) {
            if (reservation.getCustomer().getEmail().equals(customer.getEmail())) {
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }

    public void printAllReservation() {
        if (!reservationList.isEmpty()) {
            for (Reservation reservation : reservationList) {
                System.out.println(reservation);
            }
        } else System.out.println("there are no reservations");
    }

    boolean checkRoom(String roomId) {
        for (IRoom room : roomsList) {
            if (room.getRoomNumber().equals(roomId)) {
                if (roomsList.contains(room)) {
                    return true;
                }
            }
        }
        return false;
    }
}
