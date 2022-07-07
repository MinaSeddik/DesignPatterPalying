package proxy;

//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;

public class TestProxy {


    public static void main(String[] args2) {

//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(PersonService.class);
//        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
//            if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
//                return "Hello Tom!";
//            } else {
//                return proxy.invokeSuper(obj, args);
//            }
//        });
//
//        PersonService proxy = (PersonService) enhancer.create();
//        proxy.sayHello(null);
//        int lengthOfName = proxy.lengthOfName("Mary");
//
//        System.out.println("proxy.sayHello(null):" + proxy.sayHello(null));
//        System.out.println("proxy.lengthOfName(\"Mary\"):" + proxy.lengthOfName("Mary"));

    }

}
