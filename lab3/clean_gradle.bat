@echo off
echo Cleaning Gradle cache and daemons...
echo.

echo Stopping Gradle daemons...
call gradlew --stop 2>nul

echo Removing .gradle directory...
if exist .gradle rmdir /s /q .gradle

echo Removing build directories...
if exist app\build rmdir /s /q app\build
if exist build rmdir /s /q build

echo Cleaning user Gradle cache (optional - uncomment if needed)...
REM if exist %USERPROFILE%\.gradle\caches rmdir /s /q %USERPROFILE%\.gradle\caches

echo.
echo Cleanup complete!
echo Please sync the project in Android Studio.
pause


