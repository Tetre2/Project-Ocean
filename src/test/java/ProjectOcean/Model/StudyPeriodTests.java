package ProjectOcean.Model;

import ProjectOcean.IO.CourseLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPeriodTests {

    private int slot;

    @Before
    public void before() {
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();

        ICourse course = CourseFactory.CreateCourse(
                "DAT017",
                "Maskinorienterad programmering",
                "7.5",
                "1",
                "Rolf Söderström",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("Informationsteknik")));

        studyPeriod.addCourse(course, slot);

        Assert.assertTrue(studyPeriod.getCourse1() == course || studyPeriod.getCourse2() == course);

    }

    @Test
    public void removeCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();

        ICourse course = CourseFactory.CreateCourse(
                "DAT017",
                "Maskinorienterad programmering",
                "7.5",
                "1",
                "Rolf Söderström",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("Informationsteknik")));

        studyPeriod.addCourse(course, slot);
        studyPeriod.removeCourse(slot);

        Assert.assertTrue(course != studyPeriod.getCourse1());

        ICourse course2 = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));
        studyPeriod.addCourse(course, slot);
        studyPeriod.addCourse(course2, slot);
        studyPeriod.removeCourse(slot);

        Assert.assertTrue(course2 != studyPeriod.getCourse2());
    }

    @Test
    public void equalsTest(){
        StudyPeriod studyPeriod1 = new StudyPeriod();
        StudyPeriod studyPeriod2 = new StudyPeriod();
        List<ICourse> courses = CourseLoader.generatePreDefinedCourses();

        studyPeriod1.addCourse(courses.get(0), 1);
        studyPeriod2.addCourse(courses.get(0), 1);

        Assert.assertTrue(studyPeriod1.equals(studyPeriod2));

        //----
        studyPeriod1.addCourse(courses.get(2), 2);
        Assert.assertFalse(studyPeriod1.equals(studyPeriod2));

    }

}
