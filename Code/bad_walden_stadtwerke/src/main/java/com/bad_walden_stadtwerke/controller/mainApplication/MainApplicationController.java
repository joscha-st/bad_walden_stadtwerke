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

	public MainApplicationController() {
		LanguageController.addObserver(this);
	}

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
		logoutButton.setOnAction(event -> logLogoutAction());
	}

	private void setupLanguageButtons() {
		ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
		germanButton.setText(messages.getString("languageGerman"));
		englishButton.setText(messages.getString("languageEnglish"));
	}

	private void logLogoutAction() {
		System.out.println("Logout button pressed");
	}

	@FXML
	public void onButtonDeutschClick() {
		LanguageController.setLanguage(Locale.GERMAN);
	}

	@FXML
	public void onButtonEnglishClick() {
		LanguageController.setLanguage(Locale.ENGLISH);
	}

	@Override
	public void onLanguageChange(Locale newLocale) {
		updateUI();
	}

	public void updateUI() {
		setupLogoutButton();
		setupLanguageButtons();
		setupWelcomeHeadLine();
	}
}