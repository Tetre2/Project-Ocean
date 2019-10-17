package ProjectOcean.Model;

import java.util.Objects;

/**
 * Class representing one of multiple study plans held by a student
 */
public class StudyPlan {

    private Schedule schedule;

    public StudyPlan() {
        this.schedule = new Schedule();
    }

    /**
     * Attempts to add the given course to the given year, study period and slot, in the schedule
     * @param course the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourseToSchedule(ICourse course, int year, int studyPeriod, int slot) {
        schedule.addCourse(course, year, studyPeriod, slot);
    }

    /**
     * Removes the given course in the given year and study period, in the schedule
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourseFromSchedule(int year, int studyPeriod, int slot){
        schedule.removeCourse(year, studyPeriod, slot);
    }

    /**
     * Removes the year specified by the index, in the schedule
     * @param year the year to be removed
     */
    public void removeYear(int year) {
        schedule.removeYear(year);
    }

    /**
     * Creates a new year instance and adds it to the list of years, in the schedule
     */
    public void addYear(int yearNumber) {
        schedule.addYear(yearNumber);
    }

    /**
     * @return this study plan's schedule
     */
    public Schedule getSchedule() {
        return schedule;
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
        StudyPlan studyPlan = (StudyPlan) o;
        return schedule.equals(studyPlan.schedule);
    }

    /**
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(schedule);
    }

    @Override
    public String toString() {
        return "StudyPlan{" +
                "schedule=" + schedule +
                '}';
    }
}
