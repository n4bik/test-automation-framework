package pl.tomaszbuga.engine;

public class ChromeDriverHelper {
    static final String os = System.getProperty("os.name").toLowerCase();

    private static String getDriverString() {
        String base = "./drivers/chromedriver";
        String osSuffix;

        if (isWindows()) {
            osSuffix = ".exe";
        } else if (isMac()) {
            osSuffix = isIntel() ? "MacIntel" : "MacM1";
        } else if (isUnix()) {
            osSuffix = "Linux";
        } else {
            throw new RuntimeException("No driver for your OS found.");
        }

        return base + osSuffix;
    }

    public static boolean isWindows() {
        return os.contains("win");
    }

    public static boolean isMac() {
        return os.contains("mac");
    }

    // intel os.arch = "x86_64"; apple silicon (m1) os.arch = "aarch64"
    public static boolean isIntel() {
        return System.getProperty("os.arch").equals("x86_64");
    }

    public static boolean isUnix() {
        return os.contains("nix") || os.contains("nux") || os.contains("aix");
    }

    public static void setDriver() {
        System.setProperty("webdriver.chrome.driver", ChromeDriverHelper.getDriverString());
    }
}
