Feature: AppLauncher Selection

  @TestMethods
  Scenario: Select AccessServices App
    Given Login page in SF
    When Enter an Username and Password in SF
    Then The HomePage is displayed in SF
    Then The Correct AppPage is displayed