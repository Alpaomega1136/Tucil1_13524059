package com.tucil1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Save {
    private static final String OUTPUT_DIR = "test" + File.separator;

    public static void saveTxt(String fileName) {
        if (Data.Map == null || Data.rows == 0 || Data.cols == 0) {
            System.out.println("Tidak ada data untuk disimpan.");
            return;
        }

        try {
            File directory = new File(OUTPUT_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(OUTPUT_DIR + fileName + ".txt");
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < Data.rows; i++) {
                for (int j = 0; j < Data.cols; j++) {
                    Data.Cell cell = Data.Map[i][j];
                    if (cell != null) {
                        if (cell.Queen) {
                            writer.write("#");
                        } else {
                            writer.write(cell.Letter);
                        }
                    } else {
                        writer.write(".");
                    }
                }
                writer.write("\n");
            }
            writer.write("\n");
            Data.Information info = Solve.getInfo();
            if (info != null) {
                writer.write("Waktu pencarian: " + info.time + " ms\n");
                writer.write("Banyak kasus yang ditinjau: " + info.iteration + "\n");
            } else {
                writer.write("Waktu pencarian: - ms\n");
                writer.write("Banyak kasus yang ditinjau: -\n");
            }

            writer.close();
            System.out.println("File TXT berhasil disimpan ke: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file TXT.");
            e.printStackTrace();
        }
    }

    public static void saveImg(String fileName) {
        if (Data.Map == null || Data.rows == 0 || Data.cols == 0) {
            System.out.println("Tidak ada data matriks untuk digambar.");
            return;
        }

        try {
            File directory = new File(OUTPUT_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            int cellSize = 60;
            int width = Data.cols * cellSize;
            int height = Data.rows * cellSize;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            for (int i = 0; i < Data.rows; i++) {
                for (int j = 0; j < Data.cols; j++) {
                    Data.Cell cell = Data.Map[i][j];
                    int x = j * cellSize;
                    int y = i * cellSize;
                    if (cell != null) {
                        String hexColor = Data.getColorHex(cell.Letter);
                        g2d.setColor(Color.decode(hexColor));
                        g2d.fillRect(x, y, cellSize, cellSize);
                        g2d.setColor(new Color(0, 0, 0, 50)); 
                        g2d.drawRect(x, y, cellSize, cellSize);
                        if (cell.Queen) {
                            g2d.setColor(Color.BLACK);
                            Font font = new Font("SansSerif", Font.BOLD, cellSize / 2 + 10);
                            g2d.setFont(font);
                            String symbol = "♛";
                            FontMetrics metrics = g2d.getFontMetrics(font);
                            int xText = x + (cellSize - metrics.stringWidth(symbol)) / 2;
                            int yText = y + ((cellSize - metrics.getHeight()) / 2) + metrics.getAscent();
                            g2d.drawString(symbol, xText, yText);
                        }
                    } else {
                        g2d.setColor(Color.WHITE);
                        g2d.fillRect(x, y, cellSize, cellSize);
                    }
                }
            }

            g2d.dispose();
            File file = new File(OUTPUT_DIR + fileName + ".png");
            ImageIO.write(image, "png", file);
            System.out.println("Gambar PNG (Matriks) disimpan: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file PNG.");
            e.printStackTrace();
        }
    }
}