import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {

    //1.注入 RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testConfirm() {

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("comfrim方法执行了");
                if (b) {
                    System.out.println("接收消息成功");
                } else {
                    System.out.println("接收消息失败");
                }
            }
        });

        //2.发送消息
        rabbitTemplate.convertAndSend("spring_direct_exchange_confrim", "confrim", "hello world spring....");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testReturn() {

        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("return方法执行了");
                System.out.println("message:" + new String(message.getBody()));
                System.out.println("i:" + i);
                System.out.println("s:" + s);
            }
        });

        //2.发送消息
        rabbitTemplate.convertAndSend("spring_direct_exchange_confrim", "confrim", "hello world spring....");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void  testPrefetch(){
        for (int i = 0; i < 10 ; i++) {
            rabbitTemplate.convertAndSend("spring_direct_exchange_confrim","confrim","今天天气真热。");
        }
    }

    @Test
    public void  testTTl(){
        for (int i = 0; i < 10 ; i++) {
            rabbitTemplate.convertAndSend("test_exchange_ttl","ttl.test","今天天气真热。");
        }
    }

    @Test
    public void  testDlx(){
        for (int i = 0; i < 10 ; i++) {
            rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.normal","今天天气真热。");
        }
    }


    @Test
    public void  testDlxOrder() throws InterruptedException {
        rabbitTemplate.convertAndSend("order_exchange","order.dlx","今天天气真热。");
        for (int i = 1; i <= 10 ; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
    }
}
