Feature: API Test
Scenario: Get data
Given The Endpoint "http://qainterview.merchante-solutions.com:3030/"
And Content type "application/json; charset=utf-8"
Then Get all posts
And Retrieve all comments
And Fetch all users
But Check response header