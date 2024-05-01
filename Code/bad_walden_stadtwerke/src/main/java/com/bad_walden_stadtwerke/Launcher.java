/**
 * This package contains the primary classes for the Bad Walden Stadtwerke application.
 * It includes the Launcher class, which serves as the entry point for starting the main application.
 */
package com.bad_walden_stadtwerke;

/**
 * The Launcher class provides the main method which serves as the entry point for the entire application.
 * It delegates the start of the application to the MainApplication class.
 */
public class Launcher {

    /**
     * The main method that starts the application by delegating to the MainApplication's main method.
     * This method is the standard entry point for a JavaFX application packaged for deployment, where the
     * JavaFX main class cannot be directly used as the entry point due to module constraints.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        MainApplication.main(args);
    }
}
