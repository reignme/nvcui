package org.nvc.ui.common.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NVCContextFactory
{
    private static AnnotationConfigApplicationContext context;
    static
    {
        context = new AnnotationConfigApplicationContext("org.nvc.ui");
    }
    
    public static ApplicationContext getContext()
    {
        return context;
    }
    
}