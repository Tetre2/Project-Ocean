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
    private JSONParser parser;
    private List<StudyPlan> studyPlans;
    private Student student;

    @Before
    public void setup(){
        parser = new JSONParser();
        studyPlans = new ArrayList<>();
        StudyPlan studyPlan = new StudyPlan();
        Workspace workspace = new Workspace();

        List<Course> courses = courseSaverLoader.generatePreDefinedCourses();

        studyPlan.addCourseToSchedule(courses.get(0), 0, 0, 0);
        studyPlan.addYear();
        studyPlan.addCourseToSchedule(courses.get(1), 1, 0, 0);


        workspace.addCourse(courses.get(1));
        student = new Student(studyPlans, workspace);

        studyPlans.add(studyPlan);

    }

    @Test
    public void saveStudyplansTest(){

        saverLoader.saveStudyplans(student);

        try {
            System.out.println(saverLoader.loadStudent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeToFileTest();
    }

    @Test
    public void writeToFileTest(){
        //outdated
        saverLoader.saveStudyplans(student);

    }





}
