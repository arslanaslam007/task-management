package com.example.setup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class ResponseMetrics {
    private Object value;
    private HttpStatus code;
    private Error error;
}
