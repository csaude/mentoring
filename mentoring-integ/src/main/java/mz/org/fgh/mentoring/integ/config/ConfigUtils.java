package mz.org.fgh.mentoring.integ.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;


public class ConfigUtils {
    public static final String RUNTIME_PROPS_FILENAME = "mentoring_runtime.properties";
    public static final String RUNTIME_ENV_NAME = "MENTORING_RUNTIME_PROPERTIES_PATH";
    public static final String JWT_KEY_RUNTIME_PROPERTY_NAME = "jwt.key";
    private static final Logger logger = Logger.getLogger(ConfigUtils.class.getName());

    public static String retrieveJWTKeyFromRunTime() throws IOException {
        String runtimePropertiesPath = System.getenv(RUNTIME_ENV_NAME);
        if(runtimePropertiesPath == null) runtimePropertiesPath = "/opt/" + RUNTIME_PROPS_FILENAME;

        File file = new File(runtimePropertiesPath);
        if(file.exists() && file.isFile()) {
            if(!file.canRead()) {
                logger.severe("Could not read " + runtimePropertiesPath + ", permission denied");
                return null;
            } else {
                // Read
                logger.info("Loading runtime properties file " + runtimePropertiesPath);
                try (FileInputStream fileInput = new FileInputStream(file)){
                    Properties runtimeProps = new Properties();
                    runtimeProps.load(fileInput);
                    String JWTKey = runtimeProps.getProperty(JWT_KEY_RUNTIME_PROPERTY_NAME);
                    if(JWTKey == null) {
                        String message = "Could not find property: " + JWT_KEY_RUNTIME_PROPERTY_NAME + " in " + runtimePropertiesPath;
                        logger.severe(message);
                        return null;
                    }
                    return JWTKey;
                }
            }
        } else {
            return null;
        }
    }
}
