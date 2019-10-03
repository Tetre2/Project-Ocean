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
        courses = new HashMap<>();
        courseSaverLoader = new CourseSaverLoader();
        Course course = new Course(UUID.randomUUID(),"DAT017","Maskinorienterad programmering", "7.5", "1", "Joakim hacht", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);

        course = new Course(UUID.randomUUID(), "EDA433","Grundläggande Datorteknik", "7.5", "2", "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);

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
        courseSaverLoader.saveCourses(courses);
    }


    @Test
    public void saveIsSameAsLoad(){

        courseSaverLoader.saveCourses(courses);

        try {
            Assert.assertTrue(courses.equals(courseSaverLoader.loadStudyPlans()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
