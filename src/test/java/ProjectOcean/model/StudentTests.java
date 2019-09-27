package ProjectOcean.model;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentTests {

    int studyPeriod;
    int year;
    int slot;

    @Before
    public void before() {
        studyPeriod = 0;
        year = 0;
        slot = 0;
    }

    @Test
    public void addCourseTest() {
        Student student = new Student();
        Course course = new Course();
        student.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void removeCourseTest() {
        Student student = new Student();
        Course course = new Course();
        student.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        student.removeCourse(course, year, studyPeriod);
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

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(1) != null);

        student.removeYear(year);

        Assert.assertTrue(student.getCurrentStudyPlan().getSchedule().getYear(1) == null);
    }

}
