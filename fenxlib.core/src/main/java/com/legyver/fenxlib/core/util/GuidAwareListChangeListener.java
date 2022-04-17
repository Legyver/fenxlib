package com.legyver.fenxlib.core.util;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.Iterator;
import java.util.Objects;

/**
 * List change listener to sync the contents of a list to the children displayed in a Pane
 * @param <T> the type of the pane children
 */
public class GuidAwareListChangeListener<T> implements ListChangeListener<T> {
    private final ObservableList<Node> paneChildren;

    /**
     * Construct a listener that will marshall any updates to the provided pane children
     * @param children the children of the pane that needs to be updated
     */
    public GuidAwareListChangeListener(ObservableList<Node> children) {
        this.paneChildren = children;
    }

    @Override
    public void onChanged(Change<? extends T> c) {
        c.next();
        if (c.wasAdded()) {
            for (T added : c.getAddedSubList()) {
                if (added instanceof Node) {
                    GuidUtil.initGuid((Node) added);
                }
                paneChildren.add((Node) added);
            }
        } else if (c.wasRemoved()) {
            for (T removed : c.getRemoved()) {
                String removedGuid = GuidUtil.getGuid((Node) removed);
                for (Iterator<Node> it = paneChildren.iterator(); it.hasNext(); ) {
                    Node node = it.next();
                    String guid = GuidUtil.getGuid(node);

                    if (Objects.equals(removedGuid, guid)) {
                        it.remove();
                    }
                }
            }
        }
    }

}
