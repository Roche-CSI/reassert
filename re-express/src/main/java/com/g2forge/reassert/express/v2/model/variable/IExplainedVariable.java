package com.g2forge.reassert.express.v2.model.variable;

import com.g2forge.alexandria.java.fluent.optional.IOptional;
import com.g2forge.reassert.express.v2.model.IExplained;

public interface IExplainedVariable<Name, Value> extends IExplained<Value> {
	@Override
	public default Value get() {
		return getExplained().get().get();
	}

	public IOptional<IExplained<Value>> getExplained();

	public IVariable<Name, Value> getVariable();

	public default boolean isBound() {
		return !getExplained().isEmpty();
	}
}
