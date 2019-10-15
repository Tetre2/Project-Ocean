package ProjectOcean.Model;

import java.util.List;
import java.util.Objects;

/**
 * Represents a course in the model
 */
public class Course implements ICourse {

    private final String studyPeriod;
    private final CourseDetails cDetails;
    private final CourseInfo cInfo;
    private final CourseAccessibility cAccessibility;

    public Course(String studyPeriod, CourseDetails cDeatils, CourseInfo cInfo, CourseAccessibility cAccessibility) {
        this.studyPeriod = studyPeriod;
        this.cDetails = cDeatils;
        this.cInfo = cInfo;
        this.cAccessibility = cAccessibility;
    }

    @Override
    public String toString() {
        return "Course{" +
                "studyPeriod='" + studyPeriod + '\'' +
                ", " + cInfo +
                ", " + cAccessibility +
                ", " + cDetails +
                '}';
    }

    /**
     * @return this course's PM link
     */
    @Override
    public String getCoursePMLink() {
        return cDetails.getCoursePMLink();
    }

    /**
     * @return this course's description
     */
    @Override
    public String getCourseDescription() {
        return cDetails.getCourseDescription();
    }

    /**
     * @return this course's required courses
     */
    @Override
    public List<String> getRequiredCourses() {
        return cDetails.getRequiredCourses();
    }

    /**
     * @return this course's name
     */
    @Override
    public String getCourseName() {
        return cInfo.getCourseName();
    }

    /**
     * @return this course's code
     */
    @Override
    public String getCourseCode() {
        return cInfo.getCourseCode();
    }

    /**
     * @return this course's study period
     */
    public String getStudyPeriod() {
        return String.valueOf(studyPeriod);
    }

    /**
     * @return this course's study points
     */
    @Override
    public String getStudyPoints() {
        return cInfo.getStudyPoints();
    }

    /**
     * @return this course's examinator
     */
    @Override
    public String getExaminer() {
        return cAccessibility.getExaminator();
    }

    /**
     * @return this course's examination forms
     */
    @Override
    public String getExaminationMeans() {
        return cAccessibility.getExaminationMeans();
    }

    /**
     * @return this course's language
     */
    @Override
    public String getLanguage() {
        return cAccessibility.getLanguage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        boolean b = studyPeriod.equals(course.studyPeriod) &&
                cDetails.equals(course.cDetails) &&
                cInfo.equals(course.cInfo) &&
                cAccessibility.equals(course.cAccessibility);
        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyPeriod, cDetails, cInfo, cAccessibility);
    }
}


