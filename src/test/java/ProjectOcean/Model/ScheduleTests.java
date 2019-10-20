package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ScheduleTests {

    private int studyPeriod;
    private int slot;

    @Before
    public void before() {
        studyPeriod = 1;
        slot = 1;
    }

    @Test
    public void removeCourseTest() {
        Schedule schedule = new Schedule();
        schedule.addYear();

        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));
        schedule.addCourse(course, schedule.getYearByOrder(1).getID(),studyPeriod,slot);

        Year year = schedule.getYear(schedule.getYears().get(0).getID());
        ICourse actualCourse = year.getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course, actualCourse);

        schedule.removeCourse(schedule.getYearByOrder(1).getID(), studyPeriod, slot);
        ICourse course1 = year.getStudyPeriod(studyPeriod).getCourse1();
        ICourse course2 = year.getStudyPeriod(studyPeriod).getCourse2();
        Assert.assertTrue(course != course1 || course != course2);
    }

    @Test
    public void addCourseTest() {
        Schedule schedule = new Schedule();
        schedule.addYear();

        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));
        schedule.addCourse(course, schedule.getYearByOrder(1).getID(),studyPeriod,slot);

        Year year = schedule.getYear(schedule.getYears().get(0).getID());
        ICourse actualCourse = year.getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course, actualCourse);

    }

    @Test
    public void addYearTest() {
        Schedule schedule = new Schedule();
        schedule.addYear();

        int yearID =  schedule.getYears().get(0).getID();
        Assert.assertTrue(schedule.getYear(yearID) != null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        Schedule schedule = new Schedule();
        schedule.addYear();

        int yearID = schedule.getYears().get(0).getID();
        Assert.assertNotNull(schedule.getYear(yearID));

        schedule.removeYear(yearID);
        yearID = schedule.getYears().get(0).getID();
        Assert.assertNull(schedule.getYear(yearID + 1));

    }
}


