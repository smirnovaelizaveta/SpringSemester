import { Project } from '../model/project'

const MOCK_CODE_JAVA = `package org.example;

@RestController
@EnableAutoConfiguration
public class MyApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}`

const MOCK_CODE_XML = `<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>ru.springsemester</groupId>
    <artifactId>springsemester</artifactId>
    <version>1.0</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.0</version>
    </parent>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>

        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.4</version>
        </dependency>



        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>

        <!-- Spring Security -->
<!--        <dependency>-->
<!--            <groupId>org.springframework.boot</groupId>-->
<!--            <artifactId>spring-boot-starter-security</artifactId>-->
<!--        </dependency>-->

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

        <!-- For Spring Security testing -->
<!--        <dependency>-->
<!--            <groupId>org.springframework.security</groupId>-->
<!--            <artifactId>spring-security-test</artifactId>-->
<!--        </dependency>-->

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
<!--            <plugin>-->
<!--                <groupId>com.github.eirslett</groupId>-->
<!--                <artifactId>frontend-maven-plugin</artifactId>-->
<!--                <version>1.6</version>-->
<!--                <executions>-->
<!--                    <execution>-->
<!--                        <id>install node and npm</id>-->
<!--                        <goals>-->
<!--                            <goal>install-node-and-npm</goal>-->
<!--                        </goals>-->
<!--                        <configuration>-->
<!--                            <nodeVersion>v10.15.1</nodeVersion>-->
<!--                            <npmVersion>6.4.1</npmVersion>-->
<!--                        </configuration>-->
<!--                    </execution>-->
<!--                    <execution>-->
<!--                        <id>npm install</id>-->
<!--                        <goals>-->
<!--                            <goal>npm</goal>-->
<!--                        </goals>-->
<!--                        <phase>generate-resources</phase>-->
<!--                        <configuration>-->
<!--                            <arguments>install</arguments>-->
<!--                        </configuration>-->
<!--                    </execution>-->
<!--                    <execution>-->
<!--                        <id>npm run build</id>-->
<!--                        <goals>-->
<!--                            <goal>npm</goal>-->
<!--                        </goals>-->
<!--                        <phase>generate-resources</phase>-->
<!--                        <configuration>-->
<!--                            <arguments>run build</arguments>-->
<!--                        </configuration>-->
<!--                    </execution>-->
<!--                </executions>-->
<!--            </plugin>-->
        </plugins>
    </build>
</project>

`

export const MOCK_PROJECT: Project = {
  files: [
    {
      name: 'src',
      readonly: false,
      children: [
        {
          name: 'main',
          readonly: false,
          children: [
            {
              name: 'java',
              readonly: false,
              children: [
                {
                  name: 'org',
                  readonly: false,
                  children: [
                    {
                      name: 'example',
                      readonly: false,
                      children: [
                        {
                          name: 'Main.java',
                          contents: MOCK_CODE_JAVA,
                          readonly: false,
                        }
                      ]
                    }
                  ]
                }
              ]
            },
            {
              name: "resources",
              readonly: false,
              children: [
                {
                  name: "application.properties",
                  readonly: false,
                }
              ]
            }
          ]
        },
        {
          name: 'test',
          readonly: false,
          children: [
            {
              name: 'java',
              readonly: false,
              children: [
                {
                  name: 'org',
                  readonly: false,
                  children: [
                    {
                      name: 'example',
                      readonly: false,
                      children: [
                        {
                          name: 'Main.java',
                          contents: MOCK_CODE_JAVA,
                          readonly: false,
                        }
                      ]
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]
    },
    {
      name: 'pom.xml',
      contents: MOCK_CODE_XML,
      readonly: false
    },
  ]
};