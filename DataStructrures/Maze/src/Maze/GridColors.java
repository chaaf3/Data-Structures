package Maze;

import java.awt.Color;

/**
 *  An interface for colors
 *@author Koffman and Wolfgang
 */
public interface GridColors {

    Color PATH = Color.green;
    // part of the visited path
    Color BACKGROUND = Color.white;
    //can't be part of the path
    Color NON_BACKGROUND = Color.red;
    //can be part of path
    Color ABNORMAL = NON_BACKGROUND;
    Color TEMPORARY = Color.black;
    //visited but not part of path
}
/*</exercise>*/