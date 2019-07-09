package com.john.www.main.StreamAPI;

import com.john.www.main.testFunction.Project;
import lombok.AllArgsConstructor;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream6 {
    @Test
    public void test1(){
       List<Project> projects=Project.buildData();
       List<String> names=projects.stream()
               .filter(p -> {
                   System.out.println(p.getName());
                   return p.getStars()>1000;
               })
               .map(p -> {
                   System.out.println(p.getName());
                   return p.getName();
               })
               .limit(3)
               .collect(Collectors.toList());
        System.out.println(names);
        names.stream().forEach(name -> System.out.println(name));
    }
    @Test
    public void test2(){
        List<String> list= Arrays.asList("hello","world");
        Stream<String> stream1=list.stream();

        Stream<String> stream2=Arrays.stream(new String[]{"hello","world"});

        Stream<String> stream3=Stream.of("hello","world");
    }
     @AllArgsConstructor
    static class Tuple{
        int first;
        int second;

    }

    /**
     * 斐波纳挈元组序列
     */
    @Test
    public static void test3(){
       //tuple=(0,1)
        //next [0] = prev tuple[1]
        //next [1] =  prev (tuple[1]+  tuple[1])
        Stream.iterate(new Tuple(0,1),tuple->new Tuple(tuple.second,tuple.first+tuple.second))
                .limit(20)
                .forEach(tuple -> System.out.println("["+tuple.first+","+tuple.second+"]\n"));
        Stream<Integer> numbers=Stream.of(111,22,22,33);
        numbers.distinct().forEach(System.out::println);
    }

   @Test
    public void test5(){
        List<String> words=Arrays.asList("java8","lambda","in","Action");
        words.stream()
                .map(word ->word.length())
                .collect(Collectors.toList())
                .forEach(i-> System.out.println(i));
        //列出不同的单词
       List<String> list=Arrays.asList("I am a boy","I love the girl","But the girl loves another girl");
       list.stream()
               .map(word -> word.split(" "))//Stream<String[]>
               .flatMap(Arrays::stream) //扁平化为一个流,映射成一个流的内容
               .distinct()
               .collect(Collectors.toList());
   }
   @Test
    public void testQ(){
        List<Integer> numbers=Arrays.asList(1,2,3,4);
        numbers.stream()
                .map(n->n*n)
                .collect(Collectors.toList())
        .forEach(System.out::println);
   }
    /**
     * 2.
     * <p>
     * 2.1 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * 例如，给定[1, 2, 3, 4, 5]，应该返回[1, 4, 9, 16, 25]
     * <p>
     * 2.2 给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，
     * 应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。
     * 为简单起见，你可以用有两个元素的数组来代表数对。
     * <p>
     * 2.3 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
     *
     * @author biezhi
     * @date 2018/2/12
     */
    @Test
    public void testQ2(){
        List<Integer> numbers1=Arrays.asList(1,2,3,4);
        List<Integer> numbers2=Arrays.asList(2,3);
       List<int[]> list= numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j ->new int[]{i,j}))
                .collect(Collectors.toList());
        list.forEach(list1 -> System.out.println(Arrays.toString(list1)));
    }
    //2.3 如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
    @Test
    public void testQ3(){
        List<Integer> numbers1=Arrays.asList(1,2,3,4);
        List<Integer> numbers2=Arrays.asList(2,3);
        List<int[]> list= numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i+j)%3==0).map(j ->new int[]{i,j}))
                .collect(Collectors.toList());
        list.forEach(list1 -> System.out.println(Arrays.toString(list1)));
    }
}
