import java.util.ArrayList;
import java.util.HashMap;

public class BankBalance {
    public static void main(String[] args) {

    }

    public int solution(int[] A, String[] D) {
        HashMap<String, ArrayList<Integer>> monthlyPayments = new HashMap<>();
        int balance = 0;
        for (int i = 0; i < A.length; i++) {
            balance += A[i];
            if (A[i] < 0 ) {
                String month = D[i].substring(5,7);
                if (monthlyPayments.containsKey(month)) {
                    monthlyPayments.get(month).add(A[i] * -1);
                } else {
                    ArrayList<Integer> paymentList = new ArrayList<>();
                    paymentList.add(A[i]* -1);
                    monthlyPayments.put(month, paymentList);
                }
            }
        }
        int totalFee = 5 * 12;
        int feeFreeMonths = 0;
        for (ArrayList<Integer> payments : monthlyPayments.values()) {
            if (payments.size() >= 3) {
                int totalPayments = 0;
                for (Integer payment : payments) {
                    totalPayments += payment;
                }
                if (totalPayments >= 100) {
                    feeFreeMonths++;
                }
            }
        }
        System.out.println(monthlyPayments);
        totalFee = totalFee - (feeFreeMonths * 5);
        return balance - totalFee;
    }

}
