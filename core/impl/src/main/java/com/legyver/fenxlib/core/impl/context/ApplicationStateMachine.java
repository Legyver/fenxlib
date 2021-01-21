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

public class ApplicationStateMachine {
	private static final List<AppStateObserver> appStateObservers = new ArrayList<>();
	private final Graph graph;

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

	public void run(LifecyclePhase hook, ThrowingConsumer<LifecyclePhase> phaseConsumer) throws CoreException {
		graph.setStrategy(new RunWithDependentsStrategy(hook.name()));
		graph.executeStrategy((GraphExecutedCommand<Node>) (nodeName, phaseNode) -> {
			//run the hook
			phaseConsumer.accept(phaseNode.phase);
			//update observers of transition to new state
			for (AppStateObserver observer: appStateObservers) {
				observer.observe(phaseNode.phase);
			}
		});
	}

	public void reset() {
		graph.resetEvaluated();
	}

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
