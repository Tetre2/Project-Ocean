package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.Student;
import ProjectOcean.Model.StudyPlan;
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
        student = new Student(studyPlans);

        List<Course> courses = courseSaverLoader.generatePreDefinedCourses();

        studyPlan.addCourseToSchedule(courses.get(0), 0, 0, 0);
        studyPlan.addYear();
        studyPlan.addCourseToSchedule(courses.get(1), 1, 0, 0);

        studyPlans.add(studyPlan);

    }

    @Test
    public void saveStudyplansTest(){

        saverLoader.saveStudyplans(student);

        try {
            System.out.println(saverLoader.loadStudyPlans());
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeToFileTest();
    }

    @Test
    public void writeToFileTest(){
        saverLoader.saveStudyplans(student);

        File file = new File(saverLoader.getHomeDirPath(), saverLoader.getFileName());



        try {
            FileReader fileReader = new FileReader(file);
            Object obj = parser.parse(fileReader);
            JSONArray studyPlans = (JSONArray) obj;

            Assert.assertEquals(studyPlans.toArray(), this.studyPlans.toArray());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
