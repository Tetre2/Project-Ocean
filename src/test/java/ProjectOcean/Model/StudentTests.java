package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StudentTests {

    private ArrayList<StudyPlan> studyPlans;
    private Student student;
    private int studyPeriod;
    private int year;
    private int slot;

    @Before
    public void before() {
        student = new Student();
        studyPeriod = 1;
        year = 1;
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        student.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void removeCourseTest() {
        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        student.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        student.removeCourse(year, studyPeriod, slot);
        Assert.assertEquals(null, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void addYearTest(){
        student.addYear();

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(1) != null);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        student.addYear();

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(2) != null);

        student.removeYear(year);

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(2) == null);
    }

    @Test
    public void addStudyPlan() {
        Assert.assertTrue(student.getAllStudyPlans().size() == 0);
        student.addStudyPlanAsCurrent();
        Assert.assertTrue(student.getAllStudyPlans().size() == 1);
    }

    @Test
    public void removeStudyPlan() {
        student.addStudyPlanAsCurrent();
        Assert.assertTrue(student.getAllStudyPlans().size() == 1);

        StudyPlan sp = student.getAllStudyPlans().get(0);
        student.removeStudyPlan(sp);
        Assert.assertTrue(student.getAllStudyPlans().size() == 0);
    }

}
