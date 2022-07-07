package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestProxy2 {


    public static void main(String[] args){
        SimpleInterface original = new TargetObject();
        InvocationHandler handler = new SimpleInvocationHandler(original);


        SimpleInterface proxyObject = (SimpleInterface) Proxy.newProxyInstance(SimpleInterface.class.getClassLoader(),
                new Class[] { SimpleInterface.class },
                handler);

        int retValue = proxyObject.originalMethod("Hello");

        System.out.println("returned: " + retValue);

    }

}
