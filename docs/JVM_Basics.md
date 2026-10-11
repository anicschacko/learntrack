# JVM Basics

## JDK, JRE, JVM

- **JVM (Java Virtual Machine)**: the program that actually runs Java bytecode. It loads classes, verifies them, executes them (interpreting and JIT-compiling to native code) and manages memory with garbage collection.
- **JRE (Java Runtime Environment)**: the JVM plus the standard class libraries (`java.lang`, `java.util`, ...). Everything needed to *run* Java programs.
- **JDK (Java Development Kit)**: the JRE plus developer tools such as `javac` (compiler), `jar`, and `javadoc`. Everything needed to *write and build* Java programs.

JDK ⊃ JRE ⊃ JVM. (Since Java 11 there is no separate JRE download; the JDK is the normal thing to install.)

## What is bytecode?

When you compile a `.java` file with `javac`, you don't get machine code for your CPU. You get a `.class` file containing **bytecode**, a compact, platform-neutral instruction set designed for the JVM. The JVM reads this bytecode and executes it on whatever hardware it is running on.

```
Source (.java)  --javac-->  Bytecode (.class)  --JVM-->  Runs on the machine
```

## Write once, run anywhere

Because the compiler produces bytecode rather than OS-specific machine code, the same `.class` files can run on Windows, macOS or Linux, as long as a JVM exists for that platform. Each platform has its own JVM that knows how to translate the bytecode for the local OS and CPU.

So you compile once and distribute the bytecode; you don't rewrite or recompile the program per platform. The JVM is the layer that hides the differences between operating systems.
