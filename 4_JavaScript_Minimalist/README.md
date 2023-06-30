# #4 JavaScript Minimalist

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

It is disallowed to use any third party dependencies. This is why no package management is given. Everything natively
supported by the node version described in the [.nvmrc](.nvmrc) is allowed to use.

## Implementation

Please implement your solution by only changing the `weightedSort` implementation in [challenge.mjs](challenge.mjs)
file. You can change whatever you want in there, but the file **must** export a function named `weightedSort` that
accepts a string and returns a string.

For testing your solution to fulfill the spec, just run `run.sh`.

## Scoring

For rating the different solutions, the file size of [challenge.mjs](challenge.mjs) will be measured under all solutions
fulfilling the spec.

Smaller files are better.

The usage of third party dependencies is forbidden, every solution using any will be disqualified.
Splitting the implementation amongst files and just import the solution inside [challenge.mjs](challenge.mjs) is
creative, but also forbidden.

There are no rules other than defined here.
