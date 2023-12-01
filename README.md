# Reactive Programming Notes

- **Concurrency and Parallelism**
    - Parallelism is simultaneous execution of tasks, typically on different CPUs or machines.
    -  Concurrency, on the other hand, is the composition or interleaving of multiple tasks.
    - If a single CPU has multiple tasks (such as threads) on it, they are executing concurrently but not in parallel by “time slicing.”
    - Each thread gets a portion of CPU time before yielding to another thread, even if a thread has not yet finished.
    - Parallel execution is concurrent by definition, but concurrency is not necessarily parallelism.
    - In practice, this means being multithreaded is concurrency, but parallelism only occurs if those threads are being scheduled and executed on different CPUs at the exact same time.
    - Thus, generically we speak about concurrency and being concurrent, but parallelism is a specific form of concurrency.
    - Inorder to achieve concurrency / parallelism in javaRX for example we can have a look at the below code: <br />

- **What is reactive programming?**
    - Reactive programming is a programming paradigm where the focus is on developing asynchronous and non-blocking applications in an event-driven form.
    - It is a declarative programming model that enables the processing of data streams in a more efficient, scalable, and resilient way.
    - Reactive programming deals with the continuous flow of data, rather than individual events or requests, and enables the handling of complex asynchronous operations in a more streamlined way.
    - In Reactive Programming, data streams are modeled as streams of events that are pushed asynchronously to data consumers.
    - These events trigger reactions in the data consumers, which perform some action in response.
    - Reactive programming is built on the idea of composing streams of events and applying functional transformations to these streams.
    - Reactive Programming aims to provide a more efficient and scalable way of processing data streams.
    - It enables the development of highly responsive and fault-tolerant applications that can handle large volumes of data in real time.

- **Reactive Streams Specification**
    - Reactive Streams Specification is a set of rules or set of guidelines that you need to follow when designing a reactive stream.
    - These specifications introduce four interfaces that should be used and overridden when creating a reactive stream.
        - `Publisher` is a datasource that will always publish events.
          ```java
           public interface Publisher<T> {
              void subscribe(Subscriber<? super T> subscriber);
          }
          ```
        - `Subscriber` will subscribe/consume the events from the publisher.
          ```java
          package org.reactivestreams;
          public interface Subscriber<T>{
            void onSubscribe(Subscription subscription);
            void onNext(T t);
            void onError(Throwable throwable);
            void onComplete();
          }
          ```
        - `Subscription` represents the unique relationship between a `Subscriber` & a `Publisher` interface.
          ```java
          package org.reactivestreams;
          public interface Subscription{
            void request(long var1);
            void cancel();
          }
          ```
        - `Processor` represents a processing stage - which is both a `Subscriber` & a `Publisher` must obey the contracts of both.
          ```java
          package org.reactivestreams;
          public interface Processor<T, R> extends Subscriber<T>, Publisher<T>{
          }
          ```

- **Project Reactor**
    - The Project Reactor is a fourth-generation reactive library, based on the Reactive Streams specification, for building non-blocking applications on the JVM.
    - Project Reactor libraries provide two implementations for the `Publisher` interface:
        - `Mono` returns only 0 or 1 element.
        - `Flux` can produce multiple values.
    - `Mono` vs `Flux`
        - Mono and Flux are both implementations of the `Publisher` interface.
        - In simple terms, we can say that when we're doing something like a computation or making a request to a database or an external service, and expecting a maximum of one result, then we should use `Mono`.
        - When we're expecting multiple results from our computation, database, or external service call, then we should use `Flux`.

- **Reactive Stream Workflow**
    1. The `Subscriber` will call `subscribe()` method of the `Publisher` to subscribe or register with the `Publisher`.
    2. The `Publisher` creates an instance of `Subscription` and sends it to `Subscriber` saying that your subscription is successful.
    3. Next, the Subscriber will call the request(n) method of Subscription to request data from the Publisher.
    4. Next, `Publisher` call `onNext(data)` method to send data to the `Subscriber`. `Publisher` call `onNext(data)` n times.
    5. Once the `Publisher` sends all the data to `Subscriber`, the next `Publisher` call `onComplete()` method to notify `Subscriber` that all the data has been sent. If there are any errors while sending the data then the `Publisher` call `onError()` method to send error details to the `Subscriber`.

