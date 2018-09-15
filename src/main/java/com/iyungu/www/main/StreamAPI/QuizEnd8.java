package com.iyungu.www.main.StreamAPI;

import com.iyungu.www.main.testFunction.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuizEnd8 {
    /**
     * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
     * (2) 交易员都在哪些不同的城市工作过？
     * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
     * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
     * (5) 有没有交易员是在米兰工作的？
     * (6) 打印生活在剑桥的交易员的所有交易额。
     * (7) 所有交易中，最高的交易额是多少？
     * (8) 找到交易额最小的交易。
     *
     * @author biezhi
     * @date 2018/2/12
     */
    //交易员
    @Data
    @AllArgsConstructor
    static class Trader {
        private String name;//姓名
        private String citiy;//城市
    }
    //交易
    @Data
    @AllArgsConstructor
    static class Transaction {
        private Trader trader;
        private int year;//交易年份
        private int value;//交易额
    }
    public   List<Transaction> buildData(){
        Trader raoul=new Trader("Raoul","Cambridge");
        Trader mario=new Trader("Mario","Milan");
        Trader alan=new Trader("Alan","Cambridge");
        Trader brian=new Trader("Brian","Cambridge");

      return  /*List<Transaction> transactions=*/ Arrays.asList(
                new Transaction(brian,2011,300),
                new Transaction(brian,20121,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2012,950)
        );
    }
//1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
@Test
public void test1() {
    List<Transaction> transactions = buildData();
    transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .sorted(Comparator.comparingInt(Transaction::getValue))
            .collect(Collectors.toList())
            .forEach(System.out::println);
}
    //(2) 交易员都在哪些不同的城市工作过？
    @Test
    public void test2() {
        List<Transaction> transactions = buildData();
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCitiy)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
    //3) 查找所有来自于剑桥的交易员，并按姓名排序。
    @Test
    public void test3() {
        List<Transaction> transactions = buildData();
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCitiy().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
    //4) 返回所有交易员的姓名字符串，按字母顺序排序。
    @Test
    public void test4() {
        List<Transaction> transactions = buildData();
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                //.sorted(Comparator.naturalOrder())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
    //(5) 有没有交易员是在米兰工作的？
    @Test
    public void test5() {
        List<Transaction> transactions = buildData();
       boolean has= transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCitiy().equals("Milan"));
        System.out.println(has);
    }
    //6) 打印生活在剑桥的交易员的所有交易额。
    @Test
    public void test6() {
        List<Transaction> transactions = buildData();
        Integer sum=transactions.stream()
                .filter(transaction -> transaction.trader.getCitiy().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0,Integer::sum);
//                .reduce(Integer::sum).get();
        System.out.println(sum);
    }
    //7) 所有交易中，最高的交易额是多少？
    @Test
    public void test7() {
        List<Transaction> transactions = buildData();
        Integer sum=transactions.stream()
                .map(Transaction::getValue)
                .reduce(0,Integer::max);
//                .reduce(Integer::sum).get();
        System.out.println(sum);
    }
    //(8) 找到交易额最小的交易。
    @Test
    public void test8() {
        List<Transaction> transactions = buildData();
        Transaction t=transactions.stream()
              .reduce((t1,t2)-> t1.getValue()<t2.getValue()?t1:t2).get();
        System.out.println(t);
    }

}
