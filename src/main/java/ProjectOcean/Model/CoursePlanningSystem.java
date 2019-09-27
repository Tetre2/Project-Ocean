package ProjectOcean.Model;

public class CoursePlanningSystem {

    private Student student;

    public CoursePlanningSystem() {
        this.student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void addCourse(Course course, int year, int studyPeriod, int slot) {
        student.addCourse(course, year, studyPeriod,slot);
    }

    public void removeCourse(Course course, int year, int studyPeriod) {
        student.removeCourse(course, year, studyPeriod);
    }
}
