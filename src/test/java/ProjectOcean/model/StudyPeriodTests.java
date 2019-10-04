package ProjectOcean.model;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.StudyPeriod;
import org.junit.Assert;
import org.junit.Test;

public class StudyPeriodTests {

    @Test
    public void addCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();
        Course course = new Course("EDA433", "Grundläggande Datorteknik", 7.5F, 0, "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        studyPeriod.addCourse(course, 0);

        Assert.assertTrue(studyPeriod.getCourse1() == course || studyPeriod.getCourse2() == course);

    }

    @Test
    public void removeCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();
        Course course = new Course("EDA433", "Grundläggande Datorteknik", 7.5F, 0, "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        studyPeriod.addCourse(course, 0);
        studyPeriod.removeCourse(course);

        Assert.assertTrue(course != studyPeriod.getCourse1() || course != studyPeriod.getCourse2());

    }

}