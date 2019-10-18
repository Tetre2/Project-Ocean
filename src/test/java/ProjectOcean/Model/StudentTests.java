package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StudentTests {

    ArrayList<StudyPlan> studyPlans;
    private int studyPeriod;
    private int year;
    private int slot;

    @Before
    public void before() {
        studyPeriod = 1;
        year = 1;
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        Student student = new Student();
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();
        student.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void removeCourseTest() {
        Student student = new Student();
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();
        student.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        student.removeCourse(year, studyPeriod, slot);
        Assert.assertEquals(null, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void addYearTest(){
        Student student = new Student();
        student.addYear();

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(1) != null);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        Student student = new Student();
        student.addYear();

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(2) != null);

        student.removeYear(year);

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(2) == null);
    }

    @Test
    public void addStudyPlan() {
        Student student = new Student();
        student.addStudyPlan();

        Assert.assertTrue(student.getAllStudyPlans().size() == 1);
    }

    @Test
    public void removeStudyPlan() {
        Student student = new Student();

        student.addStudyPlan();
        Assert.assertTrue(student.getAllStudyPlans().size() == 1);

        StudyPlan sp = student.getAllStudyPlans().get(0);
        student.removeStudyPlan(sp);
        Assert.assertTrue(student.getAllStudyPlans().size() == 0);
    }

}
