package com.legyver.fenxlib.core.controls.builder;

public abstract class BaseControlBuilder<B extends BaseControlBuilder> {
    private String name;
    private Boolean useTextForName = true;

    protected B me() {
        return (B) this;
    }

    public B builder() {
        return me();
    }

    public B name(String name) {
        this.name = name;
        return me();
    }

    public String getName() {
        if (this instanceof TextMixin && useTextForName) {
            return ((TextMixin) this).getText();
        }
        return name;
    }

}
