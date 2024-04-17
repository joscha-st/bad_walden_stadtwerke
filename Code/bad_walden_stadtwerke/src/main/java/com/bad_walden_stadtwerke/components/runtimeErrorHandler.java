package com.bad_walden_stadtwerke.components;

//TODO: decide on if to have a unified error handler
public class runtimeErrorHandler {

    public static void handleError(String cat, Error e) {
        System.out.println("Error logged: " + cat + e.getCause()
                .toString());
        ExceptionPopup.showErrorPopup(cat, e.getCause()
                .toString());
    }
}
