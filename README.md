# Composable Asynchronous Programming

## Making asynchronous requests with CompletableFutures
  - `CompletableFuture::supplyAsync` and `CompletableFuture::join` should be in 
    two separate stream pipelines
    
    ```
    List<CompletableFuture<String>> priceFutures = this.shops.stream()
            .map(s -> CompletableFuture.supplyAsync(
                    () -> String.format("%s price is %.2f", s.getName(),
                            s.getPrice(this.availableProduct)),
                    this.provideExecutor()))
            .collect(Collectors.toList());

    // When
    List<String> prices = priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
     ```

  - Using a custom Executor
    In practice,  it will be wasteful to have more threads than queries, 
    because in doing so you’ll have threads in your pool that are never used.
    For this reason, you need to set up an Executor with a fixed number of 
    threads equal to the number of queries, so there will be exactly one thread 
    for each query
    
  - Must also set an upper limit of 100 threads in order to avoid a server 
    crash for a larger number of queries
    ```
      private Executor provideExecutor() {
          int shopSize = this.shops.size();
          int threadPoolSize = Math.min(shopSize, THREAD_LIMIT);
          return Executors.newFixedThreadPool(threadPoolSize,
                  new ThreadFactory() {
                      @Override
                      public Thread newThread(Runnable r) {
                          Thread t = new Thread(r);
                          t.setDaemon(true);
                          return t;
                      }
                  });
      }
    ```
    
  - Should create a pool made of daemon threads. A Java program can’t terminate
    or exit while a normal thread is executing, so a leftover thread waiting for 
    a never-satisfiable event causes problems. 
    By contrast, marking a thread as a daemon means it can be killed on program 
    termination. There’s no performance difference.
    
  - It's a good idea to create an Executor that better fits the characteristics
    of your application and to make use of CompletableFutures to submit their
    tasks to it. This is almost always an effective strategy and something to 
    consider when making intensive use of asynchronous operations.
    
## Parallelism—via Streams or CompletableFutures?
  - There are two different ways to do parallel computing on a collection: 
  
    - Either convert it to a parallel stream and use operations like map on it
    
    - Or iterate over the collection and spawn operations within a 
      CompletableFuture
      
      - This solution provides more control using resizing of thread pools, 
        which helps ensure that your overall computation doesn’t block just 
        because all of your fixed number of threads are waiting for I/O.
        
  - Advice for using these APIs is as follows:
  
    - If doing computation-heavy operations with no I/O, then the Stream 
      interface gives the simplest implementation and one likely to be the most 
      efficient (if all threads are compute-bound (計算密集型), then there’s no 
      point in having more threads than processor cores).
      
    - On the other hand, if your parallel units of work involve waiting for I/O 
      or network connections, then CompletableFutures give more flexibility and 
      the ability to match the number of threads to the wait/computer, or W/C 
      ratio. Another reason to avoid using parallel streams when I/O waits are 
      involved in the stream-processing pipeline is that the laziness of streams 
      can make it harder to reason about when the waits actually happen.

## Pipelining Asynchronous Tasks

```
public class CompletableFuture<T> extends Object implements Future<T>, 
        CompletionStage<T>
```

### Composing Synchronous Operations: CompletionStage Transformation

  - `CompletionStage::thenApply`
    ```
    <U> CompletionStage<U> thenApply(Function<? super T, ? extends U> fn)
    ```
    
    - Returns a new `CompletionStage` that, when this stage completes normally, 
      is executed with this stage's result as the argument to the supplied 
      function. 
      
    - `thenApply` does not block the code
    
    - `thenApply` transforms `CompletionStage<T>` to `CompletionStage<U>`  
    
    - `CompletionStage<T>` transforms the value it contains using the lambda 
      expression passed to the `thenApply` method
      
  - `CompletionStage::thenApplyAsync`
    ```
    <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn)
    ```
    - Returns a new CompletionStage that, when this stage completes normally, 
      is executed using this stage's default asynchronous execution facility, 
      with this stage's result as the argument to the supplied function. 
      
    - In general, a method without the suffix , `Async`, in its name executes 
      its task in the same Async thread as the previous task, whereas a method 
      terminating with `Async` always submits the succeeding task to the thread 
      pool, so each of the tasks can be handled by a different thread
      
### Composing Asynchronous Operations

  - Composing other `Futures` : `CompletionStage::thenCompose`
  
  - **Purpose**: to invoked the thenCompose method on one CompletableFuture
    and passed to it a second CompletableFuture, which needed as input the 
    value resulting from the execution of the first
    
  ```
  <U> CompletionStage<U> thenCompose(
          Function<? super T, ? extends CompletionStage<U>> fn)
  ```
  
  - Returns a new `CompletionStage` that, when this stage completes normally, is 
    executed with this stage as the argument to the supplied function
    
  - The `CompletionStage` API provides the `thenCompose` method allows to 
    pipeline two asynchronous operations, **passing the result of the first 
    operation to the second operation when it becomes available**
    
  - In other words, it's possible to compose two `CompletionStage` by invoking 
    the `thenCompose` method on the first CompletableFuture and passing to it a 
    Function
    
    - This Function has as argument the value returned by that first 
    `CompletionStage` when it completes
    
    - And it returns a second `CompletionStage` that uses the result of the 
      first as input for its computation
  
  ```
  List<CompletableFuture<String>> priceFutures = this.shops.stream()
                .map(s -> CompletableFuture.supplyAsync(
                        () -> s.getPriceQuote(this.availableProduct), 
                        executor))
                .map(f -> f.thenApply(Quote::parse))
                .map(f -> f.thenCompose(
                        q -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(q), 
                                executor)))
                .collect(Collectors.toList());
  ```

### Combining Asynchronous Operations

  - Combining other `Future` : `CompletionStage::thenCombine
  
  - **Purpose**: to combine the results of the operations performed by two 
    completely independent `CompletionStage`, and don’t have to wait for the 
    first to complete before starting on the second
    
  ```
  <U, V> CompletionStage<V> thenCombine(
           CompletionStage<? extends U> other, 
           BiFunction<? super T, ? super U, ? extends V> fn)
  ```
  
  - Returns a new `CompletionStage` that, when this and the other given stage 
    both complete normally, is executed with the two results as arguments to 
    the supplied function
    
  - The `thenCombine` method takes as second argument a `BiFunction`, which 
    defines how the results of the two CompletableFutures are to be combined 
    when they both become available
    
  - `thenCombine` method also comes with an Async variant. In this case, using 
    the `thenCombineAsync` method will cause the combination operation defined 
    by the `BiFunction` to be submitted to the thread pool and then executed 
    asynchronously in a separate task
    
  ```
  List<CompletableFuture<Double>> priceFutures = this.shops.stream()
                  .map(s -> CompletableFuture.supplyAsync(
                          () -> s.getPrice(this.availableProduct), 
                          executor))
                  .map(cf -> cf.thenCombine(
                          CompletableFuture.supplyAsync(
                                  () -> ExchangeService.getRate(
                                          ExchangeService.Money.EUR,
                                          ExchangeService.Money.USD)),
                          (price, rate) -> price * rate))
                  .collect(Collectors.toList());
  ```
    
### Reacting to a CompletableFuture Completion

  - `CompletionStage::thenAccept`
    Returns a new `CompletionStage` that, when this stage completes normally, 
    is executed with this stage's result as the argument to the supplied action
  ```
  CompletionStage<Void> thenAccept(Consumer<? super T> action)
  ```
  
  - This operation simply registers an action on each `CompletionStage`
  
  - This action consumes the value of the `CompletionStage` as soon as it 
    completes
    
  - This method takes as argument a `Consumer` of the value with which it 
    completes
    
  - `thenAccept` method also has an Async variant named thenAcceptAsync
    The Async variant schedules the execution of the `Consumer` passed to it 
    on a new thread from the thread pool instead of directly performing it 
    using the same thread that completed the CompletableFuture
    
    - To avoid an unnecessary context switch, and want to react to the 
      completion of the `CompletionStage` as soon as possible, 
      (instead of risking having to wait for a new thread to be available)
      using `thenAccept` is enough

  - `thenAccept` returns a `CompletionStage<Void>` because this method 
    already specifies how to consume the result produced by the `CompletionStage` 
    when it becomes available 
    
    - For a `Stream` of `CompletionStage<Void>`, to give the slowest one a 
      chance to provid its response and be consumed:
      
      - First of all, put all `CompletionStage<Void>' into an array
      
      - Second, wait for completion of all of them by using: 
        `CompletableFuture::allOf`
        
        ``` 
        public static CompletableFuture<Void> allOf(
                CompletableFuture<?>... cfs)
        ```
        
        - Returns a new CompletableFuture that is completed when all of the 
          given `CompletableFutures` complete. 
          
        - If any of the given CompletableFutures complete exceptionally, then 
          the returned `CompletableFuture` also does so, with a 
          `CompletionException` holding this exception as its cause
          
        - Otherwise, **the results**, if any, of the given `CompletableFutures` 
          are **not reflected in the returned `CompletableFuture`**, but may be 
          obtained by inspecting them individually. 
          
        - If no `CompletableFutures` are provided, returns a CompletableFuture 
          completed with the value null
          
        - Among the applications of this method is to await completion of a set 
          of independent `CompletableFutures` before continuing a program, as 
          in: 
          
          ```CompletableFuture.allOf(c1, c2, c3).join();```
          
        - `CompletableFuture.allOf(futures).join();`
          The `allOf` factory method takes as input an array of 
          `CompletableFutures` and returns a `CompletableFuture<Void>` that’s 
          completed only when all the `CompletableFutures` passed have 
          completed
          
        - This means that invoking join on the `CompletableFuture` returned by 
          the `allOf` method provides an easy way to wait for the completion of 
          all the `CompletableFutures` in the original stream
          
        - This is useful bcause it can then display a result message saying 
          “All result rereturned or timed out,”  
          
        - So a user doesn’t keep wondering whether more result might become 
          available
          
        - To wait for the completion of only one of the `CompletableFutures` in 
          an array, perhaps if consulting two servers and are happy to take the 
          result of the first to respond
          
          - In this case, can similarly use the `anyOf` factory method
          
          - As a matter of detail, this method takes as input an array of 
            `CompletableFutures` and returns a `CompletableFuture<Object>`
            that completes with the same value as the first-to-complete 
            `CompletableFuture`

    ```
    // Given
    long startTime = System.nanoTime();

    final Executor executor = this.provideExecutor();

    List<CompletableFuture<String>> priceFutures = this.shops.stream()
            .map(s -> CompletableFuture.supplyAsync(
                    () -> s.getPriceQuote(this.availableProduct), 
                    executor))
            .map(f -> f.thenApply(Quote::parse))
            .map(f -> f.thenCompose(
                    q -> CompletableFuture.supplyAsync(
                            () -> Discount.applyDiscount(q), 
                            executor)))
            .collect(Collectors.toList());

    // When
    CompletableFuture[] cfs = priceFutures.stream()
            .map(f -> f.thenAccept(System.out::println))
            .toArray(s -> new CompletableFuture[s]);

    CompletableFuture.allOf(cfs).join();

    long duration = (System.nanoTime() - startTime) / 1_000_000;
    ```
    
## Summary

- Executing relatively long-lasting operations using asynchronous tasks can
  increase the performance and responsiveness of your application, especially 
  if it relies on one or more remote external services
  
- You should consider providing an asynchronous API to your clients

  - You can easily implement it using `CompletableFutures` features
  
- A `CompletableFuture` also allows you to propagate and manage errors generated
  within an asynchronous task
  
- You can asynchronously consume from a synchronous API by simply wrapping its 
  invocation in a `CompletableFuture` 
  
- You can compose or combine multiple asynchronous tasks both when they’re
  independent and when the result of one of them is used as the input to 
  another

- You can register a callback on a `CompletableFuture` to reactively execute 
  some code when the Future completes and its result becomes available
  
- You can determine when all values in a list of `CompletableFutures` have 
  completed, or alternatively you can wait for just the first to complete

