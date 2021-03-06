package com.refutrue.athena.utils.template.builder;

import org.apache.velocity.VelocityContext;

import com.refutrue.athena.bean.User;
import com.refutrue.athena.utils.template.bean.BeanMsg;
import com.refutrue.athena.utils.template.bean.GlobalConfig;
import com.refutrue.athena.utils.template.bean.TableMsg;
import com.refutrue.athena.utils.template.exception.TemplateException;
import com.refutrue.athena.utils.template.generate.GenerateFactory;
import com.refutrue.athena.utils.template.velocity.VelocityUtil;

public class ResourceMapperBuilder extends BuilderAdapter{

	@Override
	public void execute(Class<?> cls) {
		BeanMsg beanMsg = collectBeanMsg(cls);
		GlobalConfig globalConfig = GlobalConfig.getInstance().init();
		VelocityContext ctx = new VelocityContext();
		TableMsg tableMsg = collectTableMsg(cls);
		ctx.put("tableMsg", tableMsg);
		ctx.put("beanMsg", beanMsg);
		ctx.put("globalConfig", globalConfig);
		String templateName = "ResourceMapper.vm";
		String velocityStr = VelocityUtil.zeus(templateName, ctx);
		String path = getProjectFilePath("",globalConfig.getGenerateMapperDir(),beanMsg.getBeanSimpleName() + "Mapper" + ".xml");
		GenerateFactory.getInstance().build("Resource").generate(path, new StringBuilder(velocityStr));
	}
	
	public static void main(String[] args) {
		ResourceMapperBuilder builder = new ResourceMapperBuilder();
		builder.execute(User.class);
	}

	@Override
	public void check(Class<?> cls) throws TemplateException {
		
	}

}
