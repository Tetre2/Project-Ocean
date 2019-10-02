package ProjectOcean.IO;

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

public class StudyPlanSaverLoaderTests {

    private StudyPlanSaverLoader saverLoader = new StudyPlanSaverLoader();
    private JSONParser parser;
    private List<StudyPlan> studyPlans;

    @Before
    public void setup(){
        parser = new JSONParser();
        studyPlans = new ArrayList<>();
        studyPlans.add(new StudyPlan());
        studyPlans.add(new StudyPlan());
        studyPlans.add(new StudyPlan());
    }

    @Test
    public void saveStudyplansTest(){

        saverLoader.saveStudyplans(studyPlans);

    }

    @Test
    public void writeToFileTest(){
        saverLoader.saveStudyplans(studyPlans);

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
