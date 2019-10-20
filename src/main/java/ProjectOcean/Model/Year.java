package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class representing a year-row in a student's study plan
 */
public class Year implements IYear{

    private final List<StudyPeriod> studyPeriods = new ArrayList<>();

    private int ID;
    private static int yearsCreatedDuringRuntime = 0;


    public Year() {
        this.ID = yearsCreatedDuringRuntime;

        for (int i = 0; i < 4; i++) {
            studyPeriods.add(new StudyPeriod());
        }
    }

    /**
     * Adds a course to the given study period and slot
     * @param course the course to be added
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(ICourse course, int studyPeriod, int slot) {
        studyPeriods.get(studyPeriod - 1).addCourse(course, slot);
    }

    /**
     * Removes a course from the given study period
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(int studyPeriod, int slot) {
        studyPeriods.get(studyPeriod - 1).removeCourse(slot);
    }

    /**
     * @param period index representing the desired study period
     * @return the desired study period
     */
    public StudyPeriod getStudyPeriod(int period) {
        return studyPeriods.get(period - 1);
    }

    /**
     * @return returns all studyperiods
     */
    public List<StudyPeriod> getStudyPeriods() {
        return Collections.unmodifiableList(studyPeriods);
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
        Year year = (Year) o;
        return studyPeriods.equals(year.studyPeriods);
    }

    /**
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(studyPeriods);
    }

    @Override
    public String toString() {
        return "Year{" +
                "studyPeriods=" + studyPeriods +
                '}';
    }

    /**
     *
     * @param studyPeriod the study period from within the desired course lies
     * @param slot the slot from within the desired course lies
     * @return the desired Course
     */
    @Override
    public ICourse getCourseInStudyPeriod(int studyPeriod, int slot) {
        if(slot == 1)
            return studyPeriods.get(studyPeriod - 1).getCourse1();
        return studyPeriods.get(studyPeriod - 1).getCourse2();
    }

    /**
     *
     * @return the amount of study periods in a year(this is most probably going to stay at 4 at all times)
     */
    @Override
    public int getStudyPeriodsSize() {
        return studyPeriods.size();
    }

    @Override
    public int getID() {
        return ID;
    }

    public static void incNumbersOfYearsID(){
        yearsCreatedDuringRuntime++;
    }
}
