# Framework Content

1. Template
    1. [Technologies Involved.](#technologiesInvolved)
    2. [Features.](#features)
        1. [Multi Browser support.](#multiBrowserSupport)
        2. [Parallel test execution.](#parallelTestExecution)
        3. [Report Generation.](#reportGeneration)
2. Environment setup.
3. How to start.     

<a name="technologiesInvolved"></a>
## Technologies Involved

| Technology                                               | Logo                                         |
|----------------------------------------------------------|----------------------------------------------|
| [Java 8](https://www.java.com/es/download/faq/java8.xml) | ![Java icon](.img/icons/java-icon.png)         |
| [Selenium](https://www.selenium.dev/)          | ![Selenium icon](.img/icons/selenium-icon.png) |
| [Cucumber](https://cucumber.io/)          | ![Cucumber icon](.img/icons/cucumber-icon.png) |
| [TestNG](https://testng.org/doc/documentation-main.html)          | ![TestNG icon](.img/icons/testng-icon.png) |
| [BrowserStack](https://www.browserstack.com/)          | ![BrowserStack icon](.img/icons/browserstack-icon.png) |
-----------------------

<a name="features"></a>
## Features

<a name="multiBrowserSupport"></a>
### Multi browser support
The framework supports Chrome & Firefox browsers, 
but if you want to add a new browser you can download 
the driver and add the configuration in the WebDriverFactory 
class.

<a name="parallelTestExecution"></a>
### Parallel test execution
Parallel test execution lets you run different test cases on 
the same machine simultaneously, but if you want to execute 
in different devices or browsers at the same time, 
you can do it with BrowserStack.


<a name="reportGeneration"></a>
### Report Generation
The reports are going to be generated in the folders 
"target & test output"; you can open two of the reports 
in any browser and one of them is a PDF report.

The reports support parallel execution and screenshot attachment.

-----------------------  