package com.huntkey.rx.sceo.formula.common.util;

/**
 * @author lulx on 2017/6/16 0016.
 */
public enum Scope {

    /**
     * 系统变量
     */
    VARIANT_SCOPE_SYSTEM("system","系统变量"),

    /**
     * 局部变量
     */
    VARIANT_SCOPE_LOCAL("local","局部变量");

    public String getScope() {
        return scope;
    }

    private String scope;

    private String scopeDesc;

    Scope(String scope, String scopeDesc) {
        this.scope = scope;
        this.scopeDesc = scopeDesc;
    }
}
