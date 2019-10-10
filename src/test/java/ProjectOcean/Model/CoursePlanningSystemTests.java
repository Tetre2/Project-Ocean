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
            Assert.assertTrue(searchResult.get(0).getCourseCode().toLowerCase().equals("eda433"));
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
