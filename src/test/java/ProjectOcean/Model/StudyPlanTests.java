package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        studyPlan.addCourseToSchedule(course, studyPlan.getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);

        Assert.assertEquals(course, studyPlan.getSchedule().getYear(studyPlan.getSchedule().getYearByOrder(1).getID()).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void removeCourseFromScheduleTest() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();
        ICourse course = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        studyPlan.addCourseToSchedule(course, studyPlan.getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);

        Assert.assertEquals(course, studyPlan.getSchedule().getYear(studyPlan.getSchedule().getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse1());

        studyPlan.removeCourseFromSchedule(studyPlan.getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);

        Assert.assertTrue(studyPlan.getSchedule().getYear(studyPlan.getSchedule().getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse1() == null);
    }

    @Test
    public void addYear() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();

        Assert.assertTrue(studyPlan.getSchedule().getYear(studyPlan.getSchedule().getYears().get(0).getID()) != null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYear() {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.addYear();

        Assert.assertTrue(studyPlan.getSchedule().getYear(studyPlan.getSchedule().getYears().get(0).getID()) != null);

        studyPlan.removeYear(studyPlan.getSchedule().getYears().get(0).getID());
        Assert.assertTrue(studyPlan.getSchedule().getYear(studyPlan.getSchedule().getYears().get(0).getID()) == null);

    }
}
