package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        student.getCurrentStudyPlan().getSchedule().addYear(yearNumber);
        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        student.addCourse(course, yearNumber, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void removeCourseTest() {
        Student student = new Student();
        student.getCurrentStudyPlan().getSchedule().addYear(yearNumber);
        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        student.addCourse(course, yearNumber, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse1());

        student.removeCourse(yearNumber, studyPeriod, slot);
        Assert.assertEquals(null, student.getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void addYearTest(){
        Student student = new Student();
        student.addYear(yearNumber);

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(1) != null);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        Student student = new Student();
        student.addYear(yearNumber);

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(2) != null);

        student.removeYear(yearNumber);

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(2) == null);
    }

}


