package com.github.xeniale.backendexamples.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class ConfigurableSpringLauncher extends BackboneLauncher<ConfigurableSpringLauncher.State, ConfigurableSpringLauncher> {

    public class TypesafeConfigBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

        protected Class getBeanClass(Element element) {
            return MethodInvokingFactoryBean.class;
        }

        protected void doParse(Element element, BeanDefinitionBuilder bean) {
            String mcSource = element.getAttribute("source");
            if (!StringUtils.hasText(mcSource)) {
                mcSource = "mutableConfig";
            }

            String mcPath = element.getAttribute("path");
            bean.addPropertyReference("targetObject", mcSource);
            bean.addPropertyValue("targetMethod", "branch");
            bean.addPropertyValue("arguments", ImmutableList.of(mcPath));
        }
    }

    private String springXmlResourcePath = null;

    @Override
    protected State customLaunch(BackboneLauncher.State baseState) {

        // Run Springframework's context

        ApplicationContext springContext;
        try {
            GenericApplicationContext programmableSpringContext;
            DefaultListableBeanFactory preloadedBeanFactory = new DefaultListableBeanFactory();

            preloadedBeanFactory.registerSingleton("mutableConfig", baseState.config);

            ObjectMapper om = new ObjectMapper();
            preloadedBeanFactory.registerSingleton("om", om);

            programmableSpringContext = new GenericApplicationContext(preloadedBeanFactory);
            //todo:
//            programmableSpringContext.getEnvironment().getPropertySources().addFirst(new MutableConfigPropertySource(baseState.config));

            programmableSpringContext.refresh();
            springContext = programmableSpringContext;

            if (springXmlResourcePath != null) {
                ClassPathXmlApplicationContext xmlSpringContext = new ClassPathXmlApplicationContext(
                        new String[] { springXmlResourcePath },
                        programmableSpringContext
                );
                xmlSpringContext.refresh();
                springContext = xmlSpringContext;
            }

        } catch (Exception e) {
            log().error("Failed to run Spring Framework", e);
            System.exit(4);
            throw new IllegalStateException("exception after System.exit");
        }

        return new ConfigurableSpringLauncher.State(baseState, springContext);
    }

    @Override
    protected ConfigurableSpringLauncher getThis() {
        return this;
    }

    public ConfigurableSpringLauncher withSpringXml(String resourcePath) {
        this.springXmlResourcePath = resourcePath;
        return getThis();
    }

    public static class State extends BackboneLauncher.State {

        public final ApplicationContext springContext;

        public State(BackboneLauncher.State baseState, ApplicationContext springContext) {
            super(baseState.appName, baseState.parsedCli, baseState.config);
            this.springContext = springContext;
        }
    }

}
