package ProjectOcean.Model;

import java.util.List;

/**
 * An unique factory of how a course object is created.
 */
public class CourseFactory {

    private static int studyPeriod;
    private static CourseDetails cDetails;
    private static CourseInfo cInfo;
    private static CourseAccessibility cAccessibility;

    // Can't create instance
    private CourseFactory(){

    }

    /**
     * Create a course with the current variable state of this class.
     * @return A freshly created course of type ICourse
     */
    public static ICourse CreateCourse() {
        return new Course(studyPeriod, cDetails, cInfo, cAccessibility);
    }

    /**
     * @param studyPeriod Set a course's study period
     */
    public static void SetStudyPeriod(int studyPeriod) {
        CourseFactory.studyPeriod = studyPeriod;
    }

    /**
     * @param requiredCourses Set a course's pre required courses
     * @param coursePMLink Set a link to a course's PM
     * @param courseDescription Set a course's description
     */
    public static void SetCourseDetails(List<Course> requiredCourses, String coursePMLink, String courseDescription) {
        CourseFactory.cDetails = new CourseDetails(requiredCourses, coursePMLink, courseDescription);
    }

    /**
     * @param courseCode Set a course's code
     * @param courseName Set a course's name
     * @param studyPoints Set a course's study points
     */
    public static void SetCourseInfo(String courseCode, String courseName, float studyPoints) {
        CourseFactory.cInfo = new CourseInfo(courseCode, courseName, studyPoints);
    }

    /**
     * @param examinator Set a course's examinator
     * @param examinationMeans Set a course's examination forms
     * @param language Set a course's language
     */
    public static void SetCourseAccessibility(String examinator, String examinationMeans, String language) {
        CourseFactory.cAccessibility = new CourseAccessibility(examinator, examinationMeans, language);
    }

}
