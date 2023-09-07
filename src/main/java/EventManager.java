import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    public static final String DATABASE = "jdbc:mysql://localhost:3306/event_manager";
    public static final String USER = "jaita91";
    public static final String PASSWORD = "JAITA91";

    private String title;

    public EventManager(String title) {
        this.title = title;
    }

    public void addEvent(String name, LocalDate date, int total) {
        try (Connection connection = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            Event event = new Event(name, date, total);

            String query = """
                    INSERT INTO event (title, total, date)
                    VALUES (?, ?, ?)
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setInt(2, event.getTotal());
            preparedStatement.setDate(3, Date.valueOf(event.getDate()));

            preparedStatement.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Event event) {
        if (event.getId() < 1) {
            System.out.println("Event must have id property.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {

            String query = """
                    UPDATE event
                    SET title = ?, date = ?, total = ?, bookings = ?
                    WHERE event.id = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setDate(2, Date.valueOf(event.getDate()));
            preparedStatement.setInt(3, event.getTotal());
            preparedStatement.setInt(4, event.getBookings());
            preparedStatement.setInt(5, event.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String query = """
                    SELECT *
                    FROM event
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            parseEventFromDB(events, preparedStatement);

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return events;
    }

    public List<Event> findEventsByDate(LocalDate date) {
        List<Event> events = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            String query = """
                    SELECT *
                    FROM event
                    WHERE date = ?
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(date));

            parseEventFromDB(events, preparedStatement);

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return events;
    }

    private void parseEventFromDB(List<Event> events, PreparedStatement preparedStatement) {
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                events.add(
                        new Event(
                                rs.getInt("id"),
                                rs.getString("title"),
                                rs.getDate("date").toLocalDate(),
                                rs.getInt("total"),
                                rs.getInt("bookings")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getEventsAmount() {
        return getAllEvents().size();
    }
}
