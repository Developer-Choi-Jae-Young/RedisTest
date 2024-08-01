package com.example.demo;

import com.example.demo.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final RedisRepository redisRepository;

    @GetMapping("/insert")
    public void TestInsert() {
        redisRepository.insertRedis("Test", "Test", 10000L);
    }
}
