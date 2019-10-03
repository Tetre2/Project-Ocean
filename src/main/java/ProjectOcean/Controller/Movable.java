package ProjectOcean.Controller;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.UUID;

public interface Movable {

    public UUID getUUID();

    public void relocateToPoint(Point2D p);

    public void dragDetected(MouseEvent event);
}
