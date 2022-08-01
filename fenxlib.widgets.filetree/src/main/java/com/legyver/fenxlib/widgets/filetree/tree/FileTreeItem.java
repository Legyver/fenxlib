package com.legyver.fenxlib.widgets.filetree.tree;

import com.legyver.fenxlib.api.i18n.ResourceBundleServiceRegistry;
import com.legyver.fenxlib.core.locator.IPropertyAware;
import com.legyver.fenxlib.widgets.filetree.nodes.INodeReference;
import com.legyver.fenxlib.widgets.filetree.search.BinarySearch;
import com.legyver.fenxlib.widgets.filetree.search.INamedItem;
import com.legyver.fenxlib.widgets.filetree.tree.internal.RefreshingTreeItem;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Base FileTreeItem class adding functionality to the base {@link TreeItem} implementation.
 * - association with the INodeReference for the TreeItem
 * - placeholder text for while folder is refreshing
 * - listens on the {@link TreeItem#expandedProperty()} and refreshes the folder children every time it is expanded
 *
 * @param <T> the type of the {@link INodeReference} associated with the particular node.
 */
public abstract class FileTreeItem<T extends INodeReference> extends TreeItem implements INamedItem, IPropertyAware {
    private static final Logger logger = LogManager.getLogger(FileTreeItem.class);

    /**
     * Properties to associate with the node.  Primarily used to set GUIDs on the TreeItem similarly to how it is done on Node.  See {@link Node#getProperties()}
     */
    private final ObservableMap<Object, Object> properties = FXCollections.observableMap(new HashMap<>());
    /**
     * The reference to the thing the FileTreeItem represents
     */
    protected final INodeReference nodeReference;

    /**
     * Flag for this child to be removed from parent
     */
    protected boolean canBeRemoved;

    private final Map<String, BooleanProperty> menuItemEnabledProperties = new HashMap<>();

    /**
     * Construct a FileTreeItem with the provided label, graphic, and associated file reference
     * @param label the label to display on the node
     * @param graphic the graphic to display on the node
     * @param nodeReference the file or other attribute the FileTreeItem represents
     */
    @SuppressWarnings("unchecked")
    public FileTreeItem(String label, Node graphic, INodeReference nodeReference) {
        super(label, graphic);
        this.nodeReference = nodeReference;
        initChildRefresher();
        addFauxChild();

        //TreeRoot will not have an actual nodeReference since it is a faux folder whose children are the directories imported
        if (nodeReference != null) {
            nodeReference.setTreeNode(this);
        }
    }

    protected static String i18n(String label) {
        return ResourceBundleServiceRegistry.getInstance().getMessage(label);
    }

    private void initChildRefresher() {
        expandedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                refresh();
            }
        }));
    }

    /**
     * Add a placeholder to display while the item is being refreshed
     */
    @SuppressWarnings("unchecked")
    protected void addFauxChild() {
        getChildren().add(new RefreshingTreeItem());
    }

    /**
     * Refresh the node.
     * This will remove any removed children and add any new children.
     */
    public void refresh() {
        setExpanded(true);
        Platform.runLater(() -> {
            refreshAndCheckIfRemovable();
        });
    }

    /**
     * Get the reference to the filesystem file/folder this tree node represents
     * @return the file reference
     */
    public INodeReference getNodeReference() {
        return nodeReference;
    }

    /**
     * Get the name of the node
     * @return the name
     */
    @Override
    public String getName() {
        return nodeReference.getName();
    }

    /**
     * Get the unique identifier of the node
     * @return the unique identifier of the node
     */
    public String getUniqueIdentifier() {
        return nodeReference.getUniqueIdentifier();
    }

    /**
     * Get the properties associated with this node
     * @return the properties
     */
    @SuppressWarnings("unchecked")
    @Override
    public ObservableMap<Object, Object> getProperties() {
       return properties;
    }

    /**
     * Add a child to the node
     * @param fileTreeItem the child to add
     */
    @SuppressWarnings("unchecked")
    public void addChild(FileTreeItem fileTreeItem) {
        String newNodeName = fileTreeItem.nodeReference.getName();
        ObservableList<FileTreeItem> children = getChildren();
        if (children.size() == 1) {
            //remove the placeholder
            TreeItem item = children.iterator().next();
            if (item instanceof RefreshingTreeItem) {
                children.clear();
            }
        }

        int offset = 0;
        List<FileTreeItem> childDirectories = children.stream()
                .filter(child -> child.nodeReference.isDirectory())
                .collect(Collectors.toList());

        List<FileTreeItem> searchThese;
        if (fileTreeItem.nodeReference.isDirectory()) {
            searchThese = childDirectories;
        } else {
            offset = childDirectories.size();
            searchThese = children.stream()
                    .filter(child -> !child.nodeReference.isDirectory())
                    .collect(Collectors.toList());
        }

        BinarySearch.Result<FileTreeItem> result = new BinarySearch<>(searchThese).search(newNodeName);

        if (result.getItem() == null) {
            logger.trace("Adding new file: {}", newNodeName);
            children.add(result.getIndex() + offset, fileTreeItem);
        } else {
            logger.trace("Replacing file already exists: {}", newNodeName);
        }

        logger.trace("refreshing item: {}", newNodeName);
        if (result.getItem() != null) {
            result.getItem().refreshAndCheckIfRemovable();
        }
        logger.trace("item [{}] refresh complete", newNodeName);
    }

    /**
     * Get the property for the enabled flag of a particular context menu item.
     * If the property has not already explicitly set is initiated with the value 'false'.
     * @param name the name of the menu item
     * @return the enabled property for the specific menu item
     */
    public BooleanProperty getMenuItemEnabled(String name) {
        return this.menuItemEnabledProperties.computeIfAbsent(name, (e) -> new SimpleBooleanProperty(false));
    }

    /**
     * Add an enabled property binding for the ContextMenu unique to this node in the tree.
     * By convention, the name should match the name of the MenuItem.
     * @param name name of shortcut menu item
     * @param menuItemEnabledProperty the property
     */
    public void addMenuItemEnabledProperty(String name,  BooleanProperty menuItemEnabledProperty) {
        this.menuItemEnabledProperties.put(name, menuItemEnabledProperty);
    }

    /**
     * Refresh children
     * @return true if this node can be removed from parent
     */
    abstract public boolean refreshAndCheckIfRemovable();

    /**
     * Flag the tree item for removal
     */
    public void flagForRemoval() {
        canBeRemoved = true;
    }


}
