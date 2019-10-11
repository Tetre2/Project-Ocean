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
    private List<Course> courses;

    @Before
    public void setup(){
        studyPlans = new ArrayList<>();
        courses = courseSaverLoader.generatePreDefinedCourses();

        //---- studyPlan 1 ----
        StudyPlan studyPlan = new StudyPlan();
        Workspace workspace = new Workspace();
        studyPlan.addYear();
        studyPlan.addCourseToSchedule(courses.get(0), 1, 1, 1);
        studyPlan.addYear();
        studyPlan.addCourseToSchedule(courses.get(1), 2, 1, 1);
        workspace.addCourse(courses.get(1));

        //---- studyPlan 2 ----
        StudyPlan studyPlan2 = new StudyPlan();
        Workspace workspace2 = new Workspace();
        studyPlan2.addYear();
        studyPlan2.addCourseToSchedule(courses.get(0), 1, 1, 1);
        studyPlan2.addYear();
        studyPlan2.addCourseToSchedule(courses.get(1), 2, 1, 1);
        workspace2.addCourse(courses.get(1));

        studyPlans.add(studyPlan);
        //studyPlans.add(studyPlan2);

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

        //tests so that two different students are not the same
        Student studentTest = new Student();
        studentTest.setWorkspace(new Workspace());
        ArrayList arr = new ArrayList();
        StudyPlan studyPlanTest = new StudyPlan();
        studyPlanTest.addYear();
        studentTest.addCourse(courses.get(1), 1, 1, 1);
        arr.add(studyPlanTest);
        studentTest.setStudyPlans(arr);
        Assert.assertFalse(studentTest.equals(student));



    }

}
