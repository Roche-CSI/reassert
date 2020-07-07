package com.g2forge.reassert.reassert.convert.license;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.g2forge.reassert.core.model.IVertex;

public class StandardLicenseModule extends SimpleModule {
	private static final long serialVersionUID = -6056568239544794035L;

	@Override
	public void setupModule(SetupContext context) {
		super.setupModule(context);

		context.addBeanDeserializerModifier(new BeanDeserializerModifier() {
			public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription description, JsonDeserializer<?> deserializer) {
				if (IVertex.class.isAssignableFrom(description.getBeanClass())) return new StandardLicenseDeserializer(deserializer);
				return deserializer;
			}
		});
		context.addBeanSerializerModifier(new BeanSerializerModifier() {
			@Override
			public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription description, JsonSerializer<?> serializer) {
				if (IVertex.class.isAssignableFrom(description.getBeanClass())) return new StandardLicenseSerializer(serializer);
				return serializer;
			}
		});
	}
}