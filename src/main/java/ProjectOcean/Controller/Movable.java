package ProjectOcean.Controller;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.UUID;

public interface Movable {

    UUID getUUID();

    void relocateToPoint(Point2D p);

  //  void dragDetected(MouseEvent event);
}
