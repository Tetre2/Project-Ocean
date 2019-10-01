package ProjectOcean.Model;

import java.util.UUID;

public class Course {

    private UUID id;
    private String name;

    private String courseCode;
    private String examinor;

    private float studyPoints;

    public Course(String courseCode, String name, float studyPoints, String examinor) {
        this.id = UUID.randomUUID();
        this.courseCode = courseCode;
        this.name = name;
        this.studyPoints = studyPoints;
        this.examinor = examinor;
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

    public String getExaminor() {
        return this.examinor;
    }
}
