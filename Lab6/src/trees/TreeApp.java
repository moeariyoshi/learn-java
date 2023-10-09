package trees;

/**
 * The driver class for our TreeApp GUI.  Contains initialization
 * functions and the main method for users to run.
 *
 * @author John Donaldson
 * @author Benjamin Kuperman (Spring 2007)
 * @author Alexa Sharp (Fall 2012)
 * @author Stephen Checkoway (Summer 2021)
 */

import javax.swing.JFrame;

public class TreeApp {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        TreeAppFrame frame = new TreeAppFrame();
        frame.validate();

        // Center the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
