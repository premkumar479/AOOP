
import java.time.LocalDate;

class DateRange {
    private final LocalDate checkIn;
    private final LocalDate checkOut;

    // Constructor with date validation
    public DateRange(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (!checkIn.isBefore(checkOut)) {
            throw new IllegalArgumentException(
                "Check-in must be before checkout");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Check overlapping dates
    public boolean overlaps(DateRange other) {
        return this.checkIn.isBefore(other.checkOut) &&
               other.checkIn.isBefore(this.checkOut);
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }
}

class Reservation {
    private int roomNumber;
    private DateRange stay;

    public Reservation(int roomNumber, DateRange stay) {
        this.roomNumber = roomNumber;
        this.stay = stay;
    }

    // Check room availability
    public boolean conflictsWith(Reservation other) {
        if (this.roomNumber != other.roomNumber) {
            return false;
        }

        return this.stay.overlaps(other.stay);
    }
}

public class Q_Two {
    public static void main(String[] args) {

        try {
            DateRange stay1 = new DateRange(
                LocalDate.of(2026, 11, 10),
                LocalDate.of(2026, 11, 15)
            );

            DateRange stay2 = new DateRange(
                LocalDate.of(2026, 11, 13),
                LocalDate.of(2026, 11, 18)
            );

            DateRange stay3 = new DateRange(
                LocalDate.of(2026, 11, 15),
                LocalDate.of(2026, 11, 18)
            );

            Reservation r1 = new Reservation(101, stay1);
            Reservation r2 = new Reservation(101, stay2);
            Reservation r3 = new Reservation(101, stay3);

            System.out.println("Reservation 1 and 2: "
                + (r1.conflictsWith(r2) ? "Overlapping" : "Available"));

            System.out.println("Reservation 1 and 3: "
                + (r1.conflictsWith(r3) ? "Overlapping" : "Available"));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
