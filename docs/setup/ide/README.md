# Table of Contents
1. [Recommended IDE](#recommendedIDE)
1. [Importing a Project](#importingAProject)
2. [Project Structure](#projectStructure)
3. [Maven Profile](#mavenProfile)
    1. [Maven Environment Variables](#mavenEnvironmentVariables)
        1. [Denvironment environment variable](#DenvironmentVariable)
        2. [Dhost environment variable](#DhostEnvironmentVariable)
        3. [Dlog4.configurationFile environment variable](#Dlog4.configurationFileEnvironmentVariable)

-----------------------


<a name="recommendedIDE"></a>
# Recommended IDE
The IDE recommended for this framework is **IntelliJ IDEA**

- [Download IntelliJ IDEA](https://www.jetbrains.com/idea)

<a name="importingAProject"></a>
## Importing a Project

1 - Open `IntellIJ IDEA` and select the option **Import Project**, then select the repository downloaded.

![importProject](../../../.img/intellij/importProject.png)

2 - In the Next window select `Create project from existing sources` and hit the next button.

3 - In the Next window leave at it is and hit the next button.

4 - In the Next window select both source files and hit the next button.

5 - In the following windows hit the next button until the project is open.

6 - Once the project is open if `IntellIJ` if the following appears, click in the option **Add as Maven Project**

![addAsMavenProject](../../../.img/intellij/addAsMavenProject.png)


<a name="projectStructure"></a>
# Project Structure
A project structure needs to be setup on `IntellIJ`.

:bangbang: The minimum Java Version supported is `Java 8` and you have to set it in the Project Structure.

![openProjectStructure](../../../.img/openProjectStructure.png)

:pushpin: **Go to Project Settings > Project and set the following**:

1. Set Project SDK to `1.8 Java Version`
2. Set Project Language Level to `8 - Lambdas, type annotations, etc.`

![projectSettings_Project.png](../../../.img/intellij/projectSettings_Project.png)

:pushpin: **Go to Project Settings > Modules and set the following**:

1. Set "src/main/java" as `Source Folder`
2. Set "src/test" as `Source Folder`
3. Set "src/test/java" as `Test Source Folder`
4. Set "src/main/resources" as `Resource Folder`

![projectStructure](../../../.img/intellij/projectStructure.png)


<a name="mavenProfile"></a>
# Maven Profile
We need to add a `mvn profile` on IntelliJ IDEA in order to run the test cases in the framework

1 - Click in the `add configuration` button from right upper corner

![addConfiguration](../../../.img/addConfiguration.png)

2 - Click on the `plus icon` in the upper left corner and then select `Maven`

3 - In the Name field type any name you want, and in the `command line` field type
the following: 

- clean install test -Denvironment=dev -Dhost=localHost -Dlog4j.configurationFile=src/main/resources/log4j2.yml

![mvnConfiguration](../../../.img/maven/mvnConfiguration.png)

> Note: The`-D` maven flag is to set an environment variable, please take a look in the following
> section "Maven Environment Variables" to have knowledge about it
 
<a name="mavenEnvironmentVariables"></a>
## Maven Environment Variables
There are three Maven environment variables which are:
 
1. Denvironment (mandatory) 
2. Dhost (optional)
3. Dlog4j.configurationFile (optional)

<a name="DenvironmentVariable"></a>
### Denvironment environment variable
This environment variable set the Web Application URL, this has two possible values:

- Denvironment=dev (set the Web Application URL to dev environment)
- Denvironment=test (set the Web Application URL to test environment)

<a name="DhostEnvironmentVariable"></a>
### Dhost environment variable
This environment variable handles how the application should behave to login on the
Web Application.
Due to some machines have issues with `silent authentication` sometimes a `SSO form` is loaded
to ask for credentials, to workaround this issue you can set this environment variable with the
following two possibles values:

- Dhost=localHost
- Dhost=dockerContainer

> Note: if this variable is not set, the default environment will be dockerContainer 

<a name="Dlog4.configurationFileEnvironmentVariable"></a>
### Dlog4.configurationFile environment variable
This environment variable is to get the logger output in a friendly format, if you omit this
variable the default configuration will be load for the logger.