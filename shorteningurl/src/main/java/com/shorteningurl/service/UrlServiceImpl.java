package com.shorteningurl.service;

import com.shorteningurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public List<Map<String, Object>> getUrls() {
        return urlRepository.getUrls();
    }
}
