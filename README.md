# Gang of Four Design Patterns in Java

The Gang of Four wrote a book called:

> **Design Patterns: Elements of Reusable Object-Oriented Software**

Their names are Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides.

It is one of the most famous design patterns books ever written. These code examples were
created by JD, one of the instigators of SpotADev, to illustrate the Gang of Four design
patterns in Java.

The code examples also show specialist features of the Java language so you can learn extra
stuff on the side of learning design patterns. For example, in the
[Iterator](src/main/java/com/javaspeak/designpatterns/go4/behavioural/iterator) example you
will touch on creating your own multi-threaded data structure using non-blocking CAS
operations with `VarHandle`s. The examples use modern Java throughout — records, sealed
interfaces, pattern matching, switch expressions and text blocks — so you can also see where
a modern language feature subsumes part of a classic pattern (for example, sealed interfaces
plus pattern-matching `switch` as an alternative to the Visitor pattern).

The book classifies patterns into **creational**, **structural** and **behavioural** patterns.

## Prerequisites

- JDK 25
- No Maven install needed — the repo ships with the Maven wrapper (`./mvnw`, or `mvnw.cmd`
  on Windows)

## Building and testing

```bash
./mvnw compile        # compile everything
./mvnw test           # run the JUnit 5 test suite (one or more tests per pattern)
./mvnw verify         # additionally runs javadoc with doclint enabled
```

## Running the examples

Each pattern lives in its own package with a `<Pattern>Application` class containing a `main`
method, runnable from your IDE or via the Maven exec plugin:

```bash
./mvnw compile exec:java@observer
```

Replace `observer` with any pattern id from the tables below.

## Creational patterns

Creational patterns provide ways to instantiate single objects or groups of related objects.
There are five such patterns:

| Pattern | Run with | Description |
| --- | --- | --- |
| [Abstract Factory](src/main/java/com/javaspeak/designpatterns/go4/creational/abstractfactory) | `exec:java@abstractfactory` | Provides a client with a set of related or dependent objects. The "family" of objects created by the factory is determined at run-time. |
| [Builder](src/main/java/com/javaspeak/designpatterns/go4/creational/builder) | `exec:java@builder` | Creates complex objects with constituent parts that must be created in the same order or using a specific algorithm. An external class controls the construction algorithm. |
| [Factory Method](src/main/java/com/javaspeak/designpatterns/go4/creational/factorymethod) | `exec:java@factorymethod` | Replaces class constructors, abstracting the process of object generation so that the type of the object instantiated can be determined at run-time. |
| [Prototype](src/main/java/com/javaspeak/designpatterns/go4/creational/prototype) | `exec:java@prototype` | Instantiates a new object by copying all of the properties of an existing object, creating an independent clone. Particularly useful when the construction of a new object is inefficient. |
| [Singleton](src/main/java/com/javaspeak/designpatterns/go4/creational/singleton) | `exec:java@singleton` | Ensures that only one object of a particular class is ever created. All further references to objects of the singleton class refer to the same underlying instance. |

## Structural patterns

Structural patterns provide a manner to define relationships between classes or objects:

| Pattern | Run with | Description |
| --- | --- | --- |
| [Adapter](src/main/java/com/javaspeak/designpatterns/go4/structural/adapter) | `exec:java@adapter` | Provides a link between two otherwise incompatible types by wrapping the "adaptee" with a class that supports the interface required by the client. |
| [Bridge](src/main/java/com/javaspeak/designpatterns/go4/structural/bridge) | `exec:java@bridge` | Separates the abstract elements of a class from the implementation details, providing the means to replace the implementation details without modifying the abstraction. |
| [Composite](src/main/java/com/javaspeak/designpatterns/go4/structural/composite) | `exec:java@composite` | Creates hierarchical, recursive tree structures of related objects where any element of the structure may be accessed and utilised in a standard manner. |
| [Decorator](src/main/java/com/javaspeak/designpatterns/go4/structural/decorator) | `exec:java@decorator` | Extends or alters the functionality of objects at run-time by wrapping them in an object of a decorator class. A flexible alternative to using inheritance to modify behaviour. |
| [Facade](src/main/java/com/javaspeak/designpatterns/go4/structural/facade) | `exec:java@facade` | Defines a simplified interface to a more complex subsystem. |
| [Flyweight](src/main/java/com/javaspeak/designpatterns/go4/structural/flyweight) | `exec:java@flyweight` | Reduces the memory and resource usage for complex models containing many hundreds, thousands or hundreds of thousands of similar objects. |
| [Proxy](src/main/java/com/javaspeak/designpatterns/go4/structural/proxy) | `exec:java@proxy` | Provides a surrogate or placeholder object which references an underlying object, adding a level of indirection between the client and the real subject. |

## Behavioural patterns

Behavioural patterns define manners of communication between classes and objects:

| Pattern | Run with | Description |
| --- | --- | --- |
| [Chain of Responsibility](src/main/java/com/javaspeak/designpatterns/go4/behavioural/chainofresponsibility) | `exec:java@chainofresponsibility` | Processes varied requests, each of which may be dealt with by a different handler. |
| [Command](src/main/java/com/javaspeak/designpatterns/go4/behavioural/command) | `exec:java@command` | Expresses a request, including the call to be made and all of its required parameters, in a command object. The command may be executed immediately or held for later use. |
| [Interpreter](src/main/java/com/javaspeak/designpatterns/go4/behavioural/interpreter) | `exec:java@interpreter` | Defines the grammar for instructions that form part of a language or notation, whilst allowing the grammar to be easily extended. |
| [Iterator](src/main/java/com/javaspeak/designpatterns/go4/behavioural/iterator) | `exec:java@iterator` | Provides a standard interface for traversing a collection of items in an aggregate object without the need to understand its underlying structure. |
| [Mediator](src/main/java/com/javaspeak/designpatterns/go4/behavioural/mediator) | `exec:java@mediator` | Reduces coupling between classes that communicate with each other. Instead of communicating directly, the classes send messages via a mediator object. |
| [Memento](src/main/java/com/javaspeak/designpatterns/go4/behavioural/memento) | `exec:java@memento` | Captures the current state of an object and stores it in such a manner that it can be restored at a later time without breaking the rules of encapsulation. |
| [Observer](src/main/java/com/javaspeak/designpatterns/go4/behavioural/observer) | `exec:java@observer` | Allows an object to publish changes to its state. Other objects subscribe to be immediately notified of any changes. |
| [State](src/main/java/com/javaspeak/designpatterns/go4/behavioural/state) | `exec:java@state` | Alters the behaviour of an object as its internal state changes. The pattern allows the class for an object to apparently change at run-time. |
| [Strategy](src/main/java/com/javaspeak/designpatterns/go4/behavioural/strategy) | `exec:java@strategy` | Creates an interchangeable family of algorithms from which the required process is chosen at run-time. |
| [Template Method](src/main/java/com/javaspeak/designpatterns/go4/behavioural/templatemethod) | `exec:java@templatemethod` | Defines the basic steps of an algorithm and allows the implementation of the individual steps to be changed. |
| [Visitor](src/main/java/com/javaspeak/designpatterns/go4/behavioural/visitor) | `exec:java@visitor` | Separates a relatively complex set of structured data classes from the functionality that may be performed upon the data that they hold. |

## Project conventions

- Each pattern package is fully self-contained; small helper types (for example `Dimension`)
  are deliberately duplicated between packages rather than shared, so every example can be
  read in isolation.
- Each package has a `package-info.java` with the pattern's textbook description, and a
  `<Pattern>Application` whose `runExample()` returns the demo output (making it testable);
  `main` just prints it.
- Library-level classes log diagnostics through SLF4J (log4j2 backend); only `main` writes
  the demo product to `System.out`.

## Code formatting

An Eclipse/STS formatter profile matching the project's code style is provided in
[`docs/eclipse/gangoffour.xml`](docs/eclipse/gangoffour.xml). In Eclipse or STS import it via
*Preferences → Java → Code Style → Formatter → Import*. An `.editorconfig` at the repo root
covers the basics for other editors.
