package ProjectOcean.Model;

import java.util.List;

public class CourseFactory {

    public static ICourse CreateCourse(String courseCode, String courseName, float studyPoints, int studyPeriod,
                                       String examinator, String examinationMeans, String language,
                                       List<Course> requiredCourses, String coursePMLink, String courseDescription) {
        CourseDetails cDetails = createCourseDetails(requiredCourses, coursePMLink, courseDescription);
        CourseInfo cInfo = createCourseInfo(courseCode, courseName, studyPoints);
        CourseAccessibility cAccessibility = createCourseAccessibility(examinator, examinationMeans, language);
        return new Course(studyPeriod, cDetails, cInfo, cAccessibility);
    }

    private static CourseInfo createCourseInfo(String courseCode, String courseName, float studyPoints) {
        return new CourseInfo(courseCode, courseName, studyPoints);
    }

    private static CourseDetails createCourseDetails(List<Course> requiredCourses, String coursePMLink, String courseDescription) {
        return new CourseDetails(requiredCourses, coursePMLink, courseDescription);
    }

    private static CourseAccessibility createCourseAccessibility(String examinator, String examinationMeans, String language) {
        return new CourseAccessibility(examinator, examinationMeans, language);
    }

}
