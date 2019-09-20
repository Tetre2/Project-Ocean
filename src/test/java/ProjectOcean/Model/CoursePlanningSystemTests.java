package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CoursePlanningSystemTests {

    @Test
    public void CoursePlanningSystemTest() {
        CoursePlanningSystem coursePlanningSystem = new CoursePlanningSystem();
        CourseInfo courseInfo = new CourseInfo();

        List<Course> coursePlanningSystemList = coursePlanningSystem.getAllCourses();
        List<Course> courseInfoList= courseInfo.getAllCourses();

        for(int i = 0; i < courseInfoList.size(); i++) {
            Assert.assertEquals(courseInfoList.get(i).getName(), coursePlanningSystemList.get(i).getName());
            Assert.assertEquals(courseInfoList.get(i).getCourseCode(), coursePlanningSystemList.get(i).getCourseCode());
            Assert.assertEquals(courseInfoList.get(i).getStudyPoints(), coursePlanningSystemList.get(i).getStudyPoints(), 0);
            Assert.assertEquals(courseInfoList.get(i).toString(), coursePlanningSystemList.get(i).toString());


        }

    }
}
