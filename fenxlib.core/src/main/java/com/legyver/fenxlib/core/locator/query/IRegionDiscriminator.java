package com.legyver.fenxlib.core.locator.query;

/**
 * Discriminate components within a region based on sub-region
 */
public interface IRegionDiscriminator extends IQueryDiscriminator {
	/**
	 * The sub-region to query in of the parent region
	 * @param name the name of the sub-region
	 * @return the query builder
	 */
	IRegionDiscriminator inSubRegion(String name);
}
