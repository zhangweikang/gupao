package my.project.spring.boot.mvc.extend.message;

import my.project.spring.boot.mvc.domain.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 扩展Person返回消息
 *
 * @author ZhangWeiKang
 * @create 2018/7/23
 */
@Configuration
public class PersonMessageWebMvcConfiguration extends AbstractHttpMessageConverter<Person> {

    public PersonMessageWebMvcConfiguration() {
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class aClass) {
        return aClass.isAssignableFrom(Person.class);
    }

    @Override
    protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        Properties properties = new Properties();
        InputStream body = httpInputMessage.getBody();

        properties.load(new InputStreamReader(body,getDefaultCharset()));

        Person person = new Person();
        person.setId(Long.valueOf(properties.getProperty("person.id")));
        person.setName(properties.getProperty("person.name"));
        return person;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream body = httpOutputMessage.getBody();

        Properties properties = new Properties();

        properties.setProperty("person.id",String.valueOf(person.getId()));
        properties.setProperty("person.name",person.getName());

        properties.store(new OutputStreamWriter(body,getDefaultCharset()),"Written by web server");

    }
}
