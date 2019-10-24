package ProjectOcean.Controller.FunctionalInterfaces;

import ProjectOcean.Controller.Movable;

@FunctionalInterface
public interface AddIconToScreen {

    /**
     * Adds the icon to the drag surface
     * @param icon the movable icon to be added
     */
    void addIconToScreen(Movable icon);

}
