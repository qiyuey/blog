package cn.edu.nuaa.burning.session.redis;

import org.apache.commons.lang.SerializationUtils;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Set;


public class SingleRedisConnection implements RedisConnection {

    private Jedis jedis;

    public SingleRedisConnection(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void close() {

    }

    @Override
    public Boolean isConnected() {
        return jedis.isConnected();
    }

    @Override
    public Long hset(String key, String field, Serializable object) {
        return jedis.hset(key.getBytes(),
                field.getBytes(),
                SerializationUtils.serialize(object));
    }

    @Override
    public Object hget(String key, String field) {
        byte[] bytes = jedis.hget(key.getBytes(), field.getBytes());
        if (bytes == null){
            return null;
        } else {
            return SerializationUtils.deserialize(bytes);
        }
    }

    @Override
    public Long del(String... keys) {
        return jedis.del(keys);
    }

    @Override
    public Long hdel(String key, String... fields) {
        return jedis.hdel(key, fields);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedis.expire(key, seconds);
    }

    @Override
    public Set<String> hkeys(String key) {
        return jedis.hkeys(key);
    }

    @Override
    public Boolean exists(String key) {
        return jedis.exists(key);
    }
}
