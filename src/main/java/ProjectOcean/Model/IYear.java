package ProjectOcean.Model;

public interface IYear {
    ICourse getCourseInStudyPeriod(int studyPeriod, int slot);
    int getStudyPeriodsSize();
}
