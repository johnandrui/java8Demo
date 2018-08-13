package com.iyungu.www.entity.op;

import com.iyungu.www.entity.Trader;
import com.iyungu.www.entity.Transanction;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: java8Demo
 * @Author： zhangrui@iyungu.com
 * @Description:
 * @Date 2018/5/11  10:06
 * @Modified By:
 */
public class TestTransanction {

    List<Transanction> transanctions = null;

    @Before("")
    public void before(){
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

}
