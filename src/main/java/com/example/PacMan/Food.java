package com.example.PacMan;

public class Food {
    public final int boardIndex = 4;
    private final int[][] coordinatesList = { { 7, 6 }, { 1, 2 }, { 11, 12 }, { 11, 3 }, { 3, 12 } };
    private int currentIndex = 0;
    private boolean isThere = true;
    private int strike = 0;

    public int[] getcoordinate() {
        strike += 1;
        if (strike >= 10) {
            isThere = false;
        }
        if (strike >= 20) {
            currentIndex = (currentIndex + 1) % coordinatesList.length;
            isThere = true;
            strike = 0;
        }
        // System.out.println(isThere + String.valueOf(strike));
        // System.out.println("Food" + coordinatesList[currentIndex][0] + "." +
        // coordinatesList[currentIndex][1]);
        return (coordinatesList[currentIndex]);
    }

    public boolean getIsThere() {
        return isThere;
    }

    public void eaten() {
        isThere = false;
        strike = 10;
    }
}
