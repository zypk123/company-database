package com.huntkey.rx.hr.provider.client.fallback;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.provider.client.ORMClient;
import org.springframework.stereotype.Component;

@Component
public class ORMClientFallback implements ORMClient {
    @Override
    public Result add(String data) {
        return null;
    }

    @Override
    public Result delete(String data) {
        return null;
    }

    @Override
    public Result update(String data) {
        return null;
    }

    @Override
    public Result find(String data) {
        return null;
    }

    @Override
    public Result load(String data) {
        return null;
    }

    @Override
    public Result query(String sql) {
        return null;
    }

    @Override
    public Result getEnumsObj(String parCode, String code) {
        return null;
    }

}
