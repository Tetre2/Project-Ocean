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
import java.util.*;

public class CourseSaverLoader {


    private static String fileName = "courses.json";
    private static JSONParser parser = new JSONParser();

    /**
     * Loaded a Map from "courses.json" in user hom dir
     *
     * @return returns a <code>Map<UUID, Course></code>
     * @throws IOException
     */
    public static Map<UUID, Course> loadStudyPlans() throws IOException {
        try {

            return readFromFile();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        throw new IOException();
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
    public static String getHomeDirPath() {
        return System.getProperty("user.home") + File.separatorChar + ".CoursePlanningSystem";
    }

    /**
     *
     * @return returns the filename which holds courses
     */
    public static String getFileName() {
        return fileName;
    }

    /**
     * Saves a list of courses to a "courses.json" file in the user home dir.
     */
    public static void savePreMadeCourses() {
        //creates the "main" array which contains all courses
        JSONArray jsonCourses = new JSONArray();

        List<Course> courses = generateCourses();

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
    private static List<Course> generateCourses(){
        List<Course> courses = new ArrayList<>();

        courses.add(new Course(UUID.randomUUID(),"DAT017","Maskinorienterad programmering", "7.5", "1", "Roger Johansson", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.randomUUID(),"EDA433","Grundläggande Datorteknik", "7.5", "2", "Rolf snedspö", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.randomUUID(),"MVE045","Matematisk Analys", "7.5", "1", "Zoran Konkoli", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.randomUUID(),"TMV206","Linjär Algebra", "7.5", "3", "Lukás Malý", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));
        courses.add(new Course(UUID.randomUUID(),"TDA552","Objektorienterad Programmering och Design", "7.5", "2", "Alex Gerdes", "Munta/Inlämningsuppgift", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));

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
