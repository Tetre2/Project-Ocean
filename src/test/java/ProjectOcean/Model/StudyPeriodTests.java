package ProjectOcean.Model;

import ProjectOcean.IO.CourseLoader;
import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.IO.SaverLoaderFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPeriodTests {

    private int slot;
    CourseLoader courseLoader = SaverLoaderFactory.createICourseSaveLoader();

    @Before
    public void before() {
        slot = 1;
    }

    @Test
    public void addCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();

        Course course = CourseFactory.CreateCourse(
                "DAT017",
                "Maskinorienterad programmering",
                "7.5",
                "1",
                "Rolf Söderström",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("Informationsteknik")));

        studyPeriod.addCourse(course, slot);

        Assert.assertTrue(studyPeriod.getCourse1() == course || studyPeriod.getCourse2() == course);

    }

    @Test
    public void removeCourseTest() {
        StudyPeriod studyPeriod = new StudyPeriod();

        Course course = CourseFactory.CreateCourse(
                "DAT017",
                "Maskinorienterad programmering",
                "7.5",
                "1",
                "Rolf Söderström",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("Informationsteknik")));

        studyPeriod.addCourse(course, slot);
        studyPeriod.removeCourse(slot);

        Assert.assertTrue(course != studyPeriod.getCourse1());

        Course course2 = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));
        studyPeriod.addCourse(course, slot);
        studyPeriod.addCourse(course2, slot);
        studyPeriod.removeCourse(slot);

        Assert.assertTrue(course2 != studyPeriod.getCourse2());
    }

    @Test
    public void equalsTest(){
        StudyPeriod studyPeriod1 = new StudyPeriod();
        StudyPeriod studyPeriod2 = new StudyPeriod();
        List<Course> courses = null;
        try {
            courses = courseLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            e.printStackTrace();
        } catch (OldFileException e) {
            e.printStackTrace();
        }

        studyPeriod1.addCourse(courses.get(0), 1);
        studyPeriod2.addCourse(courses.get(0), 1);

        Assert.assertTrue(studyPeriod1.equals(studyPeriod2));

        //----
        studyPeriod1.addCourse(courses.get(2), 2);
        Assert.assertFalse(studyPeriod1.equals(studyPeriod2));

    }

}
