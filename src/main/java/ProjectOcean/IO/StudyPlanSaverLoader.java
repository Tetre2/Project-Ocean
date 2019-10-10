package ProjectOcean.IO;

import ProjectOcean.Model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudyPlanSaverLoader implements IStudyPlanSaverLoader{

    private static String fileName = "studyplans.json";
    private static JSONParser parser = new JSONParser();
    private static CoursesSaverLoader courseSaverLoader = new CoursesSaverLoader();
    private static List<Course> courses = courseSaverLoader.generatePreDefinedCourses();

    public StudyPlanSaverLoader() {
    }

    @Override
    /**
     * Saves the users studyplans and workspace to the userHomeDir
     * @param student contains a list of studyPlans that will being saved and
     */
    public void saveStudyplans(Student student) {

        JSONObject jsonStudent = new JSONObject();

        //jsonStudyplans contains all studyplans
        JSONArray jsonStudyPlans = new JSONArray();
        List<StudyPlan> studyPlans = student.getAllStudyPlans();

        for (StudyPlan studyplan : studyPlans) {

            //represents a studyplan
            JSONObject jsonStudyplan = new JSONObject();

            jsonStudyplan.put("years", createJSONYearArray(studyplan));

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

    private static JSONArray createJSONYearArray(StudyPlan studyPlan){

        //adds all years to jsonStudyplan
        JSONArray jsonYears = new JSONArray();

        for (Year year : studyPlan.getSchedule().getYears()) {

            jsonYears.add(createJSONStudyPeriodArray(year));
        }

        return jsonYears;
    }

    private static JSONArray createJSONStudyPeriodArray(Year year){
        //adds all studyperiods in a year to jsonYears
        JSONArray jsonStudyperiods = new JSONArray();
        for (StudyPeriod studyPeriod : year.getStudyPeriods()) {

            jsonStudyperiods.add(createJSONStudyPeriodObject(studyPeriod));
        }
        return jsonStudyperiods;
    }

    private static JSONObject createJSONStudyPeriodObject(StudyPeriod studyPeriod){
        //adds Course1 and Course2 to a studyperiod
        JSONObject jsonStudyPeriod = new JSONObject();

        String course1 = ((studyPeriod.getCourse1() == null) ? "null" : studyPeriod.getCourse1().getId().toString());
        jsonStudyPeriod.put("Course1", course1);

        String course2 = ((studyPeriod.getCourse2() == null) ? "null" : studyPeriod.getCourse1().getId().toString());
        jsonStudyPeriod.put("Course2", course2);

        return jsonStudyPeriod;
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
     * Loads a Student from the users home dir if the file cant be find it creates a new one
     * @return returns a list of the loaded studyplanes + workspace
     */
    @Override
    public Student tryToLoadStudentFileIfNotCreateNewFile(){

        try {
            return loadStudent();
        } catch (StudyPlanNotFoundException e) {
            createNewStudent();
        }

        try {
            return loadStudent();
        } catch (StudyPlanNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Student loadStudent() throws StudyPlanNotFoundException {
        try {
            return createStudent(readFromFile());
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        }

    }

    private static Student createStudent(JSONObject jsonObject){
        Student student = new Student();
        student.setStudyPlans(createStudyPlansFromJSON(jsonObject));
        student.setWorkspace(createWorkspaceFromJSON(jsonObject));
      return student;
    }

    private static Workspace createWorkspaceFromJSON(JSONObject jsonObject){
        //adds courses to workspace
        Workspace workspace = new Workspace();
        JSONArray jsonWorkspace = (JSONArray) jsonObject.get("workspace");
        for (Object object: jsonWorkspace) {
            for (Course c: courses) {
                if(c.getId().toString().equals((String) object))
                    workspace.addCourse(c);
            }
        }
        return workspace;
    }

    private static List<StudyPlan> createStudyPlansFromJSON(JSONObject jsonObject){

        JSONArray jsonStudyplans = (JSONArray) jsonObject.get("studyplans");

        List<StudyPlan> studyPlans = new ArrayList<>();

        for (int studyplanIndex = 0; studyplanIndex < jsonStudyplans.size(); studyplanIndex++) {

            StudyPlan studyPlan = new StudyPlan();

            JSONObject jsonYears = (JSONObject) jsonStudyplans.get(studyplanIndex);
            JSONArray jsonYearArr = (JSONArray) jsonYears.get("years");
            addJSONYearToStudyPlan(studyPlan, jsonYearArr);

            studyPlans.add(studyPlan);
        }
        return studyPlans;
    }

    private static void addJSONYearToStudyPlan(StudyPlan studyPlan, JSONArray jsonYearArr){
        for (int year = 0; year < jsonYearArr.size(); year++) {
            JSONArray jsonStudyPeriod = (JSONArray) jsonYearArr.get(year);
            addJSONStudyPeriodToYearInStudyPlan(studyPlan, jsonStudyPeriod, year);
        }
    }

    private static void addJSONStudyPeriodToYearInStudyPlan(StudyPlan studyPlan, JSONArray jsonStudyPeriod, int year){

        //If Schedule always starts with one year we dont need to add a year the first loop
        studyPlan.addYear();

        for (int studyPeriod = 0; studyPeriod < jsonStudyPeriod.size(); studyPeriod++) {

            JSONObject jsonObjStudyPeriod = (JSONObject) jsonStudyPeriod.get(studyPeriod);

            addJSONCourseToStudyPeriodInStudyPlan(studyPlan, jsonObjStudyPeriod, year, studyPeriod);

        }

    }

    private static void addJSONCourseToStudyPeriodInStudyPlan(StudyPlan studyPlan, JSONObject jsonObjStudyPeriod, int year, int studyPeriod){

        String course1 = (String) jsonObjStudyPeriod.get("Course1");
        if( !course1.equals("null")) {
            for (Course c: courses) {
                if(c.getId().toString().equals(course1)) {
                    studyPlan.addCourseToSchedule(c, year, studyPeriod, 0);
                    break;
                }
            }
        }

        String course2 = (String) jsonObjStudyPeriod.get("Course2");
        if( !course2.equals("null")) {
            for (Course c: courses) {
                if(c.getId().toString().equals(course2)){
                    studyPlan.addCourseToSchedule(c, year, studyPeriod, 1);
                    break;
                }
            }
        }
    }

    private void createNewStudent(){
        File directory = new File(getHomeDirPath());
        File file = new File(directory, getFileName());
        file = new File(file.getParentFile().getAbsolutePath());
        if (!file.exists()) file.mkdirs();
        Student student = new Student();
        saveStudyplans(student);
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
    static String getHomeDirPath() {
        return System.getProperty("user.home") + File.separatorChar + ".CoursePlanningSystem";
    }

    private static String getHomeDir() {
        return System.getProperty("user.home") + File.separatorChar;
    }

    /**
     * @return returns the json file name
     */
    static String getFileName() {
        return fileName;
    }
    
}
