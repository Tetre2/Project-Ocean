package ProjectOcean.Controller;

import javafx.geometry.Point2D;

import java.util.UUID;

public interface Movable {


    /**
     * @return the UUID of the Movable instance
     */
    UUID getUUID();

    /**
     * Relocates the CourseListIconController instance according to the point parameter
     * @param p the point representing the current mouse coordinates
     */
    void relocateToPoint(Point2D p);

}
