package ProjectOcean.Model;

public class StudyPeriod {

    private Course course1;
    private Course course2;

    public void addCourse(Course course, int slot){
        if(course1 == null && slot == 0)
            course1 = course;
        else if(course2 == null && slot == 1)
            course2 = course;
    }

    public void removeCourse(Course course) {
        if(course.equals(course1))
            course1 = null;
        else
            course2 = null;
    }

    public Course getCourse1() {
        return course1;
    }

    public Course getCourse2() {
        return course2;
    }
}
