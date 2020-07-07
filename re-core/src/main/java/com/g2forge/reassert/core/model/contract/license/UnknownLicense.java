package com.g2forge.reassert.core.model.contract.license;

import com.g2forge.reassert.core.model.contract.ITerms;
import com.g2forge.reassert.core.model.contract.Terms;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Represent a license we do not yet recognize, but have some kind of text for.
 */
@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class UnknownLicense implements ILicense {
	protected final String text;

	@Override
	public String getName() {
		return "Unknown License";
	}

	@Override
	public String getSPDX() {
		return null;
	}

	/**
	 * An unknown license has no terms, which means it has no permissions and thus artifacts with this license cannot cannot be used.
	 */
	@Override
	public ITerms<ILicenseTerm> getTerms() {
		return Terms.createNone();
	}
}
