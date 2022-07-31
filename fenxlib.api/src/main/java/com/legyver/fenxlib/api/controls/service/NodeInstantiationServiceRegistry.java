package com.legyver.fenxlib.api.controls.service;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.service.OrderedServiceDelegator;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.css.Styleable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Registry that {@link NodeInstantiationService} register with.
 */
public class NodeInstantiationServiceRegistry {
    private final OrderedServiceDelegator<NodeInstantiationService> orderedServiceDelegator;
    private final Map<Class, NodeInstantiationService> preferredLoaderForClass = new HashMap<>();
    private static NodeInstantiationServiceRegistry instance;

    /**
     * Construct a registry for Node Instantiation services
     */
    public NodeInstantiationServiceRegistry() {
        ServiceLoader<NodeInstantiationService> nodeInstantiationServiceLoader = ServiceLoader.load(NodeInstantiationService.class);
        orderedServiceDelegator = new OrderedServiceDelegator<>(nodeInstantiationServiceLoader);
    }

    /**
     * Get the singleton instance of the service registry for IconServices
     * @return the singleton instance
     */
    public static NodeInstantiationServiceRegistry getInstance() {
        if (instance == null) {
            synchronized (NodeInstantiationServiceRegistry.class) {
                if (instance == null) {
                    instance = new NodeInstantiationServiceRegistry();
                }
            }
        }
        return instance;
    }

    /**
     * Instantiate a node based on the available {@link NodeInstantiationService} on the classpath.
     * The DefaultNodeInstantiationService (in fenxlib.core) is only used if another is not provided.
     * If none can load the type of node, the returned value is null
     * @param <T> The type of the JavaFX control to load
     * @param klass the class of the control to instantiate
     * @param locationContext the location to register the control in
     * @param options options to use during control instantiation
     * @return the node
     * @throws CoreException if there is an error constructing the control
     */
    public <T extends Styleable> T instantiate(Class<T> klass, LocationContext locationContext, StyleableControlOptions<T> options) throws CoreException {
        NodeInstantiationService nodeInstantiationService = preferredLoaderForClass.get(klass);

        T result = null;
        if (nodeInstantiationService == null) {
            //figure out the preferred loader for this class
            synchronized (this) {
                int score = Integer.MAX_VALUE;
                for (Iterator<NodeInstantiationService> it = orderedServiceDelegator.iterator(); it.hasNext(); ) {
                    NodeInstantiationService service = it.next();
                    T temp = service.instantiate(klass, locationContext, options);
                    if (temp != null) {//only prefer this loader if it actually works
                        if (service.getPreference() < score) {
                            nodeInstantiationService = service;
                            result = temp;
                            score = service.getPreference();
                        }
                    }
                }
                preferredLoaderForClass.put(klass, nodeInstantiationService);
            }
        }
        if (result == null && nodeInstantiationService != null) {
            result = nodeInstantiationService.instantiate(klass, locationContext, options);
        }

        return result;
    }
}
