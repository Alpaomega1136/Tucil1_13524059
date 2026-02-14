package com.tucil1;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public static int rows;
    public static int cols;

    public static  Cell[][] Map;

    public static final String[] STANDARD_COLORS = {
        "#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FFA500", 
        "#800080", "#00FFFF", "#FF00FF", "#A52A2A", "#808080", 
        "#FFC0CB", "#4B0082", "#800000", "#008080", "#000080", 
        "#808000", "#C0C0C0", "#FFD700", "#FA8072", "#40E0D0"
    };

    public static String getColorHex(char c) {
        int charValue = (int) c - 'A';
        int index = (charValue * 7) % STANDARD_COLORS.length;
        if (index < 0){
            index += STANDARD_COLORS.length;
        }
        return STANDARD_COLORS[index];
    }
    
    public static class Information{
        public final int iteration;
        public final int time;
        Information(int iteration, int time){
            this.iteration = iteration;
            this.time = time;
        }
    }

    public static class Coordinat{
        int x;
        int y;

        Coordinat(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static class Color{
        char letter;
        List<Coordinat> position;

        Color(char letter) {
            this.letter = letter;
            this.position = new ArrayList<>();
        }
    }

    public static class ListColor{
        int count;
        List<Color> colors;

        ListColor(){
            this.count = 0;
            this.colors = new ArrayList<>();
        }
    }

    public static class Queen{
        int queens;
        List<Coordinat> position;

        Queen(int queens) {
            this.queens = queens;
            this.position = new ArrayList<>();
        }
    }

    public static class Cell{
        Boolean Queen;
        char Letter;
        Cell(char Letter){
            this.Letter = Letter;
            this.Queen = false;
        }
    }
    
    public static void inputColor (ListColor jenis, BufferedReader br) throws IOException{
        if(jenis == null){ 
            return;
        }
        List<String> lines = new ArrayList<>();
        String line = br.readLine();

        while (line != null){
            line = line.trim(); 
            line = line.replaceAll("\\s+", ""); 
            if (!line.isEmpty()) { 
                lines.add(line);
            }
            line = br.readLine();
        }
        
        if (lines.isEmpty()){ 
            throw new IOException();
        }

        rows = lines.size();
        cols = lines.get(0).length();

        for(int i = 0; i < rows; i++){
            String lineRow = lines.get(i);
            if (lineRow.length() != cols) {
                throw new IOException();
            }

            for(int j = 0; j < cols; j++){
                char letter = lineRow.charAt(j);
                if (!Character.isLetter(letter) && letter != '.') {
                    throw new IOException();
                }

                addCells(jenis, letter, i, j);
            }
        }
    }

    private static void addCells(ListColor jenis, char letter, int x, int y) {
        for(int i = 0; i < jenis.count; i++){
                if(jenis.colors.get(i).letter == letter){
                    jenis.colors.get(i).position.add(new Coordinat(x, y));
                    return;
                }
            }
        // Untuk count = 0 dan tidak ada dilist saat mendata
        jenis.count++;
        Color newColor = new Color(letter);
        newColor.position.add(new Coordinat(x, y));
        jenis.colors.add(newColor);
    }
}