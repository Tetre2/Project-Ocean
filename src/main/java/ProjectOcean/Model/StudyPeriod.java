package ProjectOcean.Model;

public class StudyPeriod {

    private Course course1;
    private Course course2;

    public void setCourse(Course course, int index) {
        if(index == 0)
            course1 = course;
        else
            course2 = course;
    }
}
