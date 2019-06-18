package org.mabrarov.springboottemplateprocessor;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.source.ConfigurationProperty;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.core.env.Environment;

import java.util.Objects;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class Main implements CommandLineRunner {

  static final String CONFIG_OPTION_NAME_PREFIX = "template-processor";

  private static final ConfigurationPropertyName CONFIG_OPTION_NAME =
      ConfigurationPropertyName.of(CONFIG_OPTION_NAME_PREFIX + ".option");

  private final Environment environment;
  private final Config config;

  public static void main(String[] args) {
    final SpringApplication application = new SpringApplication(Main.class);
    application.setBannerMode(Banner.Mode.OFF);
    application.run(args);
  }

  public Main(Environment environment, Config config) {
    this.environment = environment;
    this.config = config;
  }

  @Override
  public void run(String... args) {
    System.out.printf("Value from ConfigurationProperties: %s%n", config.getOption());

    Iterable<ConfigurationPropertySource> propertySources = ConfigurationPropertySources.get(environment);
    for (ConfigurationPropertySource propertySource : propertySources) {
      ConfigurationProperty property = propertySource.getConfigurationProperty(CONFIG_OPTION_NAME);
      if (property != null) {
        System.out.printf("Value from %s property source: %s%n",
            propertySource.getUnderlyingSource(), property.getValue());
      }
    }

    Object value = StreamSupport.stream(propertySources.spliterator(), false)
        .map(s -> s.getConfigurationProperty(CONFIG_OPTION_NAME))
        .filter(Objects::nonNull)
        .findFirst()
        .map(ConfigurationProperty::getValue).orElse(null);
    System.out.printf("First value from property sources: %s%n", value);
  }
}
