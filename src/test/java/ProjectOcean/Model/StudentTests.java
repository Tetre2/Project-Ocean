package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class StudentTests {

    int studyPeriod;
    int year;
    int slot;

    @Before
    public void before() {
        studyPeriod = 1;
        year = 1;
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        Student student = new Student();
        Course course = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        student.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, student.getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
    }

    @Test
    public void removeCourseTest() {
        Student student = new Student();
        Course course = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
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

}


