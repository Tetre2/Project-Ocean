package ProjectOcean.Model;

public class CourseInfo {

    private final String courseCode;
    private final String courseName;
    private final float studyPoints;

    public CourseInfo(String courseCode, String courseName, float studyPoints) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studyPoints = studyPoints;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

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
