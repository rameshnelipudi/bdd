# BBSL
Business banking secured  layer -
Commercial mortagage applicaiton  
Test environment [bbsl](http://bb.abviz.co.uk/accounts/login/?next=/)

## config
* #chrome.switches=--headless
* #webdriver.chrome.driver=.\\src\\main\\resources\\drivers\\windows\\chromedriver.exe
* #serenity.driver.capabilities=javascriptEnabled:true
* #webdriver.driver=chrome
* #serenity.browser.width=1400
* #serenity.browser.height=800

## steps

* Understand business requirement -refer [JIRA](http://172.24.16.176:8080/login.jsp?permissionViolation=true&os_destination=%2Fbrowse%2FIOS-1561&page_caps=&user_role=)  
* Identify steps and make sure to validate scenario manually 
* Creare Feature Files, add scenarios as required
* Define valid Gherkin statements
* Implement step definitions
* Create model classe pertaining to scenario
* Create CSV static parser methods in CSVUtils.class, build objects as per modle
* Prepare test data CSV files place at right location 
* Create page Objects along with actions 
* Call required data into stepdefs, set into page actions  

## Running the tests
use either of the following 
* use juinit test runner
* Run from Featurefile
* Run from terminal


## CI/CD

## Cross broweser test

## Parallel tests
