package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class ScheduleTests {

    private int studyPeriod;
    private int slot;
    private int year;

    @Before
    public void before() {
        studyPeriod = 1;
        slot = 1;
        year = 1;
    }

    @Test
    public void removeCourseTest() {
        Schedule schedule = new Schedule();
        Course course = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        schedule.addCourse(course, year,studyPeriod,slot);
        Assert.assertEquals(course, schedule.getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        schedule.removeCourse(year, studyPeriod, slot);
        Assert.assertTrue(course != schedule.getYear(year).getStudyPeriod(studyPeriod).getCourse1() || course != schedule.getYear(year).getStudyPeriod(studyPeriod).getCourse2());
    }

    @Test
    public void addCourseTest() {
        Schedule schedule = new Schedule();
        Course course = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");

        schedule.addCourse(course, year,studyPeriod,slot);

        Assert.assertEquals(course, schedule.getYear(year).getStudyPeriod(studyPeriod).getCourse1());

    }

    @Test
    public void addYearTest() {
        Schedule schedule = new Schedule();

        schedule.addYear();
        Assert.assertTrue(schedule.getYear(1) != null);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        Schedule schedule = new Schedule();

        schedule.addYear();
        Assert.assertTrue(schedule.getYear(2) != null);

        schedule.removeYear(year);
        Assert.assertTrue(schedule.getYear(2) == null);


    }



}


