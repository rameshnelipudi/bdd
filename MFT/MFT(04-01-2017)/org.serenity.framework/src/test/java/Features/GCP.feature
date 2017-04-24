Feature: Uploading file in GCP

 Scenario: Login to GCP and uploading file and checking in destination bucket
    Given  I am on GCP page
    When I Switch to "jlr-uat-dl-appsflow" project
    And Select "Storage"
    When I click on "jlr-uat-dl-appsflow-loadfile" bucket
    And I click on "Upload files" to upload a file
    When I switch to "jlr-dev-dl" project
    Then I should see uploaded file in "jlr-dev-dl-mft-test" destination bucket







