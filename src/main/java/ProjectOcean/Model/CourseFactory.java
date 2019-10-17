package ProjectOcean.Model;

import java.util.List;

/**
 * An unique factory of how a course object is created.
 */
public class CourseFactory {

    /**
     * Create a course with the current variable state of this class.
     * @return A freshly created course of type ICourse
     */
    public static Course CreateCourse(
            String courseCode,
            String courseName,
            String studyPoints,
            String studyPeriod,
            String examiner,
            String examinationMeans,
            String language,
            List<String> requiredCourses,
            String coursePMLink,
            String courseDescription,
            String courseTypes) {

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

}
