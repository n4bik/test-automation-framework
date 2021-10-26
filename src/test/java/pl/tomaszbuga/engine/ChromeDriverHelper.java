package pl.tomaszbuga.engine;

public class ChromeDriverHelper {
    // getting the Operating System name (Windows/macOS/Linux)
    // and assigning it to the "os" variable
    static final String os = System.getProperty("os.name").toLowerCase();

    private static String getDriverString() {
        // setting the file path for the Chrome WebDriver
        String base = "./drivers/chromedriver";
        String osSuffix;

        if (isWindows()) {
            osSuffix = ".exe";
        } else if (isMac()) {
            // checking if the macOS is using Intel or Apple Silicon (M1) CPU
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

    public static boolean isIntel() {
        // intel os.arch = "x86_64"; apple silicon (m1) os.arch = "aarch64"
        return System.getProperty("os.arch").equals("x86_64");
    }

    public static boolean isUnix() {
        return os.contains("nix") || os.contains("nux") || os.contains("aix");
    }

    public static void setDriver() {
        System.setProperty("webdriver.chrome.driver", ChromeDriverHelper.getDriverString());
    }
}
