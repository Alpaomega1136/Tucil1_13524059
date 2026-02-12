package com.tucil1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) throws IOException { // Menentukan GUI / CLI
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Silakan pilih mode aplikasi:");
        System.out.println("1. GUI (Graphical User Interface)");
        System.out.println("2. CLI (Command Line Interface)");
        System.out.print("Pilihan Anda (1/2): ");
        System.out.flush();

        String input = reader.readLine();

        if (input.trim().equals("1")) {
            System.out.println(">> Membuka jendela GUI...");
            launch(args); 
        } else {
            System.out.println(">> Menjalankan mode CLI...");
            runCLI(reader);
        }
    }

    private static void runCLI(BufferedReader reader) throws IOException { // Menjalankan CLI
        System.out.print("Masukkan nama file puzzle : ");
        String fileName = reader.readLine();
        File file = new File(fileName);
        if (!file.exists()){
            System.out.println("File tidak ditemukan!");
        }
        Data.ListColor papan = new Data.ListColor();
        Data parser = new Data();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            parser.inputColor(papan, fileReader); 
            if (papan.count == 0) {
                System.out.println("File kosong atau format tidak valid.");
                return;
            } 
            else {
                //solve.java
                return;
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Queens Games Solver");
        stage.setScene(scene);
        stage.show();
    }
}