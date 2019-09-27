package ProjectOcean.model;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.StudyPlan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudyPlanTests {

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
    public void addCourseToScheduleTest() {
        StudyPlan studyPlan = new StudyPlan();
        Course course = new Course();

        studyPlan.addCourseToSchedule(course, year, studyPeriod, slot);

        Assert.assertEquals(course, studyPlan.getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());


    }

    @Test
    public void removeCourseFromScheduleTest() {
        StudyPlan studyPlan = new StudyPlan();
        Course course = new Course();

        studyPlan.addCourseToSchedule(course, year, studyPeriod, slot);

        Assert.assertEquals(course, studyPlan.getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        studyPlan.removeCourseFromSchedule(course, year, studyPeriod);

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

        Assert.assertTrue(studyPlan.getSchedule().getYear(1) != null);

        studyPlan.removeYear(1);
        Assert.assertTrue(studyPlan.getSchedule().getYear(1) == null);

    }



}