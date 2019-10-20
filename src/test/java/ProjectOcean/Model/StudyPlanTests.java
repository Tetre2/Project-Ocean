package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class StudyPlanTests {
    private int studyPeriod;
    private int slot;

    @Before
    public void before() {
        studyPeriod = 1;
        slot = 1;
    }

    @Test
    public void addCourseToScheduleTest() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();
        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));
        Year year = studyPlan.getSchedule().getYearByOrder(1);

        studyPlan.addCourseToSchedule(course, year.getID(), studyPeriod, slot);
        int yearID = studyPlan.getSchedule().getYears().get(0).getID();
        ICourse actual = studyPlan.getSchedule().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course, actual);
    }

    @Test
    public void removeCourseFromScheduleTest() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();
        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));
        Year year = studyPlan.getSchedule().getYearByOrder(1);

        studyPlan.addCourseToSchedule(course, year.getID(), studyPeriod, slot);

        int yearID = studyPlan.getSchedule().getYears().get(0).getID();
        ICourse actual = studyPlan.getSchedule().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course, actual);

        studyPlan.removeCourseFromSchedule(studyPlan.getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);
        course = studyPlan.getSchedule().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertNull(course);
    }

    @Test
    public void addYear() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();

        int yearID = studyPlan.getSchedule().getYears().get(0).getID();
        Year year = studyPlan.getSchedule().getYear(yearID);

        Assert.assertNotNull(year);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYear() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();

        int yearID = studyPlan.getSchedule().getYears().get(0).getID();
        Year year = studyPlan.getSchedule().getYear(yearID);
        Assert.assertNotNull(year);

        studyPlan.removeYear(yearID);
        yearID = studyPlan.getSchedule().getYears().get(0).getID();
        year = studyPlan.getSchedule().getYear(yearID);
        Assert.assertNull(year);

    }
}
