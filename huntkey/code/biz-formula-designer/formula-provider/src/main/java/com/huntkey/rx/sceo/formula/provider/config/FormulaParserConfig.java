package com.huntkey.rx.sceo.formula.provider.config;

import com.huntkey.rx.sceo.formula.engine.Parser;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @@author chenfei on 2017/7/19.
 */
@Configuration
public class FormulaParserConfig {

    @Autowired
    private FunctionHandlerBuildIn functionHandlerBuildIn;

    @Bean
    public Parser createFormulaParser() {

        return new Parser(functionHandlerBuildIn);
    }
}
