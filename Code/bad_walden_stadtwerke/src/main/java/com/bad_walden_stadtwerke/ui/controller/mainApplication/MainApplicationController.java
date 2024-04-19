package com.bad_walden_stadtwerke.ui.controller.mainApplication;

import com.bad_walden_stadtwerke.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.sales.types.Tariff;
import com.bad_walden_stadtwerke.ui.components.mainApplication.SidebarItems;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TreeItem;

public class MainApplicationController {

    @FXML
    private TreeView<String> sidebarTreeView;

    @FXML
    private Button logoutButton;

    @FXML
    private Button germanButton;

    @FXML
    private Button englishButton;

    private SidebarItems sidebarItems;

    @FXML
    public void initialize() {
        setupSidebar();
        setupLogoutButton();
        List<Tariff> electricity = StandardOutboundRequestHandler.makeTariffOutboundRequest("electricity");
        System.out.println(electricity);
    }
    private ChangeListener<TreeItem<String>> sidebarListener;

    private void setupSidebar() {
        if (sidebarListener != null) {
            sidebarTreeView.getSelectionModel().selectedItemProperty().removeListener(sidebarListener);
        }
        SidebarItems SidebarItems = new SidebarItems();
        sidebarTreeView.setRoot(SidebarItems);
        sidebarListener = SidebarItems.setTreeViewActionListener(sidebarTreeView);
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
        SidebarItems.updateLanguage();
        setupSidebar();
        setupLogoutButton();
    }
}