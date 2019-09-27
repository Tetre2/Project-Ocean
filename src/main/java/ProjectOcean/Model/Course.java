package ProjectOcean.Model;

import java.util.UUID;

public class Course {

    private final UUID id;
    private final String name;

    private final String courseCode;

    private final float studyPoints;

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

    public String getCourseName() {
        return name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudyPoints() {
        return String.valueOf(studyPoints);
    }
}
