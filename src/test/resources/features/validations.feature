Feature: This is my first test

@smoke
Scenario Outline: Validate if Add API works
Given Add place payload "<name>","<langauge>","<address>"
When user calls "addplaceapi" with "post" http request
Then response should be 200
And "status" in response body is "OK"
And "scope" returned in response is "APP"	
And verify place_id creatd maps to "<name>" using "getplaceapi"

Examples:
|name|langauge|address|
|A|English|Pune|

@reg
Scenario: Verify for Delete Place functionality
Given delete place payload
Then response should be 200




