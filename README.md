# CoolBlueTask

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
![CI]()

## Getting Started

* [App architecture uml](https://drive.google.com/file/d/1UqPEQ9Dv4zovixI85bWCAAbDQrYparME/view?usp=sharing)

## App Main Modules

*  buildSrc contain all app dependencies
* :core contain app shared functions, data, utils and setups across modules
* :core-testing to share tests setups and shared data across modules

## App Files

## Code Style

* Using [Spotless](https://github.com/diffplug/spotless) and [Detekt](https://github.com/detekt/detekt) to enforce high quality clear and formatted code.

## CI/CD

 Using GitHub actions as our CI.

### Auto Trigger

 1-  [pull_request](https://github.com/ezzatthrwat/CoolBlue/blob/master/.github/workflows/pull_request.yml): any push will run the following tasks.
* Spotless
* Detekt
* Unit Test

## Automation Testing

### Unit test
Unit testing most of the app/feature components for example `datasource,` , `repository`, `usecase`, `viewmodel`, etc.
Since we are a multi-module project running all the project unit tests is not a straightforward task so we created a custom Gradle task that does that so you can just call ./gradlew unitTest and it will run all the app tests.
