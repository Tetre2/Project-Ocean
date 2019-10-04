package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.StudyPlan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class CourseSaverLoaderTest {

    private CourseSaverLoader courseSaverLoader;
    private Map<UUID, Course> courses;

    @Before
    public void init(){
        courseSaverLoader = new CourseSaverLoader();
        courses = new HashMap<>();

        for (Course course : courseSaverLoader.generatePreDefinedCourses()) {
            courses.put(course.getId(), course);
        }

    }


    @Test
    public void loadStudyPlansTest(){
        try {
            courseSaverLoader.loadStudyPlans();
        } catch (IOException e) {
            e.printStackTrace();
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
            Assert.assertTrue(courses.equals(courseSaverLoader.loadStudyPlans()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
