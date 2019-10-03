package ProjectOcean.IO;

import ProjectOcean.Model.StudyPlan;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.List;

public class StudyPlanSaverLoader {

    private static String fileName = "studyplans.json";
    private static JSONParser parser = new JSONParser();

    public static void saveStudyplans(List<StudyPlan> studyPlans) {

        JSONArray jsonStudyPlans = new JSONArray();

        int i = 0;

        for (StudyPlan studyplan : studyPlans) {

            JSONObject jsonStudyplan = new JSONObject();

            jsonStudyplan.put("test", "hej - " + i);
            i++;

            jsonStudyPlans.add(jsonStudyplan);
        }

        writeToFile(jsonStudyPlans);

    }

    private static void writeToFile(JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(new File(getHomeDirPath(), fileName))) {

            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<StudyPlan> loadStudyPlans() throws IOException {
        try {

            return readFromFile();

        } catch (ParseException e) {
        }

        throw new IOException();
    }

    private static List<StudyPlan> readFromFile() throws IOException, ParseException {
        File file = new File(getHomeDirPath(), getFileName());

        FileReader fileReader = new FileReader(file);
        Object obj = parser.parse(fileReader);
        JSONArray studyPlans = (JSONArray) obj;

        return studyPlans;
    }

    public static String getHomeDirPath() {
        return System.getProperty("user.home") + File.separatorChar + ".CoursePlanningSystem";
    }

    public static String getFileName() {
        return fileName;
    }


}
