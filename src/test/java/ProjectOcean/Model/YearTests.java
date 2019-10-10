package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class YearTests {

    int studyPeriod;
    int slot;

    @Before
    public void before() {
        studyPeriod = 1;
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        Year year = new Year();
        Course course = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

    }

    @Test
    public void removeCourseTest() {
        Year year = new Year();
        Course course = new Course(UUID.randomUUID(), "EDA433", "Grundläggande Datorteknik", "7.5", "0", "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

        year.removeCourse(studyPeriod, slot);

        Assert.assertTrue(year.getStudyPeriod(studyPeriod).getCourse1() == null);

    }
}
