package ProjectOcean.IO;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.CourseFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoursesSaverLoader implements ICourseSaveLoader{

    private static String fileName = "courses.json";
    private static JSONParser parser = new JSONParser();

    public CoursesSaverLoader() {
    }

    /**
     * Loads a Map from "courses.json" in user hom dir if it cant find the file it creates a new empty one
     *
     * @return returns a <code>Map<UUID, Course></code>
     */
    @Override
    public List<Course> loadCoursesFile() throws CoursesNotFoundException{
        try {
            return readFromFile();
        } catch (ParseException e) {
            throw new CoursesNotFoundException();
        } catch (IOException e) {
            throw new CoursesNotFoundException();
        }
    }

    /**
     * Creates a file filled with all default courses
     */
    @Override
    public void createCoursesFile() {
        new File(getHomeDirPath() + fileName);
        savePreMadeCourses();
    }

    /**
     * The method tha reads the file and creates the Map
     *
     * @return returns a <code>Map<UUID, Course></code>
     * @throws IOException
     * @throws ParseException
     */
    private static List<Course> readFromFile() throws IOException, ParseException {

        //creates a file with the path to the courses.json
        File file = new File(getHomeDirPath(), getFileName());

        //Map to return when method is done
        List<Course> courses = new ArrayList<>();

        //Creates a filereader which reads the courses.json and creates it as a jsonArray
        FileReader fileReader = new FileReader(file);
        Object parsed = parser.parse(fileReader);
        JSONArray studyPlans = (JSONArray) parsed;

        //loops through all "courses"
        for (Object object : studyPlans) {

            Course course = createCourseFronJSONObject(object);
            courses.add(course);

        }

        return courses;
    }

    private static Course createCourseFronJSONObject(Object object){
        //casts the "course" to a jsonObject to be able to access the info
        JSONObject jsonObject = (JSONObject) object;

        //loops through all required courses inorder to add them to the course in the constructor
        JSONArray jArr = (JSONArray) jsonObject.get("requiredCourses");
        List<String> requiredCourses = new ArrayList<>();
        for (Object obj : jArr) {
            requiredCourses.add((String) obj);
        }

        Course course = new Course(
                (String) jsonObject.get("courseCode"),
                (String) jsonObject.get("courseName"),
                (String) jsonObject.get("studyPoints"),
                (String) jsonObject.get("studyPeriod"),
                (String) jsonObject.get("examiner"),
                (String) jsonObject.get("examinationMeans"),
                (String) jsonObject.get("language"),
                requiredCourses,
                (String) jsonObject.get("coursePMLink"),
                (String) jsonObject.get("courseDescription"),
                (String) jsonObject.get("courseTypes")
        );
        return course;
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
     * Saves a list of courses to a "courses.json" file in the user home dir.
     */
    static void savePreMadeCourses() {
        //creates the "main" array which contains all courses
        JSONArray jsonCourses = new JSONArray();

        List<Course> courses = generatePreDefinedCourses();

        for (Course course: courses) {

            //creates a json object which represents a course
            JSONObject jsonCourse = new JSONObject();

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
            jsonCourse.put("courseTypes", course.getCourseTypes());

            jsonCourses.add(jsonCourse);

        }

        writeToFile(jsonCourses);

    }

    /**
     * Creates courses a predefined list of courses
     * @return returns a list of courses
     */
    public static List<Course> generatePreDefinedCourses(){
        List<Course> courses = new ArrayList<>();

        courses.add(createCourse("DAT017","Maskinorienterad programmering", "7.5", "1", "Roger Johansson", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT"));
        courses.add(createCourse("EDA433","Grundläggande Datorteknik", "7.5", "2", "Rolf snedspö", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT"));
        courses.add(createCourse("MVE045","Matematisk Analys", "7.5", "1", "Zoran Konkoli", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", "MA"));
        courses.add(createCourse("TMV206","Linjär Algebra", "7.5", "3", "Lukás Malý", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", "MA"));
        courses.add(createCourse("TDA552","Objektorienterad Programmering och Design", "7.5", "2", "Alex Gerdes", "Munta/Inlämningsuppgift", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum", "IT"));
        courses.add(createCourse("BAT123","Beroendespecifika paradigmer", "7.5","3","Anders Bölinge", "Tenta","Svenska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum", ""));
        courses.add(createCourse("CAT123","Complex system", "7.5", "2", "Anders Fölinge", "Tenta","Engelska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum", "IT"));
        courses.add(createCourse("DAT321","Datavetenskap", "7.5", "4", "Anders Bölinge", "Tenta","Svenska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum", "IT"));
        courses.add(createCourse("FAT321","Fysik för ingenjörer", "7.5", "1", "Anders Brölinge", "Tenta","Svenska", new ArrayList<>(), "www.chalmers.se", "Lorem ipsum", "NA"));
        courses.add(createCourse("TMV027","Ändliga automater och formella språk", "7.5", "2", "Ana Bove", "Tenta/hemuppgifter", "Engelska", new ArrayList<>(), "https://student.portal.chalmers.se/sv/chalmersstudier/programinformation/sidor/sokprogramutbudet.aspx?course_id=18397&parsergrp=2", "Lorem Ipsum", "IT, MA"));

        return courses;
    }


    private static Course createCourse(String courseCode, String courseName, String studyPoints, String studyPeriod, String examiner, String examinationMeans, String language, List<String> requiredCourses, String coursePMLink, String courseDescription, String courseTypes){
        return new Course(
                courseCode,
                courseName,
                studyPoints,
                studyPeriod,
                examiner,
                examinationMeans,
                language,
                requiredCourses,
                coursePMLink,
                courseDescription,
                courseTypes
        );
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
