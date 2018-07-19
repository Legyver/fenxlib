package com.legyver.fenxlib.util;

import java.util.HashMap;
import java.util.Map;

/**
 * To avoid breaking files that may be opened by various versions of same software, we always reconcile the 
 */
public class ForwardBackwardReconcileCache {
	private final static Map<String, ReconciliationCtx> cachedModels = new HashMap<>();
	
	static void cache(Map<String, Map> originalMap, ForwardCompatibleModel cachedReferenceModel) {
		cachedModels.put(cachedReferenceModel.getUuid(), new ReconciliationCtx(originalMap, cachedReferenceModel));
	}

	static ReconciliationCtx retrieve(String uuid) {
		return cachedModels.get(uuid);
	}
	
	public static class ReconciliationCtx {
		private final Map<String, Map> originalMap;
		private final ForwardCompatibleModel referenceModel;

		public ReconciliationCtx(Map<String, Map> originalMap, ForwardCompatibleModel referenceModel) {
			this.originalMap = originalMap;
			this.referenceModel = referenceModel;
		}

		public Map<String, Map> getOriginalMap() {
			return originalMap;
		}

		public ForwardCompatibleModel getReferenceModel() {
			return referenceModel;
		}
	}
}
