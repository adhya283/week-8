abstract class Room {
    int roomNo;
    boolean available = true;

    Room(int roomNo) {
        this.roomNo = roomNo;
    }

    abstract double calculatePrice(int days);
}

class StandardRoom extends Room {
    StandardRoom(int roomNo) {
        super(roomNo);
    }

    double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int roomNo) {
        super(roomNo);
    }

    double calculatePrice(int days) {
        return days * 150;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    Customer customer;
    Room room;
    String startDate, endDate;
    int days;

    Reservation(Customer customer, Room room,
                String startDate, String endDate, int days) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }
}

public class HotelBooking {

    static void reserve(Customer c, Room r,
                        String start, String end, int days) {

        if (!r.available) {
            System.out.println("Room " + r.roomNo +
                    " is not available.");
            return;
        }

        r.available = false;

        new Reservation(c, r, start, end, days);

        System.out.println("Reservation confirmed for "
                + c.name + ", Room " + r.roomNo +
                " (" + start + "-" + end + ").");

        System.out.println("Price: $" + r.calculatePrice(days));
    }

    static void cancel(Customer c, Room r) {
        r.available = true;

        System.out.println("Reservation for " + c.name +
                ", Room " + r.roomNo +
                " cancelled successfully.");
    }

    public static void main(String[] args) {

        Room standard = new StandardRoom(101);
        Room deluxe = new DeluxeRoom(201);

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        System.out.println("Standard Room 101 is available from Jan 1 to Jan 5.");

        reserve(a, standard, "Jan 1", "Jan 5", 4);

        reserve(b, standard, "Jan 3", "Jan 7", 4);

        cancel(a, standard);

        reserve(c, deluxe, "Feb 10", "Feb 12", 2);
    }
}