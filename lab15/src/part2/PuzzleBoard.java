package part2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PuzzleBoard {
    int[][] board;
    public PuzzleBoard() {
        final int dimensions = 5;
        board = new int[dimensions][dimensions];
        Random random = new Random();
        for(int i = 0; i < dimensions; i++) {
            for(int j = 0; j < dimensions; j++) {
                board[i][j] = random.nextInt(15);
            }
        }
    }

    public int detectEquivelantAdjacentPairs() {
        int pairs = 0;
        for (int[] ints : board) {
            for (int j = 1; j < ints.length; j++) {
                if (ints[j - 1] == ints[j]) {
                    pairs++;
                }
            }
        }
        return pairs;
    }

    public Map<Integer, Integer> checkDuplicates() {
        HashMap<Integer, Integer> duplicates = new HashMap<>();
        for (int[] i : board) {
            for(int j : i) {
                duplicates.put(j, duplicates.get(j) + 1);
            }
        }
        return duplicates;
    }

    public void shiftRight(int row, int shift) {
        for(int i = 0; i < board[row].length; i++) {
            int len = board[row].length;
            int temp = board[row][(i + shift) % len];
            board[row][(i + shift) % len] = board[row][i];
            board[row][i] = temp;
        }
    }

    public void shiftUp(int col, int shift) {
        for(int i = 0; i < board.length; i++) {
            int len = board.length;
            int temp = board[(i + shift) % len][col];
            board[(i + shift) % len][col] = board[i][col];
            board[i][col] = temp;
        }
    }

    public void reverseRow(int row) {
        int[] row_arr = new int[5];
        row_arr = board[row];
        int midpoint = Math.floorDiv(board[row].length, 2);
        for(int i = 0; i < midpoint; i++) {
            int dist = midpoint - i;
            int left = midpoint;
            int right = midpoint + dist;
            int temp = board[row][right];
            left = board[row][right];
            right = board[row][left];
        }
    }
    public void reverseCol(int col) {
        int[] col_arr = new int[5];
        int midpoint = Math.floorDiv(board.length, 2);
        int index = 0;
        for(int[] i : board) {
            col_arr[index] = i[col];
            index++;
        }
        for(int i = 0; i < midpoint; i++) {
            int dist = midpoint - i;
            int left = midpoint;
            int right = midpoint + dist;
            int temp = board[right][col];
            col_arr[left] = board[right][col];
            col_arr[right] = board[left][col];
        }
        index = 0;
        for(int[] i : board) {
            i[col] = col_arr[index];
            index++;
        }
    }
}
