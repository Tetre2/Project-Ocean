package ProjectOcean.Model;

import ProjectOcean.IO.CoursesSaverLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CoursePlanningSystemTests {

    private CoursePlanningSystem model;
    private List<ICourse> courses;

    private int studyPeriod;
    private int slot;

    @Before
    public void init(){
        model = CoursePlanningSystem.getInstance();
        courses = new ArrayList<>();
        List<StudyPlan> studyPlans = new ArrayList<>();

        for (Course course : CoursesSaverLoader.generatePreDefinedCourses()) {
            courses.add(course);
        }

        StudyPlan studyPlan = new StudyPlan();
        studyPlans.add(studyPlan);

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
        model.addYear();

        Year year = model.getStudent().getCurrentStudyPlan().getSchedule().getYearByOrder(1);
        model.addCourse(course1, year.getID(), studyPeriod, slot);
        model.addCourse(course2, year.getID(), studyPeriod, slot + 1);

        int yearID = model.getStudent().getCurrentStudyPlan().getSchedule().getYears().get(0).getID();

        ICourse expected1 = model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        ICourse expected2 = model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearID).getStudyPeriod(studyPeriod).getCourse2();

        Assert.assertEquals(course1, expected1);
        Assert.assertEquals(course2, expected2);

    }

    @Test
    public void removeCourseTest() {
        ICourse course1 = courses.get(0);
        ICourse course2 = courses.get(1);
        model.addYear();

        Year year = model.getStudent().getCurrentStudyPlan().getSchedule().getYearByOrder(1);
        model.addCourse(course1, year.getID(), studyPeriod, slot);
        model.addCourse(course2, year.getID(), studyPeriod, slot + 1);

        int yearID = model.getStudent().getCurrentStudyPlan().getSchedule().getYears().get(0).getID();
        year = model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearID);

        ICourse expected1 = year.getStudyPeriod(studyPeriod).getCourse1();
        ICourse expected2 = year.getStudyPeriod(studyPeriod).getCourse2();

        Assert.assertEquals(course1, expected1);
        Assert.assertEquals(course2, expected2);

        model.removeCourse(model.getStudent().getCurrentStudyPlan().getSchedule().getYearByOrder(1).getID(), studyPeriod, slot);
        model.removeCourse(model.getStudent().getCurrentStudyPlan().getSchedule().getYearByOrder(1).getID(), studyPeriod, slot + 1);

        yearID = model.getStudent().getCurrentStudyPlan().getSchedule().getYears().get(0).getID();
        year = model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearID);
        course1 = year.getStudyPeriod(studyPeriod).getCourse1();
        course2 = year.getStudyPeriod(studyPeriod).getCourse2();

        Assert.assertNull(course1);
        Assert.assertNull(course2);

    }

    @Test
    public void addCourseToWorkspaceTest() {
        model.removeAllCoursesInWorkscpace();
        model.addCourseToWorkspace(courses.get(0));

        Assert.assertEquals(courses.get(0), model.getCoursesInWorkspace().get(0));
    }

    @Test
    public void getCoursesInWorkspaceTest() {
        model.removeAllCoursesInWorkscpace();
        Assert.assertEquals(0, model.getCoursesInWorkspace().size());

        model.addCourseToWorkspace(courses.get(0));
        Assert.assertEquals(1, model.getCoursesInWorkspace().size());

    }

    @Test
    public void removeCourseFromWorkspaceTest() {
        model.removeAllCoursesInWorkscpace();
        model.addCourseToWorkspace(courses.get(0));
        Assert.assertEquals(courses.get(0), model.getCoursesInWorkspace().get(0));

        model.removeCourseFromWorkspace(courses.get(0));
        Assert.assertEquals(0, model.getCoursesInWorkspace().size());

    }

    @Test
    public void getCourseTest() {

        ICourse expected = courses.get(0);
        ICourse actual = model.getCourse(courses.get(0));

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getStudentTest() {
        Student student = model.getStudent();

        Assert.assertNotNull(student);
    }

    @Test
    public void executeSearchTest() {
        //Test searching for examinor
        String searchText = "sÖDerStrÖM Rolf";
        List<ICourse> searchResult = model.executeSearch(searchText);
        searchResult = model.executeSearch(searchText);
        Assert.assertTrue(searchResult.size()!=0);
        Assert.assertTrue(searchResult.get(0).getExaminer().toLowerCase().contains("söderström"));
        searchResult.clear();

        //tests searching for course code
        searchText = "Eda433";
        searchResult = model.executeSearch(searchText);
        Assert.assertEquals(1, searchResult.size());
        if(!searchResult.isEmpty()) {
            Assert.assertEquals("eda433", searchResult.get(0).getCourseCode().toLowerCase());
        }
        searchResult.clear();

        //tests searching for course name
        searchText = "Maskin  matematisk";
        searchResult = model.executeSearch(searchText);
        Assert.assertFalse(searchResult.isEmpty());
        if(!searchResult.isEmpty()) {
            for(ICourse course : searchResult) {
                boolean containsFirstWord = course.getCourseName().toLowerCase().contains("maskin");
                boolean containsSecondWord = course.getCourseName().toLowerCase().contains("matematisk");
                Assert.assertTrue(containsFirstWord|| containsSecondWord);
            }
        }
    }

    @Test
    public void getYearsTest(){
        List<IYear> iYears = model.getYears();

        List<Year> years = model.getStudent().getCurrentStudyPlan().getSchedule().getYears();

        Assert.assertTrue(iYears.equals(years));

    }

    @Test
    public void addYearTest() {
        int yearSizeBefore = model.getYears().size();
        model.addYear();
        int yearSizeAfter = model.getYears().size();

        Assert.assertEquals(yearSizeBefore, yearSizeAfter - 1);

    }

    @Test
    public void removeYearTest() {
        int yearSizeBefore = model.getYears().size();
        model.addYear();
        int yearSizeAfter = model.getYears().size();

        Assert.assertEquals(yearSizeBefore, yearSizeAfter - 1);

        model.removeYear(model.getStudent().getCurrentStudyPlan().getSchedule().getYears().get(0).getID());
        yearSizeAfter = model.getYears().size();
        Assert.assertEquals(yearSizeBefore, yearSizeAfter);
    }
}
