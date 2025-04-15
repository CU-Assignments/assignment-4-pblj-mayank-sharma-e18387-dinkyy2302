class TicketCounter {
    private int availableSeats = 5; // Total seats available

    // Synchronized method to avoid race conditions
    public synchronized void bookTicket(String name, int seatsRequested) {
        System.out.println(name + " trying to book " + seatsRequested + " seat(s).");

        if (seatsRequested <= availableSeats) {
            System.out.println("Booking successful for " + name);
            availableSeats -= seatsRequested;
        } else {
            System.out.println("Booking failed for " + name + ". Not enough seats.");
        }

        System.out.println("Remaining seats: " + availableSeats + "\n");
    }
}

class BookingThread extends Thread {
    private TicketCounter counter;
    private String customerName;
    private int seatsRequested;

    public BookingThread(TicketCounter counter, String name, int seats) {
        this.counter = counter;
        this.customerName = name;
        this.seatsRequested = seats;
        setName(name);
    }

    @Override
    public void run() {
        counter.bookTicket(customerName, seatsRequested);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();

        BookingThread vip1 = new BookingThread(counter, "VIP-1", 2);
        BookingThread vip2 = new BookingThread(counter, "VIP-2", 1);
        BookingThread user1 = new BookingThread(counter, "User-1", 2);
        BookingThread user2 = new BookingThread(counter, "User-2", 2);

        // Set priorities: Higher priority for VIPs
        vip1.setPriority(Thread.MAX_PRIORITY);
        vip2.setPriority(Thread.MAX_PRIORITY - 1);
        user1.setPriority(Thread.NORM_PRIORITY);
        user2.setPriority(Thread.MIN_PRIORITY);

        // Start threads
        vip1.start();
        vip2.start();
        user1.start();
        user2.start();
    }
}
