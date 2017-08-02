package br.uema.skeleton.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkeletonApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkeletonApplication.class, args);
        new AppInitialize();
    }
}
