package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.List;

public class CourseInfo implements InformationLoader {

    private final List<Course> courses;

    public CourseInfo() {
        courses = new ArrayList<Course>();
        courses.add(new Course("DAT017","Maskinorienterad programmering", 7.5f));
        courses.add(new Course("EDA433","Grundläggande Datorteknik", 7.5f));
        courses.add(new Course("MVE045","Matematisk Analys", 7.5f));
        courses.add(new Course("TMV206","Linjär Algebra", 7.5f));
        courses.add(new Course("TDA552","Objektorienterad Programmering och Design", 7.5f));
    }

    public List getAllCourses() {
        return courses;
    }
}
