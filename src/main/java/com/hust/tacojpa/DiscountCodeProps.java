package com.hust.tacojpa;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "taco.discount")
public class DiscountCodeProps {
    private Map<String,Integer> codes = new HashMap<>();
}
