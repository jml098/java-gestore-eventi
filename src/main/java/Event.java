import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Event {
    private String title;
    private LocalDate date;
    private int total;
    private int bookings = 0;

    public Event(String title, LocalDate date, int total) {
        this.title = title;
        this.date = checkDate(date);
        this.total = checkTotal(total);
    }

    private LocalDate checkDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) throw new RuntimeException("Invalid date");

        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = checkDate(date);
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

    private int checkTotal(int total) {
        if (total <= 0) throw new RuntimeException("Total number must be greater than 0");

        return total;
    }


    @Override
    public String toString() {
        return title + "\n" +
                "Date: " + "\n" +
                "\tDay " + date.getDayOfWeek().name() + "\n" +
                "\tMonth " + date.getMonth().name() + "\n" +
                "\tYear " + date.getYear();

    }
}
