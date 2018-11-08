package my.project.cloud.interceptors;

import my.project.cloud.commons.ILoadBalances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * 扩展RestTemplate,拦截器
 *
 * @author ZhangWeiKang
 * @create 2018/8/9
 */
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private ILoadBalances loadBalances;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        URI uri = request.getURI();

        String path = uri.getPath();
        String[] split = path.substring(1).split("/");

        String serverName = split[0];
        String business = split[1];

        String productUrl = loadBalances.selectOne(serverName);


        return new MySimpleClientHttpResponse(getURLConnection(productUrl,business,uri));
    }

    private URLConnection getURLConnection(String loadBalancerUrl,String business , URI uri) throws IOException {
        StringBuffer requestUrl = new StringBuffer();
        requestUrl.append(loadBalancerUrl);
        requestUrl.append("/");
        requestUrl.append(business);
        String query = uri.getQuery();
        if (query != null){
            requestUrl.append("?");
            requestUrl.append(query);
        }
        return new URL(requestUrl.toString()).openConnection();
    }

    private static class MySimpleClientHttpResponse implements ClientHttpResponse {
        private URLConnection urlConnection;

        public MySimpleClientHttpResponse(URLConnection urlConnection) {
            this.urlConnection = urlConnection;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.valueOf(((HttpURLConnection)urlConnection).getResponseCode());
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.getStatusCode().value();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.getStatusCode().getReasonPhrase();
        }

        @Override
        public void close() {
            try {
                this.getBody().close();
            } catch (IOException e) {
            }
        }

        @Override
        public InputStream getBody() throws IOException {
            return urlConnection.getInputStream();
        }

        @Override
        public HttpHeaders getHeaders() {
            return new HttpHeaders();
        }
    }
}
