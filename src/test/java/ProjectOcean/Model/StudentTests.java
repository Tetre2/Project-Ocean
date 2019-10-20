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
        student.getCurrentStudyPlan().getSchedule().addYear();
        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        student.addCourse(course, student.getCurrentStudyPlan().getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void removeCourseTest() {
        Student student = new Student();
        student.getCurrentStudyPlan().getSchedule().addYear();
        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        student.addCourse(course, student.getCurrentStudyPlan().getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse1());

        student.removeCourse(student.getCurrentStudyPlan().getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);
        Assert.assertEquals(null, student.getCurrentStudyPlan().getSchedule().getYear(student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID()).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void addYearTest(){
        Student student = new Student();
        student.addYear();

        Assert.assertNotNull(student.getCurrentStudyPlan().getSchedule().getYear(student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID()));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        Student student = new Student();
        student.addYear();

        Assert.assertNotNull(student.getCurrentStudyPlan().getSchedule().getYear(student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID()));

        student.removeYear(student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID());

        Assert.assertNull(student.getCurrentStudyPlan().getSchedule().getYear(student.getCurrentStudyPlan().getSchedule().getYears().get(0).getID()));
    }

}


