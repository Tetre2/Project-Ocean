package ProjectOcean.IO;

import ProjectOcean.Model.*;
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
     * Saves the users studyplans and workspace to the userHomeDir
     * @param student contains a list of studyPlans that will being saved and
     */
    public static void saveStudyplans(Student student) {

        JSONArray jsonStudyPlans = new JSONArray();
        List<StudyPlan> studyPlans = student.getAllStudyPlans();

        for (StudyPlan studyplan : studyPlans) {

            //represents a studyplan
            JSONObject jsonStudyplan = new JSONObject();

            //adds all years to jsonStudyplan
            JSONArray jsonYears = new JSONArray();
            for (Year year : studyplan.getSchedule().getYears()) {

                //adds all studyperiods in a year to jsonYears
                JSONArray jsonStudyperiods = new JSONArray();
                for (StudyPeriod studyPeriod : year.getStudyPeriods()) {

                    //adds Course1 and Course2 to a studyperiod
                    JSONObject jsonStudyPeriod = new JSONObject();
                    String course1 = ((studyPeriod.getCourse1() == null) ? "null" : studyPeriod.getCourse1().getId().toString());
                    jsonStudyPeriod.put("Course1", course1);

                    String course2 = ((studyPeriod.getCourse2() == null) ? "null" : studyPeriod.getCourse1().getId().toString());
                    jsonStudyPeriod.put("Course2", course2);

                    jsonStudyperiods.add(jsonStudyPeriod);
                }

                jsonYears.add(jsonStudyperiods);
            }
            jsonStudyplan.put("years", jsonYears);

            //adds all courses in workspace to studyplan
            JSONArray workspace = new JSONArray();

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
            e.printStackTrace();
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
