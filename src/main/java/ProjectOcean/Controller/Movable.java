package ProjectOcean.Controller;

import ProjectOcean.Model.ICourse;
import javafx.geometry.Point2D;

public interface Movable {


    /**
     * @return the ICourse of the Movable instance
     */
    ICourse getICourse();

    /**
     * Relocates the CourseListIconController instance according to the point parameter
     * @param p the point representing the current mouse coordinates
     */
    void relocateToPoint(Point2D p);

}
