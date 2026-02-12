package com.tucil1;
import java.util.ArrayList;

public class Solve{
    private static long casesTried = 0;
    private static Data.Information info = null;

    public static Data.Information getInfo() {
        return info;
    }

    public static Data.Queen Solve(Data.ListColor papan, Data.Queen ratu){
        if(papan == null || ratu == null){
            return null;
        }
        casesTried = 0;
        ratu.position = new ArrayList<>();
        long startTime = System.nanoTime();
        if(iteration(0, papan, ratu)){
            long elapsedMs = (System.nanoTime() - startTime) / 1_000_000L;
            int iter = casesTried > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) casesTried;
            int time = elapsedMs > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) elapsedMs;
            info = new Data.Information(iter, time);
            return ratu;
        }
        long elapsedMs = (System.nanoTime() - startTime) / 1_000_000L;
        int iter = casesTried > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) casesTried;
        int time = elapsedMs > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) elapsedMs;
        info = new Data.Information(iter, time);

        return null;
    }

    // Brute-Force algorithm
    private static boolean iteration(int colorIdx, Data.ListColor papan, Data.Queen ratu) {
        if (colorIdx >= papan.colors.size()) { 
            // pengecekan apakah penempatan ratu benar
            casesTried++;
            if (CheckQueen(ratu)) {
                return true;
            }
            return false;
        }
        
        Data.Color currentColor = papan.colors.get(colorIdx);

        // mencoba semua kemungkinan koordinat warna.
        for (int i = 0; i < currentColor.position.size(); i++) {
            ratu.position.add(currentColor.position.get(i));

            // cek warna berikutnya
            boolean success = iteration(colorIdx + 1, papan, ratu);

            if (success) return true; // Jika ketemu

            // jika koordinat warna tersebut salah, hapus dan ganti koordinat baru
            ratu.position.remove(ratu.position.size() - 1);
        }

        // Jika tidak ketemu
        return false;
    }

    private static boolean CheckQueen(Data.Queen ratu){
        if(ratu == null || ratu.position == null || ratu.position.isEmpty()){
        return false;
        }

        int queenCounts = ratu.position.size();
        for(int i = 0; i < queenCounts; i++){
        Data.Coordinat currentQueen = ratu.position.get(i);
        int x = currentQueen.x;
        int y = currentQueen.y;
            for(int j = i + 1; j <ratu.queens;j++){
                Data.Coordinat checkQueen = ratu.position.get(i);
                int x2 = checkQueen.x;
                int y2 = checkQueen.y;
                if(!ListCheck(x,y,x2,y2)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean ListCheck(int x1, int y1, int x2, int y2){
        if(x1 == x2 || y1 == y2){ // terletak baris dan kolom sama
            return false;
        }
        if(Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1){ 
            // terletak disekitar dengan jarak satu kotak
            return false; 
        }
        return true;
    }
}