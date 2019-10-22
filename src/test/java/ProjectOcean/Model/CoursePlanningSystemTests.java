package ProjectOcean.Model;

import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.IO.ICourseLoader;
import ProjectOcean.IO.SaverLoaderFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CoursePlanningSystemTests {

    private CoursePlanningSystem model;
    private List<ICourse> courses;
    private ICourseLoader courseLoader = SaverLoaderFactory.createICourseSaveLoader();
    private List<StudyPlan> studyPlans;

    private int studyPeriod;
    private int slot;

    @Before
    public void init(){
        model = CoursePlanningSystem.getInstance();
        courses = new ArrayList<>();

        List<ICourse> loadedCourses = null;
        try {
            loadedCourses = courseLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            e.printStackTrace();
        } catch (OldFileException e) {
            e.printStackTrace();
        }

        for (ICourse course : loadedCourses) {
            courses.add(course);
        }

        studyPlans = new ArrayList<>();
        StudyPlan studyPlan1 = new StudyPlan(1);
        StudyPlan studyPlan2 = new StudyPlan(2);
        StudyPlan studyPlan3 = new StudyPlan(3);
        StudyPlan studyPlan4 = new StudyPlan(4);
        studyPlans.add(studyPlan1);
        studyPlans.add(studyPlan2);
        studyPlans.add(studyPlan3);
        studyPlans.add(studyPlan4);

        model.fillModelWithCourses(courses);

        studyPeriod = 1;
        slot = 1;

    }

    @Test
    public void setStudyPlansTest(){
        Assert.assertFalse(model.getStudent().getAllStudyPlans().equals(studyPlans));
        model.setStudyPlans(studyPlans);
        Assert.assertTrue(model.getStudent().getAllStudyPlans().equals(studyPlans));
    }

    @Test
    public void setCurrentStudyPlanTest(){
        StudyPlan currentStudyplan = studyPlans.get(0);

        Assert.assertFalse(model.getStudent().getCurrentStudyPlan().equals(currentStudyplan));
        currentStudyplan = studyPlans.get(1);
        model.setCurrentStudyPlan(currentStudyplan);
        Assert.assertTrue(model.getStudent().getCurrentStudyPlan().equals(currentStudyplan));
    }

    @Test
    public void setWorkspaceTest(){

        Workspace workspace = new Workspace();

        Assert.assertFalse(model.getStudent().getAllCoursesInWorkspace().equals(workspace));
        model.setWorkspace(workspace);
        Assert.assertTrue(model.getCoursesInWorkspace().equals(workspace.getAllCourses()));

    }

    @Test
    public void getAllCoursesTest() {
        List<ICourse> expected = courses;
        List<ICourse> actual = model.getAllCourses();

        if(expected.size() == actual.size()){
            for (ICourse icourse : expected) {
                if( ! actual.contains(icourse)){
                    Assert.assertTrue(false);
                }
            }

        }

    }

    @Test
    public void addCourseTest() {
        ICourse course1 = courses.get(0);
        ICourse course2 = courses.get(1);
        model.addYear();

        Year year = model.getStudent().getCurrentStudyPlan().getYearByOrder(1);
        model.addCourse(course1, year.getID(), studyPeriod, slot);
        model.addCourse(course2, year.getID(), studyPeriod, slot + 1);

        int yearID = model.getStudent().getCurrentStudyPlan().getYears().get(0).getID();

        ICourse expected1 = model.getStudent().getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        ICourse expected2 = model.getStudent().getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse2();

        Assert.assertEquals(course1, expected1);
        Assert.assertEquals(course2, expected2);

    }

    @Test
    public void removeCourseTest() {
        ICourse course1 = courses.get(0);
        ICourse course2 = courses.get(1);
        model.addYear();

        Year year = model.getStudent().getCurrentStudyPlan().getYearByOrder(1);
        model.addCourse(course1, year.getID(), studyPeriod, slot);
        model.addCourse(course2, year.getID(), studyPeriod, slot + 1);

        int yearID = model.getStudent().getCurrentStudyPlan().getYears().get(0).getID();
        year = model.getStudent().getCurrentStudyPlan().getYear(yearID);

        ICourse expected1 = year.getStudyPeriod(studyPeriod).getCourse1();
        ICourse expected2 = year.getStudyPeriod(studyPeriod).getCourse2();

        Assert.assertEquals(course1, expected1);
        Assert.assertEquals(course2, expected2);

        model.removeCourse(model.getStudent().getCurrentStudyPlan().getYearByOrder(1).getID(), studyPeriod, slot);
        model.removeCourse(model.getStudent().getCurrentStudyPlan().getYearByOrder(1).getID(), studyPeriod, slot + 1);

        yearID = model.getStudent().getCurrentStudyPlan().getYears().get(0).getID();
        year = model.getStudent().getCurrentStudyPlan().getYear(yearID);
        course1 = year.getStudyPeriod(studyPeriod).getCourse1();
        course2 = year.getStudyPeriod(studyPeriod).getCourse2();

        Assert.assertNull(course1);
        Assert.assertNull(course2);

    }

    @Test
    public void addCourseToWorkspaceTest() {
        model.removeAllCoursesInWorkspace();
        model.addCourseToWorkspace(courses.get(0));

        Assert.assertEquals(courses.get(0), model.getCoursesInWorkspace().get(0));
    }

    @Test
    public void getCoursesInWorkspaceTest() {
        model.removeAllCoursesInWorkspace();

        Assert.assertEquals(0, model.getCoursesInWorkspace().size());

        model.addCourseToWorkspace(courses.get(0));
        Assert.assertEquals(1, model.getCoursesInWorkspace().size());

    }

    @Test
    public void removeCourseFromWorkspaceTest() {
        model.removeAllCoursesInWorkspace();
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
        List<ICourse> searchResult;
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
        for(ICourse course : searchResult) {
            boolean containsFirstWord = course.getCourseName().toLowerCase().contains("maskin");
            boolean containsSecondWord = course.getCourseName().toLowerCase().contains("matematisk");
            Assert.assertTrue(containsFirstWord|| containsSecondWord);
        }

        //tests searching for course type
        searchText = "Naturvetenskap";
        searchResult = model.executeSearch(searchText);
        Assert.assertFalse(searchResult.isEmpty());
        if(!searchResult.isEmpty()) {
            for(ICourse course : searchResult) {
                Assert.assertTrue(course.getCourseTypes().contains("Naturvetenskap"));
            }
        }
    }

    @Test
    public void getYearsTest(){
        List<IYear> iYears = model.getYears();

        List<Year> years = model.getStudent().getCurrentStudyPlan().getYears();

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

        model.removeYear(model.getStudent().getCurrentStudyPlan().getYears().get(0).getID());
        yearSizeAfter = model.getYears().size();
        Assert.assertEquals(yearSizeBefore, yearSizeAfter);
    }

}
