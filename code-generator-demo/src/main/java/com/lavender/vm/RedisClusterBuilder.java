package com.lavender.vm;


import com.ppdai.cachecloud.redis.clients.jedis.HostAndPort;
import com.ppdai.cachecloud.redis.clients.jedis.JedisCluster;

/**
 * redis cluster 客户端builder
 * Created by yijunzhang on 14-7-27.
 */
public class RedisClusterBuilder extends JedisCluster {

    private JedisCluster jedisCluster;
    public RedisClusterBuilder(HostAndPort node) {
        super(node);
    }


}