/**
 * This package manages the main application's user interface, specifically the primary window where users interact with various functionalities after logging in. The {@code MainApplicationController} class handles dynamic updates to the UI based on user interactions and language settings.
 */
package com.bad_walden_stadtwerke.controller.mainApplication;

import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.components.mainApplication.SidebarItems;
import com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controls the main application interface, handling the sidebar navigation, user welcome message, and language settings.
 * Implements {@link LanguageChangeObserver} to respond to language changes throughout the application.
 */
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
     * Constructs a {@code MainApplicationController} and registers it as an observer for language changes.
     */
    public MainApplicationController() {
        LanguageController.addObserver(this);
    }

    /**
     * Initializes the controller by setting up the sidebar and updating the UI elements according to the current language.
     */
    @FXML
    public void initialize() {
        setupSidebar();
        updateUI();
    }

    /**
     * Sets up the welcome headline in the application's main view.
     */
    private void setupWelcomeHeadLine() {
        ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
        String welcomeMessage = messages.getString("mainApplicationWelcomeHeadline");
        welcomeHeadLineLabel.setText(MessageFormat.format(welcomeMessage, MockActiveSession.getActiveUser().getFriendlyDisplayName()));
    }

    /**
     * Configures the sidebar with navigation items.
     */
    private void setupSidebar() {
        if (sidebarListener != null) {
            sidebarTreeView.getSelectionModel().selectedItemProperty().removeListener(sidebarListener);
        }
        SidebarItems sidebarItems = new SidebarItems();
        sidebarTreeView.setRoot(sidebarItems);
        sidebarListener = sidebarItems.setTreeViewActionListener(sidebarTreeView);
    }

    /**
     * Configures the logout button.
     */
    private void setupLogoutButton() {
        ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
        logoutButton.setText(messages.getString("mainApplicationLogoutButton"));
        logoutButton.setOnAction(event -> logLogoutAction());
    }

    /**
     * Configures the language selection buttons.
     */
    private void setupLanguageButtons() {
        ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
        germanButton.setText(messages.getString("languageGerman"));
        englishButton.setText(messages.getString("languageEnglish"));
    }

    /**
     * Logs the action of clicking the logout button.
     */
    private void logLogoutAction() {
        System.out.println("Logout button pressed");
    }

    /**
     * Handles the event of clicking the German language button.
     */
    @FXML
    public void onButtonDeutschClick() {
        LanguageController.setLanguage(Locale.GERMAN);
    }

    /**
     * Handles the event of clicking the English language button.
     */
    @FXML
    public void onButtonEnglishClick() {
        LanguageController.setLanguage(Locale.ENGLISH);
    }

    /**
     * Responds to language change events by updating all UI components to reflect the new language.
     *
     * @param newLocale The new locale that has been set.
     */
    @Override
    public void onLanguageChange(Locale newLocale) {
        updateUI();
    }

    /**
     * Updates all user interface components to reflect the current settings and language.
     */
    public void updateUI() {
        setupLogoutButton();
        setupLanguageButtons();
        setupWelcomeHeadLine();
    }
}
