package ProjectOcean.Saver;

import ProjectOcean.Model.StudyPlan;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Saver {

    private static File homeDir = new File("c:/Users/.CoursePlanningSystem");

    public static void saveStudyplans(List<StudyPlan> studyPlans){

        JSONArray jsonStudyPlans = new JSONArray();

        for (StudyPlan studyplan: studyPlans) {

            JSONObject jsonStudyplan = new JSONObject();

            jsonStudyplan.put("test", "hej123");

            jsonStudyPlans.add(jsonStudyplan);
        }

        writeToFile(jsonStudyPlans);

    }

    private static void writeToFile(JSONArray jsonArray){
        try (FileWriter file = new FileWriter(new File(homeDir.getPath(), "studyplans.json"))) {

            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getHomeDir() {
        return homeDir;
    }
}
