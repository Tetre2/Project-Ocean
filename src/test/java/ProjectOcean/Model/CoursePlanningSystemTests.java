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
    public void getAllCoursesTest() {

        Map<UUID, Course> expected = courses;
        Map<UUID, Course> actual = coursePlanningSystem.getAllCourses();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseCodeTest() {
        UUID courseID = allUUIDs.get(0);

        String expected = courses.get(courseID).getCourseCode();
        String actual = coursePlanningSystem.getCourseCode(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseNameTest() {
        UUID courseID = allUUIDs.get(0);

        String expected = courses.get(courseID).getCourseName();
        String actual = coursePlanningSystem.getCourseName(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseStudyPointsTest() {

        UUID courseID = allUUIDs.get(0);

        String expected = courses.get(courseID).getStudyPoints();
        String actual = coursePlanningSystem.getStudyPoints(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllCoursesIDsTest() {

        List<UUID> expected = allUUIDs;
        List<UUID> actual = coursePlanningSystem.getAllCoursesIDs();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStudyPointsTest(){
        UUID courseID = allUUIDs.get(0);

        String expected = courses.get(courseID).getStudyPoints();
        String actual = coursePlanningSystem.getStudyPoints(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStudyPeriodTest(){

        UUID courseID = allUUIDs.get(0);

        String expected = String.valueOf(courses.get(courseID).getStudyPeriod());
        String actual = coursePlanningSystem.getStudyPeriod(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExaminatorTest(){

        UUID courseID = allUUIDs.get(0);

        String expected = courses.get(courseID).getExaminator();
        String actual = coursePlanningSystem.getExaminator(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExaminationMeansTest(){

        UUID courseID = allUUIDs.get(0);

        String expected = courses.get(courseID).getExaminationMeans();
        String actual = coursePlanningSystem.getExaminationMeans(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLanguageTest(){

        UUID courseID = allUUIDs.get(0);

        String expected = courses.get(courseID).getLanguage();
        String actual = coursePlanningSystem.getLanguage(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRequiredCoursesTest(){

        UUID courseID = allUUIDs.get(0);
        List<UUID> expected = new ArrayList<>();
        Iterator iterator = courses.get(courseID).getRequiredCourses().iterator();

        while (iterator.hasNext()){
            expected.add(((Course)iterator.next()).getId());
        }


        List<UUID> actual = coursePlanningSystem.getRequiredCourses(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCoursePMLinkTest(){

        UUID courseID = allUUIDs.get(0);

        String expected = String.valueOf(courses.get(courseID).getCoursePMLink());
        String actual = coursePlanningSystem.getCoursePMLink(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseDescriptionTest(){

        UUID courseID = allUUIDs.get(0);

        String expected = String.valueOf(courses.get(courseID).getCourseDescription());
        String actual = coursePlanningSystem.getCourseDescription(courseID);

        Assert.assertEquals(expected, actual);
    }



}
