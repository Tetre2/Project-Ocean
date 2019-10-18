package ProjectOcean.IO;

import ProjectOcean.Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StudyPlanSaverLoaderTests {

    private StudyPlanSaverLoader saverLoader = new StudyPlanSaverLoader();
    private CourseLoader courseSaverLoader = new CourseLoader();
    private List<StudyPlan> studyPlans;
    private Student student;
    private List<ICourse> courses;

    @Before
    public void setup(){
        studyPlans = new ArrayList<>();
        courses = courseSaverLoader.generatePreDefinedCourses();
        saverLoader.createNewStudentFile();

        //---- studyPlan 1 ----
        StudyPlan studyPlan = new StudyPlan(1);
        Workspace workspace = new Workspace();
        studyPlan.addCourseToSchedule(courses.get(0), 1, 1, 1);
        studyPlan.addYear();
        studyPlan.addCourseToSchedule(courses.get(1), 2, 1, 1);
        workspace.addCourse(courses.get(1));

        //---- studyPlan 2 ----
        StudyPlan studyPlan2 = new StudyPlan(2);
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

        saverLoader.saveModel(student);

    }

    @Test
    public void loadStudent(){
        /*try {
            student = saverLoader.loadStudent();
        } catch (StudyPlanNotFoundException e) {
            e.printStackTrace();
        }*/
        Assert.assertTrue(student.equals(this.student));

        //tests so that two different students are not the same
        ArrayList arr = new ArrayList();
        StudyPlan studyPlanTest = new StudyPlan(1);
        studyPlanTest.addYear();
        arr.add(studyPlanTest);
        Student studentTest = new Student();
        studentTest.setStudyPlans(arr);
        studentTest.setCurrentStudyPlan(studyPlanTest);
        studentTest.setWorkspace(new Workspace());
        studentTest.addCourse(courses.get(1), 1, 1, 1);
        Assert.assertFalse(studentTest.equals(student));



    }

}
