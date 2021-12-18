package pl.tomaszbuga.pom.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BasePage {
    protected final Logger LOGGER;
    protected final String baseUrl = "http://localhost:4200/";

    public BasePage(Class<?> cls) {
        LOGGER = LogManager.getLogger(cls);
    }
}
