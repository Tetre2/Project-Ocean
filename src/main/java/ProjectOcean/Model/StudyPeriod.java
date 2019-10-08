package ProjectOcean.Model;

import java.util.Objects;

/**
 * Represents a StudyPeriod in the model
 */
public class StudyPeriod {

    private Course course1;
    private Course course2;

    /**
     * Adds a course in a specific slot in study period
     * @param course the course to be added
     * @param slot the index representing which slot to add the course in
     */
    public void addCourse(Course course, int slot){
        if(course1 == null && slot == 0)
            course1 = course;
        else if(course2 == null && slot == 1)
            course2 = course;

    }

    /**
     * Removes the given course from the study period
     * @param course
     */
    public void removeCourse(Course course) {
        if(course.equals(course1))
            course1 = null;
        else
            course2 = null;
    }

    /**
     * @return Returns course in the first slot
     */
    public Course getCourse1() {
        return course1;
    }

    /**
     * @return Returns course in the second slot
     */
    public Course getCourse2() {
        return course2;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyPeriod that = (StudyPeriod) o;


        if(course1 == null && that.course1 == null && course2 == null && that.course2 == null){
            return true;
        }else if(course1 == null && that.course1 == null && course2.equals(that.course2)){
            return true;
        }else if(course2 == null && that.course2 == null && course1.equals(that.course1)){
            return true;
        }else {
            return false;
        }

        //return (((course1 == null && that.course1 == null) && (course2 == null && course2 == null)) || ((course1 == null && that.course1 == null) && (course2.equals(that.course2))) || ((course1.equals(that.course1)) && (course2 == null && that.course2 == null)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(course1, course2);
    }

    @Override
    public String toString() {
        return "StudyPeriod{" +
                "course1=" + course1 +
                ", course2=" + course2 +
                '}';
    }
}
