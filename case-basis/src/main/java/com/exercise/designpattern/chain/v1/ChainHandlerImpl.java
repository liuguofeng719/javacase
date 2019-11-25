package com.exercise.designpattern.chain.v1;

/**
 * @author guofeng
 * @version 1.0
 * @see jdk 1.8
 **/
public abstract class ChainHandlerImpl implements ChainHandler {

    private ChainHandler nextHandler;

    @Override
    public void handler(RequestContext context) {
        if (this.getType().equalsIgnoreCase(context.getType())) {
            this.handlerProcess(context);
        } else {
            if(nextHandler!=null) {
                nextHandler.handler(context);
            }else{
                System.out.println("没有找到对应的处理器");
            }
        }
    }

    @Override
    public void setNextHandler(ChainHandler chainHandler) {
         this.nextHandler = chainHandler;
    }

    public abstract void handlerProcess(RequestContext context);

    public abstract String getType();
}
