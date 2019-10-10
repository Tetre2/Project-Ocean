package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class StudyPeriodTests {

    private int slot;

    @Before
    public void before() {
        slot = 1;
    }


    @Test
    public void addCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();
        Course course = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        studyPeriod.addCourse(course, slot);

        Assert.assertTrue(studyPeriod.getCourse1() == course || studyPeriod.getCourse2() == course);

    }

    @Test
    public void removeCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();
        Course course1 = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        studyPeriod.addCourse(course1, slot);
        studyPeriod.removeCourse(slot);

        Assert.assertTrue(course1 != studyPeriod.getCourse1());


        Course course2 = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        studyPeriod.addCourse(course2, slot);
        studyPeriod.removeCourse(slot);

        Assert.assertTrue(course2 != studyPeriod.getCourse2());



    }
}
