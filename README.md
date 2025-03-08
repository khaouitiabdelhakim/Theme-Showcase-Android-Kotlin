# Android Theme Showcase

A simple Android app demonstrating light and dark theme implementation.

## Features

- Toggle between light and dark themes
- Custom theme attributes
- Responsive UI elements
- Material Design components

## Setup

1. Clone the repository
2. Open in Android Studio
3. Run on a device or emulator

## Project Structure

- `attrs.xml` - Custom theme attributes
- `themes.xml` (in values) - Light theme configuration
- `themes.xml` (in values-night) - Dark theme configuration
- `MainActivity.kt` - Theme toggle implementation
- `activity_main.xml` - UI layout with theme-aware components

## Key Concepts

- Using `?attr/` references for theme-aware styling
- Creating custom theme attributes 
- Implementing theme switching with `AppCompatDelegate`
- Using resource directories (`values-night`) for theme variants

## Implementation Notes

The theme toggle works by calling:
```kotlin
AppCompatDelegate.setDefaultNightMode(mode)
recreate() // Important to apply changes
```
