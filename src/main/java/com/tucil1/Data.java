package com.tucil1;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {
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
    
    public void inputColor (ListColor jenis, BufferedReader br) throws IOException{
        if(jenis == null){ // jika blm diinisialisasi
            return;
        }
        List<String> lines = new ArrayList<>();
        String line = br.readLine();

        while (line != null){
        line = line.trim();
        if (!line.isEmpty()) {  // pengecekan baris kosong
                lines.add(line);
            }
        line = br.readLine();
        }
        if (lines.isEmpty()){ // pengecekan tidak ada inputan
            return;
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        for(int i = 0; i < rows; i++){
            String lineRow = lines.get(i);
            for(int j = 0; j < cols; j++){
                char letter = lineRow.charAt(j);
                addCells(jenis, letter, i, j);
            }
        }
    }

    private void addCells(ListColor jenis, char letter, int x, int y) {
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