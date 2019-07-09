package com.john.www.main.optional;

import org.testng.annotations.Test;

import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * @ProjectName: java8Demo
 * @Author： @iyungu.com
 * @Description:
 * @Date 2018/9/9  16:53
 * @Modified By:
 */
public class TestOptional2 {
    /**
     * 1.创建 Optional
     */
    @Test
    public void createOptional(){
        //声明一个空Optional
        Optional<Address>  optionalAddress= Optional.empty();
        //依据一个非空值创建Optional,Address不可为null
        Optional<Address> optionalAddress1=Optional.of(new Address());
        //可接受null的Optional,Address可为null
        Optional<Address> optionalAddress2=Optional.ofNullable(new Address());
    }
    /**
     * 2.使用map 从Optional对象中提取和转换值
     */
    @Test
    public void map(){
        Optional<Address> optionalAddress=Optional.ofNullable(new Address("达尔文路","88号"));
        Optional<String> street= optionalAddress.map(Address::getStreet);
        System.out.println(street.get());
    }
    /**
     * 3.使用flatMap 扁平化引用 链接Optional对象
     */
    @Test
    public void flatMap(){
        Optional<User> optionalUser=Optional.of(new User());
//        Optional<String> street1=  optionalUser.map(user -> user.getAddress().getStreet());
        Optional<String> street=  optionalUser.flatMap(User::getOptAddress).map(Address::getStreet);
//        Optional<String> street= optionalAddress.map(Address::getStreet);
        System.out.println(/*street1.get()+*/"===="+street.get());
    }
    /**
     * 4.默认行为及解引用Optional 对象
     */
    @Test
    public void defaultValue(){
        Optional<Address> optionalAddress=Optional.ofNullable(null);
       String street= optionalAddress.map(Address::getStreet).orElse("北京二环");
        System.out.println(/*street1.get()+*/"===="+street);
    }



    @Test
    public void test1(){
      User user=new User();
      user.setUsername("john");
      user.setPassword("123456");
      user.setOptAddress(Optional.of(new Address("达尔文路","88号")));

      boolean isTrue=user.getOptAddress().filter(address -> address.getDoor().contains("88"))
              .isPresent();
         System.out.println("isTure="+isTrue);
        try {
            Address address1=user.getOptAddress().filter(address -> address.getDoor().contains("808"))
                    .orElseThrow(new Supplier<Throwable>() {
                        @Override
                        public Throwable get() {
                            return new Exception("挂了");
                        }
                    });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
    @Test
    public void testStreet(){
        User user=new User();
        user.setUsername("john");
        user.setPassword("123456");
        user.setAge(10);
        user.setOptAddress(Optional.of(new Address("达尔文路","88号")));
        System.out.println(getStreet(Optional.of(user),20));
    }

    public String getStreet(Optional<User> user,int minAge){

        return user.filter(u->u.getAge()>=minAge)
                .flatMap(User::getOptAddress)
                .map(Address::getStreet)
                .orElse("默认值");
    }

    /**
     * 解析异常的封装
     * @param num 参数值
     * @return
     */
    public static Optional<Integer> parseIntDo(String num){
        try {
         return  Optional.ofNullable(Integer.parseInt(num));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public int readPoint(Properties props, String name){
      return  Optional.ofNullable(props.getProperty(name))
        .flatMap(TestOptional2::parseIntDo)
        .filter(i -> i>0)
        .orElse(0);

    }

}
