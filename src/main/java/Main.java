import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EventManager eventManager = new EventManager("Milano Event Manager");

        Event event = eventManager.findEventsByDate(LocalDate.parse("2023-09-18")).get(0);
        event.book(18);
        eventManager.update(event);
    }
}
