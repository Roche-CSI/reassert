package com.g2forge.reassert.contract.analyze.model.findings;

import com.g2forge.reassert.contract.analyze.model.findings.IRiskFinding;
import com.g2forge.reassert.core.model.contract.TermRelation;
import com.g2forge.reassert.expression.explain.model.IExplained;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class SuspiciousUsageFinding implements IRiskFinding {
	protected final IExplained<TermRelation> result;

	protected final String attribute;
}
