package ProjectOcean.Model;

import java.util.*;

public class Workspace {

    private final List courses = new LinkedList<Course>();


    public void addCourse(Course course){
        if(courses.contains(course))
            return;
        else{
            courses.add(course);
        }
    }

    public void removeCourse(Course course){
        if(courses.contains(course)){
            courses.remove(course);
        }
        else{
            return;
        }
    }

    public void removeAllCourses(){
        while (courses.isEmpty() == false){
            courses.remove(0);
        }
    }

    public List<Course> getAllCourses(){
        return Collections.unmodifiableList(courses);
    }


    //Eventuell metod att implementera i ett senare skede för att tydliggöra visuella markeringar.

   /* public void findCourse(Course course){

    }*/
}
