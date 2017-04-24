Feature: check master data

  @feeds
  Scenario: Verify feed name in mater data
    Given I am on MasterData page
    When I login into jlr appsflow with valid "user" and  "userpwd"
    And I cliked on masterdata  link
    And  I search master data as "datalab.upload.feeds" to verify feeds
