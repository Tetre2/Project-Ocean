package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.List;

/**
<<<<<<< HEAD
 * Class representing a schedule held by a study plan
=======
 * Represents a Schedule in the model
>>>>>>> develop
 */
public class Schedule {

    private final List<Year> years = new ArrayList<>();


    public Schedule() {
        years.add(new Year());
    }

    /**
     * Removes the given course in the given year and study period
     * @param course the course to be removed
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(Course course, int year, int studyPeriod) {
        years.get(year).getStudyPeriod(studyPeriod).removeCourse(course);
        years.get(year).removeCourse(course, studyPeriod);
    }

    /**
     * Attempts to add the given course to the given year, study period and slot
     * @param course the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void tryAddCourse(Course course, int year, int studyPeriod, int slot) {
        years.get(year).addCourse(course, studyPeriod, slot);
    }

    /**
     * Creates a new year instance and adds it to the list of years
     */
    public void addYear(){
        years.add(new Year());
    }

    /**
     * Removes the year specified by the index
     * @param year the year to be removed
     */
    public void removeYear(int year) {
        years.remove(year);
    }

    /**
     * Gets the year specified by the index
     * @param year the index specifying the year
     * @return the desired year
     */
    public Year getYear(int year){
        return years.get(year);
    }

}
