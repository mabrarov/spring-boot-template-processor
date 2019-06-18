Spring Boot Template Processor
============

Template processor utilizing Spring Boot relaxed binding for data source

Build:

```bash
$ mvn clean package
```

Run:

```bash
$ export TEMPLATE_PROCESSOR_OPTION="environment variable" && java -Dtemplate-processor.option="Java system property" -jar target/spring-boot-template-processor-0.0.1-SNAPSHOT.jar --template-processor.option="command line"
```

Expected output:

```text
Value from ConfigurationProperties: command line
Value from SimpleCommandLinePropertySource {name='commandLineArgs'} property source: command line
Value from MapPropertySource {name='systemProperties'} property source: Java system property
Value from OriginAwareSystemEnvironmentPropertySource {name='systemEnvironment'} property source: environment variable
Value from OriginTrackedMapPropertySource {name='applicationConfig: [classpath:/application.yml]'} property source: builtin
First value from property sources: command line
```