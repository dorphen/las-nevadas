package tk.thebrick.lasnevadas;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv config = Dotenv.load();

    public static String get(String key) {
        return config.get(key.toUpperCase());
    }
}
