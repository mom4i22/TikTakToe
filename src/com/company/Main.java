package com.company;

import java.util.*;

public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char gameBoard[][] = {{' ', '|', ' ', '|', ' '},
                {'-', '-', '-', '-', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '-', '-', '-', '-'},
                {' ', '|', ' ', '|', ' '}};

        printBoard(gameBoard);

        while (true) {
            System.out.println("Pick where to place an 'X' from spot 1-9:");

            int pPos = sc.nextInt();
            while (playerPositions.contains(pPos) || cpuPositions.contains(playerPositions)) {
                System.out.println("Position is taken, choose a new one: ");
                pPos = sc.nextInt();
            }
            pickPlace(gameBoard, pPos, "person");
            String result = showWinner();
            if (result.length() > 0) {
                printBoard(gameBoard);
                System.out.println(result);
                break;
            }

            Random random = new Random();
            int cPos = random.nextInt(9) + 1;
            while (playerPositions.contains(cPos) || cpuPositions.contains(cPos)) {
                cPos = random.nextInt(9) + 1;

            }
            pickPlace(gameBoard, cPos, "computer");

            printBoard(gameBoard);
            result = showWinner();
            if (result.length() > 0) {
                printBoard(gameBoard);
                System.out.println(result);
                break;
            }
        }

        }



    public static void printBoard(char gameBoard[][]) {
        for (char[] row : gameBoard) {
            for (char column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

    public static void pickPlace(char gameBoard[][], int position, String player) {
        char symbol = 'X';
        if (player.equals("person")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (player.equals("computer")) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }

    public static String showWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List firstCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List lastCol = Arrays.asList(3, 6, 9);
        List leftDiagonal = Arrays.asList(1, 5, 9);
        List rightDiagonal = Arrays.asList(7, 5, 3);
        List<List> winCon = new ArrayList<>();
        winCon.add(topRow);
        winCon.add(midRow);
        winCon.add(botRow);
        winCon.add(firstCol);
        winCon.add(midCol);
        winCon.add(lastCol);
        winCon.add(leftDiagonal);
        winCon.add(rightDiagonal);

        for (List l : winCon) {
            if (playerPositions.containsAll(l)) {
                return "Person wins!";
            } else if (cpuPositions.containsAll(l)) {
                return "Computer wins!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Draw!";
            }
        }

        return "";
    }

}
