package ProjectOcean.Model;

import ProjectOcean.IO.CourseSaverLoaderTest;
import ProjectOcean.IO.CoursesSaverLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CoursePlanningSystemTests {
    private CoursePlanningSystem coursePlanningSystem;
    private CoursesSaverLoader coursesSaverLoader;
    private Map<UUID, Course> courses;
    private List<UUID> allUUIDs;

    private int year;
    private int studyPeriod;
    private int slot;

    @Before
    public void init(){
        List<StudyPlan> studyPlans = new ArrayList<>();
        courses = new HashMap<>();

        for (Course course : coursesSaverLoader.generatePreDefinedCourses()) {
            courses.put(course.getId(), course);
        }

        StudyPlan studyPlan = new StudyPlan();
        studyPlans.add(studyPlan);

        coursePlanningSystem = CoursePlanningSystem.getInstance();

        year = 1;
        studyPeriod = 1;
        slot = 1;

        List<UUID> IDs = new ArrayList<>();
        Iterator it = courses.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            IDs.add((UUID) pair.getKey());
        }
        allUUIDs = IDs;

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

        String expected = courses.get(courseID).getExaminer();
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


        List<String> actual = coursePlanningSystem.getRequiredCourses(courseID);

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

    @Test
    public void addCourseTest() {
        Course course1 = courses.get(allUUIDs.get(0));
        Course course2 = courses.get(allUUIDs.get(1));
        coursePlanningSystem.addCourse(course1, year, studyPeriod, slot);
        coursePlanningSystem.addCourse(allUUIDs.get(1), year, studyPeriod, slot + 1);

        Assert.assertEquals(course1, coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertEquals(course2, coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse2());

    }

    @Test
    public void removeCourseTest() {
        Course course1 = courses.get(allUUIDs.get(0));
        Course course2 = courses.get(allUUIDs.get(1));
        coursePlanningSystem.addCourse(course1, year, studyPeriod, slot);
        coursePlanningSystem.addCourse(allUUIDs.get(1), year, studyPeriod, slot + 1);

        Assert.assertEquals(course1, coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertEquals(course2, coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse2());

        coursePlanningSystem.removeCourse(year, studyPeriod, slot);
        coursePlanningSystem.removeCourse(year, studyPeriod, slot + 1);

        Assert.assertNull(coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
        Assert.assertNull(coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse2());

    }

    @Test
    public void addCourseToWorkspaceTest() {
        coursePlanningSystem.addCourseToWorkspace(allUUIDs.get(0));

        Assert.assertEquals(courses.get(allUUIDs.get(0)), courses.get(coursePlanningSystem.getCoursesInWorkspaceIDs().get(0)));
    }

    @Test
    public void getCoursesInWorkspaceIDsTest() {
        Assert.assertTrue(coursePlanningSystem.getCoursesInWorkspaceIDs().size() == 0);

        coursePlanningSystem.addCourseToWorkspace(allUUIDs.get(0));

        Assert.assertTrue(coursePlanningSystem.getCoursesInWorkspaceIDs().size() == 1);

    }

    @Test
    public void removeCourseFromWorkspaceTest() {
        coursePlanningSystem.addCourseToWorkspace(allUUIDs.get(0));
        Assert.assertEquals(courses.get(allUUIDs.get(0)), courses.get(coursePlanningSystem.getCoursesInWorkspaceIDs().get(0)));

        coursePlanningSystem.removeCourseFromWorkspace(allUUIDs.get(0));
        Assert.assertEquals(0, coursePlanningSystem.getCoursesInWorkspaceIDs().size());
    }

    @Test
    public void getCourseTest() {
        Course expected = courses.get(allUUIDs.get(0));
        Course actual = coursePlanningSystem.getCourse(allUUIDs.get(0));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStudentTest() {
        Student student = coursePlanningSystem.getStudent();

        Assert.assertNotNull(student);
    }

    @Test
    public void executeSearchTest() {
        //Test searching for examinor
        String searchText = "Rolf";
        List<UUID> searchResult = coursePlanningSystem.executeSearch(searchText);
        Assert.assertTrue(searchResult.size()!=0);
        if(searchResult.isEmpty()) {
            for(UUID id : searchResult){
                Assert.assertTrue(coursePlanningSystem.getExaminator(searchResult.get(0)).toLowerCase().contains("rolf"));
            }

        }
        searchResult.clear();
        searchText = "SöderSTröm Rolf";
        searchResult = coursePlanningSystem.executeSearch(searchText);
        Assert.assertTrue(searchResult.size()!=0);
        if(!searchResult.isEmpty()) {
            for(UUID id : searchResult){
                Assert.assertTrue(coursePlanningSystem.getExaminator(searchResult.get(0)).toLowerCase().contains("söderström"));
            }
        }
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
            for(UUID id : searchResult) {
                Assert.assertTrue(coursePlanningSystem.getCourseName(id).toLowerCase().contains("maskin") || coursePlanningSystem.getCourseName(id).toLowerCase().contains("matematisk"));
            }
        }
    }
}
