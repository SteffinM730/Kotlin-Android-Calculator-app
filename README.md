# Kotlin Calculator

A simple Android calculator app built with Kotlin. The app separates the UI layer from the calculation logic using a `CalculatorEngine` class.

## Features

- Basic arithmetic operations: addition, subtraction, multiplication, and division
- Decimal number support
- Clear, backspace, negate, and percentage actions
- Error handling for division by zero
- Clean display for whole numbers and decimal results

## Tech Stack

- Kotlin
- Android SDK
- Gradle
- AndroidX AppCompat
- Material Components
- ConstraintLayout

## Project Files

- `MainActivity.kt` - Handles Android UI events and updates the calculator display.
- `CalculatorEngine.kt` - Contains the calculator logic and state management.
- `activity_main.xml` - Defines the calculator layout.
- `build.gradle` - Android app build configuration and dependencies.

## Getting Started

1. Open the project in Android Studio.
2. Let Gradle sync the project dependencies.
3. Connect an Android device or start an emulator.
4. Run the app from Android Studio.

## Build

To build the project from Android Studio, use:

```text
Build > Make Project
```

Or run the Gradle build task if a Gradle wrapper is available:

```powershell
.\gradlew build
```

## Notes

The app uses `local.properties` for local Android SDK configuration. This file should stay out of version control and is already included in `.gitignore`.
