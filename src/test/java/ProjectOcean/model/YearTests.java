package ProjectOcean.model;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.Year;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class YearTests {

    int studyPeriod;
    int slot;

    @Before
    public void before() {
        studyPeriod = 0;
        slot = 0;
    }

    @Test
    public void addCourseTest() {
        Year year = new Year();
        Course course = new Course("EDA433", "Grundläggande Datorteknik", 7.5F, 0, "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

    }

    @Test
    public void removeCourseTest() {
        Year year = new Year();
        Course course = new Course("EDA433", "Grundläggande Datorteknik", 7.5F, 0, "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        year.addCourse(course, studyPeriod, slot);

        Assert.assertEquals(course, year.getStudyPeriod(studyPeriod).getCourse1());

        year.removeCourse(course, studyPeriod);

        Assert.assertTrue(year.getStudyPeriod(studyPeriod).getCourse1() == null);

    }

}