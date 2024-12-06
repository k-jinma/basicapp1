package com.example.basicapp1.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageSource messageSource;

    /** 
     * メッセージを取得する
     * @param code メッセージコード
     * @return メッセージ
     */
    public String getMessage(String code){
        return messageSource.getMessage(code, null, Locale.getDefault());
    }
}
