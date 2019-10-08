package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CoursePlanningSystemTests {


    private CoursePlanningSystem coursePlanningSystem;
    private List<ICourse> courses;

    @Before
    public void init(){
        coursePlanningSystem = new CoursePlanningSystem();
        courses = coursePlanningSystem.generateCourses();

    }

    @Test
    public void getAllCoursesTest() {

        List<ICourse> expected = courses;
        List<ICourse> actual = coursePlanningSystem.getAllCourses();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseCodeTest() {
        ICourse courseID = courses.get(0);

        String expected = courseID.getCourseCode();
        String actual = coursePlanningSystem.getCourseCode(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseNameTest() {
        ICourse courseID = courses.get(0);

        String expected = courseID.getCourseName();
        String actual = coursePlanningSystem.getCourseName(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseStudyPointsTest() {
        ICourse courseID = courses.get(0);

        String expected = courseID.getStudyPoints();
        String actual = coursePlanningSystem.getStudyPoints(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStudyPointsTest(){
        ICourse courseID = courses.get(0);

        String expected = courseID.getStudyPoints();
        String actual = coursePlanningSystem.getStudyPoints(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStudyPeriodTest(){
        ICourse courseID = courses.get(0);

        String expected = String.valueOf(courseID.getStudyPeriod());
        String actual = coursePlanningSystem.getStudyPeriod(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExaminatorTest(){
        ICourse courseID = courses.get(0);

        String expected = courseID.getExaminator();
        String actual = coursePlanningSystem.getExaminator(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExaminationMeansTest(){
        ICourse courseID = courses.get(0);

        String expected = courseID.getExaminationMeans();
        String actual = coursePlanningSystem.getExaminationMeans(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLanguageTest(){
        ICourse courseID = courses.get(0);

        String expected = courseID.getLanguage();
        String actual = coursePlanningSystem.getLanguage(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRequiredCoursesTest(){
        ICourse courseID = courses.get(0);

        List<String> expected = new ArrayList<>();

        for (String string : courseID.getRequiredCourses()) {
            expected.add(string);
        }

        List<String> actual = coursePlanningSystem.getRequiredCourses(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCoursePMLinkTest(){
        ICourse courseID = courses.get(0);

        String expected = String.valueOf(courseID.getCoursePMLink());
        String actual = coursePlanningSystem.getCoursePMLink(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseDescriptionTest(){
        ICourse courseID = courses.get(0);

        String expected = String.valueOf(courseID.getCourseDescription());
        String actual = coursePlanningSystem.getCourseDescription(courseID);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void executeSearchTest() {
        //Test searching for examinor
        String searchText = "SöderSTröm Rolf";
        List<ICourse> searchResult = coursePlanningSystem.executeSearch(searchText);
        searchResult = coursePlanningSystem.executeSearch(searchText);
        Assert.assertTrue(searchResult.size()!=0);
        Assert.assertTrue(coursePlanningSystem.getExaminator(searchResult.get(0)).toLowerCase().contains("söderström"));
        searchResult.clear();

        //tests searching for course code
        searchText = "Eda433";
        searchResult = coursePlanningSystem.executeSearch(searchText);
        Assert.assertTrue(searchResult.size() == 1);
        if(!searchResult.isEmpty()) {
            Assert.assertTrue(coursePlanningSystem.getCourseCode(searchResult.get(0)).toLowerCase().equals("eda433"));
        }
        searchResult.clear();

        //tests searching for course name
        searchText = "Maskin  matematisk";
        searchResult = coursePlanningSystem.executeSearch(searchText);
        Assert.assertFalse(searchResult.isEmpty());
        if(!searchResult.isEmpty()) {
            for(ICourse course : searchResult) {
                Assert.assertTrue(course.getCourseName().toLowerCase().contains("maskin") || course.getCourseName().toLowerCase().contains("matematisk"));
            }
        }
    }
}
