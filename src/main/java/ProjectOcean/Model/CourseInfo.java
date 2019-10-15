package ProjectOcean.Model;

import java.util.Objects;

/**
 * Class has responsible for information of a course.
 */
public class CourseInfo {

    private final String courseCode;
    private final String courseName;
    private final String studyPoints;

    public CourseInfo(String courseCode, String courseName, String studyPoints) {
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
        return "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", studyPoints='" + studyPoints + '\'';
    }

    /**
     * checks if this and an other object is the same
     * @param o is the object being checked against this object
     * @return true if the this object is the same as o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseInfo that = (CourseInfo) o;
        return courseCode.equals(that.courseCode) &&
                courseName.equals(that.courseName) &&
                studyPoints.equals(that.studyPoints);
    }

    /**
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseName, studyPoints);
    }
}
