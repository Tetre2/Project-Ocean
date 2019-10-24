package ProjectOcean.Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentTests {

    private Student student;
    private int studyPeriod;
    private int slot;

    private Course course1;
    private Course course2;

    @Before
    public void before() {
        student = new Student();
        studyPeriod = 1;
        slot = 1;
        course1 = CourseFactory.CreateCourse(
                "BAT123",
                "Beroendespecifika paradigmer",
                "7.5",
                "3",
                "Anders Bölinge",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("")));

        course2 = CourseFactory.CreateCourse(
                "Dat123",
                "Maskinprogramering",
                "7.5",
                "1",
                "Anders Brölinge",
                "Tenta",
                "Svenska",
                new ArrayList<>(),
                "www.google.com",
                "Lorem Ipsum",
                new ArrayList<>(Arrays.asList("")));
    }

    @Test
    public void addCourseTest() {
        Student student = new Student();
        student.addYear();

        Year year = student.getCurrentStudyPlan().getYearByOrder(1);
        student.addCourse(course1, year.getID(), studyPeriod, slot);

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        ICourse actual = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course1, actual);
    }

    @Test
    public void removeCourseTest() {
        student.getCurrentStudyPlan().addYear();
        Year year = student.getCurrentStudyPlan().getYearByOrder(1);

        student.addCourse(course1, year.getID(), studyPeriod, slot);

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        ICourse actual = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertEquals(course1, actual);

        student.removeCourse(student.getCurrentStudyPlan().getYearByOrder(1).getID(), studyPeriod, slot);
        ICourse course = student.getCurrentStudyPlan().getYear(yearID).getStudyPeriod(studyPeriod).getCourse1();
        Assert.assertNull(course);
    }

    @Test
    public void addYearTest(){
        student.addYear();

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        Year year = student.getCurrentStudyPlan().getYear(yearID);

        Assert.assertNotNull(year);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeYearTest() {
        student.addYear();

        int yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        Year year = student.getCurrentStudyPlan().getYear(yearID);
        Assert.assertNotNull(year);

        student.removeYear(yearID);
        yearID = student.getCurrentStudyPlan().getYears().get(0).getID();
        year = student.getCurrentStudyPlan().getYear(yearID);
        Assert.assertNull(year);
    }

    @Test
    public void equalsTest(){
        Assert.assertTrue(course1.equals(course1));
        Assert.assertFalse(course2.equals(course1));
    }

    @Test
    public void addStudyPlan() {
        Assert.assertTrue(student.getAllStudyPlans().size() == 1);
        StudyPlan studyPlan = new StudyPlan();
        student.addStudyPlan(studyPlan);
        Assert.assertTrue(student.getAllStudyPlans().size() == 2);
    }

    @Test
    public void removeStudyPlan() {
        List<Integer> list = new ArrayList<>();
        list.add(student.getAllStudyPlans().get(0).getId());
        StudyPlan studyPlan = new StudyPlan();
        student.addStudyPlan(studyPlan);
        list.add(studyPlan.getId());
        Assert.assertTrue(student.getAllStudyPlans().size() == 2);

        StudyPlan studyPlan2 = new StudyPlan();
        student.addStudyPlan(studyPlan2);
        list.add(studyPlan2.getId());
        Assert.assertTrue(student.getAllStudyPlans().size() == 3);

        int spId = student.getAllStudyPlans().get(1).getId();
        for (StudyPlan studyplan : student.getAllStudyPlans()) {
            if(studyplan.getId() == spId){
                list.remove(spId);
            }
        }
        student.removeStudyPlan(spId);
        Assert.assertTrue(student.getAllStudyPlans().size() == 2);

        List<Integer> ids = student.getStudyPlanIds();

        System.out.println(list);
        System.out.println(ids);

        Assert.assertFalse(student.getStudyPlanIds().contains(spId));
    }

    @Test
    public void getStudyPlanIds() {
        StudyPlan studyPlan = new StudyPlan();
        student.addStudyPlan(studyPlan);

        StudyPlan studyPlan2 = new StudyPlan();
        student.addStudyPlan(studyPlan2);

        StudyPlan studyPlan3 = new StudyPlan();
        student.addStudyPlan(studyPlan3);

        List<Integer> ids = student.getStudyPlanIds();

        Assert.assertEquals(ids.size(), 4);
    }

    @Test
    public void setCurrentStudyPlan() {
        StudyPlan studyPlan = new StudyPlan();
        student.addStudyPlan(studyPlan);

        StudyPlan studyPlan2 = new StudyPlan();
        student.addStudyPlan(studyPlan2);

        Assert.assertNotEquals(studyPlan.getId(), student.getCurrentStudyPlan().getId());

        student.setCurrentStudyPlan(studyPlan);
        Assert.assertEquals(studyPlan.getId(), student.getCurrentStudyPlan().getId());

        student.setCurrentStudyPlan(studyPlan2);
        student.setCurrentStudyPlan();
        Assert.assertEquals(student.getAllStudyPlans().get(0).getId(), student.getCurrentStudyPlan().getId());

        Assert.assertNotEquals(studyPlan2.getId(), student.getCurrentStudyPlan().getId());
        student.setCurrentStudyPlan(studyPlan2.getId());
        Assert.assertEquals(studyPlan2.getId(), student.getCurrentStudyPlan().getId());
    }

}
