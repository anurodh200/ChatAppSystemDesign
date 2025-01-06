# Design Patterns

Well-proven solutions to commonly occurring problems in software design.

## Categorization of Design Patterns
1. **Creational Design Patterns**:
       - Factory Pattern
       - Builder Pattern
       - Singleton Pattern
2. **Structural Design Patterns**:
       - Proxy Pattern
       - Adapter Pattern
3. **Behavioral Design Patterns**:
       - Observer Pattern
       - State Pattern
       - Iterator Pattern

---

## Singleton Design Pattern
1. Constructor is private.
2. Field to store the object is private.
3. Object is created with the help of a method.

### Initialization Approaches:
- **Eager way** and **Lazy way** of object initialization are available but are **not thread-safe**.
- To make it thread-safe, use:
       - Method synchronization
       - Block synchronization

### Challenges:
- Singleton can be broken through:
       - Reflection (accessing and making the constructor public)
       - Serialization & Deserialization

### Solutions:
1. If the object exists, throw an exception from inside the constructor.
2. Use **enum** to implement Singleton.
3. Implement the `readResolve` method.

---

## Factory or Factory Method Design Pattern
- When there is a superclass with multiple subclasses, this pattern allows creating objects of subclasses based on input requirements.
- Focus on creating objects for the **interface** rather than the **implementation**.
- Promotes **loose coupling**.

---

## Abstract Factory Design Pattern
- A factory calls another abstract factory, where the implementation of object creation is defined and returned based on input.
- Example:
  employee <- EmployeeFactory <- EmployeeAbstractFactory <- AndroidFactory & WebFactory


---

## Builder Design Pattern
- Useful when creating objects with many attributes.

### Problems Addressed:
1. Need to pass many arguments to create an object.
2. Some parameters might be optional.
3. Factory classes become overly complex when handling heavy objects.

### Solution:
- Build objects step-by-step and finally return the object with the desired values of attributes.

---

## Prototype Design Pattern
- The concept is to **copy an existing object** rather than creating a new instance from scratch.
- Creating a new object may be **costly**.
- Saves resources by creating a clone of a heavy object.

### Types of Cloning:
1. **Shallow Copy**: Object references are copied.
2. **Deep Copy**: The object is copied into a new object.

---

## Observer Design Pattern
- A **behavioral design pattern**.
- When the subject changes its state, all its dependent objects are notified.
- Establishes a **one-to-many relationship**.

---

## Iterator Design Pattern
- Provides a way to access the elements of an object without exposing its underlying implementation.

---

## Adapter Design Pattern
- Example: Consider an iPhone lightning port and an Android USB-C port.
- To charge an iPhone using a USB-C port, an adapter is used to bridge the incompatibility.

