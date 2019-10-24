package ProjectOcean.Controller;

/**
 * A tuple with two generic values, although made for saving study period and slot in the same variable.
 * @param <X> is a generic value
 * @param <Y> is a generic value
 */
public class Tuple<X,Y> {
    private final X studyPeriod;
    private final Y slot;

    /**
     * Creates a Tuple that is intended to store a study period and a slot.
     * @param studyPeriod study period of a year.
     * @param slot a slot of a study period.
     */
    public Tuple(X studyPeriod, Y slot){
        this.studyPeriod = studyPeriod;
        this.slot = slot;
    }

    /**
     * @return a study period.
     */
    public X getStudyPeriod(){
        return studyPeriod;
    }

    /**
     * @return a slot.
     */
    public Y getSlot(){
        return slot;
    }
}
