package ProjectOcean.Model;

import java.util.List;

public class Course {

    private String name;

    private String courseCode;

    private float studyPoints;

    public Course(String courseCode, String name, float studyPoints) {
        this.courseCode = courseCode;
        this.name = name;
        this.studyPoints = studyPoints;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", studyPoints=" + studyPoints +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public float getStudyPoints() {
        return studyPoints;
    }
}
