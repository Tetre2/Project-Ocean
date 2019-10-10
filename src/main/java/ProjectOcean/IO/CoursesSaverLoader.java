package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map;

public class CoursesSaverLoader implements ICourseSaveLoader{

    private static String fileName = "courses.json";
    private static JSONParser parser = new JSONParser();

    public CoursesSaverLoader() {
    }


    /**
     * Loaded a Map from "courses.json" in user hom dir
     *
     * @return returns a <code>Map<UUID, Course></code>
     */
    @Override
    public Map<UUID, Course> loadCourses() {
        try {
            return readFromFile();
        } catch (ParseException e) {
            createNewDefaultCourseFile();
        } catch (IOException e) {
            createNewDefaultCourseFile();
        }
        return loadCourses();
    }

    /**
     * The method tha reads the file and creates the Map
     *
     * @return returns a <code>Map<UUID, Course></code>
     * @throws IOException
     * @throws ParseException
     */
    private static Map<UUID, Course> readFromFile() throws IOException, ParseException {

        //creates a file with the path to the courses.json
        File file = new File(getHomeDirPath(), getFileName());

        //Map to return when method is done
        Map<UUID, Course> courses = new HashMap<>();

        //Creates a filereader which reads the courses.json and creates it as a jsonArray
        FileReader fileReader = new FileReader(file);
        Object parsed = parser.parse(fileReader);
        JSONArray studyPlans = (JSONArray) parsed;

        //loops through all "courses"
        for (Object object : studyPlans) {

            //casts the "course" to a jsonObject to be able to access the info
            JSONObject jsonObject = (JSONObject) object;

            //loops through all required courses inorder to add them to the course in the constructor
            JSONArray jArr = (JSONArray) jsonObject.get("requiredCourses");
            List<String> requiredCourses = new ArrayList<>();
            for (Object obj : jArr) {
                requiredCourses.add((String) obj);
            }

            //Creates a UUID from the loaded uuid string
            UUID id = UUID.fromString((String) jsonObject.get("uuid"));

            Course course = new Course(
                    id,
                    (String) jsonObject.get("courseCode"),
                    (String) jsonObject.get("courseName"),
                    (String) jsonObject.get("studyPoints"),
                    (String) jsonObject.get("studyPeriod"),
                    (String) jsonObject.get("examiner"),
                    (String) jsonObject.get("examinationMeans"),
                    (String) jsonObject.get("language"),
                    requiredCourses,
                    (String) jsonObject.get("coursePMLink"),
                    (String) jsonObject.get("courseDescription")
            );

            courses.put(id, course);
        }

        return courses;
    }

    /**
     *
     * @return returns the users home directory
     */
    static String getHomeDirPath() {
        return System.getProperty("user.home") + File.separatorChar + ".CoursePlanningSystem";
    }

    /**
     *
     * @return returns the filename which holds courses
     */
    static String getFileName() {
        return fileName;
    }

    /**
     * Creates a file filled with all default courses
     */
    private static void createNewDefaultCourseFile(){
        File file = new File(getHomeDirPath() + fileName);
        savePreMadeCourses();
    }

    /**
     * Saves a list of courses to a "courses.json" file in the user home dir.
     */
    static void savePreMadeCourses() {
        //creates the "main" array which contains all courses
        JSONArray jsonCourses = new JSONArray();

        List<Course> courses = generatePreDefinedCourses();

        for (Course course: courses) {

            //creates a json object which represents a course
            JSONObject jsonCourse = new JSONObject();

            jsonCourse.put("uuid", course.getId().toString());
            jsonCourse.put("courseCode", course.getCourseCode());
            jsonCourse.put("courseName", course.getCourseName());
            jsonCourse.put("studyPoints", course.getStudyPoints());
            jsonCourse.put("studyPeriod", course.getStudyPeriod());
            jsonCourse.put("examiner", course.getExaminer());
            jsonCourse.put("examinationMeans", course.getExaminationMeans());
            jsonCourse.put("language", course.getLanguage());
            JSONArray requiredCourses = new JSONArray();
            for (String s : course.getRequiredCourses()) {
                requiredCourses.add(s);
            }
            jsonCourse.put("requiredCourses", requiredCourses);
            jsonCourse.put("coursePMLink", course.getCoursePMLink());
            jsonCourse.put("courseDescription", course.getCourseDescription());

            jsonCourses.add(jsonCourse);

        }

        writeToFile(jsonCourses);

    }

    /**
     * Creates courses a predefined list of courses
     * @return returns a list of courses
     */
    static List<Course> generatePreDefinedCourses(){
        List<Course> courses = new ArrayList<>();

        courses.add(new Course(UUID.fromString("749d6445-4c88-410e-9c44-b88e8cb3e094"),"DAT017","Maskinorienterad programmering", "7.5", "1", "Roger Johansson", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.fromString("add797f2-1c82-45f1-9d25-cf24b46e29d8"),"EDA433","Grundläggande Datorteknik", "7.5", "2", "Rolf snedspö", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.fromString("72c83fcd-2dc0-473f-b817-29cf2db74c14"),"MVE045","Matematisk Analys", "7.5", "1", "Zoran Konkoli", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.fromString("ab1344d1-8b2f-4b67-8415-d90291c02569"),"TMV206","Linjär Algebra", "7.5", "3", "Lukás Malý", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.fromString("e74d95ea-dfb9-4aba-b2c1-28328728b50b"),"TDA552","Objektorienterad Programmering och Design", "7.5", "2", "Alex Gerdes", "Munta/Inlämningsuppgift", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.fromString("80bfb31d-82d8-4bdf-8928-f62095fe3449"),"BAT123","Beroendespecifika paradigmer", "7.5","3","Anders Bölinge", "Tenta","Turkish", new ArrayList<>(), "LINK_Zelda", "Abu Dhabi. YOLO!"));
        courses.add(new Course(UUID.fromString("f5f7d43f-d051-4afa-ab93-d3c2ad22dfd3"),"CAT123","Complex system", "7.5", "2", "Anders Fölinge", "Tenta","Turkish", new ArrayList<>(), "LINK_Zelda", "Abu Dhabi. YOLO!"));
        courses.add(new Course(UUID.fromString("42d12175-931b-4386-8757-58bc17254e07"),"DAT321","Datavetenskap", "7.5", "4", "Anders Bölinge", "Tenta","Turkish", new ArrayList<>(), "LINK_Zelda", "Abu Dhabi. YOLO!"));
        courses.add(new Course(UUID.fromString("b132bd69-f2f1-474b-9fb7-bff853c7fc28"),"FAT321","Fysik för ingenjörer", "7.5", "1", "Anders Brölinge", "Tenta","Turkish", new ArrayList<>(), "LINK_Zelda", "Abu Dhabi. YOLO!"));

        return courses;
    }

    /**
     * The actual method that creates the file and puts a json array in it
     *
     * @param jsonArray is the array being saved
     */
    private static void writeToFile(JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(new File(getHomeDirPath(), fileName))) {

            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
