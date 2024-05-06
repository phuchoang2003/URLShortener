package com.demo.urlshorterner.utils;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private String message;
    private int status;
    private long timeStamp;
}
