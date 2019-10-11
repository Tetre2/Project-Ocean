package ProjectOcean.Model;

/**
 * Class has responsible for information of a course.
 */
public class CourseInfo {

    private final String courseCode;
    private final String courseName;
    private final float studyPoints;

    public CourseInfo(String courseCode, String courseName, float studyPoints) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studyPoints = studyPoints;
    }

    /**
     * @return this course's name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @return this course's code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @return this course's study points
     */
    public String getStudyPoints() {
        return String.valueOf(studyPoints);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", studyPoints=" + studyPoints +
                '}';
    }
}
