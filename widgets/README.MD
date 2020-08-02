# fenxlib widgets
Custom widget libraries for the fenxlib project.

# Guidelines
1. Please keep all widgets under the widgets folder to minimize the size of the root.
1. Do not make the core library depend on widgets.  The goal is provide widgets as separate dependencies.
1. It is acceptable to depend on the core-impl, since widgets will likely require access to the inner workings.

# Adding a new widget
1. Add a folder following the convention widget-\<name>
1. Add a reference in the rootProject settings.gradle include('widgets:folder-name')
1. Create a build.gradle in the root of your new folder following pattern of the build.gradle in the about folder
1. Add your source code
