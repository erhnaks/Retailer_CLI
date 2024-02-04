# **Documentation**

## **Task 1:**

## GitHub link for development cycle:

[Retailer_CLI - GitHub](https://github.com/erhnaks/Retailer_CLI)

### Branches:

- main
- development
- feature-class
- feature-retailer-business-logic-implementation
- bug-testcase-in-infinite-loop

## Initial Tests Failures:

In order to begin developing test scenarios, I attempted to run the tests only to have them fail. 
I discovered that one test was stuck in an infinite loop, which was the reason tests were failing to finish. 
In order to match the date == Thursday, I also had to adjust another test that involved adding one day to the current date.

Initial Test:

`LocalDate thursday = LocalDate.now();
while (!thursday.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
thursday.plusDays(1);
}`

After the infinite loop problem was fixed:

`LocalDate thursday = LocalDate.now();
while (!thursday.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
thursday = thursday.plusDays(1);
}`

Screenshot of the initial test run:

![Screenshot 2024-02-03 122703](https://github.com/erhnaks/String_Calculator/assets/97620234/1ad7ec89-3126-4958-af48-51d3b2e25738)

Another problem with the test testDiscount2For1OnThursdays() is that it was failing because the sum seemed off. 
Would you kindly look at the below? The original test case was manually corrected and commented out. 
Kindly inform me if this was incorrect.

Comparison of the corrected version with the initial test receipt (NB: PLEASE SEE COMMENT):

![image](https://github.com/erhnaks/String_Calculator/assets/97620234/66dcbb76-aab7-4a6e-a1a9-11fa3efe8d3c)

### Running the tests:

![Screenshot 2024-02-04 111114](https://github.com/erhnaks/String_Calculator/assets/97620234/3b43c803-7c5e-4879-b030-7ef98960cd39)

### CLI test-run:

![Screenshot 2024-02-04 112456](https://github.com/erhnaks/String_Calculator/assets/97620234/3990f8b5-de8c-4225-877f-3ff4875e1840)

Author: Erhan Aksu