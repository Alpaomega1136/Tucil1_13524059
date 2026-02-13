package com.tucil1;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class GUIController {

    @FXML private TextArea blocksArea;

    @FXML private Label statusLabel, timeLabel, casesLabel;
    @FXML private GridPane boardGrid;
    @FXML private StackPane boardContainer;
    @FXML private HBox saveBox;
    

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
        timeLabel.setText("Waktu: -");
        casesLabel.setText("Iterasi: -");
        boardGrid.getChildren().clear();

        Data.ListColor papan = new Data.ListColor();
        String text = blocksArea.getText();
        if (text == null || text.trim().isEmpty()) {
            statusLabel.setText("Board kosong!");
            return;
        }

        try (BufferedReader br = new BufferedReader(new StringReader(text))) {
            Data.rows = 0; Data.cols = 0;
            Data.inputColor(papan, br);
            if (papan.count == 0) {
                statusLabel.setText("Board kosong!");
                return;
            }
        } catch (IOException e) {
            statusLabel.setText("Gagal memproses input.");
            e.printStackTrace();
            return;
        }

        Thread worker = new Thread(() -> {
            Solve.onUpdateGUI = (snapshotRatu) -> {
                javafx.application.Platform.runLater(() -> {
                    generateBoard(papan, snapshotRatu);
                    statusLabel.setText("Mencoba kemungkinan...");
                });
            };

            Data.Queen ratu = new Data.Queen(0);
            Data.Queen hasil = Solve.Solve(papan, ratu);
            Data.Information info = Solve.getInfo();

            javafx.application.Platform.runLater(() -> {
                Solve.onUpdateGUI = null;
                
                if (info != null) {
                    timeLabel.setText("Waktu: " + info.time + " ms");
                    casesLabel.setText("Iterasi: " + info.iteration);
                }

                if (hasil != null) {
                    statusLabel.setText("Solusi Ditemukan!");
                    saveBox.setVisible(true);
                    generateBoard(papan, hasil);
                } else {
                    statusLabel.setText("Solusi Tidak Ditemukan.");
                    generateBoard(papan, new Data.Queen(0)); 
                }
            });
        });

        worker.setDaemon(true);
        worker.start();
    }

    @FXML
    public void onSaveTxtClicked(ActionEvent event) {
    }

    @FXML
    public void onSaveImgClicked(ActionEvent event) {
    }

    public void generateBoard(Data.ListColor papan, Data.Queen ratu) {
        boardGrid.getChildren().clear();
        Data.Map[][] boardMap = new Data.Map[Data.rows][Data.cols];
        
        for (int i = 0; i < papan.colors.size(); i++) {
            Data.Color c = papan.colors.get(i);
            for (int j = 0; j < c.position.size(); j++) {
                Data.Coordinat coord = c.position.get(j);
                if (coord.x < Data.rows && coord.y < Data.cols) {
                    boardMap[coord.x][coord.y] = new Data.Map(c.letter);
                }
            }
        }

        if (ratu != null && ratu.position != null) {
            for (int k = 0; k < ratu.position.size(); k++) { 
                Data.Coordinat qPos = ratu.position.get(k);
                boardMap[qPos.x][qPos.y].Queen = true;
            }
        }

        for (int i = 0; i < Data.rows; i++) {
            for (int j = 0; j < Data.cols; j++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(40, 40);
                Data.Map cellData = boardMap[i][j];
                String colorHex = "#FFFFFF"; 

                if (cellData != null) {
                    colorHex = getColorForChar(cellData.Letter);
                    cell.setStyle("-fx-background-color: " + colorHex + "; -fx-border-color: rgba(0,0,0,0.1);");
                    if (cellData.Queen) {
                        Label queenLabel = new Label("♛"); 
                        queenLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #000000;"); 
                        cell.getChildren().add(queenLabel);
                    }
                } else {
                    cell.setStyle("-fx-background-color: #ffffff; -fx-border-color: rgba(0,0,0,0.1);");
                }
                boardGrid.add(cell, j, i);
            }
        }
    }

    private final String[] STANDARD_COLORS = {
        "#FF0000", 
        "#00FF00", 
        "#0000FF", 
        "#FFFF00", 
        "#FFA500", 
        "#800080", 
        "#00FFFF", 
        "#FF00FF", 
        "#A52A2A", 
        "#808080", 
        "#FFC0CB", 
        "#4B0082",
        "#800000",
        "#008080",
        "#000080",
        "#808000",
        "#C0C0C0",
        "#FFD700",
        "#FA8072",
        "#40E0D0"
    };

    // Generate Warna
    private String getColorForChar(char c) {
        int charValue = (int) c - 'A';
        int index = (charValue * 7) % STANDARD_COLORS.length;
        if (index < 0){
            index += STANDARD_COLORS.length;
        }
        return STANDARD_COLORS[index];
    }

}