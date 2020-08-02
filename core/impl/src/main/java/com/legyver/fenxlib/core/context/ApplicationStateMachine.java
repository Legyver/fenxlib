package com.legyver.fenxlib.core.context;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.util.hook.LifecyclePhase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ApplicationStateMachine {
	private static final List<AppStateObserver> appStateObservers = new ArrayList<>();

	private LifecyclePhase currentState = null;
	private boolean complete = false;

	public boolean begin(LifecyclePhase hook) throws CoreException {
		boolean proceed = hook != currentState;//avoid executing the same lifecycle phase twice

		LifecyclePhase next = null;
		switch (hook) {
			case BOOTSTRAP:
			case PRE_SHUTDOWN:
				next = hook; break;//no validation
			case PRE_INIT:
				validatePrecondition(hook.name(), LifecyclePhase.BOOTSTRAP);
				next = hook; break;
			case INIT:
				validatePrecondition(hook.name(), LifecyclePhase.PRE_INIT);
				next = hook; break;
			case POST_INIT:
				validatePrecondition(hook.name(), LifecyclePhase.INIT);
				next = hook; break;
			default:
				throw new CoreException("Unexpected hook: " + hook.name());
		}
		currentState = next;
		complete = false;
		if (proceed) {
			//new state
			for (AppStateObserver observer: appStateObservers) {
				observer.observe(currentState);
			}
		}
		return proceed;
	}

	private void validatePrecondition(String target, LifecyclePhase...preconditions) throws CoreException {
		if (complete == false) {
			throw new CoreException("Unable able to start lifecycle hook: " + target + ". Previous state: " + currentState + " not completed");
		} else if (Stream.of(preconditions)
				.noneMatch(precondition -> precondition == currentState)) {
			throw new CoreException("Unable to transition to state: " + target + ".  Precondition not met");
		}
	}

	public void end(LifecyclePhase hook) throws CoreException {
		if (currentState == hook) {
			complete = true;
		} else {
			throw new CoreException("Unexpected end of lifecycle hook: " + hook + ".  Current state " + currentState);
		}
	}

	public void reset() {
		currentState = null;
		complete = false;
	}

	public void addObserver(AppStateObserver appStateObserver) {
		appStateObservers.add(appStateObserver);
	}
}
