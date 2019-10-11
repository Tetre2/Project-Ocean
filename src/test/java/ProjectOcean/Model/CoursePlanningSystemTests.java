package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CoursePlanningSystemTests {
/*<<<<<<< HEAD
    private CoursePlanningSystem coursePlanningSystem;
    private Map<UUID, Course> courses;
    private List<UUID> allUUIDs;
=======*/


    private CoursePlanningSystem model;
    private List<ICourse> courses;

    private int year;
    private int studyPeriod;
    private int slot;

    @Before
    public void init(){
        model = new CoursePlanningSystem();
        courses = model.generateCourses();

        year = 1;
        studyPeriod = 1;
        slot = 1;

    }

    @Test
    public void getAllCoursesTest() {

        List<ICourse> expected = courses;
        List<ICourse> actual = model.getAllCourses();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addCourseTest() {
        ICourse course1 = courses.get(0);
        ICourse course2 = courses.get(1);
        model.addCourse(course1, year, studyPeriod, slot);
        model.addCourse(course2, year, studyPeriod, slot + 1);

        Assert.assertEquals(course1, model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertEquals(course2, model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse2());

    }

    @Test
    public void removeCourseTest() {
        ICourse course1 = courses.get(0);
        ICourse course2 = courses.get(1);
        model.addCourse(course1, year, studyPeriod, slot);
        model.addCourse(course2, year, studyPeriod, slot + 1);

        Assert.assertEquals(course1, model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertEquals(course2, model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse2());

        model.removeCourse(course1, year, studyPeriod, slot);
        model.removeCourse(course2, year, studyPeriod, slot + 1);

        Assert.assertNull(model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertNull(model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse2());

    }

    @Test
    public void addCourseToWorkspaceTest() {
        /*model.addCourseToWorkspace(allUUIDs.get(0));

        Assert.assertEquals(courses.get(allUUIDs.get(0)), courses.get(model.getCoursesInWorkspaceIDs().get(0)));
        */
    }

    @Test
    public void getCoursesInWorkspaceIDsTest() {
        /*
        Assert.assertTrue(coursePlanningSystem.getCoursesInWorkspaceIDs().size() == 0);

        coursePlanningSystem.addCourseToWorkspace(allUUIDs.get(0));

        Assert.assertTrue(coursePlanningSystem.getCoursesInWorkspaceIDs().size() == 1);
*/
    }

    @Test
    public void removeCourseFromWorkspaceTest() {
        /*
        coursePlanningSystem.addCourseToWorkspace(allUUIDs.get(0));
        Assert.assertEquals(courses.get(allUUIDs.get(0)), courses.get(coursePlanningSystem.getCoursesInWorkspaceIDs().get(0)));

        coursePlanningSystem.removeCourseFromWorkspace(allUUIDs.get(0));
        Assert.assertEquals(0, coursePlanningSystem.getCoursesInWorkspaceIDs().size());
        */
    }

    @Test
    public void getCourseTest() {
        /*
        Course expected = courses.get(allUUIDs.get(0));
        Course actual = coursePlanningSystem.getCourse(allUUIDs.get(0));

        Assert.assertEquals(expected, actual);
        */
    }

    @Test
    public void getStudentTest() {
        Student student = model.getStudent();

        Assert.assertNotNull(student);
    }

    @Test
    public void executeSearchTest() {
        //Test searching for examinor
        String searchText = "SöderSTröm Rolf";
        List<ICourse> searchResult = model.executeSearch(searchText);
        searchResult = model.executeSearch(searchText);
        Assert.assertTrue(searchResult.size()!=0);
        Assert.assertTrue(searchResult.get(0).getExaminator().toLowerCase().contains("söderström"));
        searchResult.clear();

        //tests searching for course code
        searchText = "Eda433";
        searchResult = model.executeSearch(searchText);
        Assert.assertTrue(searchResult.size() == 1);
        if(!searchResult.isEmpty()) {
            Assert.assertTrue(searchResult.get(0).getCourseCode().toLowerCase().equals("eda433"));
        }
        searchResult.clear();

        //tests searching for course name
        searchText = "Maskin  matematisk";
        searchResult = model.executeSearch(searchText);
        Assert.assertFalse(searchResult.isEmpty());
        if(!searchResult.isEmpty()) {
            for(ICourse course : searchResult) {
                Assert.assertTrue(course.getCourseName().toLowerCase().contains("maskin") || course.getCourseName().toLowerCase().contains("matematisk"));
            }
        }
    }
}
