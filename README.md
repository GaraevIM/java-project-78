# Data Validator

[![CI](https://github.com/GaraevIM/java-project-78/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/GaraevIM/java-project-78/actions/workflows/gradle.yml)
[![hexlet-check](https://github.com/GaraevIM/java-project-78/actions/workflows/hexlet-check.yml/badge.svg?branch=main)](https://github.com/GaraevIM/java-project-78/actions/workflows/hexlet-check.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GaraevIM_java-project-78&metric=alert_status)](https://sonarcloud.io/project/overview?id=GaraevIM_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GaraevIM_java-project-78&metric=coverage)](https://sonarcloud.io/project/overview?id=GaraevIM_java-project-78)

## Description

This project is a data validator library written in Java.
It allows you to build validation schemas for strings, numbers, and maps, and then check data against a set of rules.

The project was created as part of the Hexlet Java course to practice OOP, testing, and project infrastructure setup with Gradle, CI, and SonarCloud.

## Features

The library supports validation for:

- strings
- numbers
- maps
- nested map structures through `shape()`

Examples of available validation rules:

- `required()`
- `minLength()`
- `contains()`
- `positive()`
- `range()`
- `sizeof()`

## Project launch

### Requirements

- Java 21 or higher
- Gradle 8.14 or Gradle Wrapper

### Run tests

From the project root:

```bash
cd app
./gradlew test
```

For Windows:

```bash
cd app
gradlew.bat test
```

### Run full check

```bash
cd app
./gradlew check
```

For Windows:

```bash
cd app
gradlew.bat check
```
