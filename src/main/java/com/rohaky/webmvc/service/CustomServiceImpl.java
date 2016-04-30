package com.rohaky.webmvc.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomServiceImpl implements CustomService {
    @Override
    public List<String> listPost() {
        return Arrays.asList(
                "1th Post",
                "2th Post",
                "3th Post",
                "4th Post",
                "5th Post"
        );
    }

}
