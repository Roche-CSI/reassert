package com.g2forge.reassert.reassert.model.finding;

import org.slf4j.event.Level;

import com.g2forge.reassert.contract.analyze.model.findings.IRiskFinding;
import com.g2forge.reassert.contract.eee.explain.model.IExplained;
import com.g2forge.reassert.core.model.contract.TermRelation;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class TestRiskFinding implements IRiskFinding {
	protected final Level level;

	protected final String description;

	@Override
	public IExplained<TermRelation> getResult() {
		throw new UnsupportedOperationException();
	}
}
