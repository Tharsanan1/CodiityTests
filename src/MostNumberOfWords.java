public class MostNumberOfWords {
    public static void main(String[] args) {

    }

    public int solution(String S) {
        String[] sentences = S.split("[.!?]");
        int max = 0;
        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            int wordsCount = 0;
            for (String word : words) {
                if (word.trim().length() > 0) {
                    wordsCount++;
                }
            }
            if (wordsCount > max) {
                max = wordsCount;
            }
        }
        return max;
    }
}
