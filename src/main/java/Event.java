import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Event {
    private int id = 0;
    private String title;
    private LocalDate date;
    private int total;
    private int bookings = 0;


    public Event(String title, LocalDate date, int total) throws RuntimeException {
        this.title = title;
        this.date = checkDate(date);
        this.total = checkTotal(total);
    }

    public Event(int id, String title, LocalDate date, int total, int bookings) throws RuntimeException {
        this.id = id;
        this.title = title;
        this.date = checkDate(date);
        this.total = checkTotal(total);
        this.bookings = bookings;
    }


    private LocalDate checkDate(LocalDate date) throws RuntimeException {
        if (date.isBefore(LocalDate.now())) throw new RuntimeException("Invalid date");

        return date;
    }

    private int checkTotal(int total) throws RuntimeException {
        if (total <= 0) throw new RuntimeException("Total number must be greater than 0");

        return total;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotal() {
        return total;
    }

    public int getBookings() {
        return bookings;
    }


    public void book(int n) {
        if (bookings + n > total) throw new RuntimeException("There are no spots left.");

        bookings += n;
    }

    public void cancelBooking(int n) {
        if (bookings - n < 0) throw new RuntimeException("There are not enough bookings to be canceled.");
        if (date.isBefore(LocalDate.now())) throw new RuntimeException("Event is already ended.");

        bookings -= n;
    }


    @Override
    public String toString() {
        return "\n" + title + "\n" +
                "Date: " + "\n" +
                "\tDay " + date.getDayOfWeek().name() + "\n" +
                "\tMonth " + date.getMonth().name() + "\n" +
                "\tYear " + date.getYear() + "\n" +
                "Bookings: " + bookings;

    }
}
