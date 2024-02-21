package com.example.jg.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHelloResponseDto {
    @Test
    public void test() {
        String name = "test";
        int amount = 1000;
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(1000);
    }
}

