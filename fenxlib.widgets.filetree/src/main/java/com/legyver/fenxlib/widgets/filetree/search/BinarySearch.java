package com.legyver.fenxlib.widgets.filetree.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Standard binary search algorithm for a list of named items
 * @param <T> the type of the list contents
 */
public class BinarySearch<T extends INamedItem> {
    private static final Logger logger = LogManager.getLogger(BinarySearch.class);
    /**
     * Items to search
     */
    private final List<T> itemsToSearch;

    /**
     * Construct a binary search for a particular list of items
     * @param itemsToSearch items to search
     */
    public BinarySearch(List<T> itemsToSearch) {
        this.itemsToSearch = itemsToSearch;
    }

    /**
     * Search for a particular string in a list of named items
     * @param target the string to search for
     * @return the result with the appropriate item and/or index reached.
     */
    public Result<T> search(String target) {
        //figure out if we need to update it or insert it (in-order) by binary search
        T existing = null;
        int upperBound = itemsToSearch.size() - 1;
        int lowerBound = 0;
        int index = itemsToSearch.size();

        while (lowerBound <= upperBound) {
            index = (lowerBound + upperBound) / 2;
            T item = itemsToSearch.get(index);
            String name = item.getName();
            int compareTo = name.compareTo(target);
            if (compareTo == 0) {
                logger.trace("Found match: {}", name);
                existing = item;
                break;
            }
            if (compareTo > 0) {
                logger.trace("{} is greater than {}", name, target);
                upperBound = index - 1;
            }
            if (compareTo < 0) {
                logger.trace("{} is less than {}", name, target);
                lowerBound = index + 1;
            }
        }
        return new Result<>(existing, index);
    }

    /**
     * Result class for the binary search
     * @param <T> the type of the items in the list being searched
     */
    public static class Result<T extends INamedItem> {
        /**
         * The found item (if any)
         */
        private final T item;
        /**
         * The index to add the item at
         */
        private final int index;

        private Result(T item, int index) {
            this.item = item;
            this.index = index;
        }

        /**
         * Get the item if found
         * @return the found item (else null)
         */
        public T getItem() {
            return item;
        }

        /**
         * Return the index the item should be added at
         * @return the index
         */
        public int getIndex() {
            return index;
        }
    }
}
