# #5 Kotlin Refactoring

The codes in [Main.kt](./src/main/kotlin/com/mediamarktsaturn/techsummit23/Main.kt) looks very java-like :-).

Can you refactor them to a more kotlin "way"?

## Verify the your changes

### Local

If a JDK >= 17 is installed on the machine, run this

```shell
cd 5_Kotlin_Refactoring
./gradlew test
```

to verify the changes.

### Remote

On push, a GitHub workflow (named: `Verify #5 Kotlin Refactoring`) will run and run the test cases on any changes in this folder.
