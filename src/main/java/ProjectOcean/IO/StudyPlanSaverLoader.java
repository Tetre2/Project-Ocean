package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.IO.Exceptions.StudyPlanNotFoundException;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import ProjectOcean.Model.Student;
import ProjectOcean.Model.StudyPlan;
import ProjectOcean.Model.Course;
import ProjectOcean.Model.Year;
import ProjectOcean.Model.StudyPeriod;
import ProjectOcean.Model.Workspace;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlanSaverLoader implements IStudyPlanSaverLoader{

    private static String fileName = "studyplans.json";
    private static final int VERSION = 1;
    private static JSONParser parser = new JSONParser();
    private static CourseLoader courseSaverLoader = new CourseLoader();
    private static List<ICourse> courses;
    //not nice but needed
    static {
        try {
            courses = courseSaverLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            e.printStackTrace();
        } catch (OldFileException e) {
            e.printStackTrace();
        }
    }

    StudyPlanSaverLoader() {
    }

    //--------------Save---------------
    /**
     * Saves the users studyPlans and workspace to the userHomeDir
     * @param model is what will being saved
     */
    @Override
    public void saveModel(CoursePlanningSystem model) {

        JSONObject jsonStudent = new JSONObject();

        jsonStudent.put("version", VERSION);

        jsonStudent.put("studyplans", createJSONStudyPlans(model.getStudent()));

        jsonStudent.put("workspace", createJSONWorkspace(model.getStudent()));

        jsonStudent.put("currentStudyPlan", createJSONCurrentStudyPlanPointerToStudyPlans(model.getStudent()));

        writeToFile(jsonStudent);

    }

    private static JSONArray createJSONStudyPlans(Student student){
        //jsonStudyPlans contains all studyPlans
        JSONArray jsonStudyPlans = new JSONArray();
        List<StudyPlan> studyPlans = student.getAllStudyPlans();

        for (StudyPlan studyplan : studyPlans) {
            //represents a studyPlan
            JSONObject jsonStudyPlan = new JSONObject();
            jsonStudyPlan.put("id", studyplan.getId());
            jsonStudyPlan.put("years", createJSONYearArray(studyplan));
            jsonStudyPlans.add(jsonStudyPlan);
        }
        return jsonStudyPlans;
    }

    private static JSONArray createJSONWorkspace(Student student){
        //adds all courses in workspace to studyPlan
        JSONArray workspace = new JSONArray();
        for (Course course : student.getAllCoursesInWorkspace()) {
            workspace.add(course.getCourseCode());
        }
        return workspace;
    }

    private static int createJSONCurrentStudyPlanPointerToStudyPlans(Student student){
        return student.getAllStudyPlans().indexOf(student.getCurrentStudyPlan());
    }

    private static JSONArray createJSONYearArray(StudyPlan studyPlan){
        //adds all years to jsonStudyPlan
        JSONArray jsonYears = new JSONArray();
        for (Year year : studyPlan.getYears()) {
            jsonYears.add(createJSONStudyPeriodArray(year));
        }
        return jsonYears;
    }

    private static JSONArray createJSONStudyPeriodArray(Year year){
        //adds all studyPeriods in a year to jsonYears
        JSONArray jsonStudyPeriods = new JSONArray();
        for (StudyPeriod studyPeriod : year.getStudyPeriods()) {
            jsonStudyPeriods.add(createJSONStudyPeriodObject(studyPeriod));
        }
        return jsonStudyPeriods;
    }

    private static JSONObject createJSONStudyPeriodObject(StudyPeriod studyPeriod){
        //adds Course1 and Course2 to a studyPeriod
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
     * Loads a list of studyPlans from the users home dir if the file cant be find throws a exception
     * @return returns a list of the loaded studyPlanes
     * @exception throws a exception if file cant be found
     */
    @Override
    public List<StudyPlan> loadStudyPlans() throws StudyPlanNotFoundException, OldFileException {
        if(!checkIfCorrectVersion())
            throw new OldFileException();
        try {
            return createStudyPlansFromJSON(readFromFile());
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        }
    }

    /**
     * Loads a studyPlan from the users home dir if the file cant be find throws a exception
     * @return returns a studyPlane
     * @exception throws a exception if file cant be found
     */
    @Override
    public StudyPlan loadCurrentStudyPlan(List<StudyPlan> studyPlans) throws StudyPlanNotFoundException, OldFileException {
        if(!checkIfCorrectVersion())
            throw new OldFileException();
        try {
            return createCurrentStudyPlanFromJSON(readFromFile(), studyPlans);
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        }
    }

    /**
     * Loads a workspace from the users home dir if the file cant be find throws a exception
     * @return returns a workspace
     * @exception throws a exception if file cant be found
     */
    @Override
    public Workspace loadWorkspace() throws StudyPlanNotFoundException, OldFileException {
        if(!checkIfCorrectVersion())
            throw new OldFileException();
        try {
            return createWorkspaceFromJSON(readFromFile());
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        }
    }

    private static StudyPlan createCurrentStudyPlanFromJSON(JSONObject jsonObject, List<StudyPlan> studyPlans){
        int studyPlanPointer = (int)(long) jsonObject.get("currentStudyPlan");

        if(studyPlanPointer == -1)
            return null;

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

        JSONArray jsonStudyPlans = (JSONArray) jsonObject.get("studyplans");
        List<StudyPlan> studyPlans = new ArrayList<>();

        for (int studyplanIndex = 1; studyplanIndex <= jsonStudyPlans.size(); studyplanIndex++) {
            JSONObject jsonStudyPlan = (JSONObject) jsonStudyPlans.get(studyplanIndex-1);
            JSONArray jsonYearArr = (JSONArray) jsonStudyPlan.get("years");

            StudyPlan studyPlan = new StudyPlan((int)(long)jsonStudyPlan.get("id"));

            addJSONYearsToStudyPlan(studyPlan, jsonYearArr);

            studyPlans.add(studyPlan);
        }
        return studyPlans;
    }

    private static void addJSONYearsToStudyPlan(StudyPlan studyPlan, JSONArray jsonYearArr){
        for (int year = 1; year <= jsonYearArr.size(); year++) {
            studyPlan.addYear();
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
                    int yearID = studyPlan.getYearByOrder(year).getID();
                    studyPlan.addCourse(c, yearID, studyPeriod, 1);
                    break;
                }
            }
        }

        String course2 = (String) jsonObjStudyPeriod.get("Course2");
        if( !course2.equals("null")) {
            for (ICourse c: courses) {
                if(c.getCourseCode().equals(course2)){
                    int yearID = studyPlan.getYearByOrder(year).getID();
                    studyPlan.addCourse(c, yearID, studyPeriod, 2);
                    break;
                }
            }
        }
    }

    /**
     * Reads the file in the users home dir and creates a list from that
     * @return returns a list of studyPlans
     * @throws IOException
     * @throws ParseException
     */
    private static JSONObject readFromFile() throws IOException, ParseException {
        File file = new File(getHomeDirPath(), getFileName());

        FileReader fileReader = new FileReader(file);
        Object obj = parser.parse(fileReader);
        return (JSONObject) obj;
    }

    //--------------Other---------------

    private static boolean checkIfCorrectVersion() throws StudyPlanNotFoundException{
        try {
            JSONObject jsonObject = readFromFile();
            int version = (int)(long) jsonObject.get("version");
            return version == VERSION;
        } catch (IOException e) {
            throw new StudyPlanNotFoundException();
        } catch (ParseException e) {
            throw new StudyPlanNotFoundException();
        }
    }

    /**
     * Creates a empty student file and creates a new file if it does not exist
     */
    @Override
    public void createNewStudentFile(){
        File directory = new File(getHomeDirPath());
        File file = new File(directory, getFileName());
        file = new File(file.getParentFile().getAbsolutePath());
        if (!file.exists())
            file.mkdirs();

        CoursePlanningSystem model = CoursePlanningSystem.getInstance();
        StudyPlan studyPlan = new StudyPlan();
        model.setStudyPlans(Arrays.asList(studyPlan));
        model.setWorkspace(new Workspace());
        model.setCurrentStudyPlan(studyPlan);

        saveModel(model);
    }

    /**
     * @return returns the path to the users home dir
     */
    private static String getHomeDirPath() {
        return System.getProperty("user.home") + File.separatorChar + ".CoursePlanningSystem";
    }

    /**
     * @return returns the json file name
     */
    private static String getFileName() {
        return fileName;
    }

}
