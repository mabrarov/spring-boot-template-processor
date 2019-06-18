package org.mabrarov.springboottemplateprocessor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

  private final Config config;

  public Main(final Config config) {
    this.config = config;
  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) {
    System.out.printf("Hello, %s!%n", config.getName());
  }
}
