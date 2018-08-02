# Composable Asynchronous Programming

- ## Making asynchronous requests with CompletableFutures
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
    
- ## Parallelism—via Streams or CompletableFutures?
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

