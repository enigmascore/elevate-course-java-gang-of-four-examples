# Modernise Repo

## Overview 

This repo was created some years ago. It needs modernizing and improving.  This document specifies the requirements for modernizing the modiule.

## How to resolve Open Questions

Each Open Question at the bottom of this document has a number ( OQ_1,
OQ_2, ... ) and a proposal. To resolve one: JD states the decision
( accept the proposal or give a different answer ), the question is
then REMOVED from the Open Questions section and its decision is
folded into the appropriate numbered section of this document
( marked "( decided by JD, YYYY-MM-DD )" ). The numbering of the
remaining Open Questions is NOT changed when one is removed. When all
are resolved the Open Questions section is deleted entirely.

## 1. Build & Toolchain

- R_1_1: Upgrade the project to Java 25. In `pom.xml` replace the
  compiler plugin's `<source>13</source>/<target>13</target>` with
  `<release>25</release>`, pin the `maven-compiler-plugin` to a
  current version (it is currently unversioned), and either remove
  the unused `java.version` property or make the plugin consume it.
- R_1_2: Upgrade `exec-maven-plugin` from 1.2.1 (released 2011) to a
  current 3.x version.
- R_1_3: Add a pinned, current `maven-surefire-plugin` so tests
  actually run in `mvn test` under the chosen test framework
  (see section 7).
- R_1_4: Fix the pom metadata: `<description>` says "Gang of Four
  spring boot" but the project has no Spring Boot — change it to an
  accurate description. Fix the comment typo `mvn exec:java@commnd`
  (the execution id is `command`).
- R_1_5: Add exec executions for the two patterns missing from the
  pom's run list: `chainofresponsibility` and `iterator` (both also
  need entry-point classes — see R_5_1).
- R_1_6: Add the Maven wrapper (`mvnw` / `.mvn/`) so the repo builds
  with a known Maven version without a local install.
- R_1_7: Enable javadoc doclint in the build (add
  `maven-javadoc-plugin`) so the javadoc errors listed in section 6
  are caught by CI once fixed.

## 2. README & Documentation

- R_2_1: Convert `README.txt` to `README.md` using proper Markdown
  markup: headings (`#`, `##`), bullet lists, and bold pattern names
  in place of the current `====` banner lines and indentation-based
  layout. Preserve the existing content (book/author attribution,
  the three category sections, per-pattern descriptions).
- R_2_2: Extend the README with: prerequisites (JDK 25, Maven or the
  wrapper), how to build, how to run each pattern
  (`mvn exec:java@<pattern>`), how to run the tests, and links from
  each pattern name to its source package.
- R_2_3: Convert `docs/eclipse/README.txt` to Markdown, or fold its
  content (importing the `gangoffour.xml` formatter) into the main
  README and delete the separate file.
- R_2_4: Add a `package-info.java` to each pattern package (there are
  currently none) carrying the pattern's "text book description" so
  it appears in generated javadoc at package level.

## 3. Code Modernization to Java 25 Idioms

Apply across all 23 pattern packages; representative examples cited.

- R_3_1: Convert pure data-carrier classes to records:
  `command/RequestImpl`, `observer/ObservableEventImpl`,
  `builder/Dimension`, `flyweight/Dimension`,
  `flyweight/CanvasElement`, `interpreter/Value`, and the
  hand-rolled immutable `DocumentMemento` inner class in
  `memento/DocumentImpl`.
- R_3_2: Use sealed interfaces where the pattern has a closed set of
  implementations: `state/State` (3 states), `memento/Memento`,
  `visitor/ShapeVisitable`. Where a sealed hierarchy is introduced,
  show pattern-matching `switch` as the modern companion idiom.
- R_3_3: Replace anonymous inner classes with lambdas / comparator
  factories: `command/CommandApplication` (anonymous `Response` —
  also annotate `Response` with `@FunctionalInterface`),
  `builder/BuildManager` (anonymous `Comparator`, which also boxes
  needlessly via `Integer.valueOf(..).compareTo(..)` — use
  `Comparator.comparingInt`), `command/SortCommand`
  (`ValueComparator` → `Comparator.comparing`).
- R_3_4: Replace `StringBuffer`/`StringBuilder` append-chains that
  build ASCII art or HTML with text blocks (`"""`). Widespread:
  ~15 shape-drawing classes plus `templatemethod/AbstractHtmlPage`
  and `composite` HTML building. Replace the two legacy
  `StringBuffer` usages (`state/AbstractAccountState`,
  `templatemethod/AbstractHtmlPage`) regardless.
- R_3_5: Use pattern matching for `instanceof` and `switch`:
  `interpreter/Value` (three instanceof-then-cast chains and a
  `toString` that should be a pattern-matching switch),
  `memento/DocumentImpl` (unguarded cast). Convert the switch
  statement in `abstractfactory/ShapeSelector` to an arrow-form
  switch expression.
- R_3_6: Use the diamond operator everywhere (23 sites still spell
  out full type arguments) and use `var` for obvious local types
  where it aids readability.
- R_3_7: Replace legacy APIs: `StringTokenizer` in
  `interpreter/InsertExpression`/`SelectExpression` with
  `String.split` or regex; `AtomicReferenceFieldUpdater` in
  `iterator/ConcurrentLinkedList` with `VarHandle` (also removes
  the `@SuppressWarnings("rawtypes")`); `LinkedList`-as-Deque in
  `memento/MementoApplication` with `ArrayDeque`.
- R_3_8: Remove `Cloneable`/`Object.clone()` from
  `creational/prototype` in favour of a copy constructor or record
  `with`-style copying, eliminating the `CloneNotSupportedException`
  propagation up through `main`. Same for `iterator/LinkedElement`.
- R_3_9: Replace `interpreter/InterpreterContext`'s reflective
  `field.setAccessible(true)` access with a non-reflective design
  (it breaks under strong encapsulation on modern JDKs).
- R_3_10: Mechanical cleanups, widespread: add missing `@Override`
  (`SortCommand.compare`, `BuildManager` anonymous comparator,
  `Value.toString`, `AbstractAccountState.toString`,
  `prototype/Shape.clone`); replace C-style `String args[]` with
  `String[] args` (3 files); drop redundant `public` on interface
  methods (make consistent — half the interfaces have it); remove
  the redundant `new String(document)` copy in `DocumentImpl` and
  the explicit `super()` in `ObserverImpl`; iterate maps via
  `entrySet()`/`forEach` instead of `keySet()`+`get`
  (`CapitaliseCommand`, `CommandApplication`); replace the indexed
  for-loop over a `LinkedList` in `AbstractHtmlPage` (O(n²)) with an
  enhanced for-loop.
- R_3_11: Replace the hand-rolled equals/hashCode in
  `mediator/ChatUserImpl` (and fix `compareTo` being inconsistent
  with `equals` — see R_4_6).

## 4. Bug Fixes & Correctness

- R_4_1: Fix `iterator/ConcurrentLinkedList` (the CAS-based list),
  which has real concurrency defects:
  - `remove()`'s head-removal path uses plain `set()` instead of
    `compareAndSet()`, silently losing a concurrent `add`.
  - `removeObject()` passes a freshly re-read field as the CAS
    expected value instead of the previously read local, defeating
    the CAS check (TOCTOU).
  - No node deletion-marking, so concurrent removals of adjacent
    nodes can resurrect a removed node (the classic Harris
    linked-list problem).
  - `removeObject()` and `LinkedElement.clone()` are recursive —
    `StackOverflowError` on long lists.
  - `getSnapshotIterator()` throws NPE on an empty list.
  - `LinkedElement.previousLinkedElement` is non-volatile — the
    snapshot is not safely published across threads.
  - `nextLinkedElementUpdater` should be `private static final`.
  - Use `NoSuchElementException` instead of raw `RuntimeException`.
- R_4_2: Fix `iterator/SnapshotIteratorImpl.next()` to throw
  `NoSuchElementException` at exhaustion instead of returning null,
  and make `SnapshotIterable`/`SnapshotIterator` extend
  `java.lang.Iterable`/`java.util.Iterator` so for-each works.
- R_4_3: Fix `decorator/RemoveMultipleSpacesTransformer`: it calls
  `this.transform(output)` instead of
  `this.transformer.transform(output)` — infinite recursion the
  moment it is not last in the chain (compare the correct
  `TextCapitaliseTransformer`).
- R_4_4: Fix `command/SortCommand`'s comparator, which returns 1 for
  equal values to keep duplicates in a `TreeMap` — this violates the
  Comparator contract (non-symmetric). Use a structure that supports
  duplicates legitimately (e.g. sort a list of entries, or a
  multimap-style value list).
- R_4_5: Fix `memento/MementoApplication` undo logic: undoing past
  the first save passes null to `restoreFromMemento` → NPE. Guard
  emptiness; also rename the misspelled `momentoDeque` field.
- R_4_6: Fix `mediator` package: replace check-then-put on the
  `ConcurrentHashMap` with `computeIfAbsent` (current code can drop
  a group's subscriber set under race); guard `sendMessage` against
  a group with no subscribers; `ConcurrentSkipListSet<ChatUser>`
  requires `Comparable` but the `ChatUser` interface is not — either
  make the interface extend `Comparable<ChatUser>` or supply a
  comparator.
- R_4_7: Fix `observer/ObserverApplication`: `observerThree` is
  constructed with the name "ObserverTwo" (copy-paste bug visible in
  program output); make the observers list private/final and add a
  `removeObserver` operation to complete the pattern.
- R_4_8: Fix `flyweight` immutability: `ShapeCache`'s statics are
  not final, and the shared `int[][] points` array is mutable,
  contradicting the "read only instance" javadoc — make the cached
  flyweights genuinely immutable.
- R_4_9: Fix `state/StateApplication` writing
  `accountState.balance = balance` directly on a package-private
  field, bypassing the State API; stop modelling money as `float`
  (use `BigDecimal` or integer cents).
- R_4_10: Fix `builder/BuildManager`: `paint()` before
  `mergeCoordinates()` NPEs on the package-private `points` array —
  enforce ordering or initialize safely.
- R_4_11: Fix `composite/AbstractHtmlElement` exposing its internal
  mutable list via `getHtmlElements()` (callers mutate it directly);
  provide an `addElement` method and return an unmodifiable view.
- R_4_12: `proxy/TriangleProxy`: make `numberTimesInvoked` handling
  correct (or document single-threaded intent) and inject the
  subject instead of hard-wiring it in the constructor.
- R_4_13: `templatemethod/AbstractHtmlPage`: harden the hand-rolled
  HTML tokenizer's `while (html.length() != 0)` loop against inputs
  where it fails to make progress.

## 5. Consistency, Naming & Structure

- R_5_1: Every pattern package must have a `<Pattern>Application`
  class with a `main` method. Currently
  `ChainOfResponsibilityApplication` is an empty class with no main
  (only comments pointing at the decorator package) and the
  `iterator` package has no Application class at all.
- R_5_2: Rename the misspelled class `ProtypeFactory` →
  `PrototypeFactory`.
- R_5_3: Standardize the demo entry-point method: each Application
  should expose the same method shape (e.g. a public `runExample()`)
  instead of the current mix of `runExample` / `drawShapes` /
  `render` / `showWebPage` / etc. with mixed visibility.
- R_5_4: Standardize exception handling: no `throws Exception` or
  `throws CloneNotSupportedException` propagated to `main`
  (interpreter, prototype, templatemethod packages); no raw
  `throw new Exception(...)`; give `ObjectNotRegisteredException`
  message/cause constructors and throw with diagnostic text.
- R_5_5: Deduplicate the two near-identical `Dimension` classes
  (builder, flyweight) or accept the duplication as intentional
  per-package isolation and document that convention in the README.
- R_5_6: Fix prose typos throughout javadoc/comments ("consructor",
  "seperators", "sunscribed", "accomodate", "Systm.out",
  "in all intense and purposes", "downloaded further" for
  "downgraded", etc.) and replace the 4 files containing smart
  quotes / em-dashes with plain ASCII.
- R_5_7: Normalize formatting: remove trailing whitespace
  (1,833 lines), bring the two files that deviate from the house
  brace style into line (`ChatUserImpl`, `TableElement`), and add an
  `.editorconfig` matching the `docs/eclipse/gangoffour.xml`
  formatter settings.

## 6. Javadoc Cleanup

- R_6_1: Fix malformed javadoc that doclint will reject: missing `@`
  on the author tag in `mediator/ChatUser`; `@param last` naming a
  nonexistent parameter in `iterator/SnapshotIteratorImpl`; dropped
  leading "T" and missing `*` continuation lines in
  `ProtypeFactory`; duplicated/orphaned javadoc blocks on
  `flyweight/CanvasElement` and `proxy/TriangleProxy`; `<ul>` blocks
  without `<li>` in ~20 Application-class "Text book description"
  headers; unescaped generics (`<HtmlElement>`, `<String>`) in the
  composite and decorator packages.
- R_6_2: Fill in the empty `@param`/`@return`/`@throws` tags
  (singleton/ShapeManager, interpreter/Interpreter, BuildManager,
  BridgeApplication, FacadeApplication, ProxyApplication,
  AbstractShapeBridge, ShapeAdapter, CanvasImpl) and add class
  descriptions to the 9 classes that have only an `@author` tag.
- R_6_3: Fix javadoc that references classes renamed long ago
  (`ApplicationDecorator`, `ApplicationMemento`, `ApplicationState`,
  etc. — ~20 occurrences) and doc/code mismatches
  (`prototype/Shape` documented as abstract but concrete;
  `StrategyApplication` "SquareStrategy ... draw a Triangle").
- R_6_4: Standardize the `@author` date format (currently 6 variants
  of the same 2020 dates across 142 files).

## 7. Testing

- R_7_1: Establish a real test suite: currently 1 test class /
  1 test method for 141 main classes. Add at least one unit test per
  pattern package asserting the observable behaviour of the example
  (e.g. rendered output, state transitions, notification delivery).
- R_7_2: Rework the existing
  `ConcurrentLinkedListSingleThreadedTest`: assert the final element
  count (today a short iterator passes silently), remove the
  `System.out.println` from the assertion loop, and add negative
  tests (remove-missing-element, empty-list snapshot, `next()` past
  end).
- R_7_3: Add genuinely multi-threaded tests for the CAS-based
  `ConcurrentLinkedList` (concurrent add/remove, snapshot isolation
  under concurrent mutation) once R_4_1 is fixed.
- R_7_4: Run tests in CI via surefire (see R_1_3, R_8_1).
- R_7_5: Use JUnit 5 (Jupiter) as the test framework, replacing
  TestNG 6.9.8. Convert the one existing test, swapping assertion
  arguments (TestNG's `Assert` order is `(actual, expected)` —
  reversed from JUnit). Remove the TestNG dependency and the
  `/test-output/` entry from `.gitignore`.
  ( decided by JD, 2026-09-11 )

## 8. Repo Infrastructure

- R_8_1: Add a GitHub Actions workflow that builds on JDK 25 and
  runs the tests on every push/PR.
- R_8_2: Add a `LICENSE` file at repo root. Related: every one of
  the 142 source files carries the same 33-line proprietary SpotADev
  license header (naming 9 individuals and a personal email) —
  ~4,700 lines of boilerplate; see OQ_2.
- R_8_3: Tidy `.gitignore`: remove the suspicious `*.` pattern and
  entries for tooling no longer relevant after modernization
  (`.springBeans`); keep IDE/build output entries.

## Open Questions

- OQ_2: What to do with the 33-line SpotADev license header
  duplicated in all 142 files? Proposal: remove the header from
  every file and rely on a single root `LICENSE` file (JD to choose
  the license, e.g. MIT for a teaching repo).
- OQ_3: How aggressively should the examples adopt modern idioms,
  given this is a teaching repo? Records/sealed/pattern-matching
  (section 3) change what the examples teach. Proposal: adopt them
  fully — the repo should show how the GoF patterns look in modern
  Java 25, and the README can note where a modern language feature
  subsumes part of a classic pattern (e.g. sealed + switch vs
  visitor).
- OQ_4: Fix or replace the buggy `ConcurrentLinkedList`? Fixing
  lock-free removal properly requires deletion-marking (Harris
  algorithm), which is substantial. Proposal: fix it properly and
  keep it, since the README advertises it as the "extra stuff"
  teaching example — but simplify `remove` semantics if full
  Harris-style unlinking proves too heavy for a course example.
- OQ_5: Logging: library-level classes print via `System.out`
  (33 files). Proposal: keep plain `System.out` in the
  `*Application` demo classes (appropriate for runnable examples)
  but have non-demo classes return/expose their output rather than
  print, which also makes them testable (R_7_1).
- OQ_6: The observer package defines its own `Observable`/`Observer`
  interfaces whose names shadow the JDK's deprecated
  `java.util.Observable`/`Observer` — confusing but compiles fine.
  Proposal: keep the hand-rolled pattern (that is the point of the
  example) but rename to `Subject`/`Listener` or similar to avoid
  the name clash, and mention `java.util.concurrent.Flow` in the
  javadoc as the modern JDK equivalent.
