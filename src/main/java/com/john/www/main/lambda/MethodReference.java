package com.john.www.main.lambda;

import com.john.www.main.testFunction.Project;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  16:28
 * @Modified By:
 */
public class MethodReference {
    /**
     * 方法引用：
     * 1.指向静态方法的引用
     * 2.指向现有对象的实例方法的方法引用
     */
    public static List<Integer> findNumbers(List<Integer> numbers, Predicate<Integer> predicate){
         List<Integer> numbersFound=numbers.stream()
                 .filter(predicate)
                 .collect(Collectors.toList());
         return numbersFound;
    }
    public static boolean multipleof3(Integer number){
           return (number % 3)==0;
    }

    public static void main(String[] args) {
        List<Integer> numbers= Arrays.asList(1,3,4,6,9,12,114,15);
        List<Integer> multipleof3=findNumbers(numbers,MethodReference::multipleof3);
        System.out.println(multipleof3.contains(3));

        Project project= Project.builder().name("Blade").build();
        Arrays.asList(project).stream()
                .map(Project::getName)
                .count();
        System.out.println( Arrays.asList(project).stream()
                .map(Project::getName)
                .count());
    }

}
