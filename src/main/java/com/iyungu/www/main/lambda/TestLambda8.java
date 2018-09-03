package com.iyungu.www.main.lambda;

import com.iyungu.www.main.testFunction.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/1  17:02
 * @Modified By:
 */
public class TestLambda8 {
    private static List<Project> buildData(){
        List<Project> data = new ArrayList<>();

        data.add(Project.builder().name("Blade").language("java").author("biezhi")
                .stars(3500).description("Lightning fast and elegant mvc framework for Java8").build());

        data.add(Project.builder().name("Tale").language("java").author("biezhi")
                .stars(2600).description("Best beautiful java blog, worth a try").build());

        data.add(Project.builder().name("Vue.js").language("js").author("yyx990803")
                .stars(83000).description("A progressive, incrementally-adoptable JavaScript framework for building UI on the web.").build());

        data.add(Project.builder().name("Flask").language("python").author("pallets")
                .stars(10500).description("The Python micro framework for building web applications").build());

        data.add(Project.builder().name("Elves").language("java").author("biezhi")
                .stars(200).description("Spider").build());
        return data;
    }

    public static void main(String[] args) {
        List<Project> projects=buildData();
//        List<String> names=getNames(projects);
        List<String> names=getNames(projects,project->project.getStars()>10000);
        List<String> desc=getValues(projects,project->project.getStars()>10000,project -> project.getDescription());
        List<Integer> starts=getValues(projects,project->project.getStars()>10000,ProjectFunction.buildStarFunction());
        names.forEach(System.out::println);
        desc.forEach(System.out::println);
        starts.forEach(System.out::println);
    }
    public static List<String> getNames(List<Project> projects,Predicate<Project> predicate){
      List<String> names=new ArrayList<>();
        for (Project project: projects
             ) {
            if(predicate.test(project)){
           names.add(project.getName());
            }
        }
        return names;
    }
    public static <R>List<R> getValues(List<Project> projects,Predicate<Project> predicate, Function<Project,R> function){
        List<R> values=new ArrayList<>();
        for (Project project: projects
                ) {
            if(predicate.test(project)){
                values.add(function.apply(project));
            }
        }
        return values;
    }
}

interface ProjectFunction<R> extends Function<Project,R>{
      static ProjectFunction<Integer> buildStarFunction(){
          return Project::getStars;
      }
}
