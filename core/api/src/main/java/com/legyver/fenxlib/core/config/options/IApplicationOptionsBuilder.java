package com.legyver.fenxlib.core.config.options;

import com.legyver.fenxlib.core.config.options.mixins.HookRegistrationMixin;

public interface IApplicationOptionsBuilder<B extends IApplicationOptionsBuilder> {
	B mixinLifecycleHook(HookRegistrationMixin hookRegistrationMixin);
}
