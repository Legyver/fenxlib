package com.legyver.fenxlib.api.lifecycle.hooks;

import com.legyver.fenxlib.api.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Lifecycle hooks converted to an internalized map
 */
public class LifecycleHookMap {
    private final Map<String, List<ApplicationLifecycleHook>> hookMap = new HashMap<>();

    /**
     * Add all hooks unless one has been marked as usurp (by default usurp is false in the ApplicationLifecycleHook interface).
     * If a hook is marked as usurp, only that hook from the list of similarly-named hooks is added.
     */
    public void registerHooks() {
        //add all hooks unless one has been marked as usurp (by default usurp is false in the ApplicationLifecycleHook interface)
        for (String name : hookMap.keySet()) {
            List<ApplicationLifecycleHook> hookList = hookMap.get(name);
            boolean usurped = false;
            for (ApplicationLifecycleHook hook : hookList) {
                if (hook.usurp()) {
                    registerLifecycleHook(hook);
                    usurped = true;
                    break;
                }
            }
            if (!usurped) {
                for (ApplicationLifecycleHook hook : hookMap.get(name)) {
                    registerLifecycleHook(hook);
                }
            }
        }
    }

    private void registerLifecycleHook(ApplicationLifecycleHook applicationLifecycleHook) {
        ApplicationContext.getApplicationLifecycleHookRegistry()
                .registerHook(applicationLifecycleHook.getLifecyclePhase(), applicationLifecycleHook.getExecutableHook(), applicationLifecycleHook.getPriority());
    }

    //map-ish methods
    /**
     * Add a lifecycle hook
     * @param name the name of the hook for usurp purposes
     * @param hook the hook to add
     */
    public void addLifecycleHook(String name, ApplicationLifecycleHook hook) {
        List<ApplicationLifecycleHook> hookList = hookMap.computeIfAbsent(name, x -> new ArrayList<>());
        hookList.add(hook);
    }

    /**
     * Check if the map has any hooks by that name
     * @param name the name of the hook
     * @return true if there are any hooks known by that name
     */
    public boolean containsHook(String name) {
        return hookMap.containsKey(name);
    }

    /**
     * Add a hook to a map if there are no hooks with that name currently in the map
     * @param hookClass the class of the hook.  The name is typically based on class.getSimpleName()
     * @param fallBackProducer the function to create a new hook if none exists
     * @param <T> the type of the application lifecycle hook
     */
    public <T extends ApplicationLifecycleHook> void computeIfAbsent(Class<T> hookClass, Function<String, T> fallBackProducer) {
        computeIfAbsent(hookClass.getSimpleName(), fallBackProducer);
    }

    /**
     * Add a hook to a map if there are no hooks with that name currently in the map.
     * If a fallBackProducer produces a null hook, the hook will not be added.
     * @param hookName the name of the hook.  The name is typically based on class.getSimpleName()
     * @param fallBackProducer the function to create a new hook if none exists
     * @param <T> the type of the application lifecycle hook
     */
    public <T extends ApplicationLifecycleHook> void computeIfAbsent(String hookName, Function<String, T> fallBackProducer) {
        if (!containsHook(hookName)) {
            T hook = fallBackProducer.apply(hookName);
            if (hook != null) {
                addLifecycleHook(hookName, hook);
            }
        }
    }
}
