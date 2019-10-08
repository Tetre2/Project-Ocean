package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseTests {

    @Test
    public void courseUniqueIdTest() {
        ICourse course1 = new Course("DAT017","Maskinorienterad programmering", 7.5f, 1, "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        ICourse course2 = new Course("DAT017","Maskinorienterad programmering", 7.5f, 1, "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        //Checks that ICourse references are different
        Assert.assertFalse(course1 == course2);

        //Checks that ICourse values are different
        Assert.assertFalse(course1.equals(course2));

    }

}
