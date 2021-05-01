package com.legyver.fenxlib.core.impl.context;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.api.context.AppStateObserver;
import com.legyver.fenxlib.core.api.util.hook.LifecyclePhase;
import com.legyver.utils.graphrunner.Graph;
import com.legyver.utils.graphrunner.GraphExecutedCommand;
import com.legyver.utils.graphrunner.RunWithDependentsStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * The state machine representing the lifecycle of the application
 * BOOTSTRAP -> PRE_INIT -> INIT -> POST_INIT
 *                                          \
 *                                           PRE_SHUTDOWN
 * Note: PRE_SHUTDOWN does not have any prerequisite states.
 */
public class ApplicationStateMachine {
	private static final List<AppStateObserver> appStateObservers = new ArrayList<>();
	private final Graph graph;

	/**
	 * Construct the graph of application states to form the basis of the state machine
	 */
	public ApplicationStateMachine() {
		Node bootstrap = new Node(LifecyclePhase.BOOTSTRAP);
		Node preInit = new Node(LifecyclePhase.PRE_INIT);
		Node init = new Node(LifecyclePhase.INIT);
		Node postInit = new Node(LifecyclePhase.POST_INIT);
		Node preShutdown = new Node(LifecyclePhase.PRE_SHUTDOWN);
		graph = new Graph.Builder().nodes(bootstrap, preInit, init, postInit, preShutdown)
				.connect(new Graph.Connection()
						.from(bootstrap.getNodeName())
						.to(preInit.getNodeName()))
				.connect(new Graph.Connection()
						.from(preInit.getNodeName())
						.to(init.getNodeName()))
				.connect(new Graph.Connection()
						.from(init.getNodeName())
						.to(postInit.getNodeName()))
				.build();
	}

	/**
	 * Run all states up to and including the LifecyclePhase specified
	 * @param lifecyclePhase the lifecycle phase to run up to
	 * @param phaseConsumer a consumer that accepts the current phase of the lifecycle
	 * @throws CoreException if an error occurs in executing a lifecycle hook
	 */
	public void run(LifecyclePhase lifecyclePhase, ThrowingConsumer<LifecyclePhase> phaseConsumer) throws CoreException {
		graph.setStrategy(new RunWithDependentsStrategy(lifecyclePhase.name()));
		graph.executeStrategy((GraphExecutedCommand<Node>) (nodeName, phaseNode) -> {
			//run the lifecyclePhase
			phaseConsumer.accept(phaseNode.phase);
			//update observers of transition to new state
			for (AppStateObserver observer: appStateObservers) {
				observer.observe(phaseNode.phase);
			}
		});
	}

	/**
	 * Reset the underlying application lifecycle graph.
	 */
	public void reset() {
		graph.resetEvaluated();
	}

	/**
	 * Add an observer to be notified of a change in application state
	 * @param appStateObserver the observer to be notified
	 */
	public void addObserver(AppStateObserver appStateObserver) {
		appStateObservers.add(appStateObserver);
	}

	private class Node implements Graph.Payload {
		final LifecyclePhase phase;

		private Node(LifecyclePhase phase) {
			this.phase = phase;
		}

		@Override
		public String getNodeName() {
			return phase.name();
		}
	}
}
