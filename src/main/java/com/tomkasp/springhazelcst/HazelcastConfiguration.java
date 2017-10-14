package com.tomkasp.springhazelcst;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.singletonList;

/**
 * @author Tomasz Kasprzycki
 */
@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config config() {

        Config config = new Config();

        NetworkConfig network = config.getNetworkConfig();
        network.setPort(5701).setPortCount(20);
        network.setPortAutoIncrement(true);

        JoinConfig joinConfig = network.getJoin();

        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig().setEnabled(true).setMembers(singletonList("127.0.0.1"));

        return config;
    }
}
