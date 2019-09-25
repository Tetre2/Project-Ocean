package ProjectOcean.model;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.Schedule;
import ProjectOcean.Model.Year;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScheduleTests {

    private int studyPeriod;
    private int slot;
    private int year;

    @Before
    public void before() {
        studyPeriod = 0;
        slot = 0;
        year = 0;
    }

    @Test
    public void removeCourseTest() {
        Schedule schedule = new Schedule();
        Course course = new Course();
        schedule.tryAddCourse(year,studyPeriod,slot, course);
        Assert.assertEquals(course, schedule.getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        schedule.removeCourse(course, year, studyPeriod);
        Assert.assertTrue(course != schedule.getYear(year).getStudyPeriod(studyPeriod).getCourse1() || course != schedule.getYear(year).getStudyPeriod(studyPeriod).getCourse2());


    }

    @Test
    public void tryAddCourse() {
        Schedule schedule = new Schedule();
        Course course = new Course();

        schedule.tryAddCourse(year,studyPeriod,slot, course);

        Assert.assertEquals(course, schedule.getYear(year).getStudyPeriod(studyPeriod).getCourse1());

    }



}
