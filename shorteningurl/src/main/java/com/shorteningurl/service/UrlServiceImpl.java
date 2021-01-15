package com.shorteningurl.service;

import com.shorteningurl.repository.UrlRepository;
import com.shorteningurl.utils.Base62Util;
import com.shorteningurl.utils.UrlTypeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private UrlTypeValidation urlTypeValidation;

    @Autowired
    private Base62Util base62Util;

    @Override
    public List<Map<String, Object>> getUrls() {
        return urlRepository.getUrls();
    }

    @Override
    public String generateShortUrl(String longurl){

        if(!urlTypeValidation.valid(longurl)){
            throw new IllegalArgumentException("잘못된 URL 타입입니다.");
        }

        longurl = longurl.replace("https://","").replace("http://","");
        Integer id = urlRepository.getUrlIdByLongUrl(longurl);
        String shorturl;
        if(id == null) {
            id = urlRepository.updateLongUrl(longurl);
        }
        shorturl = base62Util.encoding(id);

        return "http://localhost:8080/"+shorturl;
    }

    @Override
    public String getLongUrlByShortUrl(String shorturl) {
        int id = base62Util.decoding(shorturl);
        String longurl = urlRepository.getLongUrlById(id);

        return longurl;
    }

}
