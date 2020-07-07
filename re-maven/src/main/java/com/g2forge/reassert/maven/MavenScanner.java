package com.g2forge.reassert.maven;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.g2forge.alexandria.java.io.RuntimeIOException;
import com.g2forge.alexandria.java.type.ref.ITypeRef;
import com.g2forge.reassert.core.api.IReassertGraphBuilder;
import com.g2forge.reassert.core.api.scanner.IScanner;
import com.g2forge.reassert.core.model.artifact.Artifact;
import com.g2forge.reassert.core.model.coordinates.Coordinates;
import com.g2forge.reassert.core.model.file.File;
import com.g2forge.reassert.core.model.file.Parsed;
import com.g2forge.reassert.maven.model.MavenPOM;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class MavenScanner implements IScanner {
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	protected final MavenSystem system;

	@Override
	public boolean isRelevant(IScanner.IItem item) {
		return item.getPath().getFileName().toString().toLowerCase().endsWith(".xml");
	}

	@Override
	public void load(Collection<IScanner.IItem> items, Artifact<?> container, IReassertGraphBuilder builder) {
		final XmlMapper mapper = system.getMapper();
		for (IScanner.IItem item : items) {
			final File file = new File(item.getCoordinates());
			builder.vertex(file).vertex(item.getCoordinates()).edge(file, item.getCoordinates(), new Coordinates());

			final MavenPOM pom;
			try {
				pom = mapper.readValue(item.getData().getStream(ITypeRef.of(InputStream.class)), MavenPOM.class);
			} catch (IOException e) {
				if (!item.getPath().getFileName().toString().toLowerCase().equals("pom.xml")) continue;
				else throw new RuntimeIOException(e);
			}
			builder.vertex(pom).edge(file, pom, new Parsed());

			builder.callback(new Artifact<>(null, resolveCoordinates(pom).getCoordinates()));
		}
	}

	protected MavenPOM resolveCoordinates(MavenPOM pom) {
		if (pom.getParent() == null) return pom;

		final MavenPOM.MavenPOMBuilder builder = pom.toBuilder();

		{ // Inherit coordinates
			final MavenCoordinates pomCoordinates = pom.getCoordinates(), parentCoordinates = pom.getParent().getCoordinates();
			final MavenCoordinates.MavenCoordinatesBuilder coordinatesBuilder = MavenCoordinates.builder();
			coordinatesBuilder.groupId(pomCoordinates.getGroupId() != null ? pomCoordinates.getGroupId() : parentCoordinates.getGroupId());
			coordinatesBuilder.artifactId(pomCoordinates.getArtifactId() != null ? pomCoordinates.getArtifactId() : parentCoordinates.getArtifactId());
			coordinatesBuilder.version(pomCoordinates.getVersion() != null ? pomCoordinates.getVersion() : parentCoordinates.getVersion());
			builder.coordinates(coordinatesBuilder.build());
		}

		return builder.build();
	}
}