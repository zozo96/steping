package start;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sonya
 * @date 2020/11/4 1:20
 */
@SpringBootApplication
@RestController
public class StepStart {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // 注入对象
    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public String hello() {
        // 读取配置
        String port = env.getProperty("server.port");
        return port;
    }
}
