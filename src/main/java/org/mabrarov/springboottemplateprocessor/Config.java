package org.mabrarov.springboottemplateprocessor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(Main.CONFIG_OPTION_NAME_PREFIX)
public class Config {

  private String option;

  public String getOption() {
    return option;
  }

  public void setOption(String option) {
    this.option = option;
  }
}
