package model;

import java.util.Objects;

public class Room implements IRoom {

    public final  String roomNumber;
    private final  Double price;
    private final  RoomType enumeration;
    private final  boolean isFree;

    public Room(String roomNumber, Double price, RoomType enumeration, boolean isFree) {
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
        this.isFree = isFree;
    }



    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString() {
        return "Room: " + roomNumber + ", price: " + price + ", " + enumeration + " BED";
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Room room = (Room) o;
//        return isFree == room.isFree && roomNumber.equals(room.roomNumber) && price.equals(room.price) && enumeration == room.enumeration;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, price, enumeration, isFree);
    }
}
