package com.demo.myapp.payloads;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SrcDestDto implements Serializable {
    private String source;
    private  String destination;
}
