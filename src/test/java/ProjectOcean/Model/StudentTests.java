package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class StudentTests {

    int studyPeriod;
    int yearNumber;
    int slot;

    Course course1;
    Course course2;

    @Before
    public void before() {
        studyPeriod = 1;
        yearNumber = 1;
        slot = 1;

        course1 = CourseFactory.CreateCourse(
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

        course2 = CourseFactory.CreateCourse(
                "Dat123",
                "Maskinprogramering",
                "7.5",
                "1",
                "Anders Brölinge",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("")));

    }

    @Test
    public void addCourseTest() {
        Student student = new Student();
        student.addYear();

        Year year = student.getCurrentStudyPlan().getYearByOrder(1);
        student.addCourse(course1, year.getID(), studyPeriod, slot);

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        ICourse actual = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course1, actual);
    }

    @Test
    public void removeCourseTest() {
        Student student = new Student();
        student.getCurrentStudyPlan().addYear();
        Year year = student.getCurrentStudyPlan().getYearByOrder(1);

        student.addCourse(course1, year.getID(), studyPeriod, slot);

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        ICourse actual = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course1, actual);

        student.removeCourse(student.getCurrentStudyPlan().getYearByOrder(1).getID(), studyPeriod, slot);
        ICourse course = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertNull(course);
    }

    @Test
    public void addYearTest(){
        Student student = new Student();
        student.addYear();

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        Year year = student.getCurrentStudyPlan().getYear(yearID);

        Assert.assertNotNull(year);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        Student student = new Student();
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
    public void equalsTest(){
        Assert.assertTrue(course1.equals(course1));
        Assert.assertFalse(course2.equals(course1));
    }

}


