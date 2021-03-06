package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.Model.Course;
import ProjectOcean.Model.CourseFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseLoader {

    private static final String fileName = "courses.json";
    private static final int VERSION = 2;
    private static final JSONParser parser = new JSONParser();

    /**
     * Loads a Map from "courses.json" in user hom dir if it cant find the file it creates a new empty one
     * @throws CoursesNotFoundException throws a exception if the file cant be found
     * @throws OldFileException throws a exception if the file is of an older version
     * @return returns a <code>List of Course</code>
     */
    public static List<Course> loadCoursesFile() throws CoursesNotFoundException, OldFileException {
        if(!checkIfCorrectVersion())
            throw new OldFileException();
        try {
            return getCoursesFromJSON();
        } catch (ParseException e) {
            throw new CoursesNotFoundException();
        } catch (IOException e) {
            throw new CoursesNotFoundException();
        }
    }

    /**
     * The method tha reads the file and creates the Map
     *
     * @return returns a <code>List of Course</code>
     * @throws IOException if the program cant find the json file
     * @throws ParseException if the program cant parse the file
     */
    private static List<Course> getCoursesFromJSON() throws IOException, ParseException {
        //Map to return when method is done
        List<Course> courses = new ArrayList<>();

        JSONArray studyPlans = (JSONArray) readFormFile().get("courses");

        //loops through all "courses"
        for (Object courseObject : studyPlans) {
            Course course = createCourseFromJSONObject(courseObject);
            courses.add(course);

        }

        return courses;
    }

    private static Course createCourseFromJSONObject(Object object){
        //casts the "course" to a jsonObject to be able to access the info
        JSONObject jsonObject = (JSONObject) object;

        //loops through all required courses inorder to add them to the course in the constructor
        JSONArray jArr = (JSONArray) jsonObject.get("requiredCourses");
        List<String> requiredCourses = new ArrayList<>();
        for (Object obj : jArr) {
            requiredCourses.add((String) obj);
        }

        jArr = (JSONArray) jsonObject.get("courseTypes");
        List<String> courseTypes = new ArrayList<>();
        for (Object obj : jArr) {
            courseTypes.add((String) obj);
        }

        return CourseFactory.CreateCourse(
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
                courseTypes
        );
    }

    private static boolean checkIfCorrectVersion() throws CoursesNotFoundException{
        try {
            JSONObject jsonObject = readFormFile();
            int version = (int)(long) jsonObject.get("version");
            return version == VERSION;
        } catch (IOException e) {
            throw new CoursesNotFoundException();
        } catch (ParseException e) {
            throw new CoursesNotFoundException();
        }
    }

    private static JSONObject readFormFile() throws IOException, ParseException {
        //creates a file with the path to the courses.json
        File file = new File(CourseLoader.class.getClassLoader().getResource(fileName).getFile());

        //Creates a fileReader which reads the courses.json and creates it as a jsonArray
        FileReader fileReader = new FileReader(file);
        Object parsed = parser.parse(fileReader);
        return (JSONObject) parsed;
    }

}
