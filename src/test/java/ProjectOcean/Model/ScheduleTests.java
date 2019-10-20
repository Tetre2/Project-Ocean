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
        Assert.assertEquals(course, schedule.getYear(schedule.getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse1());

        schedule.removeCourse(schedule.getYearByOrder(1).getID(), studyPeriod, slot);
        Assert.assertTrue(course != schedule.getYear(schedule.getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse1() || course != schedule.getYear(schedule.getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse2());
    }

    @Test
    public void addCourseTest() {
        Schedule schedule = new Schedule();
        schedule.addYear();
        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));
        schedule.addCourse(course, schedule.getYearByOrder(1).getID(),studyPeriod,slot);

        Assert.assertEquals(course, schedule.getYear(schedule.getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse1());

    }

    @Test
    public void addYearTest() {
        Schedule schedule = new Schedule();

        schedule.addYear();

        Assert.assertTrue(schedule.getYear(schedule.getYears().get(0).getID()) != null);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        Schedule schedule = new Schedule();

        schedule.addYear();
        Assert.assertTrue(schedule.getYear(schedule.getYears().get(0).getID()) != null);

        schedule.removeYear(schedule.getYears().get(0).getID());
        Assert.assertTrue(schedule.getYear(schedule.getYears().get(0).getID() + 1) == null);

    }
}


