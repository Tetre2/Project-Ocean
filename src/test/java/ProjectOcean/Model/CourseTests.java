package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

public class CourseTests {

    @Test
    public void courseUniqueIdTest() {

        Course course1 = new Course(UUID.randomUUID(), "DAT017","Maskinorienterad programmering", "7.5", "1", "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        Course course2 = new Course(UUID.randomUUID(), "DAT017","Maskinorienterad programmering", "7.5", "1", "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        //Checks that UUID references are different
        Assert.assertFalse(course1.getId()==course2.getId());
        //Checks that UUID values are different
        Assert.assertFalse(course1.getIdToString().equals(course2.getIdToString()));
    }

    @Test
    public void toStringTest() {
        Course course1 = new Course(UUID.randomUUID(), "DAT017","Maskinorienterad programmering", "7.5", "1", "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");

        String actual = course1.toString();
        String expected = "Course{" +
                "id=" + course1.getId() +
                ", courseCode='" + course1.getCourseCode() + '\'' +
                ", courseName='" + course1.getCourseName() + '\'' +
                ", studyPoints='" + course1.getStudyPoints()+ '\'' +
                ", studyPeriod='" + course1.getStudyPeriod()+ '\'' +
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
