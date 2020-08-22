package com.g2forge.reassert.reassert.algorithm.usage;

import org.junit.Test;

import com.g2forge.reassert.contract.term.StandardUsagePropogation;
import com.g2forge.reassert.core.model.artifact.Artifact;
import com.g2forge.reassert.list.ListCoordinates;
import com.g2forge.reassert.reassert.ATestReassert;
import com.g2forge.reassert.reassert.TestGraph;
import com.g2forge.reassert.reassert.algorithm.ReassertUsageVisitor;

public class TestReassertUsageVisitor extends ATestReassert {
	@Test
	public void commercial() {
		test("commercial");
	}
	
	@Override
	protected TestGraph load(final Artifact<ListCoordinates> artifact) {
		return new TestGraph(artifact, new ReassertUsageVisitor(StandardUsagePropogation.create()));
	}

	@Test
	public void unspecified() {
		test("unspecified");
	}
}
