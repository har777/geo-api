package api;

import org.junit.Test;
import org.postgis.Point;

public class MainTest {

    @Test
    public void printPointString() {
        Point point = new Point(77.615833, 12.936023);
        System.out.println(point.toString());
    }
}