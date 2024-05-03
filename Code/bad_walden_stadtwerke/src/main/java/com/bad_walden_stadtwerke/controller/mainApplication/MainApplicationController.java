/**
 * The MainApplicationController class manages the main application interface, including the sidebar,
 * language settings, and user logout functionality.
 * <p>
 * It implements {@link com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver} to listen for language changes
 * and update the UI accordingly.
 * </p>
 * <p>
 * This controller interacts with {@link com.bad_walden_stadtwerke.mock.MockActiveSession} to retrieve active user data,
 * {@link com.bad_walden_stadtwerke.components.mainApplication.SidebarItems} to populate the sidebar items,
 * and {@link com.bad_walden_stadtwerke.controller.language.LanguageController} to manage language settings.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.controller.mainApplication;

import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.components.mainApplication.SidebarItems;
import com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.utility.CentralLoggingUtility;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApplicationController implements LanguageChangeObserver {

	@FXML
	private TreeView<String> sidebarTreeView;

	@FXML
	private Button logoutButton;

	@FXML
	private Button germanButton;

	@FXML
	private Button englishButton;

	@FXML
	private Label welcomeHeadLineLabel;

	private ChangeListener<TreeItem<String>> sidebarListener;


	/**
     * Constructor for MainApplicationController.
     * Registers this controller as an observer of language changes.
     */
	public MainApplicationController() {
		LanguageController.addObserver(this);
	}

	/**
     * Initializes the main application interface.
     * Called after the FXML file has been loaded.
     */
	@FXML
	public void initialize() {
		setupSidebar();
		updateUI();
	}


	private void setupWelcomeHeadLine() {
		ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
		String welcomeMessage = messages.getString("mainApplicationWelcomeHeadline");
		welcomeHeadLineLabel.setText(MessageFormat.format(welcomeMessage, MockActiveSession.getActiveUser().getFriendlyDisplayName()));
	}

	private void setupSidebar() {
		if (sidebarListener != null) {
			sidebarTreeView.getSelectionModel().selectedItemProperty().removeListener(sidebarListener);
		}
		SidebarItems sidebarItems = new SidebarItems();
		sidebarTreeView.setRoot(sidebarItems);
		sidebarListener = sidebarItems.setTreeViewActionListener(sidebarTreeView);
	}

	private void setupLogoutButton() {
		ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
		logoutButton.setText(messages.getString("mainApplicationLogoutButton"));
		logoutButton.setOnAction(event -> CentralLoggingUtility.handleEvent("UI","logout button pressed"));
	}

	private void setupLanguageButtons() {
		ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
		germanButton.setText(messages.getString("languageGerman"));
		englishButton.setText(messages.getString("languageEnglish"));
	}

	private void logLogoutAction() {
		System.out.println("Logout button pressed");
	}

	/**
     * Handles the action when the "Deutsch" button is clicked.
     * Sets the language to German.
     */
	@FXML
	public void onButtonDeutschClick() {
		LanguageController.setLanguage(Locale.GERMAN);
	}

	 /**
     * Handles the action when the "English" button is clicked.
     * Sets the language to English.
     */
	@FXML
	public void onButtonEnglishClick() {
		LanguageController.setLanguage(Locale.ENGLISH);
	}

	/**
     * Called when the language changes.
     * Updates the UI elements with new language settings.
     *
     * @param newLocale The new locale representing the language change.
     */
	@Override
	public void onLanguageChange(Locale newLocale) {
		updateUI();
	}

	/**
     * Updates the UI elements with the current language settings.
     * Called after a language change.
     */
	public void updateUI() {
		setupLogoutButton();
		setupLanguageButtons();
		setupWelcomeHeadLine();
	}
}