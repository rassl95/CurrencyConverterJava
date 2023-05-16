import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public  class GettingCurrentDate {
    private static final LocalDate DATE = LocalDate.now();
    private static final String CURRENTDATE = DATE.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    private GettingCurrentDate() {
    }

    public static String getCurrentDate() {
        return CURRENTDATE;
    }
}
