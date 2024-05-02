/**
 * The Launcher class is responsible for launching the main application by invoking the {@link MainApplication#main(String[])} method.
 * <p>
 * It contains a main method that delegates the execution to the main method of the {@link MainApplication} class.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke;

public class Launcher {
	/**
     * The main method of the Launcher class, which starts the main application.
     *
     * @param args The command-line arguments passed to the application.
     */
	public static void main(String[] args) {
		MainApplication.main(args);
	}
}
