package com.jave.config;

import com.jave.MongoRepositoryAdapter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"com.jave"},
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$"),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+Handler$"),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MongoRepositoryAdapter.class})
        },
        useDefaultFilters = false)
public class UseCaseConfig {
}
