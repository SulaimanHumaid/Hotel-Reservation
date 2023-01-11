package model;

public class FreeRoom extends Room {
    String roomNumber;
    final Double price = 0.0;
    RoomType enumeration;
    boolean isFree;

    private FreeRoom(String roomNumber, Double price, RoomType enumeration, boolean isFree) {
        super(roomNumber, 0.0, enumeration, isFree);
    }

    @Override
    public String toString() {
        return "FreeRoom{" + "roomNumber='" + roomNumber + '\'' + ", price=" + price + ", enumeration=" + enumeration + ", isFree=" + isFree + '}';
    }
}
