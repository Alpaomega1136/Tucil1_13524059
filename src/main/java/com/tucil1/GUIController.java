package com.tucil1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GUIController {

    @FXML
    public void onLoadClicked(ActionEvent event) {
        System.out.println("Tombol Load diklik");
    }

    @FXML
    public void onClearClicked(ActionEvent event) {
        System.out.println("Tombol Clear diklik");
    }

    @FXML
    public void onSolveClicked(ActionEvent event) {
        System.out.println("Tombol Solve diklik");
    }

    @FXML
    public void onSaveTxtClicked(ActionEvent event) {
    }

    @FXML
    public void onSaveImgClicked(ActionEvent event) {
    }

}