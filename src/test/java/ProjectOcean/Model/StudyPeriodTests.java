package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StudyPeriodTests {

    private int slot;

    @Before
    public void before() {
        slot = 1;
    }


    @Test
    public void addCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();
        CourseFactory.SetStudyPeriod(1);
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", 7.5f);
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();

        studyPeriod.addCourse(course, slot);

        Assert.assertTrue(studyPeriod.getCourse1() == course || studyPeriod.getCourse2() == course);

    }

    @Test
    public void removeCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();

        CourseFactory.SetStudyPeriod(1);
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", 7.5f);
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course1 = CourseFactory.CreateCourse();
        studyPeriod.addCourse(course1, slot);
        studyPeriod.removeCourse(course1, slot);

        Assert.assertTrue(course1 != studyPeriod.getCourse1());


        CourseFactory.SetStudyPeriod(1);
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", 7.5f);
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course2 = CourseFactory.CreateCourse();
        studyPeriod.addCourse(course1, slot);
        studyPeriod.addCourse(course2, slot);
        studyPeriod.removeCourse(course2, slot);

        Assert.assertTrue(course2 != studyPeriod.getCourse2());
    }
}
