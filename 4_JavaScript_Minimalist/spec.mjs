import {weightedSort} from './challenge.mjs';
import specFile from './spec.json' assert {type: 'json'};

const arrayShallowEquals = (toCheck, expected) => toCheck.length === expected.length && toCheck.every((value, index) => value === expected[index]);

const passed = specFile.specifications.reduce((accumulator, current) => {
  const result = weightedSort(current.input).split(/\r\n|\r|\n/);
  return accumulator && arrayShallowEquals(result, current.expected);
}, true)

if (passed) {
  console.log("📯 It's a match! 🐝📝");
} else {
  console.error('⚠️ You missed something :(. Try again! ❌');
  process.exit(1);
}
