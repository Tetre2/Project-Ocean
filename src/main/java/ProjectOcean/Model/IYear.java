package ProjectOcean.Model;

public interface IYear {

    /**
     *
     * @param studyPeriod the study period from within the desired course lies
     * @param slot the slot from within the desired course lies
     * @return the desired Course
     */
    ICourse getCourseInStudyPeriod(int studyPeriod, int slot);

    /**
     *
     * @return the amount of study periods in a year(this is most probably going to stay at 4 at all times)
     */
    int getStudyPeriodsSize();

    int getYearNumber();
}
