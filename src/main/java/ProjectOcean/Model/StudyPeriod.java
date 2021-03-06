package ProjectOcean.Model;

import java.util.Objects;

/**
 * Class representing a single study period in a year
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
        if(slot == 1)
            course1 = course;
        else if(slot == 2)
            course2 = course;

    }

    /**
     * Removes the given course from the study period
     * @param slot the slot to remove the course from
     */
    public void removeCourse(int slot) {
        if(slot == 1)
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

    /**
     * checks if this and an other object is the same
     * @param o is the object being checked against this object
     * @return true if the this object is the same as o
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyPeriod that = (StudyPeriod) o;

        boolean isEqual = true;

        if(!((course1 != null && course1.equals(that.course1)) || (course1 == null && that.course1 == null))){
            isEqual = false;
        }
        if(!((course2 != null && course2.equals(that.course2)) || ((course2 == null && that.course2 == null)))){
            isEqual = false;
        }
        return isEqual;
    }

    /**
     * @return a hash code
     */
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
