package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a student profile
 */
public class Student {

    private Workspace workspace = new Workspace();

    private List<StudyPlan> studyPlans;
    private StudyPlan currentStudyPlan;

    public Student() {
        this.currentStudyPlan = new StudyPlan();
        studyPlans = new ArrayList<>();
        studyPlans.add(this.currentStudyPlan);
    }

    /**
     * Attempts to add the given course to the given year, study period and slot, in the study plan
     * @param course the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(ICourse course, int year, int studyPeriod, int slot) {
        currentStudyPlan.addCourseToSchedule(course, year, studyPeriod, slot);
    }

    /**
     * Removes the given course in the given year and study period, in the study plan
     * @param course the course to be removed
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(ICourse course, int year, int studyPeriod, int slot) {
        currentStudyPlan.removeCourseFromSchedule(course, year, studyPeriod, slot);
    }

    /**
     * Creates a new year instance and adds it to the list of years, in the study plan
     */
    public void addYear() {
        currentStudyPlan.addYear();
    }

    /**
     * Removes the year specified by the index, in the study plan
     * @param year the year to be removed
     */
    public void removeYear(int year){
        currentStudyPlan.removeYear(year);
    }

    /**
     * @return the student's current active study plan
     */
    public StudyPlan getCurrentStudyPlan() {
        return currentStudyPlan;
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

}
