package msa.gateway.msagateway.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;

/**
 *  default 로드 밸런싱 설정 변경
 */
public class RibbonConfiguration {

    @Bean
    public IPing ribbonPing(final IClientConfig config){
        // service 상태 확인
        return new PingUrl(false, "/actuator/health");
    }

    @Bean
    public IRule ribbonRule(final IClientConfig config){
        // 기본적인 round-robin을 대체한다
        // 핑을 보내 응답하지 않는 인스턴스를 건너뛴다.
        return new AvailabilityFilteringRule();
    }

}
