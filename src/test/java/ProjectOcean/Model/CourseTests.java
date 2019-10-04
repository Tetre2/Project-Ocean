package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseTests {

    @Test
    public void courseUniqueIdTest() {
        Course course1 = new Course("DAT017","Maskinorienterad programmering", 7.5f, 1, "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        Course course2 = new Course("DAT017","Maskinorienterad programmering", 7.5f, 1, "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        //Checks that UUID references are different
        Assert.assertFalse(course1.getId()==course2.getId());
        //Checks that UUID values are different
        Assert.assertFalse(course1.getIdToString().equals(course2.getIdToString()));

    }

}
