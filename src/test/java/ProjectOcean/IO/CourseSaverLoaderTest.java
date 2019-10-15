package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class CourseSaverLoaderTest {

    private CoursesSaverLoader courseSaverLoader;
    private List<Course> courses;

    @Before
    public void init(){
        courseSaverLoader = new CoursesSaverLoader();
        courses = new ArrayList<>();

        for (Course course : courseSaverLoader.generatePreDefinedCourses()) {
            courses.add(course);
        }

    }


    @Test
    public void loadStudyPlansTest(){
        try {
            courseSaverLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            Assert.assertFalse(true);
        }
    }


    @Test
    public void saveCourses(){
        courseSaverLoader.savePreMadeCourses();
    }


    @Test
    public void saveIsSameAsLoad(){

        courseSaverLoader.savePreMadeCourses();

        try {
            courses = courseSaverLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            Assert.assertFalse(true);
        }

        System.out.println(courses.toString());
        try {
            System.out.println(courseSaverLoader.loadCoursesFile().toString());
        } catch (CoursesNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Assert.assertTrue(courses.equals(courseSaverLoader.loadCoursesFile()));
        } catch (CoursesNotFoundException e) {
            Assert.assertFalse(true);
        }

    }

}
