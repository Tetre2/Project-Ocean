package ProjectOcean.Model;

public class StudyPlan {

    private Schedule schedule;

    public StudyPlan() {
        this.schedule = new Schedule();
    }

    public void addCourseToSchedule(Course course, int year, int studyPeriod, int slot) {
        schedule.tryAddCourse(course, year, studyPeriod, slot);
    }

    public void removeCourseFromSchedule(Course course, int year, int studyPeriod){
        schedule.removeCourse(course, year, studyPeriod);
    }

    public void removeYear(int year) {
        schedule.removeYear(year);
    }

    public void addYear() {
        schedule.addYear();
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
