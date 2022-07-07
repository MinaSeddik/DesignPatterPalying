package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SimpleInvocationHandler implements InvocationHandler {

    private final SimpleInterface originalObject;

    public SimpleInvocationHandler(SimpleInterface originalObject) {
        this.originalObject = originalObject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("BEFORE");

        Object ret = method.invoke(originalObject, args);

        System.out.println("AFTER");
        return ret;

    }


}
