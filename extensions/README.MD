# fenxlib extensions
Extension libraries for the fenxlib core library.

# Guidelines
1. Please keep all extensions under the extensions folder to minimize the size of the root.
1. Do not make the core library depend on extensions.  The goal is provide extensions as separate dependencies.
1. Do not depend on the core:impl module except for tests.  Use the core:api module instead.

# Adding a new extension
1. Add a folder following the convention ext-\<name> where name is the name of the library the extension supports, ie: ext-tuktukfx.  This is done to keep artifact identification intuitive and avoid collisions in module names.
1. Add a reference in the rootProject settings.gradle include('extensions:folder-name')
1. Create a build.gradle in the root of your new folder following pattern of the build.gradle in the tuktukfx folder
1. Add your source code
