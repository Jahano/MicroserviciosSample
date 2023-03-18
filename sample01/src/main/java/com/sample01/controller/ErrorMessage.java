package com.sample01.controller;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorMessage {
    private String code;
    List<Map<String,String>> messages;
}
