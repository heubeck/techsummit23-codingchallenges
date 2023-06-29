import {weightedSort} from './challenge.mjs';
import specFile from './spec.json' assert {type: 'json'};

const arrayShallowEquals = (toCheck, expected) => {
  if (toCheck.length !== expected.length) {
    console.debug(`Expected result to discover ${expected.length} weighted entries, but got ${toCheck.length}`);
    return false;
  }

  return toCheck.every((value, index) => {
    if (value !== expected[index]) {
      console.debug(`Expected ${expected[index]} on position ${index + 1}, but got ${value} instead.`);
      return false;
    }
    return true;
  });
};

const passed = specFile.specifications.reduce((accumulator, current, index) => {
  console.log(`Checking specification #${index + 1}...`);
  const result = weightedSort(current.input).split(/\r\n|\r|\n/);
  const comparisonResult = arrayShallowEquals(result, current.expected);
  return accumulator && comparisonResult;
}, true)

if (passed) {
  console.log("📯 It's a match! 🐝📝");
} else {
  throw new Error('⚠️ You missed something :(. Try again! ❌');
}
