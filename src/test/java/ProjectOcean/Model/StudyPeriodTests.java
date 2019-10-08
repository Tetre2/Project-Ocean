package ProjectOcean.Model;

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
        Course course1 = new Course("EDA433", "Grundläggande Datorteknik", 7.5F, 0, "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        studyPeriod.addCourse(course1, 0);
        studyPeriod.removeCourse(course1, 0);

        Assert.assertTrue(course1 != studyPeriod.getCourse1());


        Course course2 = new Course("EDA433", "Grundläggande Datorteknik", 7.5F, 0, "Brasse Brassesson", "Tenta", "Svesnka", null, "link", "Good course");
        studyPeriod.addCourse(course2, 1);
        studyPeriod.removeCourse(course2, 1);

        Assert.assertTrue(course2 != studyPeriod.getCourse2());



    }
}
