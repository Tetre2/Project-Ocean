package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseTests {

    @Test
    public void courseUniqueIdTest() {
        CourseFactory.SetStudyPeriod(1);
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", 7.5f);
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod(1);
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", 7.5f);
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        ICourse course1 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod(1);
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", 7.5f);
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        ICourse course2 = CourseFactory.CreateCourse();

        //Checks that ICourse references are different
        Assert.assertFalse(course1 == course2);

        //Checks that ICourse values are different
        Assert.assertFalse(course1.equals(course2));

    }

}
