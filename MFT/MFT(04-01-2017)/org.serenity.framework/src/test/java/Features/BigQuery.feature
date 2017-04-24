Feature: Uploading file in GCP

   Scenario: Check data for opcode 1 has been correctly added to BigQuery for tax code 0
      Given I instantiate the BigQuery service
      And I log into the Google console
      And the expected tables are present under the "tlog" dataset
        | enrichedOpCode   |







