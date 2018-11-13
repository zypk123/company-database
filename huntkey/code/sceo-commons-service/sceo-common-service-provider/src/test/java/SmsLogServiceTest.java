import com.huntkey.rx.sceo.common.service.provider.CommonServiceProviderApplication;
import com.huntkey.rx.sceo.common.service.provider.service.EmailLogService;
import com.huntkey.rx.sceo.common.service.provider.service.SmsLogService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhaomj on 2017/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommonServiceProviderApplication.class)
public class SmsLogServiceTest {

    @Autowired
    private SmsLogService smsLogService;

    @Autowired
    private EmailLogService emailLogService;


    @Test
    @Ignore
    public void testSmsInsert() {

    }

//    @Test
//    public void testEmail(){
//        EmailLogWithBLOBs email = new EmailLogWithBLOBs();
//        email.setMailSysCode("SCEO_SECURITY_CENTER");
//        email.setMailModuleCode("USER_REG");
//        email.setMailRecipients("123@456.789");
//        email.setMailCopyRecipients("123@456.789");
//        email.setMailBlindCopyRecipients("123@456.789");
//        email.setMailSubject("test subject");
//        email.setMailBody("test email body");
//        email.setMailBodyFormat(EmailBodyFormat.TEXT.getValue());
//        Assert.assertEquals(1,emailLogService.insert(email));
//    }

}
