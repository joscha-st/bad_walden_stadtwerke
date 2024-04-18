package com.bad_walden_stadtwerke.ui.controller.mainApplication;

import com.bad_walden_stadtwerke.ui.components.mainApplication.Sidebar;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeItem;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainApplicationController {

    @FXML
    private TreeView<String> sidebarTreeView;

    @FXML
    private Button logoutButton;

    @FXML
    private Button germanButton;

    @FXML
    private Button englishButton;

    @FXML
    public void initialize() {
        setupSidebar();
        setupLogoutButton();
    }

    private void setupSidebar() {
        TreeItem<String> rootItem = Sidebar.createRootItem();
        sidebarTreeView.setRoot(rootItem);
        Sidebar.setTreeViewActionListener(sidebarTreeView);
    }

    private void setupLogoutButton() {
        ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
        logoutButton.setText(messages.getString("mainApplicationLogoutButton"));
        logoutButton.setOnAction(event -> logLogoutAction());
    }

    private void logLogoutAction() {
        System.out.println("Logout button pressed");
    }

    @FXML
    public void onButtonDeutschClick() {
        LanguageController.setLanguage(Locale.GERMAN);
        updateUI();
    }

    @FXML
    public void onButtonEnglishClick() {
        LanguageController.setLanguage(Locale.ENGLISH);
        updateUI();
    }

    private void updateUI() {
        Sidebar.updateLanguage();
        setupSidebar();
        setupLogoutButton();
    }
}