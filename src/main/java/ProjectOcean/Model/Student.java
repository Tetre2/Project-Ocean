package ProjectOcean.Model;

import java.util.List;

public class Student {

    private List<ProjectOcean.Model.StudyPlan> studyPlans;
    private StudyPlan currentStudyPlan;

    public Student() {
        this.currentStudyPlan = new StudyPlan();
    }

    public void addCourse(Course course, int year, int studyPeriod, int slot) {
        currentStudyPlan.addCourseToSchedule(course, year, studyPeriod, slot);
    }

    public void removeCourse(Course course, int year, int studyPeriod) {
        currentStudyPlan.removeCourseFromSchedule(course, year, studyPeriod);
    }

    public void addYear() {
        currentStudyPlan.addYear();
    }

    public void removeYear(int year){
        currentStudyPlan.removeYear(year);
    }

    public StudyPlan getCurrentStudyPlan() {
        return currentStudyPlan;
    }
}
