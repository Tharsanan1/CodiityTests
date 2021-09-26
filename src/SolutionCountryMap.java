import java.util.HashMap;
import java.util.HashSet;

public class SolutionCountryMap {
    private static HashMap<Integer, HashMap<Integer, String>> map = new HashMap<>();
    private static int lenX;
    private static int lenY;
    public int solution(int[][] A) {
        lenY = A[0].length;
        lenX = A.length;
        System.out.println(lenX + " " + lenY);
        String countryName = 0 + " " + 0;
        int value = A[0][0];
        traverse(A, 0, 0, countryName, value);
        HashSet<String> set = new HashSet<>();
        for (HashMap<Integer, String> hm : map.values()) {
            for (String s : hm.values()) {
                set.add(s);
            }
        }
        return set.size();

    }

    private void traverse(int[][] A, int x, int y, String countryName, int value) {
        System.out.println("kkk : " + x + " " + y + " country : " + countryName + " value " + value + map);
        if (map.containsKey(x) && map.get(x).containsKey(y)) {
            if (A[x][y] == value && !map.get(x).get(y).equals(countryName)) {
                map.get(x).put(y, countryName);
                if (x <= lenX-1 && y + 1 <= lenY - 1) {
                    traverse(A, x, y+1, countryName, value);
                }
                if (x <= lenX-1 && y - 1 <= lenY - 1 && y-1 >= 0) {
                    traverse(A, x, y-1, countryName, value);
                }
                if (x + 1 <= lenX-1 && y <= lenY - 1) {
                    traverse(A, x + 1, y, countryName, value);
                }
                if (x - 1 <= lenX-1 && x-1 >=0 && y <= lenY - 1) {
                    traverse(A, x-1, y, countryName, value);
                }
            }
            System.out.println("hit");
            return;
        } else {
            if (A[x][y] == value) {
                if (map.containsKey(x)) {
                    map.get(x).put(y, countryName);
                } else {
                    HashMap<Integer, String> mapLoc = new HashMap<>();
                    mapLoc.put(y, countryName);
                    map.put(x, mapLoc);
                }
                if (x <= lenX-1 && y + 1 <= lenY - 1) {
                    traverse(A, x, y+1, countryName, value);
                }
                if (x <= lenX-1 && y - 1 <= lenY - 1 && y-1 >= 0) {
                    traverse(A, x, y-1, countryName, value);
                }
                if (x + 1 <= lenX-1 && y <= lenY - 1) {
                    traverse(A, x + 1, y, countryName, value);
                }
                if (x - 1 <= lenX-1 && x-1 >=0 && y <= lenY - 1) {
                    traverse(A, x-1, y, countryName, value);
                }
            } else {
                countryName = x + " " + y;
                value = A[x][y];
                if (map.containsKey(x)) {
                    map.get(x).put(y, countryName);
                } else {
                    HashMap<Integer, String> mapLoc = new HashMap<>();
                    mapLoc.put(y, countryName);
                    map.put(x, mapLoc);
                }
                if (x <= lenX-1 && y + 1 <= lenY - 1) {
                    traverse(A, x, y+1, countryName, value);
                }
                if (x <= lenX-1 && y - 1 <= lenY - 1 && y-1 >= 0) {
                    traverse(A, x, y-1, countryName, value);
                }
                if (x + 1 <= lenX-1 && y <= lenY - 1) {
                    traverse(A, x + 1, y, countryName, value);
                }
                if (x - 1 <= lenX-1 && x-1 >=0 && y <= lenY - 1) {
                    traverse(A, x-1, y, countryName, value);
                }
            }
        }
    }

}
