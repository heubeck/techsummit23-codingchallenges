import java.util.Arrays;
import java.util.stream.Collectors;

public class Challenge {

    // TODO: Make empty before publish
    String weightedSort(String text) {
        var cleanedText = text
            .replaceAll("[-']", "")
            .replaceAll("[.,!?]", " ");

        var lines = cleanedText.split("\s+");
        var result = Arrays.stream(lines)
            .distinct()
            .map(l -> new Weighted(l))
            .sorted()
            .map(Weighted::toString)
            .peek(l -> System.out.println("    " + l + " \\"))
            .collect(Collectors.joining(System.lineSeparator()));

        return result;
    }

    record Weighted(String word) implements Comparable<Weighted> {
        @Override
        public int compareTo(Weighted o) {
            var weightDelta = o.weight() - weight();
            if (weightDelta != 0) {
                return weightDelta;
            }
            var length = Integer.min(o.word().length(), word.length());
            for (int i = 0; i < length; i++) {
                var charDelta = o.word().charAt(i) - word.charAt(i);
                if (charDelta != 0) {
                    return charDelta;
                }
            }
            return 0;
        }
        private int weight() {
            return word.chars().sum();
        }
        @Override
        public String toString() {
            return "%s|%s".formatted(word, weight());
        }
    }

}
