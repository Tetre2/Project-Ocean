package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        CourseFactory.SetStudyPeriod(1);
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", 7.5f);
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();

        studyPlan.addCourseToSchedule(course, year, studyPeriod, slot);

        Assert.assertEquals(course, studyPlan.getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());


    }

    @Test
    public void removeCourseFromScheduleTest() {
        StudyPlan studyPlan = new StudyPlan();
        CourseFactory.SetStudyPeriod(1);
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", 7.5f);
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();

        studyPlan.addCourseToSchedule(course, year, studyPeriod, slot);

        Assert.assertEquals(course, studyPlan.getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        studyPlan.removeCourseFromSchedule(course, year, studyPeriod, slot);

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
