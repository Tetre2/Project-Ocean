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
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

    }

    @Test
    public void getCourseInStudyPeriodTest() {
        Year year = new Year();

        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course expectedCourse = CourseFactory.CreateCourse();
        year.addCourse(expectedCourse, studyPeriod, slot);

        ICourse actualCourse = year.getCourseInStudyPeriod(studyPeriod, slot);
        System.out.println(expectedCourse.toString());
        System.out.println(actualCourse.toString());

        Assert.assertTrue(expectedCourse.equals(actualCourse));

    }

    @Test
    public void getStudyPeriodsSizeTest() {
        Year year = new Year();
        int nStudyPeriods = 4;

        Assert.assertEquals(year.getStudyPeriodsSize(), nStudyPeriods);
    }

    @Test
    public void removeCourseTest() {
        Year year = new Year();
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

        year.removeCourse(studyPeriod, slot);

        Assert.assertTrue(year.getStudyPeriod(studyPeriod).getCourse1() == null);
    }
}
