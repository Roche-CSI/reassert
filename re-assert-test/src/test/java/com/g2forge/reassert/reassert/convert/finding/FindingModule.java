package com.g2forge.reassert.reassert.convert.finding;

import org.slf4j.event.Level;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.g2forge.alexandria.java.function.IFunction1;
import com.g2forge.reassert.contract.v2.model.finding.ExpressionContextFinding;
import com.g2forge.reassert.contract.v2.model.licenseusage.ICTName;
import com.g2forge.reassert.core.api.described.IDescription;
import com.g2forge.reassert.core.model.contract.terms.TermRelation;
import com.g2forge.reassert.core.model.report.IContextFinding;
import com.g2forge.reassert.core.model.report.IFinding;
import com.g2forge.reassert.express.v2.model.IExplained;
import com.g2forge.reassert.express.v2.model.IExpression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindingModule extends SimpleModule {
	protected static abstract class ContextualFindingMixin extends FindingMixin {
		@JsonIgnore
		public abstract Level getLevel();
	}

	@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected static abstract class ExplainedMixin {}

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected static abstract class ExpressionContextualFindingMixin extends ContextualFindingMixin {
		@JsonIgnore
		protected IExpression<ICTName, TermRelation> expression;
	}

	@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY)
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	protected static abstract class FindingMixin {
		@JsonIgnore
		public abstract IFinding getInnermostFinding();
	}

	private static final long serialVersionUID = -1153968277342886689L;

	protected final IFunction1<? super Object, ? extends IDescription> describer;

	@Override
	public void setupModule(SetupContext context) {
		this.setMixInAnnotation(IFinding.class, FindingMixin.class);
		this.setMixInAnnotation(IContextFinding.class, ContextualFindingMixin.class);
		this.setMixInAnnotation(ExpressionContextFinding.class, ExpressionContextualFindingMixin.class);
		this.setMixInAnnotation(IExplained.class, ExplainedMixin.class);
		super.setupModule(context);

		/*context.addBeanDeserializerModifier(new BeanDeserializerModifier() {
			public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription description, JsonDeserializer<?> deserializer) {
				if (TermConstant.class.isAssignableFrom(description.getBeanClass())) return new TermConstantDeserializer();
				return deserializer;
			}
		});
		context.addBeanSerializerModifier(new BeanSerializerModifier() {
			@Override
			public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription description, JsonSerializer<?> serializer) {
				if (TermConstant.class.isAssignableFrom(description.getBeanClass())) return new TermConstantSerializer(getDescriber());
				return serializer;
			}
		});*/
	}
}