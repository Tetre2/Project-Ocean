package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.StudyPlanNotFoundException;
import ProjectOcean.Model.Course;
import ProjectOcean.Model.Student;
import ProjectOcean.Model.StudyPlan;
import ProjectOcean.Model.Workspace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        saverLoader.createNewStudentFile();

        //---- studyPlan 1 ----
        StudyPlan studyPlan = new StudyPlan();
        Workspace workspace = new Workspace();
        studyPlan.addYear();
        studyPlan.addCourse(courses.get(0), studyPlan.getYearByOrder(1).getID(), 1, 1);
        studyPlan.addYear();
        studyPlan.addCourse(courses.get(1), studyPlan.getYearByOrder(2).getID(), 1, 1);
        workspace.addCourse(courses.get(1));

        //---- studyPlan 2 ----
        StudyPlan studyPlan2 = new StudyPlan();
        Workspace workspace2 = new Workspace();
        studyPlan2.addYear();
        studyPlan2.addCourse(courses.get(0), studyPlan2.getYearByOrder(1).getID(), 1, 1);
        studyPlan2.addYear();
        studyPlan2.addCourse(courses.get(1), studyPlan2.getYearByOrder(2).getID(), 1, 1);
        workspace2.addCourse(courses.get(1));

        studyPlans.add(studyPlan);
        student = new Student(studyPlans, workspace);

    }

    @Test
    public void saveStudyplansTest(){
        saverLoader.saveStudyplans(student);
    }

    @Test
    public void loadStudent(){
        try {
            student = saverLoader.loadStudent();
        } catch (StudyPlanNotFoundException e) {
            e.printStackTrace();
        }


        //tests so that two different students are not the same
        ArrayList studyPlans = new ArrayList();
        StudyPlan studyPlanTest = new StudyPlan();
        studyPlanTest.addYear();
        studyPlans.add(studyPlanTest);
        Student studentTest = new Student(studyPlans);
        studentTest.addYear();
        studentTest.addCourse(courses.get(1), studentTest.getCurrentStudyPlan().getYearByOrder(1).getID(), 1, 1);
        Assert.assertFalse(studentTest.equals(student));



    }

}
