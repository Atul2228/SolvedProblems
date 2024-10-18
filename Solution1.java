import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println("Rows and cols");
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int col = input.nextInt();
        int[][] houses = new int[row][col];
        System.out.println("enter values");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int val = input.nextInt();
                houses[i][j] = val;

            }

        }

        brideHuntingForSam(houses);

    }

    static void brideHuntingForSam(int[][] houses) {

        int max = Integer.MIN_VALUE;
        HashMap<Integer, ArrayList<ArrayList<Integer>>> arr = new HashMap<>();
        int matchedQualites = 0;
        for (int row = 0; row < houses.length; row++) {
            for (int col = 0; col < houses[row].length; col++) {
                if (row == 0 && col == 0) {
                    continue;
                }

                if (houses[row][col] == 1) {
                    if (col != 0) {
                        // left
                        if ((row == 0) && (col == 1)) {
                            continue;

                        } else {
                            if (houses[row][col - 1] == 1) {
                                matchedQualites++;
                            }
                        }

                    }
                    if (col != houses[row].length - 1) {
                        // right
                        if (houses[row][col + 1] == 1) {
                            matchedQualites++;
                        }
                    }
                    if (row != 0) {
                        // up
                        if (row != 1 && col != 0) {
                            if (houses[row - 1][col] == 1) {
                                matchedQualites++;
                            }
                        }

                        if (col != houses[row].length - 1) {
                            // right up diogonal
                            if (houses[row - 1][col + 1] == 1) {
                                matchedQualites++;
                            }

                        }

                        if (col != 0) {
                            // left up diogonal
                            if (row != 1 && col != 1) {
                                if (houses[row - 1][col - 1] == 1) {
                                    matchedQualites++;
                                }
                            }
                        }
                    }

                    if (row != houses.length - 1) {

                        // down
                        if (houses[row + 1][col] == 1) {
                            matchedQualites++;
                        }

                        if (col != houses[row].length - 1) {
                            // right down diogonal
                            if (houses[row + 1][col + 1] == 1) {
                                matchedQualites++;
                            }

                        }
                        if (col != 0) {
                            // left down diogonal
                            if (houses[row + 1][col - 1] == 1) {
                                matchedQualites++;
                            }
                        }
                    }
                    ArrayList<Integer> addresses = new ArrayList<>();
                    addresses.add(row + 1);
                    addresses.add(col + 1);
                    if (matchedQualites > max) {
                        max = matchedQualites;
                    }
                    arr.putIfAbsent(matchedQualites, new ArrayList<>());
                    arr.get(matchedQualites).add(addresses);

                    matchedQualites = 0;

                }

            }

        }

        int minDistance = Integer.MAX_VALUE;
        if(arr.isEmpty()){
            System.out.println("NO Suitable girl found");
            return;
        }
       
        int index1 = 0, index2 = 0;
        for (ArrayList<Integer> address : arr.get(max)) {
            int a = address.get(0);
            int b = address.get(1);
            if ((a + b) < minDistance) {
                minDistance = a + b;
                index1 = a;
                index2 = b;

            } else if ((a + b) == minDistance) {
                System.out.println("polygamy not allowed");
            }

        }
        System.out.println(index1 + ":" + index2 + ":" + max);

    }

}
