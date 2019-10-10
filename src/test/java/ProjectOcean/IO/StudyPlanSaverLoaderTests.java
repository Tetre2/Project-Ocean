package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.Student;
import ProjectOcean.Model.StudyPlan;
import ProjectOcean.Model.Workspace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudyPlanSaverLoaderTests {

    private StudyPlanSaverLoader saverLoader = new StudyPlanSaverLoader();
    private CoursesSaverLoader courseSaverLoader = new CoursesSaverLoader();
    private List<StudyPlan> studyPlans;
    private Student student;

    @Before
    public void setup(){
        studyPlans = new ArrayList<>();
        List<Course> courses = courseSaverLoader.generatePreDefinedCourses();

        //---- studyPlan 1 ----
        StudyPlan studyPlan = new StudyPlan();
        Workspace workspace = new Workspace();
        studyPlan.addYear();
        studyPlan.addCourseToSchedule(courses.get(0), 0, 0, 0);
        studyPlan.addYear();
        studyPlan.addCourseToSchedule(courses.get(1), 1, 0, 0);
        workspace.addCourse(courses.get(1));

        //---- studyPlan 1 ----
        StudyPlan studyPlan2 = new StudyPlan();
        Workspace workspace2 = new Workspace();
        studyPlan2.addYear();
        studyPlan2.addCourseToSchedule(courses.get(0), 0, 0, 0);
        studyPlan2.addYear();
        studyPlan2.addCourseToSchedule(courses.get(1), 1, 0, 0);
        workspace2.addCourse(courses.get(1));

        studyPlans.add(studyPlan);
        studyPlans.add(studyPlan2);

        student = new Student();
        student.setStudyPlans(studyPlans);
        student.setWorkspace(workspace);


    }

    @Test
    public void saveStudyplansTest(){

        saverLoader.saveStudyplans(student);

    }

    @Test
    public void loadStudent(){
        student = saverLoader.tryToLoadStudentFileIfNotCreateNewFile();
        Assert.assertTrue(student.equals(this.student));
    }

}
