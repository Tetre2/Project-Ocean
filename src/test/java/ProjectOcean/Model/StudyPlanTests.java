package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class StudyPlanTests {
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
    public void addCourseToScheduleTest() {
        StudyPlan studyPlan = new StudyPlan();

        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        studyPlan.addCourseToSchedule(course, year, studyPeriod, slot);

        Assert.assertEquals(course, studyPlan.getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void removeCourseFromScheduleTest() {
        StudyPlan studyPlan = new StudyPlan();

        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        studyPlan.addCourseToSchedule(course, year, studyPeriod, slot);

        Assert.assertEquals(course, studyPlan.getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        studyPlan.removeCourseFromSchedule(year, studyPeriod, slot);

        Assert.assertTrue(studyPlan.getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1() == null);
    }

    @Test
    public void addYear() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();

        Assert.assertTrue(studyPlan.getSchedule().getYear(1) != null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYear() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();

        Assert.assertTrue(studyPlan.getSchedule().getYear(2) != null);

        studyPlan.removeYear(1);
        Assert.assertTrue(studyPlan.getSchedule().getYear(2) == null);

    }
}
