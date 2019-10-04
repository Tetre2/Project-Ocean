package ProjectOcean.Model;

import java.util.Collections;
import java.util.List;

/**
 * Represents a Student in the model
 */
public class Student {

    private List<StudyPlan> studyPlans;
    private StudyPlan currentStudyPlan;

    public Student(List<StudyPlan> studyPlans) {
        this.studyPlans = studyPlans;
        this.currentStudyPlan = new StudyPlan();
    }

    /**
     * Attempts to add the given course to the given year, study period and slot, in the study plan
     * @param course the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(Course course, int year, int studyPeriod, int slot) {
        currentStudyPlan.addCourseToSchedule(course, year, studyPeriod, slot);
    }

    /**
     * Removes the given course in the given year and study period, in the study plan
     * @param course the course to be removed
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(Course course, int year, int studyPeriod) {
        currentStudyPlan.removeCourseFromSchedule(course, year, studyPeriod);
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
     * @return all studyplans
     */
    public List<StudyPlan> getAllStudyPlans(){
        return Collections.unmodifiableList(studyPlans);
    }

}
