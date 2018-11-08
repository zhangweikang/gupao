package my.project.cloud.commons;

/**
 * 负载均衡接口
 *
 * @author ZhangWeiKang
 * @create 2018/8/8
 */
public interface ILoadBalances {

    <T> T selectOne(String serviceName);
}
