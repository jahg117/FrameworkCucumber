# Framework Content

1. Template
    1. Technologies Involved
    2. Features
        1. Multi Browser support.
        2. Parallel test execution.
        3. Report Generation.
2. Environment setup.
    1. Install Java.
    2. Install and Configure IntelliJIDEA.
    3. How to clone the project.
3. How to use this Framework.
    1. Create a new test case.
    2. Run tests in local.
    3. Run tests in parallel in local and browserstack.     

## Technologies Involved

| Technology                                               | Logo                                         |
|----------------------------------------------------------|----------------------------------------------|
| [Java 8](https://www.java.com/es/download/faq/java8.xml) | ![Java icon](.img/icons/java-icon.png)         |
| [Selenium](https://www.selenium.dev/)          | ![Selenium icon](.img/icons/selenium-icon.png) |
| [Cucumber](https://cucumber.io/)          | ![Cucumber icon](.img/icons/cucumber-icon.png) |
| [TestNG](https://testng.org/doc/documentation-main.html)          | ![TestNG icon](.img/icons/testng-icon.png) |
| [BrowserStack](https://www.browserstack.com/)          | ![BrowserStack icon](.img/icons/browserstack-icon.png) |

## Features

### Multi browser support
:pushpin: The framework supports Chrome & Firefox browsers, 
but if you want to add a new browser you can download 
the driver and add the configuration in the WebDriverFactory 
class.

### Parallel test execution
:pushpin: Parallel test execution lets you run different test cases on 
the same machine simultaneously, but if you want to execute 
in different devices or browsers at the same time, 
you can do it with BrowserStack.


### Report Generation
:pushpin: The reports are going to be generated in the folders 
`target and test output`; you will find a PDF file as a report
and two HTML files in the directory to open in any browser.

:pushpin: The reports support parallel execution and screenshot attachment.

-----------------------  
# Environment Setup

## Install Java
:pushpin: Find the related documentation here :point_right: [Install Java Documentation](docs/setup/java/README.md)

## Install IntelliJ IDEA
:pushpin: Find the related documentation here :point_right: [Install IntelliJ IDEA Documentation](docs/setup/ide/README.md)

## How to clone the project
:pushpin: Find the related documentation here :point_right: [Clone Documentation](docs/setup/bitbucket/README.md)

-----------------------  
# How to use the framework.

## Create a new test case.
:pushpin: Find the related documentation here :point_right: [Create Test Cases Documentation](docs/project/testcase/README.md)

## Run tests in local.
:pushpin: Find the related documentation here :point_right: [Run tests Documentation](docs/project/runtests/README.md)

## Run tests in parallel in local and browserstack.
:pushpin: Find the related documentation here :point_right: [Run tests in parallel Documentation](docs/project/parallel/README.md)
