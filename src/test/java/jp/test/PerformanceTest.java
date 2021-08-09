package jp.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;

import jp.test.Object.UserInfoObject;
import jp.test.Service.UserInfoService;

public class PerformanceTest extends UserTestApplicationTests {
	 
	 
    @Autowired
    public UserInfoService userInfoService;
    //@Ignore("not ready yet")
//    @Test
//    public void testGetFile(){
//        Assert.assertSame("不成功",true,userInfoService.testCsv());
//    }
// 
//    @Test
//    public void testGetEntFileList(){
//        Assert.assertSame("企业数量不为10",10,userInfoService.getEntFileList());
//    }
//}
}
