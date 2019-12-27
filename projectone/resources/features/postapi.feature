Feature: POST api Test with JSON 
Scenario Outline: post data
Given The post endpoint "http://qainterview.merchante-solutions.com:3030/"
And Post content type "application/json; charset=utf-8"
Then Send new <posts>
And Update post
Examples:
            |posts|
            |1200|
            |1400|
            |1600|
            |1800|