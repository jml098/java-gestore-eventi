import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event{
    private LocalTime hour;
    private BigDecimal price;

    public Concert(String title, LocalDate date, int total, LocalTime hour, BigDecimal price) {
        super(title, date, total);
        this.hour = hour;
        this.price = price;
    }

    public LocalTime getHour() {
        return hour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFormattedHour () {
        return hour.getHour() + ":" + hour.getMinute();
    }

    public String getFormattedPrice () {
        return price.setScale(2, RoundingMode.HALF_UP).toString() + "€";
    }


    @Override
    public String toString() {
        return getTitle() + " - " + getDate() + " | " + getFormattedHour() + " - " + getFormattedPrice();
    }
}
