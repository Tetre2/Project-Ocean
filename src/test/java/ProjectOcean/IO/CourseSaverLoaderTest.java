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

        Course course = new Course(UUID.randomUUID(),"DAT017","Maskinorienterad programmering", "7.5", "1", "Roger Johansson", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);
        course = new Course(UUID.randomUUID(),"EDA433","Grundläggande Datorteknik", "7.5", "2", "Rolf snedspö", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);
        course = new Course(UUID.randomUUID(),"MVE045","Matematisk Analys", "7.5", "1", "Zoran Konkoli", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);
        course = new Course(UUID.randomUUID(),"TMV206","Linjär Algebra", "7.5", "3", "Lukás Malý", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);
        course = new Course(UUID.randomUUID(),"TDA552","Objektorienterad Programmering och Design", "7.5", "2", "Alex Gerdes", "Munta/Inlämningsuppgift", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
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
