package ProjectOcean.Model;

/**
 * The model's main aggregate class acting like an interface for the views and controllers
 */
public class CoursePlanningSystem {

    private Student student;

    public CoursePlanningSystem() {
        this.student = new Student();
    }

    /**
     * @return the current student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Attempts to add the given course to the given year, study period and slot for the current student
     * @param course the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(Course course, int year, int studyPeriod, int slot) {
        student.addCourse(course, year, studyPeriod,slot);
    }

    /**
     * Removes the given course in the given year and study period, for the current student
     * @param course the course to be removed
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(Course course, int year, int studyPeriod) {
        student.removeCourse(course, year, studyPeriod);
    }
}
