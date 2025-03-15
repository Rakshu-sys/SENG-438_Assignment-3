**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:      |     |
| -------------- | --- |
| Student Names: | Rakshita Suri    |
|                | Mohit Kaila |
|                | Shalin Wickremeratna  |
|                | Okibe Abang    |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

This assignment focusses on exploring unit tests even more and increasing the coverage of the unit tests. It is an extension to Assignment 2. We woek with the same test suite using JFreeChart. We focus on testing the same test classes: Range and DataUtilities. We try to increase the coverage by increasing the developing more unit tests and making sure that the existing ones improve their coverage. We document our test strategy for the new unit tests. We use EclEmma as our coverage tool to observe the metrics. The coverages we try to achieve are: 90% statement coverage, 70% branch coverage, and 60% condition coverage. EclEmma is an additional software tool which is avialable via Eclipse. Just like the previous assignment, we use Eclipse for this assignment to perform testing and improve our testing skills. 

# 2 Manual data-flow coverage calculations for X and Y methods

For the calculateColumnTotal and calculateRowTotal methods, we manually traced the execution of each test case to determine the data flow coverage. We identified the def-use pairs (definition-use pairs) and calculated whether each pair was exercised by the test cases. For example, in calculateColumnTotal, we examined the flow from the parameter validRows through the loops and conditional statements. Each pair where a variable is defined and later used was assessed to determine if our tests triggered both the definition and the use of the variable. Similarly, in calculateRowTotal, we traced the data flow from the validCols parameter, checking if it was used correctly across the loops and ensuring that the correct values were summed.

After analyzing the def-use pairs, we determined which conditions and branches were not covered in the initial tests. For instance, some tests failed to cover the case where validRows or validCols is empty. Based on this, we designed additional tests to ensure these critical paths were covered.

# 3 A detailed description of the testing strategy for the new unit test

Our strategy was focused on improving code coverage by increasing statement, branch, and condition coverage. We began by analyzing the current tests to identify the areas where coverage was lacking. For instance, the tests did not cover certain edge cases, such as handling empty arrays or arrays with null values. We used EclEmma to track our progress and refine our tests based on the feedback provided by the coverage tool.

Our test strategy included:

Adding new unit tests to ensure that all paths, especially the conditional branches, were tested.
Focusing on scenarios where edge cases might break the functionality, such as null inputs, boundary conditions (e.g., empty arrays), and mismatched array lengths.
Regularly running tests and reviewing coverage metrics to identify any uncovered code.
Using pair programming to analyze the code and discuss test strategies, ensuring we wrote tests that both fulfilled coverage criteria and validated correct behavior.

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Test for empty row/column handling:

This test was created to handle edge cases where the row or column is empty. We added tests to verify that both the calculateColumnTotal and calculateRowTotal methods correctly return 0 when an empty row or column is provided.
Impact: It covered scenarios where the code was not handling edge cases, thus improving both statement and branch coverage.
Test for null values:

We designed tests to ensure that the calculateColumnTotal method handles null values in the columns and calculateRowTotal handles null values in rows correctly. These tests checked if the total calculation ignored null values and didn't throw exceptions.
Impact: It addressed uncovered code paths related to handling null data, improving condition and branch coverage.
Test for different row/column lengths:

We wrote a test that checks for rows with different lengths in calculateRowTotal, ensuring that the code correctly calculates the sum when there is a mismatch in the number of columns across rows.
Impact: This test improved branch and condition coverage for scenarios where the row and column dimensions don't match.
Test for large datasets:

We created a test for large arrays to check how calculateColumnTotal handles larger data sets and ensures that the sum is correct. This is especially useful for verifying performance.
Impact: This test improved statement coverage by covering more lines of the code, especially loops over large data sets.
Test for invalid indices:

This test checks the handling of invalid indices in calculateColumnTotal and calculateRowTotal, ensuring that the methods return the expected results or fail gracefully when given invalid row or column indices.
Impact: It increased coverage by testing error handling and edge cases, improving both condition and branch coverage.

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

For the DataUtilities class, we achieved:

Statement Coverage: 98.6%
Branch Coverage: 95.7%
Method Coverage: 93.1%

We used EclEmma to track the coverage and identified areas where we needed to improve. We focused on improving coverage for edge cases, like handling null values and empty arrays. As a result, we were able to achieve the required coverage for the calculateColumnTotal and calculateRowTotal methods.

For the RangeTest class, we achieved:

Statement Coverage: 100%
Branch Coverage: 100%
Condition Coverage: 100%

We applied a similar strategy to improve the coverage for RangeTest. We developed additional test cases to ensure edge cases like null values and varying lengths of ranges were covered. Our tests targeted all branches of the logic in the Range class, and we ensured that all conditions were evaluated to both true and false. Additionally, we verified that the proper exceptions were thrown in certain scenarios, which significantly improved coverage.

Screenshots from EclEmma are included in the report, showing the areas marked in green (covered) and red (uncovered) for each method in DataUtilities and RangeTest. This allows us to demonstrate the areas where we improved coverage and where additional testing might be required in the future.

# 6 Pros and Cons of coverage tools used and Metrics you report



# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation

Advantages of requirements-based test generation:

- Focuses on testing the functionality according to the requirements, ensuring that the software behaves as expected in real-world scenarios.

- Easier to correlate test cases with user stories or business needs.

Disadvantages of requirements-based test generation:

- May miss certain edge cases that are not explicitly mentioned in the requirements.

- Can lead to incomplete test coverage, as only the specified requirements are covered.

Advantages of coverage-based test generation:

- Provides more comprehensive test coverage by focusing on ensuring that all parts of the code are tested.

- Helps detect areas of the code that are not adequately tested, even if they are not part of the original requirements.

Disadvantages of coverage-based test generation:

- Focuses on code paths rather than user behavior, so it might test redundant scenarios that don’t contribute to the actual quality of the software.

- It can be time-consuming to ensure 100% coverage, leading to diminishing returns for the effort spent on coverage.

# 8 A discussion on how the team work/effort was divided and managed

Two members of the team worked on testing the Range class and the other two focussed on developing tests for Data Utilities class. We did regular check-ins to make sure we are able to submit the assignment on time. We used pair programming to develop new unit tests and improve the coverage. Most importantly, we helped each other out whenever we were stuck with something or needed an advice. We reviewed both test classes regularly by collaborating together in order to make sure that the quality of testing was what we had expected. This made sure that every member of the group collaborated and contributed equally towards the completion of this assignment. We were able to manage the assignment efficiently using proper communication. 

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

The initial challenge was getting familiar with EclEmma because two of our team members had issues with integrating that tool. However, that issue was overcome by collective effort to figure out what was wrong. Secondly, it was difficult to come up with the new unit tests that focussed on the optimal coverage criteria. We had to analyze the code properly to understand what were missing from the initial tests. The use of pair programming made it quite easy. One member could analyze the code and notice the missing coverage while the other one could work on developing tests that fulfill the coverage. This assignment was slightly more challenging than the previous assignment as we only focussed on comparing the test cases against the required output. However, this assignment needed a deeper understanding of the code structure and coverage criteria. We learned a lot more about testing and got hands-on experience through this assignment. We also learned how to use the coverage tools. Additionally, this assignment also taught us the importance of collaboration and pair-programming.  

# 10 Comments/feedback on the lab itself

The lab was a knowledgable experience with clearly set instructions that were easy to follow. By the end of it, we were very comfortable with unit testing. Even though we faced some challenges, they became learning experiences for us. The goal of the lab was also clearly mentioned and achievable for us. 
