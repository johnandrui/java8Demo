package com.iyungu.www.main.StreamAPI;

import com.iyungu.www.entity.Trader;
import com.iyungu.www.entity.Transanction;
/*import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;*/
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:
 * @Date 2018/5/11  10:06
 * @Modified By:
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-mvc.xml","classpath:spring/spring-context.xml","classpath:spring/spring-mybatis.xml"})*/
public class TestStreamAPI5Transanction {

   // List<Transanction> transanctions = null;

    Trader raoul = new Trader("Raoul","Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new  Trader("Alan","Cambridge");
    Trader brian = new  Trader("Brian","Cambridge");

    List<Transanction> transanctions = Arrays.asList(
            new Transanction(brian,2011,300),
                new Transanction(raoul,2012,1000),
                new Transanction(raoul,2011,400),
                new Transanction(mario,2012,710),
                new Transanction(mario,2012,700),
                new Transanction(alan,2012,950)
        );

   /* @Before*/
    public void init(){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new  Trader("Alan","Cambridge");
        Trader brian = new  Trader("Brian","Cambridge");

        transanctions = Arrays.asList(
                new Transanction(brian,2011,300),
                new Transanction(raoul,2012,1000),
                new Transanction(raoul,2011,400),
                new Transanction(mario,2012,710),
                new Transanction(mario,2012,700),
                new Transanction(alan,2012,950)
        );
    }
    /**
     * 练习：
     *  1.找出2011年发生的所有交易，并按交易额排序（从低到高）
     *  2.交易员都在哪些不同的城市工作过？
     *  3.查找所有来自剑桥的交易员，并按姓名排序
     *  4.返回所有交易员名字的字符串，按字母顺序排序
     *  5.有没有交易员是在米兰工作的？
     *  6.打印生活在剑桥的交易员的所有交易额
     *  7.所有交易中，最高的交易额是多少？
     *  8.找到交易额最小的交易
     */
    @Test
    public void test1(){
        //1.找出2011年发生的所有交易，并按交易额排序（从低到高）
        transanctions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted((t1,t2) -> Integer.compare(t1.getValue(),t2.getValue()))
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
      //2.交易员都在哪些不同的城市工作过？
      transanctions.stream()
              .map(t -> t.getTrader().getCity())
              .distinct()
              .forEach(System.out::println);
    }
    @Test
    public void test3(){
        //3.查找所有来自剑桥的交易员，并按姓名排序
        transanctions.stream()
                .filter(t -> t.getTrader().getCity() == "Cambridge")
                .map(Transanction::getTrader)
                .sorted((t1,t2) -> t1.getName().compareTo(t2.getName()))
                .distinct()
                .forEach(System.out::println);
    }
    @Test
    public void test4(){
        //4.返回所有交易员名字的字符串，按字母顺序排序
        String str = transanctions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .distinct()
                .reduce("",String::concat);

        transanctions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(TestStreamAPI5Transanction::filterCharacter)
                .sorted((s1,s2) ->s1.compareToIgnoreCase(s2))
                .forEach(System.out::print);


    }

    public static Stream<String> filterCharacter(String str){
        List<String>  list = new ArrayList<>();
        for(Character ch : str.toCharArray()){
            list.add(ch.toString());
        }
        return list.stream();
    }
     @Test
    public void test5(){
         //5.有没有交易员是在米兰工作的？
         boolean b = transanctions.stream()
                 .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
     }
     @Test
    public void test6(){
         //6.打印生活在剑桥的交易员的所有交易额
         Optional<Integer> sum = transanctions.stream()
                 .filter(t ->t.getTrader().getCity().equals("Cambridge"))
                 .map(Transanction::getValue)
                 .reduce(Integer::sum);
     }
     @Test
    public void test7(){
        // 7.所有交易中，最高的交易额是多少？
         Optional<Integer> maxValue =transanctions.stream()
                 .map(Transanction::getValue)
                 .max(Integer::compare);
         //8.找到交易额最小的交易
         Optional<Transanction> record = transanctions.stream()
                 .min((t1,t2) -> Integer.compare(t1.getValue(),t2.getValue()));
     }



}
