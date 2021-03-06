package com.refutrue.athena.utils.template.builder;

import org.apache.velocity.VelocityContext;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.utils.template.bean.BeanMsg;
import com.refutrue.athena.utils.template.bean.GlobalConfig;
import com.refutrue.athena.utils.template.exception.TemplateException;
import com.refutrue.athena.utils.template.generate.GenerateFactory;
import com.refutrue.athena.utils.template.velocity.VelocityUtil;

public class ControllerBuilder extends BuilderAdapter{

	@Override
	public void execute(Class<?> cls) {
		BeanMsg beanMsg = collectBeanMsg(cls);
		GlobalConfig globalConfig = GlobalConfig.getInstance().init();
		VelocityContext ctx = new VelocityContext();
		ctx.put("beanMsg", beanMsg);
		ctx.put("globalConfig", globalConfig);
		String templateName = "Controller.vm";
		String velocityStr = VelocityUtil.zeus(templateName, ctx);
		String path = getProjectFilePath(globalConfig.getBasePackage(),globalConfig.getGenerateControllerDir(),beanMsg.getBeanSimpleName() + "Controller" + ".java");
		GenerateFactory.getInstance().build(globalConfig.getGenerateMethod()).generate(path, new StringBuilder(velocityStr));
	}
	
	public static void main(String[] args) {
		ControllerBuilder builder = new ControllerBuilder();
		builder.execute(User.class);
	}

	@Override
	public void check(Class<?> cls) throws TemplateException {
		
	}

}
