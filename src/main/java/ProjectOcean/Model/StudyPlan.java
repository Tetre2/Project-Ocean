package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class representing one of multiple study plans held by a student
 */
public class StudyPlan {

    private final List<Year> years;
    private final int id;
    private static int studyPlansCreatedDuringRuntime = 0;

    public StudyPlan(int id) {
        this.id = id;
        years = new ArrayList<>();
        studyPlansCreatedDuringRuntime = id + 1;
    }

    public StudyPlan() {
        this.id = studyPlansCreatedDuringRuntime;
        years = new ArrayList<>();
        studyPlansCreatedDuringRuntime++;
    }

    /**
     * Removes the given course in the given year and study period
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(int year, int studyPeriod, int slot) {
        getYear(year).removeCourse(studyPeriod, slot);
    }

    /**
     * Attempts to add the given course to the given year, study period and slot
     * @param course the course to be added
     * @param yearID the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(ICourse course, int yearID, int studyPeriod, int slot) {
        getYear(yearID).addCourse(course, studyPeriod, slot);
    }

    /**
     * Creates a new year instance and adds it to the list of years
     */
    public void addYear(){
        years.add(new Year());
    }

    /**
     * Removes the year specified by the index
     * @param id the year to be removed
     */
    public void removeYear(int id) {
        Year tempYear = null;
        for (Year year : years) {
            if(year.getID() == id){
                tempYear = year;
                break;
            }
        }
        years.remove(tempYear);
    }

    /**
     * Gets the year specified by the index
     * @param id the index specifying the year
     * @return the desired year
     */
    public Year getYear(int id){
        for (Year year : years) {
            if(year.getID() == id )
                return year;
        }
        return null;
    }

    public Year getYearByOrder(int year) {
        return years.get(year - 1);
    }

    /**
     * @return returns all years
     */
    public List<Year> getYears() {
        return Collections.unmodifiableList(years);
    }

    /**
     * @return the id of a studyPlan
     */
    public int getId() {
        return id;
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
        return studyPlan.getYears().equals(years);
    }

    /**
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(years);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "years=" + years +
                '}';
    }
}
