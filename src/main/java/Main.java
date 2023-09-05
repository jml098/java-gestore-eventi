import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean tryEvent = true;
        while (tryEvent) {
            try {
                System.out.print("Inserisci nome evento: ");
                String name = scanner.nextLine();

                System.out.print("Inserisci data evento: ");
                LocalDate date = LocalDate.parse(scanner.nextLine());


                System.out.print("Inserisci posti totali evento: ");
                int total = Integer.parseInt(scanner.nextLine());


                Event event = new Event(name, date, total);

                System.out.print("Vuoi prenotare dei posti? (si/no)");
                boolean wantsToBook = scanner.nextLine().equalsIgnoreCase("si");

                boolean tryBook = true;
                while (tryBook && wantsToBook) {
                    try {
                        System.out.print("Quanti posti vuoi prenotare?: ");
                        int number = Integer.parseInt(scanner.nextLine());

                        event.book(number);
                        tryBook = false;
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }

                System.out.print("Vuoi disdire dei posti? (si/no)");
                boolean wantsToCancel = scanner.nextLine().equalsIgnoreCase("si");

                boolean tryCancel = true;
                while (tryCancel && wantsToCancel) {
                    try {
                        System.out.print("Quanti posti vuoi disdire?: ");
                        int number = Integer.parseInt(scanner.nextLine());

                        event.cancelBooking(number);
                        tryCancel = false;
                    } catch (RuntimeException e) {

                    }
                }


                System.out.println("prenotati " + event.getBookings() + " posti su " + event.getTotal());
                tryEvent = false;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
