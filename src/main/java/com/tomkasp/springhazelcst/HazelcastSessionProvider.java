package com.tomkasp.springhazelcst;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class HazelcastSessionProvider implements SessionProvider {

    public static final long sessionKey = 1L;
    private final HazelcastInstance hazelcastInstance;
    private IMap<Long, String> sessionMap;

    @Autowired
    public HazelcastSessionProvider(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @PostConstruct
    public void init() {
        sessionMap = hazelcastInstance.getMap("session");
    }

    @Override
    public String getSession(Long key) {
        return sessionMap.get(key);
    }

    @Override
    public boolean setSession(String session) {
        try {
            boolean obtainTheLock = sessionMap.tryLock(sessionKey, 5, TimeUnit.SECONDS);
            try {
                if (!obtainTheLock) {
                    return false;
                }
                sessionMap.put(sessionKey, session);
            } finally {
                sessionMap.unlock(sessionKey);
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
