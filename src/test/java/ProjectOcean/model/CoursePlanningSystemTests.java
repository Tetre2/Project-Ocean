package ProjectOcean.model;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.CoursePlanningSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoursePlanningSystemTests {

    int studyPeriod;
    int year;
    int slot;

    @Before
    public void before() {
        studyPeriod = 0;
        year = 0;
        slot = 0;
    }

    @Test
    public void addCourseTest() {
        CoursePlanningSystem coursePlanningSystem = new CoursePlanningSystem();
        Course course = new Course();

        coursePlanningSystem.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());

    }

    @Test
    public void removeCourseTest() {
        CoursePlanningSystem coursePlanningSystem = new CoursePlanningSystem();
        Course course = new Course();

        coursePlanningSystem.addCourse(course, year, studyPeriod, slot);

        Assert.assertEquals(course, coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());

        coursePlanningSystem.removeCourse(course, year, studyPeriod);

        Assert.assertEquals(null, coursePlanningSystem.getStudent().getCurrentStudyPlan().getSchedule().getYear(year).getStudyPeriod(studyPeriod).getCourse1());
    }

}
