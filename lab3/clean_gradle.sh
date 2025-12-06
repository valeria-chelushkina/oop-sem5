#!/bin/bash
echo "Cleaning Gradle cache and daemons..."
echo ""

echo "Stopping Gradle daemons..."
./gradlew --stop 2>/dev/null || true

echo "Removing .gradle directory..."
rm -rf .gradle

echo "Removing build directories..."
rm -rf app/build
rm -rf build

echo "Cleaning user Gradle cache (optional - uncomment if needed)..."
# rm -rf ~/.gradle/caches

echo ""
echo "Cleanup complete!"
echo "Please sync the project in Android Studio."


