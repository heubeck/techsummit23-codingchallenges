# #3 Java Minimalist

## Specification

The task is to sort the words of a given text by a defined weight:

* A 'word' is defined as the group of characters not separated by whitespace, dash-separated characters (like `dash-separated`) shall be considered as one word.
* The weight of a word is the sum of its characters ascii value (`aBc` = 98 + 66 + 99 = 263)
* Punctuation characters (`.`, `,`, `?`, `!` and so on) shall be ignored
* The words shall be sorted descending by their weight and outputted as distinct list as follows:

```text
<heavy word>|<weight>
<light word>|<weight>
```

## Implementation

Please implement your solution by only changing the [Challenge.java](Challenge.java) file.
For testing your solution to fulfill the spec, just run the `run.sh` script.

## Scoring

For rating the different solutions, the file size of `Challenge.java` will be measured under all solutions fulfilling the spec.
Smaller files are better, there are no rules other than defined here.
