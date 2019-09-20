package ProjectOcean.Model;

import java.util.List;
import java.util.UUID;

public class Course {

    private UUID id;
    private String name;

    private String courseCode;

    private float studyPoints;

    public Course(String courseCode, String name, float studyPoints) {
        this.id = UUID.randomUUID();
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

    public UUID getId() {
        return this.id;
    }

    public String getIdToString() {
        return this.id.toString();
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
