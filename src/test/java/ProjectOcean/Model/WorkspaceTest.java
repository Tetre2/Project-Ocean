package ProjectOcean.Model;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.CourseFactory;
import ProjectOcean.Model.ICourse;
import ProjectOcean.Model.Workspace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class WorkspaceTest {

    private Workspace ws = new Workspace();
    private List courses = new LinkedList<Course>();

    @Before
    public void createCourses() {

        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("")));

        ICourse course2 = CourseFactory.CreateCourse("CAT123","Complex system", "7.5", "2","Anders Frölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        ICourse course3 = CourseFactory.CreateCourse("DAT321","Datavetenskap", "7.5", "4","Anders Bröninge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        ICourse course4 = CourseFactory.CreateCourse("FAT321","Fysik för ingenjörer", "7.5", "1","Anders Brölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Naturvetenskap")));

        ws.addCourse(course);
        ws.addCourse(course2);
        ws.addCourse(course3);
        ws.addCourse(course4);

        courses.add(course);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
    }

    @Test
    public void getAllCoursesTest(){

        Assert.assertEquals(courses, ws.getAllCourses());

    }

    @Test
    public void addCourseTest(){
        ws.removeAllCourses();

        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("")));

        ws.addCourse(course);
        Assert.assertTrue(ws.getAllCourses().get(0) == course);

        //Checking that a course cannot be added twice.
        ICourse course2 = CourseFactory.CreateCourse("CAT123","Complex system", "7.5", "2","Anders Frölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        ICourse course3 = CourseFactory.CreateCourse("DAT321","Datavetenskap", "7.5", "4","Anders Bröninge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        ICourse course4 = CourseFactory.CreateCourse("FAT321","Fysik för ingenjörer", "7.5", "1","Anders Brölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Naturvetenskap")));

        ws.addCourse(course2);
        ws.addCourse(course3);
        ws.addCourse(course4);

        ws.addCourse(course);   //This is the duplicate

        List coursesTemp = new LinkedList<Course>();

        coursesTemp.add(course);
        coursesTemp.add(course2);
        coursesTemp.add(course3);
        coursesTemp.add(course4);
        Assert.assertEquals(coursesTemp, ws.getAllCourses());
    }

    @Test
    public void removeCourseTest(){
        ws.removeAllCourses();

        ICourse course = CourseFactory.CreateCourse("BAT123","Beroendespecifika paradigmer", "7.5", "3","Anders Bölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("")));

        ws.addCourse(course);
        Assert.assertFalse(ws.getAllCourses() == null);
        ws.removeCourse(course);
        Assert.assertTrue(ws.getAllCourses().isEmpty());

        //Check that nothing happens to the list in we try to remove a Course that does not exist i the list.
        ICourse course2 = CourseFactory.CreateCourse("CAT123","Complex system", "7.5", "2","Anders Frölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        ICourse course3 = CourseFactory.CreateCourse("DAT321","Datavetenskap", "7.5", "4","Anders Bröninge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        ICourse course4 = CourseFactory.CreateCourse("FAT321","Fysik för ingenjörer", "7.5", "1","Anders Brölinge", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Naturvetenskap")));

        ws.addCourse(course);
        ws.addCourse(course2);
        ws.addCourse(course3);
        ws.addCourse(course4);

        List coursesTemp = new LinkedList<Course>();

        coursesTemp.add(course);
        coursesTemp.add(course2);
        coursesTemp.add(course3);
        coursesTemp.add(course4);
        ICourse courseTemp = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", new ArrayList<>(Arrays.asList("Informationsteknik")));

        ws.removeCourse(courseTemp);
        Assert.assertEquals(coursesTemp, ws.getAllCourses());
    }

    @Test
    public void removeAllCourseTest(){
        Assert.assertFalse(ws.getAllCourses() == null);
        ws.removeAllCourses();
        Assert.assertTrue(ws.getAllCourses().isEmpty());
    }
}
