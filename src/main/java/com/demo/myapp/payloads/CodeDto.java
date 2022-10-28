package com.demo.myapp.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CodeDto {
    private int route_id;
    private String loc_code;
}
