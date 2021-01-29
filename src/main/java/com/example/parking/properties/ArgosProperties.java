package com.example.parking.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "argos")
public class ArgosProperties {

    private String formationLink;
    private String efepLink;
    private String cameleonLink;
    private String klifLink;
    private String nasBasePath;
}
