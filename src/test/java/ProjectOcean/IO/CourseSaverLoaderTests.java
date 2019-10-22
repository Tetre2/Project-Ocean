package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.Model.ICourse;
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
        List<ICourse> courses = new ArrayList<>();

        List<ICourse> loadedCourses = null;
        try {
            loadedCourses = courseSaverLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            e.printStackTrace();
        } catch (OldFileException e) {
            e.printStackTrace();
        }

        for (ICourse course : loadedCourses) {
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
