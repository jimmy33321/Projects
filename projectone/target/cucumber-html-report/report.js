$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("resources/features/userapi.feature");
formatter.feature({
  "line": 1,
  "name": "USER api Test with JSON",
  "description": "",
  "id": "user-api-test-with-json",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 2,
  "name": "user data",
  "description": "",
  "id": "user-api-test-with-json;user-data",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 3,
  "name": "The user endpoint \"http://qainterview.merchante-solutions.com:3030/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "User content type \"application/json; charset\u003dutf-8\"",
  "keyword": "And "
});
formatter.step({
  "line": 5,
  "name": "Send user \u003cusers\u003e",
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "Update user",
  "keyword": "And "
});
formatter.examples({
  "line": 7,
  "name": "",
  "description": "",
  "id": "user-api-test-with-json;user-data;",
  "rows": [
    {
      "cells": [
        "users"
      ],
      "line": 8,
      "id": "user-api-test-with-json;user-data;;1"
    },
    {
      "cells": [
        "200"
      ],
      "line": 9,
      "id": "user-api-test-with-json;user-data;;2"
    },
    {
      "cells": [
        "300"
      ],
      "line": 10,
      "id": "user-api-test-with-json;user-data;;3"
    },
    {
      "cells": [
        "400"
      ],
      "line": 11,
      "id": "user-api-test-with-json;user-data;;4"
    },
    {
      "cells": [
        "500"
      ],
      "line": 12,
      "id": "user-api-test-with-json;user-data;;5"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 9,
  "name": "user data",
  "description": "",
  "id": "user-api-test-with-json;user-data;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 3,
  "name": "The user endpoint \"http://qainterview.merchante-solutions.com:3030/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "User content type \"application/json; charset\u003dutf-8\"",
  "keyword": "And "
});
formatter.step({
  "line": 5,
  "name": "Send user 200",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "Update user",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "http://qainterview.merchante-solutions.com:3030/",
      "offset": 19
    }
  ],
  "location": "usersDetails.the_user_endpoint(String)"
});
formatter.result({
  "duration": 1783991,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "application/json; charset\u003dutf-8",
      "offset": 19
    }
  ],
  "location": "usersDetails.user_content_type(String)"
});
formatter.result({
  "duration": 71495,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 10
    }
  ],
  "location": "usersDetails.send_user(int)"
});
formatter.result({
  "duration": 138880186,
  "status": "passed"
});
formatter.match({
  "location": "usersDetails.update_user()"
});
formatter.result({
  "duration": 97175034,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "user data",
  "description": "",
  "id": "user-api-test-with-json;user-data;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 3,
  "name": "The user endpoint \"http://qainterview.merchante-solutions.com:3030/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "User content type \"application/json; charset\u003dutf-8\"",
  "keyword": "And "
});
formatter.step({
  "line": 5,
  "name": "Send user 300",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "Update user",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "http://qainterview.merchante-solutions.com:3030/",
      "offset": 19
    }
  ],
  "location": "usersDetails.the_user_endpoint(String)"
});
formatter.result({
  "duration": 110710,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "application/json; charset\u003dutf-8",
      "offset": 19
    }
  ],
  "location": "usersDetails.user_content_type(String)"
});
formatter.result({
  "duration": 59414,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "300",
      "offset": 10
    }
  ],
  "location": "usersDetails.send_user(int)"
});
formatter.result({
  "duration": 134884399,
  "status": "passed"
});
formatter.match({
  "location": "usersDetails.update_user()"
});
formatter.result({
  "duration": 115262551,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "user data",
  "description": "",
  "id": "user-api-test-with-json;user-data;;4",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 3,
  "name": "The user endpoint \"http://qainterview.merchante-solutions.com:3030/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "User content type \"application/json; charset\u003dutf-8\"",
  "keyword": "And "
});
formatter.step({
  "line": 5,
  "name": "Send user 400",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "Update user",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "http://qainterview.merchante-solutions.com:3030/",
      "offset": 19
    }
  ],
  "location": "usersDetails.the_user_endpoint(String)"
});
formatter.result({
  "duration": 79427,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "application/json; charset\u003dutf-8",
      "offset": 19
    }
  ],
  "location": "usersDetails.user_content_type(String)"
});
formatter.result({
  "duration": 76492,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "400",
      "offset": 10
    }
  ],
  "location": "usersDetails.send_user(int)"
});
formatter.result({
  "duration": 147769023,
  "status": "passed"
});
formatter.match({
  "location": "usersDetails.update_user()"
});
formatter.result({
  "duration": 97218481,
  "status": "passed"
});
formatter.scenario({
  "line": 12,
  "name": "user data",
  "description": "",
  "id": "user-api-test-with-json;user-data;;5",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 3,
  "name": "The user endpoint \"http://qainterview.merchante-solutions.com:3030/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "User content type \"application/json; charset\u003dutf-8\"",
  "keyword": "And "
});
formatter.step({
  "line": 5,
  "name": "Send user 500",
  "matchedColumns": [
    0
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 6,
  "name": "Update user",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "http://qainterview.merchante-solutions.com:3030/",
      "offset": 19
    }
  ],
  "location": "usersDetails.the_user_endpoint(String)"
});
formatter.result({
  "duration": 108303,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "application/json; charset\u003dutf-8",
      "offset": 19
    }
  ],
  "location": "usersDetails.user_content_type(String)"
});
formatter.result({
  "duration": 54367,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "500",
      "offset": 10
    }
  ],
  "location": "usersDetails.send_user(int)"
});
formatter.result({
  "duration": 137435818,
  "status": "passed"
});
formatter.match({
  "location": "usersDetails.update_user()"
});
formatter.result({
  "duration": 97908455,
  "status": "passed"
});
});