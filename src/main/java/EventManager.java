import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private String title;
    private List<Event> events = new ArrayList<>();

    public EventManager(String title) {
        this.title = title;
    }

    public static void main(String[] args) {
        EventManager lalala = new EventManager("lalala");

        lalala.addEvent(new Event("abc", LocalDate.parse("2024-03-05"), 50));

        System.out.println(lalala);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> findEventByDate(LocalDate date) {
        return events.stream().filter(event -> event.getDate().isEqual(date)).toList();
    }

    public int getEventsAmount() {
        return events.size();
    }

    public void reset() {
        events = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "EventManager{" +
                "title='" + title + '\'' +
                ", events=" +  events.toString() +
                '}';
    }
}
