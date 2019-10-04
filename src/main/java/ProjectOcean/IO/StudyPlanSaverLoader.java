package ProjectOcean.IO;

import ProjectOcean.Model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StudyPlanSaverLoader {

    private static String fileName = "studyplans.json";
    private static JSONParser parser = new JSONParser();
    private static CourseSaverLoader courseSaverLoader = new CourseSaverLoader();
    private static List<Course> courses = courseSaverLoader.generatePreDefinedCourses();

    /**
     * Saves the users studyplans and workspace to the userHomeDir
     * @param student contains a list of studyPlans that will being saved and
     */
    public static void saveStudyplans(Student student) {


        JSONObject jsonStudent = new JSONObject();

        //jsonStudyplans contains all studyplans
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


            jsonStudyPlans.add(jsonStudyplan);
        }

        jsonStudent.put("studyplans", jsonStudyPlans);

        //adds all courses in workspace to studyplan
        JSONArray workspace = new JSONArray();
        for (Course course : student.getAllCoursesInWorkspace()) {
            workspace.add(course.getId().toString());
        }
        jsonStudent.put("workspace", workspace);


        writeToFile(jsonStudent);

    }

    /**
     * Creates the file and saves the jsonArray in it
     * @param jsonObject is the jsonArray being saved
     */
    private static void writeToFile(JSONObject jsonObject) {
        try (FileWriter file = new FileWriter(new File(getHomeDirPath(), fileName))) {

            file.write(jsonObject.toJSONString());
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
    public static Student loadStudent() throws IOException {

        try {
            return createStudent(readFromFile());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;

    }


    private static Student createStudent(JSONObject jsonObject){

        //adds courses to workspace
        Workspace workspace = new Workspace();
        JSONArray jsonWorkspace = (JSONArray) jsonObject.get("workspace");
        for (Object object: jsonWorkspace) {
            for (Course c: courses) {
                if(c.getId().toString().equals((String) object))
                    workspace.addCourse(c);
            }
        }

        //adds studyplans
        JSONArray jsonStudyplans = (JSONArray) jsonObject.get("studyplans");

        List<StudyPlan> studyPlans = new ArrayList<>();

        for (int year = 0; year < jsonStudyplans.size(); year++) {

            StudyPlan studyPlan = new StudyPlan();

            //If Schedule always starts with one year we dont need to add a year the first loop
            studyPlan.addYear();

            JSONObject jsonYears = (JSONObject) jsonStudyplans.get(year);

            JSONArray jsonYearArr = (JSONArray) jsonYears.get("years");

            for (int studyPeriod = 0; studyPeriod < jsonYearArr.size(); studyPeriod++) {

                JSONArray jsonStudyPeriod = (JSONArray) jsonYearArr.get(studyPeriod);

                for (int i = 0; i < jsonStudyPeriod.size(); i++) {

                    JSONObject jsonStudyperiod = (JSONObject) jsonStudyPeriod.get(i);

                    String course1 = (String) jsonStudyperiod.get("Course1");
                    if( !course1.equals("null")) {
                        for (Course c: courses) {
                            if(c.getId().toString().equals(course1))
                                studyPlan.addCourseToSchedule(c, year, studyPeriod, 0);
                        }
                    }

                    String course2 = (String) jsonStudyperiod.get("Course2");
                    if( !course1.equals("null")) {
                        for (Course c: courses) {
                            if(c.getId().toString().equals(course2))
                                studyPlan.addCourseToSchedule(c, year, studyPeriod, 1);
                        }
                    }

                }

            }

            studyPlans.add(studyPlan);
        }

      return new Student(studyPlans, workspace);
    }

    /**
     * Reads the file in the users home dir and creates a list from that
     * @return returns a list of studyplans
     * @throws IOException
     * @throws ParseException
     */
    private static JSONObject readFromFile() throws IOException, ParseException {
        File file = new File(getHomeDirPath(), getFileName());

        FileReader fileReader = new FileReader(file);
        Object obj = parser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;


        return jsonObject;
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
