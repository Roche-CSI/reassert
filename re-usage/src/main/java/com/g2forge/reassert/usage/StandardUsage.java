package com.g2forge.reassert.usage;

import com.g2forge.reassert.core.model.contract.ITerms;
import com.g2forge.reassert.core.model.contract.Terms;
import com.g2forge.reassert.core.model.contract.usage.IUsage;
import com.g2forge.reassert.core.model.contract.usage.IUsageTerm;
import com.g2forge.reassert.term.StandardUsageTerm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StandardUsage implements IUsage {
	PrivateDistribution(Terms.<IUsageTerm>builder().include(StandardUsageTerm.DistributionPrivate, StandardUsageTerm.DistributionService, StandardUsageTerm.UseCopy, StandardUsageTerm.DistributingBinary).exclude(StandardUsageTerm.Commercial, StandardUsageTerm.DistributionPublic, StandardUsageTerm.UseLink, StandardUsageTerm.UseModified, StandardUsageTerm.DistributingSource).build()),
	OSSLibrary(Terms.<IUsageTerm>builder().include(StandardUsageTerm.DistributionPrivate, StandardUsageTerm.DistributionPublic, StandardUsageTerm.UseLink, StandardUsageTerm.DistributingBinary, StandardUsageTerm.DistributingSource).exclude(StandardUsageTerm.Commercial, StandardUsageTerm.DistributionService, StandardUsageTerm.UseCopy, StandardUsageTerm.UseModified).build()),
	CommercialDistribution(Terms.<IUsageTerm>builder().include(StandardUsageTerm.Commercial, StandardUsageTerm.DistributionPublic, StandardUsageTerm.DistributionPrivate, StandardUsageTerm.UseCopy, StandardUsageTerm.DistributingBinary).exclude(StandardUsageTerm.DistributionService, StandardUsageTerm.UseLink, StandardUsageTerm.UseModified, StandardUsageTerm.DistributingSource).build()),
	CommercialSaaS(Terms.<IUsageTerm>builder().include(StandardUsageTerm.Commercial, StandardUsageTerm.DistributionPublic, StandardUsageTerm.DistributionPrivate, StandardUsageTerm.DistributionService, StandardUsageTerm.UseCopy, StandardUsageTerm.DistributingBinary).exclude(StandardUsageTerm.UseLink, StandardUsageTerm.UseModified, StandardUsageTerm.DistributingSource).build());

	protected final ITerms<IUsageTerm> terms;

	@Override
	public String getName() {
		return name() + " usage";
	}
}
