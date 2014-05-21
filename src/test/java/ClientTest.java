import com.zchen.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.SourceExtractor;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.support.MarshallingUtils;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-ws-servlet.xml")
public class ClientTest {

    private String uri = "http://127.0.0.1:8080/userService/user.wsdl";
    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Test
    public void testMarshalSend() throws Exception {
        User user = new User(1, "chenzhouce");
        User returnUser = (User) webServiceTemplate.marshalSendAndReceive(uri, user);
        System.out.println(returnUser);
    }

    @Test
    public void testSourceSend() throws Exception {
        User user = new User(1, "chenzhouce");

        StringWriter prepareData = new StringWriter();
        webServiceTemplate.getMarshaller().marshal(user, new StreamResult(prepareData));
        String userString = prepareData.toString();


        Source requestPayload = new StringSource(userString);
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        boolean ret =  webServiceTemplate.sendSourceAndReceiveToResult(uri, requestPayload, result);
        System.out.println("Return :" + ret);
        System.out.println(sw);

        Source resultSource = new StringSource(sw.toString());
        User returnUser = (User) webServiceTemplate.getUnmarshaller().unmarshal(resultSource);
        System.out.println(returnUser);
    }

    @Test
    public void testSendCallback() throws Exception {
        User user = new User(1, "chenzhouce");
        User returnUser = (User) webServiceTemplate.marshalSendAndReceive(uri, user, new WebServiceMessageCallback() {
            public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
                System.out.println(message);
            }
        });
        System.out.println(returnUser);
    }
}