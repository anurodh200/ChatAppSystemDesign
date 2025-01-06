# Multitasking In Java
- Process-based multitasking (Allow programs to run concurrently. Eg.- Running the Ms paint also working with the word processor)
- Thread-based multitasking (Allows part to the program to run concurrently. Eg.- Ms word that is printing and formatting text at the same time)

### Thread v/s Process
- Two threads share the same address space.
- Context switching between threads is usually less expensive than between processes.
- The cost of communication between threads is relatively low.

###  Why multithreading?
- In single-threaded env, only one task at a time can be performed.
- Multitasking allows idle CPU time to be put to good use.

### Threads??
- A thread is an independent sequential path of execution within a program.
- At runtime, threads in a program exist in a common memory space and can, therefore, share both data and code(i.e, they are lightweight compared to processes)

### 3 Imp concepts to multithreading in java
1. Creating threads and providing the code that gets executed by a thread.
2. Accessing common data and code through syncronizatino.
3. Transistioning between thread states.

### Main Thread:
- When a standalone app is run, a user thread is automatically created to execute the main() method of the application.
- While no other user thread are spawned, the program terminates the main() method.
- All other threads spawned from main thread, called the child threads.

### Thread Creation:
```java
public class Thread1 extends Thread{
    @Override
    public void run(){
        for(int i = 0; i < 6; i++){
            System.out.println("Thread1 " + i);
        }
    }
}

public class ThreadTester{
    public static void main(String[] args) {
        System.out.println();
        Thread thread1 = new Thread1();
        thread1.start();
    }
}
```

