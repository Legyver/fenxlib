package com.legyver.fenxlib.api;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.DefaultFileBrowseLocation;
import com.legyver.fenxlib.api.files.FileRegistry;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.api.locator.query.QueryableComponentRegistry;
import com.legyver.fenxlib.api.regions.ApplicationRegions;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.EventTarget;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

/**
 * God utility for directing the requests where they need to go
 */
public abstract class Fenxlib {

    private static QueryableComponentRegistry componentRegistry() {
        return ApplicationContext.getComponentRegistry();
    }

    /**
     * Get the location of the menu bar
     * @return the menu bar location
     */
    public static LocationContext menusLocation() {
        return new DefaultLocationContext(ApplicationRegions.MENUS.getName());
    }

    /**
     * Get a menu by name
     * @param name the name of the menu
     * @return the menu, if it is registered
     * @throws CoreException if there is an error executing the query
     */
    public static Optional<Menu> getMenu(String name) throws CoreException {
        LocationContext rootMenuContext = new LocationContextDecorator(menusLocation());
        rootMenuContext.setName(name);
        return getMenu(rootMenuContext);
    }

    /**
     * Get a menu from a specific location
     * @param locationContext the location of the menu item
     * @return the menu, if it is registered
     * @throws CoreException if there is an error executing the query
     */
    public static Optional<Menu> getMenu(LocationContext locationContext) throws CoreException {
        return new ComponentQuery.QueryBuilder().fromLocation(locationContext)
                .typed(Menu.class).execute();
    }

    /**
     * Register a component with the registry
     * @param context the context specifying the register-as information
     * @param target the node to be registered
     * @param <T> the type of the JavaFX component
     */
    public static <T extends EventTarget> void register(LocationContext context, T target) {
        register(context, target, false);
    }

    /**
     * Register a component with the registry.
     * Note: if typeOnly is false, then any node currently sharing the same location context will be overwritten by this target.
     * @param context the context specifying the register-as information
     * @param target the node to be registered
     * @param <T> the type of the JavaFX component
     * @param typedOnly flag for registering just as type
     */
    public static <T extends EventTarget> void register(LocationContext context, T target, boolean typedOnly) {
        componentRegistry().register(context, target, typedOnly);
    }

    /**
     * Remove the registration for component at the specified location
     * @param context the context specifying the registered-as information
     */
    public static void deregister(LocationContext context) {
        componentRegistry().deregister(context);
    }

    private static Stage primaryStage() {
        return ApplicationContext.getPrimaryStage();
    }

    /**
     * Get the primary stage of the application
     * @return the primary stage of the application
     */
    public static Stage getPrimaryStage() {
        return primaryStage();
    }

    private static FileRegistry fileRegistry() {
        return ApplicationContext.getFileRegistry();
    }

    /**
     * Set the default browse to location when opening file browsers in your application
     * @param browseTo the default browse location
     */
    public static void setDefaultBrowseLocation(File browseTo) {
        DefaultFileBrowseLocation defaultFileBrowseLocation = fileRegistry().getDefaultFileBrowseLocation();
        defaultFileBrowseLocation.setInitialDirectory(browseTo);
    }

    /**
     * Get the HostServices for the client desktop
     * @return the host services
     */
    public static HostServices getHostServices() {
        return ApplicationContext.getHostServices();
    }

    /**
     * Register a JavaFX application with the framework.
     * This exposes things like desktop Host Services to be registered with the framework.
     * @param application the application to register
     */
    public static void registerApplication(Application application) {
        ApplicationContext.setHostServices(application.getHostServices());
    }
}
