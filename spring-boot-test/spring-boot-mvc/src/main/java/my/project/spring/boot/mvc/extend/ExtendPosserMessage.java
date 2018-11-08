package my.project.spring.boot.mvc.extend;

import my.project.spring.boot.mvc.extend.message.PersonMessageWebMvcConfiguration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 扩展消息
 *
 * @author ZhangWeiKang
 * @create 2018/7/23
 */
public class ExtendPosserMessage extends WebMvcConfigurationSupport {

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PersonMessageWebMvcConfiguration());
    }
}
