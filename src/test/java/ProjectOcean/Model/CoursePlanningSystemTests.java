package ProjectOcean.Model;

import ProjectOcean.IO.CoursesSaverLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CoursePlanningSystemTests {

    private CoursePlanningSystem model;
    private List<ICourse> courses;

    private int yearNumber;
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


        yearNumber = 1;
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
        model.getStudent().getCurrentStudyPlan().addYear(yearNumber);
        model.addCourse(course1, yearNumber, studyPeriod, slot);
        model.addCourse(course2, yearNumber, studyPeriod, slot + 1);

        Assert.assertEquals(course1, model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertEquals(course2, model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse2());

    }

    @Test
    public void removeCourseTest() {
        ICourse course1 = courses.get(0);
        ICourse course2 = courses.get(1);
        model.getStudent().getCurrentStudyPlan().addYear(yearNumber);
        model.addCourse(course1, yearNumber, studyPeriod, slot);
        model.addCourse(course2, yearNumber, studyPeriod, slot + 1);

        Assert.assertEquals(course1, model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertEquals(course2, model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse2());

        model.removeCourse(yearNumber, studyPeriod, slot);
        model.removeCourse(yearNumber, studyPeriod, slot + 1);

        Assert.assertNull(model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertNull(model.getStudent().getCurrentStudyPlan().getSchedule().getYear(yearNumber).getStudyPeriod(studyPeriod).getCourse2());

    }

    @Test
    public void addCourseToWorkspaceTest() {
        model.removeAllCoursesInWorkscpace();
        model.addCourseToWorkspace(courses.get(0));

        Assert.assertTrue(courses.get(0).equals(model.getCoursesInWorkspace().get(0)));
    }

    @Test
    public void getCoursesInWorkspaceTest() {
        model.removeAllCoursesInWorkscpace();

        Assert.assertTrue(model.getCoursesInWorkspace().size() == 0);

        model.addCourseToWorkspace(courses.get(0));

        Assert.assertTrue(model.getCoursesInWorkspace().size() == 1);

    }

    @Test
    public void removeCourseFromWorkspaceTest() {
        model.removeAllCoursesInWorkscpace();
        model.addCourseToWorkspace(courses.get(0));
        Assert.assertTrue(courses.get(0).equals(model.getCoursesInWorkspace().get(0)));

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
        String searchText = "SnEdSpö Rolf";
        List<ICourse> searchResult = model.executeSearch(searchText);
        searchResult = model.executeSearch(searchText);
        Assert.assertTrue(searchResult.size()!=0);
        Assert.assertTrue(searchResult.get(0).getExaminer().toLowerCase().contains("snedspö"));
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

    @Test
    public void getYearsTest(){
        List<IYear> iYears = model.getYears();

        List<Year> years = model.getStudent().getCurrentStudyPlan().getSchedule().getYears();

        Assert.assertTrue(iYears.equals(years));

    }

    @Test
    public void addYearTest() {
        int yearSizeBefore = model.getYears().size();
        model.addYear(yearNumber);
        int yearSizeAfter = model.getYears().size();

        Assert.assertEquals(yearSizeBefore, yearSizeAfter - 1);

    }

    @Test
    public void removeYearTest() {
        int yearSizeBefore = model.getYears().size();
        model.addYear(yearNumber);
        int yearSizeAfter = model.getYears().size();

        Assert.assertEquals(yearSizeBefore, yearSizeAfter - 1);

        model.removeYear(yearNumber);
        yearSizeAfter = model.getYears().size();
        Assert.assertEquals(yearSizeBefore, yearSizeAfter);
    }
}
