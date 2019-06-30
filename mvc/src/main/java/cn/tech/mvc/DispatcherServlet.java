package cn.tech.mvc;

/**
 * Created by Administrator on 2018/11/27.
 */

import cn.tech.mvc.annotation.*;
import cn.tech.mvc.core.IOCInvoke;
import cn.tech.mvc.listener.EventSubject;
import cn.tech.mvc.listener.impl.EventSubjector;
import cn.tech.mvc.listener.ApplicationListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * @Author Lyle xiajun94@FoxMail.com
 * @Description
 * @name DispatcherServlet
 * @Date 2018/11/27 13:48
 */
public class DispatcherServlet extends HttpServlet {

    public static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal();

    // 被观察者
    private EventSubject subjector = EventSubjector.getInstance();

    Logger logger = Logger.getLogger(this.getClass().getName());

    // 扫描的配置文件
    Properties properties = new Properties();

    // 存放basePackage包中扫描到的所有类，用户创建bean
    List<String> classNames = new ArrayList();

    // 请求路径对应的controller中的方法名称
    Map<String, Method> handleMapping = new HashMap<>();

    // IOC待注入的bean
    Map<String, Object> beans = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        init0(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception ex){
            ex.printStackTrace();
            resp.getWriter().append("500: internal server error! ").flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            doDispatch(req, resp);
        } catch (Exception ex){
            ex.printStackTrace();
            resp.getWriter().append("500: internal server error! ").flush();
        }
    }

    protected void loadProp(ServletConfig config){

        InputStream propInputStream = null;

        try {
            propInputStream = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("configLocation"));
            properties.load(propInputStream);
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {
                if (null != propInputStream) {
                    propInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doScanner(String basePackage) {

        URL url = this.getClass().getClassLoader().getResource(basePackage.replace(".", "/"));
        if (null == url){
            throw new RuntimeException("basePackage不存在");
        }
        File dir = new File(url.getFile());

        for (File f : dir.listFiles()){
            if (f.isFile()){
                classNames.add(basePackage + "." + f.getName().replace(".class", ""));
            } else {
                doScanner(basePackage + "." + f.getName());
            }
        }
    }

    private void init0(ServletConfig config) throws ServletException {

        beforeStartup();

        // 1.加载配置文件
        loadProp(config);

        // 2.扫描"basePackage"中配置的要扫描的包
        doScanner(properties.getProperty("basePackage"));

        // 3.创建bean
        doInstances();
        afterCreateBean();

        // 4.IOC注入
        doIOC();

        // 5.扫描requestMapping
        initMapping();

        subjector.notifyListener();
    }

    public void beforeStartup(){

        THREAD_LOCAL.set(System.currentTimeMillis());

        System.out.println("------------------------------------------");
        System.out.println("                                          ");
        System.out.println("                  booting                 ");
        System.out.println("                                          ");
        System.out.println("------------------------------------------");
    }

    protected void doInstances() {

        for (String cls : classNames){
            createInstance(cls);
        }

        try {
            Class clz = ApplicationContext.class;

            Method method = clz.getDeclaredMethod("setBeans", new Class[]{Map.class});
            method.setAccessible(true);
            method.invoke(clz, beans);

            Method method1 = clz.getDeclaredMethod("setProperties", Properties.class);
            method1.setAccessible(true);
            method1.invoke(clz, properties);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void afterCreateBean(){

        // 将启动监听器放到被观察者里
        Iterator<Map.Entry<String, Object>> it = beans.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            Object bean = entry.getValue();
            if (bean instanceof ApplicationListener) {
                EventSubjector.getInstance().addListener((ApplicationListener) bean);
            }
        }
    }

    protected void createInstance(String className){

        try {
            Class clz = Class.forName(className);
            if (clz.isAnnotationPresent(Controller.class) || clz.isAnnotationPresent(Service.class) || clz.isAnnotationPresent(Component.class)){

                String id = null;
                if (clz.isAnnotationPresent(Controller.class)){
                    Controller controllerAnno = (Controller) clz.getAnnotation(Controller.class);
                    id = controllerAnno.value();
                }
                if (clz.isAnnotationPresent(Service.class)){
                    Service serviceAnno = (Service) clz.getAnnotation(Service.class);
                    id = serviceAnno.value();
                }
                if (clz.isAnnotationPresent(Component.class)){
                    Component comAnno = (Component) clz.getAnnotation(Component.class);
                    id = comAnno.value();
                }
                if ("".equals(id.trim())){
                    id = clz.getSimpleName();
                }
                try {
                    beans.put(id, getBeanOrCreate(clz));
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            if (clz.isAnnotationPresent(Bean.class)){
                try {
                    beans.put(clz.getSimpleName(), clz.newInstance());
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            // service 将其实现的接口也一并加入，可以用接口注入
            if (clz.isAnnotationPresent(Service.class)){
                Class[] interfaces = clz.getInterfaces();
                for (Class i : interfaces) {
                    try {
                        beans.put(i.getSimpleName(), getBeanOrCreate(clz));
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    private Object getBeanOrCreate(Class clz) throws Exception{

        String name = clz.getSimpleName();

        Object bean = beans.get(name);
        if (null == bean){
            bean = beans.get(name + "impl");
        }
        if (null == bean){
            bean = clz.newInstance();
        }

        return bean;
    }

    protected void doIOC(){

        try {
            IOCInvoke ioc = (IOCInvoke) Class.forName("cn.tech.mvc.core.impl.DefaultIOCInvoker").newInstance();
            ioc.invoke(beans);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void initMapping(){

        for (Map.Entry entry : beans.entrySet()){

            Class clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(Controller.class)){
                continue;
            }

            String[] baseUrl = ((RequestMapping) clazz.getAnnotation(RequestMapping.class)).value();
            for (String base : baseUrl){

                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods){
                    if (method.isAnnotationPresent(RequestMapping.class)){
                        try {
                            String[] url = method.getAnnotation(RequestMapping.class).value();
                            for (String u : url){

                                // 将多个'/'，替换为一个
                                if (!"".equals(u)){
                                    u = "/" + u;
                                }
                                String fullUrl = (base + u).replaceAll("/+", "/");

                                handleMapping.put(fullUrl, method);
                                logger.info(String.format("mapping url: %s -> %s: %s()", fullUrl, clazz.getName(), method.getName()));
                            }
                        } catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    protected void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{

        String url = req.getRequestURI().replace(req.getContextPath(), "").replaceAll("/+", "/");
        if (!handleMapping.containsKey(url)){
            resp.getWriter().append("404: not found.").flush();
            return;
        }

        // url对应的方法
        Method method = handleMapping.get(url);
        Annotation[][] ans = method.getParameterAnnotations();
        // 方法的参数列表
        Class[] paramTypes = method.getParameterTypes();
        // 请求参数列表
        Map<String, String[]> paramMap = req.getParameterMap();
        // 执行的方法参数
        Object[] invokeParam = new Object[paramTypes.length];

        for (int i = 0, j = paramTypes.length; i < j; i++){

            String className = paramTypes[i].getSimpleName();
            if (className.equals("HttpServletRequest")){
                invokeParam[i] = req;
                continue;
            }
            if (className.equals("HttpServletResponse")){
                invokeParam[i] = resp;
                continue;
            }

            for (Annotation a : ans[i]){
                if (a instanceof RequestParam){
                    RequestParam r = (RequestParam) a;
                    String name = r.value();
                    invokeParam[i] = null == paramMap.get(name) ? null : paramMap.get(name)[0];
                }
            }
        }

        PrintWriter pw = null;
        try {
            // 执行controller方法，将返回值输出到response中
            logger.info("resolve url: " + url);
            Object result = method.invoke(beans.get(method.getDeclaringClass().getSimpleName()), invokeParam);
            String responseText = method.isAnnotationPresent(ResponseBody.class) ? new ObjectMapper().writeValueAsString(result) : result.toString();
            pw = resp.getWriter();
            pw.append(responseText).flush();
        } finally {
            if (null != pw){
                pw.close();
            }
        }
    }

}