import java.util.*;

public class MinMaxGap {
    public static void main(String[] args) {
        int[] arr = createRandomArray(-1000, 1000, 3000);
        long start = System.currentTimeMillis();
        MinMaxGap s = new MinMaxGap();
        s.solution1(3000, arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " " + countHit +  " " + countHitEffectiveness + " " + countMissed + " " + countMissedEffectiveness);
    }
    public int solution(int[] A) {
        boolean flag = true;
        int length = 0;
        int currentValue = A[0];
        int nextIndex = currentValue;
        while (flag) {
            length++;
            if (currentValue == -1) {
                break;
            } else {
                currentValue = A[nextIndex];
                nextIndex = currentValue;

            }
        }
        return length;
    }

    public int solution1(int K, int[] A) {
        int length = A.length;
        int count = 0;

        for (int i = 0; i < length; i++) {
            int min = A[i];
            int max = A[i];
            for (int j = i; j < length; j++) {
                if (min > A[j]) {
                    min = A[j];
                }
                if (max < A[j]) {
                    max = A[j];
                }
                if (max - min <= K) {
                    count++;
                } else {
                    count += length - j - 1;
                    break;
                }
            }
        }
        return count;
    }

    public int solution(int K, int[] A) {
        int length = A.length;
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < length; i++) {
            if (!flag) {
                break;
            }
            for (int j = i; j < length; j++) {
                MinMax minMax = optimisedMinMaxFind(A, i, j);
                int min = minMax.getMin();
                int max = minMax.getMax();
                if (max - min <= K) {
                    count++;
                    if (count >= 1000000000) {
                        flag = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return count;
    }

    private static class MinMax {
        int min;
        int max;

        public MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

    private MinMax findMinMax(int[] A, int start, int end) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = start; i <= end; i++) {
            if (A[i] < min) {
                min = A[i];
            }
            if (A[i] > max) {
                max = A[i];
            }
        }
        return new MinMax(min, max);
    }

    private static HashMap<Integer, HashMap<Integer, MinMax>> minMaxMap = new HashMap<>();
    private static int countHit = 0;
    private static int countHitEffectiveness = 0;
    private static int countMissed = 0;
    private static int countMissedEffectiveness = 0;
    private MinMax optimisedMinMaxFind (int[] A, int start, int end) {
        MinMax minMax;
        if (minMaxMap.containsKey(start)) {
            if (minMaxMap.get(start).containsKey(end - 1)) {
//                System.out.println("Hit : " + start + " " + end);
                countHit++;
                countHitEffectiveness += end - start;
                minMax = minMaxMap.get(start).get(end - 1);
                if (A[end] < minMax.getMin()) {
                    minMax.setMin(A[end]);
                }
                if (A[end] > minMax.max) {
                    minMax.max = A[end];
                }
                HashMap map = new HashMap();
                map.put(end, minMax);
                minMaxMap.put(start, map);
                return minMax;
            }
        }

        if (minMaxMap.containsKey(start-1) && minMaxMap.get(start-1).containsKey(end)) {
            countHit++;
            countHitEffectiveness += end - start;
            minMax = minMaxMap.get(start-1).get(end);
            if (A[start-1] == minMax.getMin() || A[start-1] == minMax.getMax()) {
                minMax = findMinMax(A, start, end);
            }
            HashMap map = new HashMap();
            map.put(end, minMax);
            minMaxMap.put(start, map);
            return minMax;
        }
        countMissed ++;
        countMissedEffectiveness += (end-start);
        minMax = findMinMax(A, start, end);
        HashMap map = new HashMap();
        map.put(end, minMax);
        minMaxMap.put(start, map);
        return minMax;

    }


    private static int[] createRandomArray(int start, int end, int length) {
        Random r = new Random();
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(end-start) + start;
        }
        return arr;
    }
}
