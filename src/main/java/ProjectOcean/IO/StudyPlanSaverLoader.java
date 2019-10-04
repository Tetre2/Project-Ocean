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

    /**
     * Saves the users studyplans to the userHomeDir
     * @param studyPlans is the list of studyPlans being saved
     */
    public static void saveStudyplans(List<StudyPlan> studyPlans) {

        JSONArray jsonStudyPlans = new JSONArray();

        for (StudyPlan studyplan : studyPlans) {

            JSONObject jsonStudyplan = new JSONObject();

            jsonStudyplan.put("test", "");

            jsonStudyPlans.add(jsonStudyplan);
        }

        writeToFile(jsonStudyPlans);

    }

    /**
     * Creates the file and saves the jsonArray in it
     * @param jsonArray is the jsonArray being saved
     */
    private static void writeToFile(JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(new File(getHomeDirPath(), fileName))) {

            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a List of studyplans from the users home dir
     * @return returns a list of the loaded studyplanes
     * @throws IOException
     */
    public static List<StudyPlan> loadStudyPlans() throws IOException {
        try {

            return readFromFile();

        } catch (ParseException e) {
        }

        throw new IOException();
    }

    /**
     * Reads the file in the users home dir and creates a list from that
     * @return returns a list of studyplans
     * @throws IOException
     * @throws ParseException
     */
    private static List<StudyPlan> readFromFile() throws IOException, ParseException {
        File file = new File(getHomeDirPath(), getFileName());

        FileReader fileReader = new FileReader(file);
        Object obj = parser.parse(fileReader);
        JSONArray studyPlans = (JSONArray) obj;

        return studyPlans;
    }

    /**
     * @return returns the path to the users home dir
     */
    public static String getHomeDirPath() {
        return System.getProperty("user.home") + File.separatorChar + ".CoursePlanningSystem";
    }

    /**
     * @return returns the json file name
     */
    public static String getFileName() {
        return fileName;
    }


}
