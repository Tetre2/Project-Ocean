package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CourseInfoTests {

    @Test
    public void getAllCoursesTest() {

        Course course = new Course("DAT017","Maskinorienterad programmering", 7.5f);

        CourseInfo courseInfo = new CourseInfo();
        List<Course> courses = courseInfo.getAllCourses();

        Assert.assertTrue(course.getName() == courses.get(0).getName());
        Assert.assertTrue(course.getCourseCode() == courses.get(0).getCourseCode());
        Assert.assertTrue(course.getStudyPoints() == courses.get(0).getStudyPoints());


    }

}
