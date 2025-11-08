package com.automation.megamind.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // Import the SLF4J factory

public class Log {

    // Initialize the SLF4J Logger for this utility class
    private static final Logger Log = LoggerFactory.getLogger(Log.class);

    // --- Logging methods for different levels ---

    public static void startTestCase(String sTestCaseName){
        // Log at INFO level
        Log.info("****************************************************************************************");
        Log.info("$$$$$$$$$$$$$$$$$$$$$      {}       $$$$$$$$$$$$$$$$$$$$$$", sTestCaseName);
        Log.info("****************************************************************************************");
    }

    public static void endTestCase(){
        Log.info("XXXXXXXXXXXXXXXXXXXXXX             -E-N-D-             XXXXXXXXXXXXXXXXXXXXXX");
    }

    public static void info (String message) {
        // Simple INFO message
        Log.info(message);
    }

    public static void warn (String message) {
        Log.warn(message);
    }

    public static void error (String message) {
        Log.error(message);
    }

    // Recommended practice: Use parameterized logging for better performance
    public static void debug (String message, Object... args) {
        // Use {} placeholders. The string concatenation only happens if DEBUG is enabled.
        Log.debug(message, args);
    }
}