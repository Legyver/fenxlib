package com.legyver.fenxlib.core.impl.files;

import com.legyver.utils.mapqua.mapbacked.RawMapAware;

/**
 * Creates a POJO from a map
 * @param <T> type of the POJO
 */
public interface SourceDocumentInstantiator<T extends RawMapAware> extends MapDecoratorPojoInstantiator<T> {

}
