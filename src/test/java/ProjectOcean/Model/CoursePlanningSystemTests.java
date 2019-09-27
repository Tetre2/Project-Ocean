package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CoursePlanningSystemTests {


    private CoursePlanningSystem coursePlanningSystem;
    private Map<UUID, Course> courses;
    private List<UUID> allUUIDs;

    @Before
    public void init(){
        coursePlanningSystem = new CoursePlanningSystem();
        courses = coursePlanningSystem.getAllCourses();

        List<UUID> IDs = new ArrayList<>();
        Iterator it = courses.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            IDs.add((UUID) pair.getKey());
        }
        allUUIDs = IDs;

    }


    @Test
    public void testCoursePlanningSystem() {

    }

    @Test
    public void getCourseCodeTest() {
        UUID courseID = allUUIDs.get(0);

        String actual = courses.get(courseID).getCourseCode();
        String sCourseCode = coursePlanningSystem.getCourseCode(courseID);

        Assert.assertEquals(sCourseCode, actual);
    }

    @Test
    public void getCourseNameTest() {
        UUID courseID = allUUIDs.get(0);

        String actual = courses.get(courseID).getCourseName();
        String sCourseCode = coursePlanningSystem.getCourseName(courseID);

        Assert.assertEquals(sCourseCode, actual);
    }

    @Test
    public void getCourseStudyPointsTest() {

    }

    @Test
    public void getAllCoursesIDsTest() {

    }

    @Test
    public void getStudyPointsTest(){

    }

    @Test
    public void getStudyPeriodTest(){

    }

    @Test
    public void getExaminatorTest(){

    }

    @Test
    public void getExaminationMeansTest(){

    }

    @Test
    public void getLanguageTest(){

    }

    @Test
    public void getRequiredCoursesTest(){

    }

    @Test
    public void getCoursePMLinkTest(){

    }

    @Test
    public void getCourseDescriptionTest(){

    }



}
