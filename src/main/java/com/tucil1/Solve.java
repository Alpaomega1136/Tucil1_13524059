package com.tucil1;

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
        long startTime = System.nanoTime();
        if(iteration(papan, ratu)){
            long elapsedMs = (System.nanoTime() - startTime) / 1_000_000L;
            int iter = casesTried > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) casesTried;
            int time = elapsedMs > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) elapsedMs;
            info = new Data.Information(iter, time);
            return ratu;
        }
        info = null;
        return null;
    }

    // Brute-Force algorithm 
    private static boolean iteration(Data.ListColor papan, Data.Queen ratu){
        return true;
    }

    private static boolean CheckQueen(Data.Queen ratu){
        if(ratu == null || ratu.position == null || ratu.position.isEmpty()){
        return false;
        }
        for(int i = 0; i < ratu.queens; i++){
        Data.Coordinat currentQueen = ratu.position.get(i);
        int x = currentQueen.x;
        int y = currentQueen.y;
            for(int j = i + 1; i <ratu.queens;j++){
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
        if(((x2 - 1 < x1) && (x1 < x2 + 1)) && ((y2 - 1 < y1) && (y1 < y2 + 1))){ 
            // terletak disekitar dengan jarak satu kotak
            return false; 
        }
        return true;
    }
}