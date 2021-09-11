# FileTree
## Usage
### Dependency
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.widgets.filetree', version: '2.1.0.0'
```
## Code
```java
         //Any files added via import or filesystem watches on added directories will be added here
        FileTreeRegistry fileTreeRegistry = new FileTreeRegistry();

        //while watching file system, only auto-add folders and xml files
        FileWatchHandler fileWatchHandler = new FileWatchHandler.Builder()
                .fileFilter(new SuffixFileFilter(".xml"))
                .build(fileTreeRegistry);

        BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
                ...
                //add a region on the left side of the app
                .left(new RegionInitializationOptions.SideBuilder("Files")
                        //add the FileExplorer to this region
                        .factory(new StackPaneRegionFactory(false, new SimpleFileExplorerFactory(fileTreeRegistry, fileWatchHandler))))
                .build();
```
## Themain

