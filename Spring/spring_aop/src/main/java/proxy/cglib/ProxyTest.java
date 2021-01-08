package proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyTest {
    public static void main(String[] args) {

        //创建目标对象
        final Target target = new Target();

        //获得增强对象
        final Advice advice = new Advice();

        //返回值，就是生成的代理对象，基于cglib
        //1、创建增强器
        Enhancer enhancer = new Enhancer();
        //2、设置父类（目标）
        enhancer.setSuperclass(Target.class);
        //3、设置回调
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                //执行前置
                advice.before();
                //执行目标
                Object invoke = method.invoke(target, args);
                //执行后置
                advice.afterReturning();

                return invoke;
            }
        });
        //4、创建代理对象  父子关系
        Target proxy = (Target) enhancer.create();

        proxy.save();
        //前置增强....
        //save running...
        //后置增强....
    }
}
