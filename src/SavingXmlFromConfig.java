import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

class SavingXmlFromConfig {

    private static final String CONFIG_FILE = "src\\config.properties";
    private String url;
    private String filePath;

    public SavingXmlFromConfig() {
        loadConfig();
    }

    public String getUrl() {
        return url;
    }

    public String getFilePath() {
        return filePath;
    }

    private void loadConfig() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            url = properties.getProperty("url");
            filePath = properties.getProperty("xml.file.path");
        } catch (IOException e) {
            System.out.println("Config file's problem");
        }
    }
}
