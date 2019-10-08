package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.Student;
import ProjectOcean.Model.StudyPlan;
import ProjectOcean.Model.Workspace;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StudyPlanSaverLoaderTests {

    private StudyPlanSaverLoader saverLoader = new StudyPlanSaverLoader();
    private CourseSaverLoader courseSaverLoader = new CourseSaverLoader();
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
        /*studyPlan.addYear();
        studyPlan.addCourseToSchedule(courses.get(1), 1, 0, 0);
        workspace.addCourse(courses.get(1));*/

        //---- studyPlan 1 ----
        StudyPlan studyPlan2 = new StudyPlan();
        Workspace workspace2 = new Workspace();
        studyPlan2.addYear();
        studyPlan2.addCourseToSchedule(courses.get(0), 0, 0, 0);
        /*studyPlan2.addYear();
        studyPlan2.addCourseToSchedule(courses.get(1), 1, 0, 0);
        workspace2.addCourse(courses.get(1));*/

        studyPlans.add(studyPlan);
        //studyPlans.add(studyPlan2);

        student = new Student(studyPlans, workspace);


    }

    @Test
    public void saveStudyplansTest(){

        saverLoader.saveStudyplans(student);

    }

    @Test
    public void loadStudent(){

        Student student = null;
        try {
            student = saverLoader.loadStudent();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }

        Assert.assertTrue(student.equals(this.student));

    }





}
