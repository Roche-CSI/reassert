package com.g2forge.reassert.core.model.contract.license;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class LicenseVersion {
	protected final int major;

	protected final Integer minor;

	protected final Integer patch;

	public LicenseVersion(int major) {
		this(major, null, null);
	}

	public LicenseVersion(int major, int minor) {
		this(major, minor, null);
	}
	
	@Override
	public String toString() {
		final StringBuilder retVal = new StringBuilder();
		retVal.append(getMajor());
		
		final Integer minor = getMinor();
		final Integer patch = getPatch();
		if (minor != null) {
			retVal.append('.').append(minor);
			if (patch != null) retVal.append('.').append(patch);
		}
		if (minor == null && patch != null) throw new IllegalStateException();
		return retVal.toString();
	}
}
