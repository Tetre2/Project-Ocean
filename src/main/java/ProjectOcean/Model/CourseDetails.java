package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class has responsible for details of a course.
 */
public class CourseDetails {

    private final List<Course> requiredCourses;
    private final String coursePMLink;
    private final String courseDescription;

    public CourseDetails(List<Course> requiredCourses, String coursePMLink, String courseDescription) {
        this.requiredCourses = requiredCourses;
        this.coursePMLink = coursePMLink;
        this.courseDescription = courseDescription;
    }

    /**
     * @return this course's required courses
     */
    public List<String> getRequiredCourses() {
        List<String> requiredCourses = new ArrayList<>();

        for (Course course : this.requiredCourses) {
            requiredCourses.add(course.getCourseName());
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

}
