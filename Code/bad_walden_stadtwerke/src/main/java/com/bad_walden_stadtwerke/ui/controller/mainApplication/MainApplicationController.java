package com.bad_walden_stadtwerke.ui.controller.mainApplication;

import com.bad_walden_stadtwerke.ui.components.mainApplication.sidebar.SidebarItems;
import com.bad_walden_stadtwerke.ui.controller.LanguageChangeObserver;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TreeItem;

public class MainApplicationController implements LanguageChangeObserver {

    private static MainApplicationController instance;

    @FXML
    private TreeView<String> sidebarTreeView;

    @FXML
    private Button logoutButton;

    @FXML
    private Button germanButton;

    @FXML
    private Button englishButton;

    private ChangeListener<TreeItem<String>> sidebarListener;

    public MainApplicationController() {
        LanguageController.addObserver(this);
    }

    @FXML
    public void initialize() {
        setupSidebar();
        updateUI();
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
    }
}