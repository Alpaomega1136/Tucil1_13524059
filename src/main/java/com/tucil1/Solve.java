package com.tucil1;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Solve{
    private static long casesTried = 0;
    private static Data.Information info = null;
    public static Consumer<Data.Queen> onUpdateGUI = null;

    public static Data.Information getInfo() {
        return info;
    }

    public static Data.Queen Solve(Data.ListColor papan, Data.Queen ratu){
        if(papan == null || ratu == null){
            return null;
        }
        casesTried = 0;
        info = null;
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
            casesTried++;
            if (casesTried % 1000 == 0 && onUpdateGUI != null) { // Update GUI
                
                Data.Queen snapshot = new Data.Queen(ratu.queens);
                snapshot.position = new ArrayList<>(ratu.position); 
                
                onUpdateGUI.accept(snapshot); 

                try { Thread.sleep(1); } catch (InterruptedException e) {}
            }
            if (CheckQueen(ratu)) {
                return true;
            }
            return false;
        }
        
        Data.Color currentColor = papan.colors.get(colorIdx);

        for (int i = 0; i < currentColor.position.size(); i++) {
            ratu.position.add(currentColor.position.get(i));

            boolean success = iteration(colorIdx + 1, papan, ratu);

            if (success){
                return true;
            }
            ratu.position.remove(ratu.position.size() - 1);
        }
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
            for(int j = i + 1; j < queenCounts ;j++){
                Data.Coordinat checkQueen = ratu.position.get(j);
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