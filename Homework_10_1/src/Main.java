import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        LocalDateTime d1 = LocalDateTime.of(2019, 2, 1, 13, 55, 23);
        LocalDateTime d2 = LocalDateTime.of(2021, 6, 24, 14, 56, 53);
        int[] timeBetween = getDateTimeBetween(d1, d2);
        System.out.println("There are: " + timeBetween[0] + " year(s), " + timeBetween[1] + " month(s), " + timeBetween[2] + " day(s), " + timeBetween[3] + " hour(s), " + timeBetween[4] + " minute(s), " + timeBetween[5] + " second(s) between the two dates.");
        allDataBetweenDates(d1, d2);
        System.out.println(TimeDateStringToLocalDateTime());
    }

    public static int[] getDateTimeBetween(LocalDateTime date1, LocalDateTime date2) {
        if(date1 == null || date2 == null) {
            new IllegalArgumentException("The date cannot be null!").printStackTrace();
            return null;
        }
        Period p = Period.between(date1.toLocalDate(), date2.toLocalDate());

        date2 = LocalDateTime.of(date1.getYear(), date1.getMonth(), date1.getDayOfMonth(), date2.getHour(), date2.getMinute(), date2.getSecond());

        Duration duration = Duration.between(date1, date2);

        long seconds = duration.getSeconds();

        long hours = seconds / 3600;
        long minutes = ((seconds % 3600) / 60);
        long secs = (seconds % 60);

        return new int[]{p.getYears(), p.getMonths(), p.getDays(), (int) hours, (int) minutes, (int) secs};
    }

    public static void allDataBetweenDates(LocalDateTime date1, LocalDateTime date2) {
        List<LocalDate> list = date1.toLocalDate().datesUntil(date2.toLocalDate()).collect(Collectors.toList());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).format(formatter));
        }
    }

    public static LocalDateTime TimeDateStringToLocalDateTime() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter a correct formatted dateTime-String (yyyymmddhhmmss)");
        String dateString = s.next();
        if(dateString.length() != 14) {
            new IllegalArgumentException("The date is not in a correct format! (yyyymmddhhmmss)").printStackTrace();
            return null;
        }
        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(4, 6));
        int day = Integer.parseInt(dateString.substring(6, 8));
        int hours = Integer.parseInt(dateString.substring(8, 10));
        int minutes = Integer.parseInt(dateString.substring(10, 12));
        int seconds = Integer.parseInt(dateString.substring(12, 14));
        if(year > 9999 || month < 1 || month > 12 || day < 1 || day > 31) {
            new IllegalArgumentException("The specification of year, month or day is wrong!").printStackTrace();
            return null;
        }
        return LocalDateTime.of(year, month, day, hours, minutes, seconds);
    }
}
