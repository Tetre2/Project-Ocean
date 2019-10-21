package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentTests {

    private Student student;
    private int studyPeriod;
    private int slot;

    @Before
    public void before() {
        student = new Student();
        studyPeriod = 1;
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        student.addYear();
        ICourse course = CourseFactory.CreateCourse(
                "BAT123",
                "Beroendespecifika paradigmer",
                "7.5",
                "3",
                "Anders Bölinge",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("")));

        Year year = student.getCurrentStudyPlan().getYearByOrder(1);
        student.addCourse(course, year.getID(), studyPeriod, slot);

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        ICourse actual = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course, actual);
    }

    @Test
    public void removeCourseTest() {
        student.getCurrentStudyPlan().addYear();
        ICourse course = CourseFactory.CreateCourse(
                "BAT123",
                "Beroendespecifika paradigmer",
                "7.5",
                "3",
                "Anders Bölinge",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("")));
        Year year = student.getCurrentStudyPlan().getYearByOrder(1);

        student.addCourse(course, year.getID(), studyPeriod, slot);

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        ICourse actual = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course, actual);

        student.removeCourse(student.getCurrentStudyPlan().getYearByOrder(1).getID(), studyPeriod, slot);
        course = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertNull(course);
    }

    @Test
    public void addYearTest(){
        student.addYear();

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        Year year = student.getCurrentStudyPlan().getYear(yearID);

        Assert.assertNotNull(year);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        student.addYear();

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        Year year = student.getCurrentStudyPlan().getYear(yearID);
        Assert.assertNotNull(year);

        student.removeYear(yearID);
        yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        year = student.getCurrentStudyPlan().getYear(yearID);
        Assert.assertNull(year);
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

        Integer spID = student.getAllStudyPlans().get(0).getID();
        student.removeStudyPlan(spID);
        Assert.assertTrue(student.getAllStudyPlans().size() == 0);
    }

}
