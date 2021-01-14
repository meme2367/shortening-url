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
    /*
      0. 유효성검사
      1. db에 이미 있는 longurl이면 그대로 id값가져다 62인코딩한 short url  반환 (+http:// 붙여서)
      2. db에 없으면 longurl저장하면서(https:// 나 http:// 제거하고) id가져와 62인코딩해서 short url 결과 반환 (http:// 붙여서)
      3.
      * */
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

        System.out.println("====check=====");
        System.out.println(base62Util.decoding(shorturl));
        System.out.println("====check=====");

        return "http://localhost:8080/"+shorturl;
    }

    @Override
    public String getLongUrlByShortUrl(String shorturl) {
        //http 다 뺀 뒤 조금만있음
        int id = base62Util.decoding(shorturl);
        String longurl = urlRepository.getLongUrlById(id);

        return longurl;
    }

}
