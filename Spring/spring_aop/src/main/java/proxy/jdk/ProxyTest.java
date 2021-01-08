package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        //创建目标对象
        final Target target = new Target();

        //获得增强对象
        final Advice advice = new Advice();

        //返回值，就是动态生成的代理对象
        //动态对象和目标对象是兄弟关系，可以用接口来接收
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),     //目标对象的类加载器
                target.getClass().getInterfaces(),      //目标对象相同的接口字节码对象数组
                new InvocationHandler() {
                    //调用代理对象的任何方法，实质执行的都是invoke方法
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //前置增强
                        advice.before();    //前置增强....
                        //执行目标方法
                        Object invoke = method.invoke(target, args);
                        //后置增强
                        advice.afterReturning();    //后置增强....
                        return invoke;
                    }
                }
        );

        //目标对象的方法
        target.save();  //save running...

        //调用代理对象的方法
        proxy.save();
        //前置增强....
        //save running...
        //后置增强....
    }
}
