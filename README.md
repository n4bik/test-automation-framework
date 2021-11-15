# Test Automation Framework

Created by Tomasz Buga, SDET | https://www.tomaszbuga.pl

GitHub: https://github.com/n4bik

LinkedIn: https://www.linkedin.com/in/tomasz-buga-79933886/

## S01E06 Zeitgeist - PageLoadStrategy Branch
Related article available at:
https://sdet-tomaszbuga.medium.com/

## Project Overview

Test Automation Framework - Reusable template for Java/Selenium frameworks. Learn more about the project here: https://sdet-tomaszbuga.medium.com/

## Tech Stack

`Java 11` `Maven` `TestNG` `Selenium` `WebDriverManager` `Apache Commons Lang3`

## Key Files Description

`pom.xml` - this file contains all the dependencies that are being used by the application (and basically everything for the building/testing/deploying automation with Java)

## Getting started
### Prerequisites
Java 11 SDK (https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

Apache Maven (https://maven.apache.org/download.cgi)

### Clone the repository & run the project
Launch `Terminal.app` and navigate (using `cd` command) to directory, where you'd like to store your copy of the source code

Use the command below to clone the repository to your local directory

```
git clone https://github.com/n4bik/test-automation-framework.git
``` 

Navigate (using `cd` command) to the root directory of the project (basically - it's where the `pom.xml` file is located)

Run the command below in order to run the tests

```
mvn clean test
```
