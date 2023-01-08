# fenxlib tuktukfx
An extension of the tuktukfx task library for fenxlib

## Usage
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.extensions.tuktukfx', version: '3.0.0-beta.9'
```


## Example
```java
public class Example {
    private void sumbitTask(FileSystemWatchTaskContext context) {
        //create the task
        FileSystemWatchTask sourceScanTask = new FileSystemWatchTask(context);
        JavaFxAdapter adapter = new JavaFxAdapter<>(sourceScanTask);
        //configure the adapter to have the task loop stop gracefully when the application starts shutting down
        TaskAbortBinding.taskAbortObservesShutdown(adapter);
        
        //since we're using a thread pool to monitor the filesystem, shut down the thread pool on application exit
        //we use a delay matching the tryAcquire timeout in FileSystemWatchTaskProcessor#runUntilAbort() so we don't get an interrupted exception in the latter
        TaskExecutor.getInstance().configure().delayShutdown(2000);
        TaskExecutor.getInstance().submitTask(adapter);
    }
}
```