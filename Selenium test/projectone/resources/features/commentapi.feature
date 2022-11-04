Feature: COMMENT api Test with JSON 
Scenario Outline: comment data
Given The comment endpoint "http://qainterview.merchante-solutions.com:3030/"
And Comment content type "application/json; charset=utf-8"
Then Send comment <comments>
And Update comment
Examples:
            |comments|
            |900|
            |1000|
            |1100|
            |1200|