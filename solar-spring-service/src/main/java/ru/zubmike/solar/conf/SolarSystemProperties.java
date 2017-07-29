package ru.zubmike.solar.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.zubmike.solar.utils.DataBaseConfiguration;

@ConfigurationProperties(prefix = "solar-system")
public class SolarSystemProperties {

    private DataBaseConfiguration database;

    public DataBaseConfiguration getDatabase() {
        return database;
    }

    public void setDatabase(DataBaseConfiguration database) {
        this.database = database;
    }
}
