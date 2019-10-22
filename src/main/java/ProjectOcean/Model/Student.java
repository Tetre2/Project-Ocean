package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class representing a student profile
 */
public class Student {

    private List<StudyPlan> studyPlans;
    private StudyPlan currentStudyPlan;
    private Workspace workspace;

    public Student() {
        this(new ArrayList<>(), new Workspace(), new StudyPlan());
    }

    public Student(List<StudyPlan> studyPlans){
        this(studyPlans, new Workspace(), new StudyPlan());
    }

    public Student(List<StudyPlan> studyPlans, Workspace workspace){
        this(studyPlans, workspace, new StudyPlan());
    }

    public Student(List<StudyPlan> studyPlans, Workspace workspace, StudyPlan currentStudyPlan) {
        this.studyPlans = studyPlans;
        this.currentStudyPlan = currentStudyPlan;
        this.workspace = workspace;
    }

    /**
     * Attempts to add the given course to the given year, study period and slot, in the study plan
     * @param course the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(ICourse course, int year, int studyPeriod, int slot) {
        currentStudyPlan.addCourse(course, year, studyPeriod, slot);
    }

    /**
     * Removes the given course in the given year and study period, in the study plan
     * @param yearID the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(int yearID, int studyPeriod, int slot) {
        currentStudyPlan.removeCourse(yearID, studyPeriod, slot);
    }

    /**
     * Creates a new year instance and adds it to the list of years, in the study plan
     */
    public void addYear() {
        currentStudyPlan.addYear();
    }

    /**
     * Removes the year specified by the index, in the study plan
     * @param id the year to be removed
     */
    public void removeYear(int id){
        currentStudyPlan.removeYear(id);
    }

    /**
     * @return the student's current active study plan
     */
    public StudyPlan getCurrentStudyPlan() {
        return currentStudyPlan;
    }

    /**
     * @return all studyplans
     */
    public List<StudyPlan> getAllStudyPlans() {
        return Collections.unmodifiableList(studyPlans);
    }

    /**
     * Returns a list of courses that exists currently in the workspace.
     * @return
     */
    public List<Course> getAllCoursesInWorkspace(){
        return workspace.getAllCourses();
    }

    /**
     * Adds a course to the students workspace
     * @param course is the course to be added
     */
    public void addCourseToWorkspace(Course course){
        workspace.addCourse(course);
    }

    /**
     * Removes a course from the workspace
     * @param course is the course to be added
     */
    public void removeCourseFromWorkspace(Course course) {
        workspace.removeCourse(course);
    }

    /**
     * Removes all courses
     */
    public void removeAllCoursesInWorkscpace(){
        workspace.removeAllCourses();
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
        Student student = (Student) o;
        return studyPlans.equals(student.studyPlans) &&
                currentStudyPlan.equals(student.currentStudyPlan) &&
                workspace.equals(student.workspace);
    }

    /**
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(studyPlans, currentStudyPlan, workspace);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studyPlans=" + studyPlans +
                ", currentStudyPlan=" + currentStudyPlan +
                ", workspace=" + workspace +
                '}';
    }
}
