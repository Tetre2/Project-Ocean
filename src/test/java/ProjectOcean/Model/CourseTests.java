package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CourseTests {

    String studyPeriod;
    String courseCode;
    String courseName;
    String studyPoints;
    List<String> requiredCourses;
    String coursePMLink;
    String courseDescription;
    String examiner;
    String examinationMeans;
    String language;
    ICourse course;

    @Before
    public void init(){
        studyPeriod = "1";
        courseCode = "DAT017";
        courseName = "Maskinorienterad programmering";
        studyPoints = "7.5";
        requiredCourses = new ArrayList<>();
        coursePMLink = "www.google.com";
        courseDescription = "Lorem ipsum";
        examiner = "Rolf Söderström";
        examinationMeans = "Tenta";
        language = "Svenska";

        CourseFactory.SetStudyPeriod(studyPeriod);
        CourseFactory.SetCourseInfo(courseCode,courseName, studyPoints);
        CourseFactory.SetCourseDetails(requiredCourses, coursePMLink, courseDescription);
        CourseFactory.SetCourseAccessibility(examiner, examinationMeans, language);
        course = CourseFactory.CreateCourse();


    }

    @Test
    public void courseUniqueIdTest() {
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        ICourse course1 = CourseFactory.CreateCourse();

        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        CourseFactory.SetCourseAccessibility("Rolf Söderström", "Tenta", "Svenska");
        ICourse course2 = CourseFactory.CreateCourse();

        //Checks that ICourse references are different
        Assert.assertNotSame(course1, course2);
    }

    //These tests come from CPS test, but where removed because the methods where removed from CPS.
    //Similar tests need to be written here though if there is time.

    @Test
    public void getCourseCodeTest() {
       // ICourse courseID = courses.get(0);

        String expected = courseCode;
        String actual = course.getCourseCode();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCourseNameTest() {
        String expected = courseName;
        String actual = course.getCourseName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getStudyPointsTest(){
        String expected = studyPoints;
        String actual = course.getStudyPoints();

        Assert.assertEquals(expected, actual);
    }

        @Test
    public void getStudyPeriodTest(){
            String expected = studyPeriod;
            String actual = course.getStudyPeriod();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExaminerTest(){
        String expected = examiner;
        String actual = course.getExaminer();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExaminationMeansTest(){
        String expected = examinationMeans;
        String actual = course.getExaminationMeans();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLanguageTest(){
        String expected = language;
        String actual = course.getLanguage();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getRequiredCoursesTest(){
        List<String> expected = requiredCourses;
        List<String> actual = course.getRequiredCourses();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCoursePMLinkTest(){
        String expected = coursePMLink;
        String actual = course.getCoursePMLink();

        Assert.assertEquals(expected, actual);
    }

        @Test
    public void getCourseDescriptionTest(){
            String expected = courseDescription;
            String actual = course.getCourseDescription();

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void toStringTest() {
        CourseFactory.SetStudyPeriod("1");
        CourseFactory.SetCourseInfo("DAT017","Maskinorienterad programmering", "7.5");
        CourseFactory.SetCourseDetails(new ArrayList<>(), "www.google.com", "Lorem Ipsum");
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
