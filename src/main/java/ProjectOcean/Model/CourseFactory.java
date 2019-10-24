package ProjectOcean.Model;

import java.util.List;

/**
 * An unique factory of how a course object is created.
 */
public class CourseFactory {

    //Makes it so you can't create an instance of CourseFactory
    private CourseFactory(){

    }

    /**
     * Create a course with the current variable state of this class.
     * @param courseCode course code of a Course
     * @param courseName name of a Course
     * @param studyPoints study points of a Course
     * @param studyPeriod study period a Course is given
     * @param examiner examiner of a course
     * @param examinationMeans a Courses examination means
     * @param language languages of a course
     * @param requiredCourses required courses for a Course
     * @param coursePMLink link to Courses PM
     * @param courseDescription Courses description
     * @param courseTypes type of the Course
     * @return A freshly created course of type ICourse. courseCode
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
            List<String> courseTypes) {

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
