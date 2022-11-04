Feature: USER api Test with JSON 
Scenario Outline: user data
Given The user endpoint "http://qainterview.merchante-solutions.com:3030/"
And User content type "application/json; charset=utf-8"
Then Send user <users>
And Update user
Examples:
            |users|
            |200|
            |300|
            |400|
            |500|