import java.lang.annotation.*;
import java.lang.reflect.Method;

//@Retention注解指定Login注解可以保留多久
@Retention(RetentionPolicy.RUNTIME)
//@Target注解指定注解能修饰的目标(只能是方法)
@Target(ElementType.METHOD)
@interface Login{
    String username() default "zhangsan";
    String password() default "123456";
}

public class Test {
    public static void main(String[] args) throws Exception{
        //1.1通过反射获取info方法类
        Method method = Test.class.getMethod("info");
        //2.1判断该方法上是否存在@Login注释
        boolean annotationPresent = method.isAnnotationPresent(Login.class);
        if(annotationPresent){
            System.out.println("info方法上存在@Login注释");
        }else{
            System.out.println("info方法上不存在@Login注释");
        }
        //3.1获取方法上的所有注释
        Annotation[] annotations = method.getAnnotations();
        for(Annotation a : annotations){
            //如果是@Login注释，则强制转化，并调用username方法，和password方法。
            if(a !=null && a instanceof Login){
                String username = ((Login)a).username();
                String password = ((Login)a).password();
                System.out.println("username:" + username);
                System.out.println("password:" + password);
            }
            System.out.println(a);
        }
    }

    @Login
    @Deprecated
    public void info(){}
}