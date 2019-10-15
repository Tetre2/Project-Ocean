package ProjectOcean.Model;

import ProjectOcean.IO.CoursesSaverLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StudyPeriodTests {

    private int slot;

    @Before
    public void before() {
        slot = 1;
    }


    @Test
    public void addCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course = CourseFactory.CreateCourse();
        studyPeriod.addCourse(course, slot);

        Assert.assertTrue(studyPeriod.getCourse1() == course || studyPeriod.getCourse2() == course);

    }

    @Test
    public void removeCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course1 = CourseFactory.CreateCourse();
        studyPeriod.addCourse(course1, slot);
        studyPeriod.removeCourse(slot);

        Assert.assertTrue(course1 != studyPeriod.getCourse1());

        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course2 = CourseFactory.CreateCourse();
        studyPeriod.addCourse(course1, slot);
        studyPeriod.addCourse(course2, slot);
        studyPeriod.removeCourse(slot);

        Assert.assertTrue(course2 != studyPeriod.getCourse2());
    }

    @Test
    public void eqaulsTest(){
        StudyPeriod studyPeriod1 = new StudyPeriod();
        StudyPeriod studyPeriod2 = new StudyPeriod();
        List<Course> courses = CoursesSaverLoader.generatePreDefinedCourses();

        studyPeriod1.addCourse(courses.get(0), 1);
        studyPeriod2.addCourse(courses.get(0), 1);

        Assert.assertTrue(studyPeriod1.equals(studyPeriod2));

        //----
        studyPeriod1.addCourse(courses.get(2), 2);
        Assert.assertFalse(studyPeriod1.equals(studyPeriod2));

    }

}
