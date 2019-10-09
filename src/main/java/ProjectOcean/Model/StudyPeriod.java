package ProjectOcean.Model;

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
        if(course1 == null && slot == 1)
            course1 = course;
        else if(course2 == null && slot == 2)
            course2 = course;

    }

    /**
     * Removes the given course from the study period
     * @param course
     */
    public void removeCourse(Course course, int slot) {
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
}
