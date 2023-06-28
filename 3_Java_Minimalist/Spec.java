///usr/bin/env jbang "$0" "$@" ; exit $?
//JAVA 17+
//SOURCES Challenge.java
//FILES spec.properties
//DEPS org.assertj:assertj-core:3.24.2

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

class Spec {

    public static void main(String[] args) throws IOException{
        var properties = new Properties();
        properties.load(Spec.class.getResourceAsStream("/spec.properties"));

        var challenge = new Challenge();

        properties.entrySet().stream()
            .filter(e -> e.getKey().toString().endsWith("input"))
            .forEach(e -> {
                var result = challenge.weightedSort(e.getValue().toString());
                var resultLines = result.lines().toList();

                var expected = properties.getProperty(e.getKey().toString().replace("input", "expected"));
                var expectedLines = Arrays.asList(expected.split("\s+"));
                assertThat(resultLines).containsExactlyElementsOf(expectedLines);
            });

        System.out.println("📯 It's a match! 🐝📝");
    }
}
