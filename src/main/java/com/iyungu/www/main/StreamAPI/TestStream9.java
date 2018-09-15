package com.iyungu.www.main.StreamAPI;

import com.iyungu.www.main.testFunction.Project;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class TestStream9 {

    //计数
    @Test
    public void test1() {
        List<Project> projects = Project.buildData();
        long a = projects.stream()
                .collect(counting()).longValue();//计数
        Project p = projects.stream()
                .collect(maxBy(Comparator.comparingInt(Project::getStars))).get();//求最大的
        int b = projects.stream()
                .collect(summingInt(Project::getStars));//求和
        System.out.println("求和:" + b);
        double average = projects.stream()
                .collect(averagingInt(Project::getStars));//求平均值
        System.out.println("求平均值double:" + average);
        System.out.println("求平均值bigdecimal:" + new BigDecimal(average).setScale(2));//保留两位小数
        String str = Stream.of("Hello", "Java8")
                .collect(joining(" "));
        System.out.println("str=" + str);

        int sum2 = projects.stream().collect(reducing(0, Project::getStars, (x, y) -> x + y));//求和
        System.out.println("自定义求和2：" + sum2);

        int sum3 = projects.stream()
                .map(Project::getStars)
                .collect(reducing((x, y) -> x + y)).get();
        System.out.println("自定义求和3：" + sum3);

    }

    //归约
    @Test
    public void test2() {
        List<Project> projects = Project.buildData();
        Map<String, Integer> collect = projects.stream()
                .collect(toMap(Project::getName, Project::getStars));
        System.out.println(collect);
        Map<String, Project> collect2 = projects.stream()
                .collect(toMap(Project::getName, Function.identity()));
        System.out.println(collect2);
    }

    //分组
    @Test
    public void test3() {
        List<Project> projects = Project.buildData();
        Map<String, List<Project>> collect = projects.stream()
                .collect(groupingBy(Project::getAuthor));
        System.out.println("归约一：" + collect);
        Map<String, Map<String, List<Project>>> collect2 = projects.stream()
                .collect(groupingBy(Project::getAuthor,
                        groupingBy(p -> {
                            if ("java".equalsIgnoreCase(p.getLanguage()) ||
                                    "python".equalsIgnoreCase(p.getLanguage())) {
                                return "后端";
                            }
                            return "前端";
                        })
                ));
        System.out.println("归约二：" + collect2);
        Map<String, Map<String, Long>> collect1 = projects.stream()
                .collect(groupingBy(Project::getAuthor,
                        groupingBy(p -> {
                            if ("java".equalsIgnoreCase(p.getLanguage()) ||
                                    "python".equalsIgnoreCase(p.getLanguage())) {
                                return "后端";
                            }
                            return "前端";
                        }, counting())
                ));
        System.out.println("归约三：" + collect1);
    }

    //数据分区
    public static boolean isBackEnd(Project p) {
        return "java".equalsIgnoreCase(p.getLanguage()) ||
                "python".equalsIgnoreCase(p.getLanguage());
    }

    @Test
    public void test4() {
        List<Project> projects = Project.buildData();
        Map<Boolean, List<Project>> list = projects.stream()
                .collect(partitioningBy(TestStream9::isBackEnd));
        System.out.println("写法一：" + list);
        Map<Boolean, List<Project>> list2 = projects.stream()
                .collect(partitioningBy(p -> {
                    if ("java".equalsIgnoreCase(p.getLanguage()) ||
                            "python".equalsIgnoreCase(p.getLanguage())) {
                        return true;
                    }
                    return false;
                }));
        System.out.println("写法二：" + list2);
    }

    //转换类型
    @Test
    public void test5() {
        List<Project> projects = Project.buildData();
        HashSet<Project> collect = projects.stream()
                .collect(toCollection(HashSet::new));
        System.out.println("转为HashSet:" + collect.toString());
        List<Project> collect2 = projects.stream()
                .collect(toCollection(CopyOnWriteArrayList::new));
        System.out.println("转为ArrayList:" + collect2.toString());
        //按照作者名称选出每组star最高的项目
        Map<String, Project> list3 = projects.stream()
                .collect(groupingBy(Project::getAuthor, collectingAndThen(
                        maxBy(Comparator.comparingInt(Project::getStars)),
                        java.util.Optional::get
                )));
        System.out.println("筛选：" + list3.toString());
    }

    //并行流
    @Test
    public void test6() {
    /*    int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }*/
        //普通的串行流
//        long t0 = System.nanoTime();
//        long count = values.stream().sorted().count();
//        System.out.println("count=" + count);
//        long t1 = System.nanoTime();
//        long mills = TimeUnit.NANOSECONDS.toMillis(t1 - t0);

        //并行流
//        List<Integer> list=Arrays.asList(1,3,4,2,9);
//        list.stream()
//                .parallel();
        Arrays.asList("a1","a2","b1","c1","c2")
                .parallelStream()
                .filter(s->{
                    System.out.format("filter:%s [%s]\n",s,Thread.currentThread().getName());
                    return true;
                })
                .map(s->{
                    System.out.format("map:%s [%s]\n",s,Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1,s2)->{
                    System.out.format("sorted:%s <>%s [%s]\n",s1,s2,Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s->System.out.format("forEach:%s [%s]\n",s,Thread.currentThread().getName()));

    }

}
