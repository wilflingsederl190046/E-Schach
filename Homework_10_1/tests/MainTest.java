import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void getDateTimeBetweenYear() {
        LocalDateTime d1 = LocalDateTime.of(2019, 2, 1, 13, 55, 23);
        LocalDateTime d2 = LocalDateTime.of(2021, 2, 1, 13, 55, 23);
        int[] expResult = {2, 0, 0, 0, 0, 0};
        int[] result = Main.getDateTimeBetween(d1, d2);
        assertArrayEquals(expResult, result);
    }

    @Test
    void getDateTimeBetweenMonth() {
        LocalDateTime d1 = LocalDateTime.of(2019, 2, 1, 13, 55, 23);
        LocalDateTime d2 = LocalDateTime.of(2019, 6, 1, 13, 55, 23);
        int[] expResult = {0, 4, 0, 0, 0, 0};
        int[] result = Main.getDateTimeBetween(d1, d2);
        assertArrayEquals(expResult, result);
    }

    @Test
    void getDateTimeBetweenDay() {
        LocalDateTime d1 = LocalDateTime.of(2019, 2, 1, 13, 55, 23);
        LocalDateTime d2 = LocalDateTime.of(2019, 2, 24, 13, 55, 23);
        int[] expResult = {0, 0, 23, 0, 0, 0};
        int[] result = Main.getDateTimeBetween(d1, d2);
        assertArrayEquals(expResult, result);
    }

    @Test
    void getDateTimeBetweenHour() {
        LocalDateTime d1 = LocalDateTime.of(2019, 2, 1, 13, 55, 23);
        LocalDateTime d2 = LocalDateTime.of(2019, 2, 1, 14, 55, 23);
        int[] expResult = {0, 0, 0, 1, 0, 0};
        int[] result = Main.getDateTimeBetween(d1, d2);
        assertArrayEquals(expResult, result);
    }

    @Test
    void getDateTimeBetweenMinute() {
        LocalDateTime d1 = LocalDateTime.of(2019, 2, 1, 13, 55, 23);
        LocalDateTime d2 = LocalDateTime.of(2019, 2, 1, 13, 56, 23);
        int[] expResult = {0, 0, 0, 0, 1, 0};
        int[] result = Main.getDateTimeBetween(d1, d2);
        assertArrayEquals(expResult, result);
    }

    @Test
    void getDateTimeBetweenSecond() {
        LocalDateTime d1 = LocalDateTime.of(2019, 2, 1, 13, 55, 23);
        LocalDateTime d2 = LocalDateTime.of(2019, 2, 1, 13, 55, 53);
        int[] expResult = {0, 0, 0, 0, 0, 30};
        int[] result = Main.getDateTimeBetween(d1, d2);
        assertArrayEquals(expResult, result);
    }

    @Test
    void getDateTimeBetweenWithNull() {
        LocalDateTime d1 = LocalDateTime.of(2019, 2, 1, 13, 55, 23);
        LocalDateTime d2 = null;
        int[] expResult = null;
        int[] result = Main.getDateTimeBetween(d1, d2);
        assertEquals(expResult, result);
    }
}