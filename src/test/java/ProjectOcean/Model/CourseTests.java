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
    String courseTypes;
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
        courseTypes = "NA";

        course = CourseFactory.CreateCourse(courseCode, courseName, studyPoints, studyPeriod, examiner, examinationMeans, language, requiredCourses, coursePMLink, courseDescription, courseTypes);


    }

    @Test
    public void courseUniqueIdTest() {
        ICourse course1 = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");
        ICourse course2 = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");

        //Checks that ICourse references are different
        Assert.assertNotSame(course1, course2);
    }

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
    public void getCourseTypesTest() {
        String expected = courseTypes;
        String actual = course.getCourseDescription();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void toStringTest() {
        ICourse course1 = CourseFactory.CreateCourse("DAT017","Maskinorienterad programmering", "7.5", "1","Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT");

        String actual = course1.toString();
        String expected = "Course{" +
                "courseCode='" + course1.getCourseCode() + '\'' +
                ", courseName='" + course1.getCourseName() + '\'' +
                ", studyPoints='" + course1.getStudyPoints() + '\'' +
                ", studyPeriod='" + course1.getStudyPeriod() + '\'' +
                ", examiner='" + course1.getExaminer() + '\'' +
                ", examinationMeans='" + course1.getExaminationMeans() + '\'' +
                ", language='" + course1.getLanguage() + '\'' +
                ", requiredCourses=" + course1.getRequiredCourses() +
                ", coursePMLink='" + course1.getCoursePMLink() + '\'' +
                ", courseDescription='" + course1.getCourseDescription() + '\'' +
                '}';

        Assert.assertEquals(expected, actual);
    }
}
