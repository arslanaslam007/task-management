package com.example.setup.util;

import com.example.setup.object.ResponseMetrics;
import org.springframework.http.HttpStatus;

public class MetricsUtils {
    public static ResponseMetrics convertToOkResponse(Object value){
        return new ResponseMetrics(value, HttpStatus.OK,null);
    }
}
