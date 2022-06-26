package ru.internet.sergeevss90.owner;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:properties/browserStackDriver.properties"
})

public interface BrowserStackInterface extends Config {
    String username();
    String access_key();
    String project();
    String build();
    String name();
    String app();
    String device();
    String osVersion();
    String bsUrl();
}
