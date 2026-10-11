# Setup Instructions

## JDK version used

- OpenJDK **Temurin 17.0.20.1** (LTS), 64-Bit Server VM
- IDE: IntelliJ IDEA (project SDK `temurin-17`, language level 17)

Verify your install:

```bash
java -version
javac -version
```

## Hello World

Create `HelloWorld.java`:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile and run from the terminal:

```bash
javac HelloWorld.java   # produces HelloWorld.class (bytecode)
java HelloWorld         # the JVM loads the class and runs main()
```

Expected output:

```
Hello, World!
```

What happened: `javac` compiled the source into bytecode (`HelloWorld.class`); `java` started a JVM, which loaded that class and executed `main`. In IntelliJ, the green Run icon next to `main` does the same two steps.

`Screenshot_1791718440.png`.

## Running LearnTrack

See the "How to compile and run" section of the [README](../README.md).
