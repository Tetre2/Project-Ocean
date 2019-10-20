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

    @Before
    public void before() {
        studyPeriod = 1;
        yearNumber = 1;
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        Student student = new Student();
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

        Year year = student.getCurrentStudyPlan().getSchedule().getYearByOrder(1);
        student.addCourse(course, year.getID(), studyPeriod, slot);

        int yearID = student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID();
        ICourse actual = student.getCurrentStudyPlan().getSchedule().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course, actual);
    }

    @Test
    public void removeCourseTest() {
        Student student = new Student();
        student.getCurrentStudyPlan().getSchedule().addYear();
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
        Year year = student.getCurrentStudyPlan().getSchedule().getYearByOrder(1);

        student.addCourse(course, year.getID(), studyPeriod, slot);

        int yearID = student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID();
        ICourse actual = student.getCurrentStudyPlan().getSchedule().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course, actual);

        student.removeCourse(student.getCurrentStudyPlan().getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);
        course = student.getCurrentStudyPlan().getSchedule().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertNull(course);
    }

    @Test
    public void addYearTest(){
        Student student = new Student();
        student.addYear();

        int yearID = student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID();
        Year year = student.getCurrentStudyPlan().getSchedule().getYear(yearID);

        Assert.assertNotNull(year);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        Student student = new Student();
        student.addYear();

        int yearID = student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID();
        Year year = student.getCurrentStudyPlan().getSchedule().getYear(yearID);
        Assert.assertNotNull(year);

        student.removeYear(yearID);
        yearID = student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID();
        year = student.getCurrentStudyPlan().getSchedule().getYear(yearID);
        Assert.assertNull(year);
    }

}


