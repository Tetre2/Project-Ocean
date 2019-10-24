package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class YearTests {

    int studyPeriod;
    int slot;
    int yearNumber;

    @Before
    public void before() {
        studyPeriod = 1;
        slot = 1;
        yearNumber = 1;
    }

    @Test
    public void addCourseTest() {
        Year year = new Year();
        Course course = CourseFactory.CreateCourse(
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
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void getCourseInStudyPeriodTest() {
        Year year = new Year();
        Course expectedCourse = CourseFactory.CreateCourse("BAT123",
                "Beroendespecifika paradigmer",
                "7.5",
                "3",
                "Anders Bölinge",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("")));
        year.addCourse(expectedCourse, studyPeriod, slot);

        ICourse actualCourse = year.getCourseInStudyPeriod(studyPeriod, slot);
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
        Course course = CourseFactory.CreateCourse(
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

        year.addCourse(course, studyPeriod, slot);
        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

        year.removeCourse(studyPeriod, slot);
        Assert.assertTrue(year.getStudyPeriod(studyPeriod).getCourse1() == null);
    }
}
