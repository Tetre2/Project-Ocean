package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
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

        List<Course> courseList = null;

        try {
            courseList = courseSaverLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < courseList.size(); i++) {
            Assert.assertTrue(courses.get(i).equals(courseList.get(i)));
        }

    }

}
