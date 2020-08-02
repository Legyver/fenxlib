# fenxlib skins
Custom skins for the fenxlib project.

# Guidelines
1. Please keep all skins under the skins folder to minimize the size of the root.
1. Do not make the core library depend on skins.  The goal is provide skins as separate dependencies.

# Adding a new skin
1. Add a folder following the convention skin-\<name>
1. Add a reference in the rootProject settings.gradle include('skins:folder-name')
1. Create a build.gradle in the root of your new folder following pattern of the build.gradle in the number folder
1. Add your source code
