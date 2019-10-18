package ProjectOcean.Controller;

public class Tuple<X,Y> {
    private final X studyPeriod;
    private final Y slot;

    public Tuple(X studyPeriod, Y slot){
        this.studyPeriod = studyPeriod;
        this.slot = slot;
    }

    public X getStudyPeriod(){
        return studyPeriod;
    }
    public Y getSlot(){
        return slot;
    }
}
