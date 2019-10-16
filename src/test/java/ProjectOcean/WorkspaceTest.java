package ProjectOcean;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.CourseFactory;
import ProjectOcean.Model.ICourse;
import ProjectOcean.Model.Workspace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class WorkspaceTest {

    private Workspace ws = new Workspace();
    private List courses = new LinkedList<Course>();

    @Before
    public void createCourses() {
        CourseFactory.SetStudyPeriod("3");
        CourseFactory.SetCourseInfo("BAT123", "Beroendespecifika paradigmer", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Bölinge", "Tenta","Svenska");
        ICourse course = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("2");
        CourseFactory.SetCourseInfo("CAT123", "Complex system", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum!");
        CourseFactory.SetCourseAccessibility("Anders Fölinge", "Tenta","Svenska");
        ICourse course2 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("4");
        CourseFactory.SetCourseInfo("DAT321", "Datavetenskap", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Bölinge", "Tenta","Svenska");
        ICourse course3 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("FAT321", "Fysik för ingenjörer", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Brölinge", "Tenta","Svenska");
        ICourse course4 = CourseFactory.CreateCourse();

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
        CourseFactory.SetStudyPeriod("3");
        CourseFactory.SetCourseInfo("BAT123", "Beroendespecifika paradigmer", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Bölinge", "Tenta","Svenska");
        ICourse course = CourseFactory.CreateCourse();
        ws.addCourse(course);
        Assert.assertTrue(ws.getAllCourses().get(0) == course);

        //Checking that a course cannot be added twice.
        CourseFactory.SetStudyPeriod("2");
        CourseFactory.SetCourseInfo("CAT123", "Complex system", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Fölinge", "Tenta","Svenska");
        ICourse course2 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("4");
        CourseFactory.SetCourseInfo("DAT321", "Datavetenskap", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Bölinge", "Tenta","Svenska");
        ICourse course3 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("FAT321", "Fysik för ingenjörer", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Brölinge", "Tenta","Svenska");
        ICourse course4 = CourseFactory.CreateCourse();

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

        CourseFactory.SetStudyPeriod("3");
        CourseFactory.SetCourseInfo("BAT123", "Beroendespecifika paradigmer", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Bölinge", "Tenta","Svenska");
        ICourse course = CourseFactory.CreateCourse();

        ws.addCourse(course);
        Assert.assertFalse(ws.getAllCourses() == null);
        ws.removeCourse(course);
        Assert.assertTrue(ws.getAllCourses().isEmpty());

        //Check that nothing happens to the list in we try to remove a Course that does not exist i the list.
        CourseFactory.SetStudyPeriod("2");
        CourseFactory.SetCourseInfo("CAT123", "Complex system", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Fölinge", "Tenta","Svenska");
        ICourse course2 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("4");
        CourseFactory.SetCourseInfo("DAT321", "Datavetenskap", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Bölinge", "Tenta","Svenska");
        ICourse course3 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("FAT321", "Fysik för ingenjörer", "7.5");
        CourseFactory.SetCourseDetails(null, "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility("Anders Brölinge", "Tenta","Svenska");
        ICourse course4 = CourseFactory.CreateCourse();

        ws.addCourse(course);
        ws.addCourse(course2);
        ws.addCourse(course3);
        ws.addCourse(course4);

        List coursesTemp = new LinkedList<Course>();

        coursesTemp.add(course);
        coursesTemp.add(course2);
        coursesTemp.add(course3);
        coursesTemp.add(course4);

        CourseFactory.SetStudyPeriod("2");
        CourseFactory.SetCourseInfo("HAT123", "Hierarkiska strukturer", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.chalmers.se", "Lorem ipsum");
        CourseFactory.SetCourseAccessibility( "Anders Bölinge", "Tenta","Svenska");
        ICourse courseTemp = CourseFactory.CreateCourse();

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
