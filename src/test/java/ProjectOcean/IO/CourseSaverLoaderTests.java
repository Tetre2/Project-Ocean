package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.Model.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CourseSaverLoaderTests {

    private CourseLoader courseSaverLoader;

    @Before
    public void init(){
        courseSaverLoader = new CourseLoader();
        List<Course> courses = new ArrayList<>();

        List<Course> loadedCourses = null;
        try {
            loadedCourses = courseSaverLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            e.printStackTrace();
        } catch (OldFileException e) {
            e.printStackTrace();
        }

        for (Course course : loadedCourses) {
            courses.add(course);
        }

    }

    @Test
    public void loadStudyPlansTest(){
        try {
            courseSaverLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            Assert.assertFalse(true);
        } catch (OldFileException e) {
            Assert.assertFalse(true);
        }
    }

}
