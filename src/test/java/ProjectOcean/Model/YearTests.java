package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class YearTests {

    int studyPeriod;
    int slot;

    @Before
    public void before() {
        studyPeriod = 1;
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        Year year = new Year();
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

    }

    @Test
    public void removeCourseTest() {
        Year year = new Year();
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

        year.removeCourse(studyPeriod, slot);

        Assert.assertTrue(year.getStudyPeriod(studyPeriod).getCourse1() == null);

    }
}
