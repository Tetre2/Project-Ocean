package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class has responsible for details of a course.
 */
public class CourseDetails {

    private final List<String> requiredCourses;
    private final String coursePMLink;
    private final String courseDescription;
    private final String courseTypes;

    public CourseDetails(List<String> requiredCourses, String coursePMLink, String courseDescription, String courseTypes) {
        this.requiredCourses = requiredCourses;
        this.coursePMLink = coursePMLink;
        this.courseDescription = courseDescription;
        this.courseTypes = courseTypes;
    }

    /**
     * @return this course's required courses
     */
    public List<String> getRequiredCourses() {
        List<String> requiredCourses = new ArrayList<>();

        for (String string : this.requiredCourses) {
            requiredCourses.add(string);
        }

        return Collections.unmodifiableList(requiredCourses);
    }

    /**
     * @return this course's PM link
     */
    public String getCoursePMLink() {
        return coursePMLink;
    }

    /**
     * @return this course's description
     */
    public String getCourseDescription() {
        return courseDescription;
    }

    /**
     *
     * @return this course's course Types
     */
    public String getCourseTypes() {
        return courseTypes;
    }

    @Override
    public String toString() {
        return "requiredCourses=" + requiredCourses +
                ", coursePMLink='" + coursePMLink + '\'' +
                ", courseDescription='" + courseDescription + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDetails that = (CourseDetails) o;
        return requiredCourses.equals(that.requiredCourses) &&
                coursePMLink.equals(that.coursePMLink) &&
                courseDescription.equals(that.courseDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requiredCourses, coursePMLink, courseDescription);
    }
}
