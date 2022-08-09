package com.legyver.fenxlib.api.controls.builder;

import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;

/**
 * Base builder for specifying options for JavaFX components
 * @param <B> type of the sub-classing builder
 */
public abstract class BaseControlBuilder<B extends BaseControlBuilder> {
    private String name;
    private Boolean useTextForName = true;

    /**
     * convenience method to return the typed sub-classing builder instance
     * @return this builder
     */
    protected B me() {
        return (B) this;
    }

    /**
     * Method used by mixins to obtain the sub-classing builder instance
     * @return this builder
     */
    public B builder() {
        return me();
    }

    /**
     * Specify a name for the control.
     * This name is used in registering the component
     * @param name the name of the component
     * @return this builder
     */
    public B name(String name) {
        this.name = name;
        return me();
    }

    /**
     * Get the name of the control.
     * In the event that the subclassing builder is using a TextMixin and the {@link #useTextForName} flag is set
     * then the text of the component will be used as the name so a separate name is not necessary
     * @return the name
     */
    public String getName() {
        if (this instanceof TextOptionMixin && useTextForName) {
            return ((TextOptionMixin) this).getText();
        }
        return name;
    }

}
