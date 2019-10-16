package ProjectOcean.Model;

import java.util.List;
import java.util.Objects;

/**
 * Represents a course in the model
 */
public class Course implements ICourse {

    private final String courseCode;
    private final String courseName;
    private final String studyPoints;
    private final String studyPeriod;
    private final String examiner;
    private final String examinationMeans;
    private final String language;
    private final List<String> requiredCourses;
    private final String coursePMLink;
    private final String courseDescription;

    public Course(String courseCode, String courseName, String studyPoints, String studyPeriod, String examiner, String examinationMeans, String language, List<String> requiredCourses, String coursePMLink, String courseDescription) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studyPoints = studyPoints;
        this.studyPeriod = studyPeriod;
        this.examiner = examiner;
        this.examinationMeans = examinationMeans;
        this.language = language;
        this.requiredCourses = requiredCourses;
        this.coursePMLink = coursePMLink;
        this.courseDescription = courseDescription;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", studyPoints='" + studyPoints + '\'' +
                ", studyPeriod='" + studyPeriod + '\'' +
                ", examiner='" + examiner + '\'' +
                ", examinationMeans='" + examinationMeans + '\'' +
                ", language='" + language + '\'' +
                ", requiredCourses=" + requiredCourses +
                ", coursePMLink='" + coursePMLink + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                '}';
    }

    /**
     * @return this course's PM link
     */
    @Override
    public String getCoursePMLink() {
        return coursePMLink;
    }

    /**
     * @return this course's description
     */
    @Override
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     * @return this course's required courses
     */
    @Override
    public List<String> getRequiredCourses() {
        return requiredCourses;
    }

    /**
     * @return this course's name
     */
    @Override
    public String getCourseName() {
        return courseName;
    }

    /**
     * @return this course's code
     */
    @Override
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @return this course's study period
     */
    public String getStudyPeriod() {
        return studyPeriod;
    }

    /**
     * @return this course's study points
     */
    @Override
    public String getStudyPoints() {
        return studyPoints;
    }

    /**
     * @return this course's examinator
     */
    @Override
    public String getExaminer() {
        return examiner;
    }

    /**
     * @return this course's examination forms
     */
    @Override
    public String getExaminationMeans() {
        return examinationMeans;
    }

    /**
     * @return this course's language
     */
    @Override
    public String getLanguage() {
        return language;
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
        Course course = (Course) o;
        return  courseCode.equals(course.courseCode) &&
                courseName.equals(course.courseName) &&
                studyPoints.equals(course.studyPoints) &&
                studyPeriod.equals(course.studyPeriod) &&
                examiner.equals(course.examiner) &&
                examinationMeans.equals(course.examinationMeans) &&
                language.equals(course.language) &&
                requiredCourses.equals(course.requiredCourses) &&
                coursePMLink.equals(course.coursePMLink) &&
                courseDescription.equals(course.courseDescription);
    }

    /**
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseName, studyPoints, studyPeriod, examiner, examinationMeans, language, requiredCourses, coursePMLink, courseDescription);
    }
}


