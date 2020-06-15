# Releases
## 1.1.1
### New stuff
- Factories
  - components
    - JFXTabPaneFactory
    - TabContentFactory
    - TabPaneFactory
### Refactoring
- Changes to support Tabs
  - NodeFactory now extends StyleableFactory
  - AbstractWrappingFactory parameterized type changed from Node to Styleable
## 1.1
### New stuff
- Factories
  - components
    - TextFactory
    - TextFlowFactory
  - regions
    - PopupMenuItemFactory
- Decorators
  - styles
    - StyleDecorator
- Utilities
  - Support for storing application config files on Windows in APPDATA
  - Support for multiple pre_shutdown hooks
 
### Refactoring
  - Moved file-centric utilities to 'files' package
  - Moved ApplicationOptions-related stuff to 'options' package
## 1.0
### New stuff
- Factories
  - components
    - AccordionMenuFactory
    - BladeFactory
    - BorderPaneFactory
    - DatePickerFactory
    - HBoxFactory
    - JFXDialogLayoutFactory
    - JFXListViewFactory
    - LabelFactory
    - ListViewFactory
    - MoreFieldFactory
    - RegionFactory
    - SliderFactory
    - SplitPaneFactory
    - StackPaneFactory
    - SvgIconFactory
    - SystemBrowserHyperlinkFactory
    - TextFieldFactory
    - TitledPaneFactory
    - VBoxFactory
    - WebViewFactory
  - regions
    - BottomRegionFactory
    - TopRegionFactory
  - menus
    - CloseCurrentFileFactory
    - FileOptionsChooserFactory
    - OpenRecentFileFactory
    - RecentlyOpenedFileFactory
    - SaveCurrentFileFactory
    - SelectDirectoryMenuFactory
    - SeparatorMenuItemFactory
    - ZipFileMenuFactory
- Decorators
  - styles
    - ButtonIconDecorator
    - ButtonTooltipDecorator
    - CssClassDecorator
  - menus
    - OpenFileDecorator
    - SaveFileDecorator
  - utils
    - GuidNodeDecorator
    - RegisterAsDecorator
- Skins
  - CurrencyFieldSkin
  - PercentageFieldSkin
- Utilities
  - AbstractBindingFactory
  - QueryableComponentRegistry
  - NamedComponentQuery
  - TypedComponentQuery
  - Application Lifecycle hooks
  - Support for storing application config files in user.home