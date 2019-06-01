import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Testcase2 {
    @Before
    public void before(){
        System.out.println("测试前的准备工作");
    }

    @After
    public void after(){
        System.out.println("测试后的处理");
    }

    @Test
    public void testsum1(){
        int result = SumUtil.sum1(1,2);
        Assert.assertEquals(result,3);
    }

    @Test
    public void testsum2(){
        int result = SumUtil.sum2(1,2,3);
        Assert.assertEquals(result,5);
    }
}
