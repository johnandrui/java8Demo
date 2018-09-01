package com.iyungu.www.main.lambda;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:
 * @Date 2018/8/31  9:08
 * @Modified By:
 */
public class TestLambda6 {
    public static void main(String[] args) {
        TestLambda6 testLambda6=new TestLambda6();
        //with type declaration
        MathOperation addition=(int a,int b) -> a+b;
        //with out type declaration
        MathOperation subtraction=(a,b) -> a-b;
        //with return statement along with curly braces
        MathOperation multiplication = (a,b) -> {return a * b;};
        //with return statement along without culy braces
        MathOperation division=(a,b) -> a/b;
        System.out.println("10 + 5 = "+testLambda6.opreate(10,5,addition));
        System.out.println("10 - 5 = "+testLambda6.opreate(10,5,subtraction));
        System.out.println("10 * 5 = "+testLambda6.opreate(10,5,multiplication));
        System.out.println("10 / 5 = "+testLambda6.opreate(10,5,division));
        //with parenthesis
        GreetingService greetingService=message -> System.out.println("Hello"+message);
        //without parenthesis
        GreetingService greetingService1=(message) -> System.out.println("Hello"+message);
        greetingService.sayMessage("Mahesh");
        greetingService1.sayMessage("Suresh");
    }
    interface MathOperation{
        int operation(int a,int b);
    }
    interface GreetingService{
        void sayMessage(String message);
    }
    private int opreate(int a,int b,MathOperation mathOperation){
        return  mathOperation.operation(a,b);
    }
}

