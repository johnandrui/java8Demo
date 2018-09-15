package com.iyungu.www.main.StreamAPI;

import com.iyungu.www.main.testFunction.Project;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestStream7 {

    @Test
    public void test1() {
        List<Project> projects = Project.buildData();
        boolean has=projects.stream()
                .anyMatch(p -> p.getAuthor().equals("john"));
        System.out.println("anyMatch="+has);
        boolean has2=projects.stream()
                .allMatch(p -> p.getAuthor().equals("john"));
        System.out.println("allMatch="+has2);
        boolean has3=projects.stream()
                .noneMatch(p -> p.getAuthor().equals("john"));
        System.out.println("noneMatch="+has3);
         Optional<Project>  has4=projects.stream()
                .findAny();
        System.out.println("findAny="+has4.get());
        System.out.println("first="+projects.stream()
                .findFirst().get());
    }
    //reduce
    @Test
    public void test2(){

        List<Integer> numbers= Arrays.asList(2,4,5,6);
        Integer sum=0;
        for (Integer x : numbers) {
            sum+=x ;
            System.out.println(sum);
        }
        System.out.println("sum="+sum);
        Integer reduce=numbers.stream().reduce(0,(a,b)->a+b);
        Integer reduce2=numbers.stream().reduce(0,Integer::sum);
        System.out.println("sum="+sum+";reduce="+reduce+";reduce2="+reduce2);
    }
    @Test
    public void test3(){
        List<Project> projects=Project.buildData();
        int ass=projects.stream()
                .mapToInt(p->p.getStars())
                .sum();
        System.out.println("ass="+ass);
    }
    @Test
    public void test4(){
        List<Project> projects=Project.buildData();
       projects.stream()
                .limit(2)
               .map(Project::getName)
                .collect(Collectors.toList())
       .forEach(System.out::println);
    }
    @Test
    public void test5(){
        List<Project> projects=Project.buildData();
       Integer sum =projects.stream()
                .filter(p -> p.getName().equals("Blade"))
                .map(Project::getStars)
                .reduce(0,Integer::sum);
        System.out.println("sum="+sum);
    }



}
