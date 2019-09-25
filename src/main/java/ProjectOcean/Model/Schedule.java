package ProjectOcean.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Schedule {

    private final List<Year> years = new ArrayList<>();

    //A schedule starts with one year by default
    public Schedule() {
        years.add(new Year());
    }


    public void removeCourse(Course course, int year, int studyPeriod) {
        years.get(year).getStudyPeriod(studyPeriod).removeCourse(course);
    }


    public void tryAddCourse(int year, int studyPeriod, int slot, Course course) {
        years.get(year).getStudyPeriod(studyPeriod).addCourse(course, slot);
    }


    public Year getYear(int year){
        return years.get(year);
    }

}
