# #3 Java Minimalist

## Specification

The task is to sort the words of a given text by a defined weight:

* A 'word' is defined as the group of characters not separated by whitespace, dash- or apostrophe-separated characters (like `dash-separated` or `Thomas's`) shall be considered as one word (dashes and apostrophes have no weight).
* The weight of a word is the sum of its characters ascii value (`aBc` = 98 + 66 + 99 = 263)
* Punctuation characters (`.`, `,`, `?`, `!` and so on) shall be ignored
* The words shall be sorted descending by their weight and outputted as distinct list as follows:
  ```text
    <heavy word>|<weight>
    <light word>|<weight>
  ```
  If two words have exactly the same weight, they should be sorted descending by the ascii codes of their first character (on equality the second char, and so on).

It is disallowed to use any third party dependencies.

## Implementation

Please implement your solution by only changing the [Challenge.java](Challenge.java) file.
For testing your solution to fulfill the spec, just run the `run.sh` script after installing [jbang](https://www.jbang.dev/).

## Scoring

For rating the different solutions, the file size of `Challenge.java` will be measured under all solutions fulfilling the spec.
Smaller files are better, there are no rules other than defined here.
