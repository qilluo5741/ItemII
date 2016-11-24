package com.sharebo.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.cache.Cache;

import com.sharebo.util.RedisUtil;
import com.sharebo.util.SerializeUtil;
/***
 * mybatis”Îredis
 * @author niewei
 */
public class MybatisRedisCache implements Cache {
	private static Logger logger = LoggerFactory
			.getLogger(MybatisRedisCache.class);
	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private String id;

	public MybatisRedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		logger.warn("init Mapper id is £∫{}", id);
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void putObject(Object key, Object value) {
		try {
			RedisUtil.getJedis().set(SerializeUtil.serialize(key.toString()),
					SerializeUtil.serialize(value));
			logger.warn("redis put[{}] ", key);
		} catch (Exception e) {
			logger.error("ERROR:May not connected service");
		}
	}

	public Object getObject(Object key) {
		Object value = null;
		try {
			value = SerializeUtil.unserialize(RedisUtil.getJedis().get(
					SerializeUtil.serialize(key.toString())));
			logger.warn("redis get[{}] ", key);
		} catch (Exception e) {
			logger.error("ERROR:May not connected service");
			return null;
		}
		return value;
	}

	public Object removeObject(Object key) {
		Object obj = null;
		try {
			obj = RedisUtil.getJedis().expire(
					SerializeUtil.serialize(key.toString()), 0);
			logger.warn("redis remove[{}] ", key);
		} catch (Exception e) {
			logger.error("ERROR:May not connected service");
			return null;
		}
		return obj;
	}

	public void clear() {
		RedisUtil.getJedis().flushDB();
	}

	public int getSize() {
		return Integer.valueOf(RedisUtil.getJedis().dbSize().toString());
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

}