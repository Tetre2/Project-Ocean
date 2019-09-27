package ProjectOcean;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.Workspace;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class WorkspaceTest {

    @Test
    public void getAllCoursesTest(){
        Workspace ws = new Workspace();
        Course course = new Course("BAT123", "Beroendespecifika paradigmer", 7.5F);
        Course course2 = new Course("CAT123", "Complex system", 7.5F);
        Course course3 = new Course("DAT321", "Datavetenskap", 7.5F);
        Course course4 = new Course("FAT321", "Fysik för ingenjörer", 7.5F);

        List courses = new LinkedList<Course>();

        courses.add(course);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);

        ws.addCourse(course);
        ws.addCourse(course2);
        ws.addCourse(course3);
        ws.addCourse(course4);

        Assert.assertEquals(courses, ws.getAllCourses());

    }

    @Test
    public void addCourseTest(){
        Workspace ws = new Workspace();
        Course course = new Course("BAT123", "Beroendespecifika paradigmer", 7.5F);
        ws.addCourse(course);
        Assert.assertTrue(ws.getAllCourses().get(0) == course);

        //Checking that a course cannot be added twice.
        Course course2 = new Course("CAT123", "Complex system", 7.5F);
        Course course3 = new Course("DAT321", "Datavetenskap", 7.5F);
        Course course4 = new Course("FAT321", "Fysik för ingenjörer", 7.5F);

        ws.addCourse(course2);
        ws.addCourse(course3);
        ws.addCourse(course4);

        ws.addCourse(course);   //This is the duplicate

        List courses = new LinkedList<Course>();

        courses.add(course);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        Assert.assertEquals(courses, ws.getAllCourses());
    }

    @Test
    public void removeCourseTest(){
        Workspace ws = new Workspace();
        Course course = new Course("BAT123", "Beroendespecifika paradigmer", 7.5F);
        ws.addCourse(course);
        Assert.assertFalse(ws.getAllCourses() == null);
        ws.removeCourse(course);
        Assert.assertTrue(ws.getAllCourses().isEmpty());

        //Check that nothing happens to the list in we try to remove a Course that does not exist i the list.
        Course course2 = new Course("CAT123", "Complex system", 7.5F);
        Course course3 = new Course("DAT321", "Datavetenskap", 7.5F);
        Course course4 = new Course("FAT321", "Fysik för ingenjörer", 7.5F);
        ws.addCourse(course);
        ws.addCourse(course2);
        ws.addCourse(course3);
        ws.addCourse(course4);

        List courses = new LinkedList<Course>();

        courses.add(course);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);

        ws.removeCourse(new Course( "HAT123", "Hierarkiska strukturer", 7.5F));
        Assert.assertEquals(courses, ws.getAllCourses());
    }

    @Test
    public void removeAllCourseTest(){
        Workspace ws = new Workspace();
        Course course = new Course("BAT123", "Beroendespecifika paradigmer", 7.5F);
        Course course2 = new Course("CAT123", "Complex system", 7.5F);
        Course course3 = new Course("DAT321", "Datavetenskap", 7.5F);
        Course course4 = new Course("FAT321", "Fysik för ingenjörer", 7.5F);
        ws.addCourse(course);
        ws.addCourse(course2);
        ws.addCourse(course3);
        ws.addCourse(course4);
        Assert.assertFalse(ws.getAllCourses() == null);
        ws.removeAllCourses();
        Assert.assertTrue(ws.getAllCourses().isEmpty());
    }
}
