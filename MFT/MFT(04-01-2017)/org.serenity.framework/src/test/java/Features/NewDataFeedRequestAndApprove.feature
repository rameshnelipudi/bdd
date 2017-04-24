@CreateDataFeedReq
Feature: Creating a new data feed request and approve
  Login as a MFT user, create a new data feed request,review and approve

  @CreateNewFeedAndApprove
  Scenario: verify creating new data request and approve
    Given  I am on JLR ADL login page
    And    I login into JLR Analytic DataLab applicaton with valid "user" and  "userpwd"
    And  I clicked on "New Data Feed Request"
    Then I entered  "RequestTitle"
    Then I entered "10" as value of budget value
    And  I selected project id as "jlr-dev-dl"
    And  I entered FeedName as "TestFeedName"
    And  I selected  destinationBucket as "jlr-dev-dl-mft-test"
    And  I entered FolderName as "TestFoldername"
    And  I selected FeedStatus as "Active"
    And  I entered contactEmailAddress as "tester@appsbroker.com"
    And  I entered Tags as "myTestTags"
    And  I entered feedDescription "samplefeeddescription"
    And  I entered dataOwner "tester@test.com"
    And  I selected DataSensitivity as "Proprietary"
    And  I selected ImportDataToBigQuery flag as "Yes"  and fill bigQuery definition values if yes
    And  I logined as approver with valid user and password
    And  I should see review button in email and click
    And  I should see approve button
    And  I Should compare and approve feed request data
    Then I click on approve
#  Scenario: Login to GCP and uploading file and checking in destination bucket
    Given  I am on GCP page
    When I Switch to "jlr-uat-dl-appsflow" project
    And Select "Storage"
    When I click on "jlr-uat-dl-appsflow-loadfile" bucket
    And I click on "Upload files" to upload a file
    When I switch to "jlr-dev-dl" project
    Then I should see uploaded file in "jlr-dev-dl-mft-test" destination bucket




