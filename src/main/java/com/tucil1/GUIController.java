package com.tucil1;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class GUIController {

    @FXML private TextField nField, mField, pField;
    @FXML private ChoiceBox<String> modeChoice;
    @FXML private TextArea blocksArea;
    @FXML private TextArea customBoardArea;
    @FXML private VBox customInputBox;

    @FXML private Label statusLabel, timeLabel, casesLabel;
    @FXML private GridPane boardGrid;
    @FXML private StackPane boardContainer;
    @FXML private HBox saveBox;
    @FXML private TextField saveNameField;
    

    @FXML // Load .txt file
    public void onLoadClicked(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File file = chooser.showOpenDialog(boardContainer.getScene().getWindow());
        if (file == null) return;

        try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            blocksArea.setText(content);
            boardGrid.getChildren().clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClearClicked(ActionEvent event) {
        blocksArea.clear();
        boardGrid.getChildren().clear();
        saveBox.setVisible(false);
        timeLabel.setText("Waktu: -");
        casesLabel.setText("Iterasi: -");
        statusLabel.setText("Siap.");
    }

    @FXML
    public void onSolveClicked(ActionEvent event) {
        statusLabel.setText("Sedang mencari solusi...");
        saveBox.setVisible(false);
        Data.ListColor papan = new Data.ListColor();
        String text = blocksArea.getText();
        if (text == null || text.trim().isEmpty()) {
            statusLabel.setText("Board kosong!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new StringReader(text))) {
            Data.inputColor(papan, br);
            if (papan.count == 0) {
                statusLabel.setText("Board kosong!");
                return;
            }
        } catch (IOException e) {
            statusLabel.setText("Gagal memproses input.");
            e.printStackTrace();
        }
        // solve.java
    }

    @FXML
    public void onSaveTxtClicked(ActionEvent event) {
    }

    @FXML
    public void onSaveImgClicked(ActionEvent event) {
    }

}