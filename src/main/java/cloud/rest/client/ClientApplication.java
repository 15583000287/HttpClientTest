package cloud.rest.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 如何关闭httpclient在控制台输出DEBUG日志
 *
 * httpclient使用commons-logging框架
 * 而SpringBoot使用logback框架的时候，commons-logging会自动转移到logback
 * 解决：创建一个logback.xml日志配置文件，内容如下
 *  <?xml version="1.0" encoding="UTF-8" ?>
 *  <configuration>
 *     <logger name="org.apache" level="WARN"/>
 *  </configuration>
 */
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) throws Exception{
        //创建默认的HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //调用6次服务并输入调用结果
        for(int i=0; i<6;i++){
            //调用Get方法请求服务
            HttpGet httpGet = new HttpGet("http://localhost:9000/router");
            //获取响应
            HttpResponse response = httpClient.execute(httpGet);
            // 根据 响应解析出字符串
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }

}
