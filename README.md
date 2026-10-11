# LearnTrack

A console-based Student & Course Management System built with Core Java (no external libraries).
Admins can manage **students**, **courses** and **enrollments** through a menu-driven UI. All data is held in memory (`ArrayList`) and is lost when the program exits.

## Features

- **Students**: add, view all, search by ID, deactivate (soft delete: `active = false`)
- **Courses**: add, view all, activate/deactivate
- **Enrollments**: enroll a student in a course, view all enrollments, update status (ACTIVE / COMPLETED / CANCELLED)
- Invalid menu choices and bad input are re-prompted instead of crashing the app.

## Project structure

```
src/com/airtribe/learntrack
├── entity      Person, Student, Trainer, Course, Enrollment
├── enums       EnrollmentStatus
├── service     BaseService, StudentService, CourseService, EnrollmentService
├── ui          Main (entry point + input helpers), StudentServiceUI, CourseServiceUI, EnrollmentServiceUI
├── exception   EntityNotFoundException, InvalidInputException
├── util        IdGenerator, InputValidator
└── constants   AppConstants, MenuOptions (placeholders)
docs/           Problem.md, Setup_Instructions.md, JVM_Basics.md, Design_Notes.md
```

## How to compile and run

Requires JDK 17 or newer (see [docs/Setup_Instructions.md](docs/Setup_Instructions.md)).

From the project root:

```bash
mkdir -p out/production/LearnTrack
javac -d out/production/LearnTrack $(find src -name "*.java")
java -cp out/production/LearnTrack com.airtribe.learntrack.ui.Main
```

Or open the folder in IntelliJ IDEA and run `Main`.

## Using the app

```
WELCOME TO LEANTRACK PORTAL
1. Student Management
2. Course Management
3. Enrollment Management
0. Exit
```

Type the number of an option and press Enter. In each sub-menu, `9` returns to the main menu.

## Further reading

- [docs/Design_Notes.md](docs/Design_Notes.md): design decisions
- [docs/JVM_Basics.md](docs/JVM_Basics.md): JDK / JRE / JVM basics
- [docs/Setup_Instructions.md](docs/Setup_Instructions.md): environment setup
