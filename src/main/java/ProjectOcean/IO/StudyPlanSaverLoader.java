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
    private static final int VERSION = 1;
    private static JSONParser parser = new JSONParser();
    private static CourseLoader courseSaverLoader = new CourseLoader();
    private static List<ICourse> courses = courseSaverLoader.generatePreDefinedCourses();

    public StudyPlanSaverLoader() {
    }

    //--------------Save---------------

    /**
     * Saves the users studyplans and workspace to the userHomeDir
     * @param student contains a list of studyPlans that will being saved and
     */
    @Override
    public void saveModel(Student student) {

        JSONObject jsonStudent = new JSONObject();

        jsonStudent.put("version", VERSION);

        jsonStudent.put("studyplans", createJSONStudyplans(student));

        jsonStudent.put("workspace", createJSONWorkspace(student));

        jsonStudent.put("currentStudyPlan", createJSONCurrentStudyPlanPointerToStudyplans(student));

        writeToFile(jsonStudent);

    }

    private static JSONArray createJSONStudyplans(Student student){
        //jsonStudyplans contains all studyplans
        JSONArray jsonStudyPlans = new JSONArray();
        List<StudyPlan> studyPlans = student.getAllStudyPlans();

        for (StudyPlan studyplan : studyPlans) {
            //represents a studyplan
            JSONObject jsonStudyplan = new JSONObject();
            jsonStudyplan.put("id", studyplan.getId());
            jsonStudyplan.put("years", createJSONYearArray(studyplan));
            jsonStudyPlans.add(jsonStudyplan);
        }
        return jsonStudyPlans;
    }

    private static JSONArray createJSONWorkspace(Student student){
        //adds all courses in workspace to studyplan
        JSONArray workspace = new JSONArray();
        for (Course course : student.getAllCoursesInWorkspace()) {
            workspace.add(course.getCourseCode());
        }
        return workspace;
    }

    private static int createJSONCurrentStudyPlanPointerToStudyplans(Student student){
        return student.getAllStudyPlans().indexOf(student.getCurrentStudyPlan());
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

        String course1 = ((studyPeriod.getCourse1() == null) ? "null" : studyPeriod.getCourse1().getCourseCode());
        jsonStudyPeriod.put("Course1", course1);

        String course2 = ((studyPeriod.getCourse2() == null) ? "null" : studyPeriod.getCourse2().getCourseCode());
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



    //--------------Load---------------

    /**
     * Loads a list of studyplans from the users home dir if the file cant be find throws a exeption
     * @return returns a list of the loaded studyplanes
     * @exception throws a exeption if file cant be found
     */
    @Override
    public List<StudyPlan> loadStudyplans() throws StudyPlanNotFoundException, OldStudyplanExeption {
        if(!checkIfCorrectVertion())
            throw new OldStudyplanExeption();
        try {
            return createStudyPlansFromJSON(readFromFile());
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        }
    }

    /**
     * Loads a studyplan from the users home dir if the file cant be find throws a exeption
     * @return returns a studyplane
     * @exception throws a exeption if file cant be found
     */
    @Override
    public StudyPlan loadCurrentWorkspace(List<StudyPlan> studyPlans) throws StudyPlanNotFoundException, OldStudyplanExeption {
        if(!checkIfCorrectVertion())
            throw new OldStudyplanExeption();
        try {
            return createCurrentStudyPlanFromJSON(readFromFile(), studyPlans);
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        }
    }

    /**
     * Loads a workspace from the users home dir if the file cant be find throws a exeption
     * @return returns a workspace
     * @exception throws a exeption if file cant be found
     */
    @Override
    public Workspace loadWorkspace() throws StudyPlanNotFoundException, OldStudyplanExeption {
        if(!checkIfCorrectVertion())
            throw new OldStudyplanExeption();
        try {
            return createWorkspaceFromJSON(readFromFile());
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        }
    }

    private static StudyPlan createCurrentStudyPlanFromJSON(JSONObject jsonObject, List<StudyPlan> studyPlans){
        int studyPlanPointer = (int) jsonObject.get("currentStudyPlan");
        return studyPlans.get(studyPlanPointer);
    }

    private static Workspace createWorkspaceFromJSON(JSONObject jsonObject){
        //adds courses to workspace
        Workspace workspace = new Workspace();
        JSONArray jsonWorkspace = (JSONArray) jsonObject.get("workspace");
        for (Object object: jsonWorkspace) {
            for (ICourse c: courses) {
                if(c.getCourseCode().equals((String) object))
                    workspace.addCourse(c);
            }
        }
        return workspace;
    }

    private static List<StudyPlan> createStudyPlansFromJSON(JSONObject jsonObject){

        JSONArray jsonStudyplans = (JSONArray) jsonObject.get("studyplans");
        List<StudyPlan> studyPlans = new ArrayList<>();

        for (int studyplanIndex = 1; studyplanIndex <= jsonStudyplans.size(); studyplanIndex++) {

            JSONObject jsonStudyplan = (JSONObject) jsonStudyplans.get(studyplanIndex-1);
            JSONArray jsonYearArr = (JSONArray) jsonStudyplan.get("years");

            StudyPlan studyPlan = new StudyPlan((int)jsonStudyplan.get("id"));

            addJSONYearsToStudyPlan(studyPlan, jsonYearArr);

            studyPlans.add(studyPlan);
        }
        return studyPlans;
    }

    private static void addJSONYearsToStudyPlan(StudyPlan studyPlan, JSONArray jsonYearArr){
        for (int year = 1; year <= jsonYearArr.size(); year++) {
            JSONArray jsonStudyPeriod = (JSONArray) jsonYearArr.get(year-1);
            addJSONStudyPeriodToYearInStudyPlan(studyPlan, jsonStudyPeriod, year);
        }
    }

    private static void addJSONStudyPeriodToYearInStudyPlan(StudyPlan studyPlan, JSONArray jsonStudyPeriod, int year){

        for (int studyPeriod = 1; studyPeriod <= jsonStudyPeriod.size(); studyPeriod++) {

            JSONObject jsonObjStudyPeriod = (JSONObject) jsonStudyPeriod.get(studyPeriod-1);

            addJSONCourseToStudyPeriodInStudyPlan(studyPlan, jsonObjStudyPeriod, year, studyPeriod);

        }

    }

    private static void addJSONCourseToStudyPeriodInStudyPlan(StudyPlan studyPlan, JSONObject jsonObjStudyPeriod, int year, int studyPeriod){
        String course1 = (String) jsonObjStudyPeriod.get("Course1");
        if( !course1.equals("null")) {
            for (ICourse c: courses) {
                if(c.getCourseCode().equals(course1)) {
                    studyPlan.addCourseToSchedule(c, year, studyPeriod, 1);
                    break;
                }
            }
        }

        String course2 = (String) jsonObjStudyPeriod.get("Course2");
        if( !course2.equals("null")) {
            for (ICourse c: courses) {
                if(c.getCourseCode().equals(course2)){
                    studyPlan.addCourseToSchedule(c, year, studyPeriod, 2);
                    break;
                }
            }
        }
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

    private static boolean checkIfCorrectVertion() throws StudyPlanNotFoundException{
        try {
            JSONObject jsonObject = readFromFile();
            int version = (int) jsonObject.get("version");
            return version == VERSION;
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        }
    }

    //--------------Other---------------

    /**
     * Creates a empty student file and creates a new file if it does not exist
     */
    @Override
    public void createNewStudentFile(){
        File directory = new File(getHomeDirPath());
        File file = new File(directory, getFileName());
        file = new File(file.getParentFile().getAbsolutePath());
        if (!file.exists()) file.mkdirs();
        Student student = new Student();
        saveModel(student);
    }

    /**
     * @return returns the path to the users home dir
     */
    static String getHomeDirPath() {
        return System.getProperty("user.home") + File.separatorChar + ".CoursePlanningSystem";
    }

    /**
     * @return returns the json file name
     */
    static String getFileName() {
        return fileName;
    }

}
