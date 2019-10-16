package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

public class CourseTests {

    @Test
    public void courseUniqueIdTest() {
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        ICourse course1 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        ICourse course2 = CourseFactory.CreateCourse();

        //Checks that ICourse references are different
        Assert.assertNotSame(course1, course2);
    }

    //These tests come from CPS test, but where removed because the methods where removed from CPS.
    //Similar tests need to be written here though if there is time.
    /*
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
    */

    @Test
    public void toStringTest() {
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        Course course1 = CourseFactory.CreateCourse();

        String actual = course1.toString();
        String expected = "Course{" +
                "studyPeriod='" + course1.getStudyPeriod() + '\'' +
                ", courseCode='" + course1.getCourseCode() + '\'' +
                ", courseName='" + course1.getCourseName() + '\'' +
                ", studyPoints='" + course1.getStudyPoints()+ '\'' +
                ", examiner='" + course1.getExaminer()+ '\'' +
                ", examinationMeans='" + course1.getExaminationMeans()+ '\'' +
                ", language='" + course1.getLanguage()+ '\'' +
                ", requiredCourses=" + course1.getRequiredCourses()+
                ", coursePMLink='" + course1.getCoursePMLink()+ '\'' +
                ", courseDescription='" + course1.getCourseDescription()+ '\'' +
                '}';

        Assert.assertEquals(expected, actual);
    }
}
