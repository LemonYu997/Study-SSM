package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    //目标方法执行之前 执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle....");
        String param = request.getParameter("param");
        //返回true，代表放行；返回false，代表不放行
        if("yes".equals(param)) {   //要把param放在equals里边，放在外边如果不存在会出现空指针异常
            //访问 http://localhost:8080/target?param=yes
            return true;
        } else {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return false;
        }
    }

    //在目标方法执行之后 视图对象返回之前执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //在目标资源执行完之后执行，会覆盖目标资源中的字符串，导致打印结果为test!!!
        modelAndView.addObject("name", "test!!!");
        System.out.println("postHandle....");
    }

    //在流程都执行完毕后 执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion....");
    }

    //根据配置文件决定拦截器执行顺序
    //preHandle....
    //preHandle2222....
    //目标资源执行.....
    //postHandle2222....
    //postHandle....
    //afterCompletion2222....
    //afterCompletion....
}
