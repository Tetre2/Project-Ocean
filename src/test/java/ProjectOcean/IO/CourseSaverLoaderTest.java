package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CourseSaverLoaderTest {

    private CoursesSaverLoader courseSaverLoader;
    private Map<UUID, Course> courses;

    @Before
    public void init(){
        courseSaverLoader = new CoursesSaverLoader();
        courses = new HashMap<>();

        for (Course course : courseSaverLoader.generatePreDefinedCourses()) {
            courses.put(course.getId(), course);
        }

    }


    @Test
    public void loadStudyPlansTest(){
        courseSaverLoader.loadCourses();
    }


    @Test
    public void saveCourses(){
        courseSaverLoader.savePreMadeCourses();
    }


    @Test
    public void saveIsSameAsLoad(){

        courseSaverLoader.savePreMadeCourses();

        courses = courseSaverLoader.loadCourses();

        Assert.assertTrue(courses.equals(courseSaverLoader.loadCourses()));

    }

}
