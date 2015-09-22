package com.tayjay.fullpower.proxy;

/**
 * Created by Taylar on 29/08/2015.
 */
public abstract class CommonProxy implements IProxy
{
    public abstract void preInit();
    public abstract void init();
    public abstract void postInit();

}
