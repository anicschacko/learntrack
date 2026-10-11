# Design Notes

## Why ArrayList instead of an array?

The number of students, courses and enrollments isn't known up front and grows as the admin adds records. A plain array has a fixed size, so we would have to pick a maximum or manually allocate a bigger array and copy elements. `ArrayList` grows automatically, offers `add`, `remove`, `isEmpty` and `size`, and is type-safe through generics (`ArrayList<Student>`). Lookups by ID are a simple linear scan, which is fine for in-memory data of this size.

## Where static members are used and why

- `IdGenerator` keeps `private static int` counters (`studentIdCounter`, `courseIdCounter`, `enrollmentCounter`) with static methods `getNextStudentId()`, `getNextCourseId()`, `getNextEnrollmentId()`. IDs must be unique across the whole application, not per object, so shared class-level state is the right fit.
- `InputValidator` contains only static, stateless helpers (`isValidName`, `isValidEmail`, ...) so callers don't need an instance.
- `Student.getHeaders()` / `Course.getHeaders()` are static because column headers belong to the class, not to a particular record.
- `Main` holds the single shared `Scanner` and the input/print helpers as static members so every UI class reuses one console reader.

## Where inheritance is used and what it gives us

- `Person` is the base class (id, firstName, lastName, email, `getDisplayName()`).
- `Student extends Person` and `Trainer extends Person`, both calling `super(...)` in their constructors.
- Both override `getDisplayName()` (adding " (Student)" / " (Trainer)") and call `super.getDisplayName()` to reuse the base formatting, which demonstrates polymorphism.
- Benefit: shared identity fields and behaviour live in one place, and code that works with a `Person` handles any subtype.

Related OOP points: `Student` demonstrates constructor overloading (with and without email); fields are private with public getters/setters; `EnrollmentStatus` is an enum rather than a free-form string to prevent invalid values.

## Separation of concerns

- **entity**: data and simple display helpers only.
- **service**: business logic and the `ArrayList` storage (`StudentService`, `CourseService`, `EnrollmentService`).
- **ui**: menus, reading input, calling services. `Main` is the entry point; each feature has its own `*ServiceUI` class.
- **exception**: `EntityNotFoundException` and `InvalidInputException` give clear, specific error messages.
- **util**: `IdGenerator`, `InputValidator`.

## Error handling

Input reading re-prompts on invalid numbers or choices, and each menu's `try/catch` prints the exception message instead of letting the program crash.

## Known limitations

- Data is in memory only.
- Enrollment does not yet verify that the student/course exist, nor that they are active.
- Enrollment status update looks up the first enrollment for a student ID, not a specific enrollment ID.
