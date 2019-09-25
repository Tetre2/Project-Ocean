package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a year-row in a student's study plan
 */
public class Year {

    private final List<StudyPeriod> studyPeriods = new ArrayList<>();

    public Year() {
        for (int i = 0; i < 4; i++) {
            studyPeriods.add(new StudyPeriod());
        }
    }

    public StudyPeriod getStudyPeriod(int period) {
        return studyPeriods.get(period);
    }

    public void addCourse(Course course, int studyPeriod, int slot) {
        studyPeriods.get(studyPeriod).addCourse(course, slot);
    }

    public void removeCourse(Course course, int studyPeriod) {
        studyPeriods.get(studyPeriod).removeCourse(course);
    }



}
