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
            return o.weight() - weight();
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
