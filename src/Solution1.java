import java.util.HashMap;
import java.util.Random;

public class Solution1 {
    public static void main(String[] args) {

    }
    class Tree {
        public int x;
        public Tree l;
        public Tree r;
    }
    public int solution(Tree T) {
        // write your code in Java SE 8
        int len = 0;
        if (T == null) {
            return 0;
        }
        else {
            if (T.l != null) {
                len = traverseFroZigZag(T.l, true, 0);
            }
            if (T.r != null) {
                int lenR = traverseFroZigZag(T.r, false, 0);
                if (lenR > len) {
                    len = lenR;
                }
            }
        }
        return len;
    }

    private int traverseFroZigZag(Tree t, boolean left, int length) {
        int leftLen = 0;
        int rightLen = 0;
        boolean flag = false;
        if (t.l != null) {
            flag = true;
            if (left) {
                leftLen = traverseFroZigZag(t.l, true, length);
            } else {
                leftLen = traverseFroZigZag(t.l, true, length + 1);
            }
        }
        if (t.r != null) {
            flag = true;
            if (left) {
                rightLen = traverseFroZigZag(t.r, false, length + 1);
            } else {
                rightLen = traverseFroZigZag(t.r, false, length);
            }
        }
        if (!flag) {
            return length;
        }
        if (leftLen > rightLen) {
            return leftLen;
        } else {
            return rightLen;
        }
    }

}
