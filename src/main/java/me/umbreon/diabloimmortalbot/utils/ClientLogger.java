package me.umbreon.diabloimmortalbot.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientLogger {

    private static File logFolder;
    private static File logFile;

    private ClientLogger() {
    }

    public static void checkIfLogFolderExists(String logFolderPath) {
        logFolder = new File(logFolderPath);
        if (!doesLogFolderExists()) {
            logFolder.mkdir();
        }
    }

    public static void createNewErrorLogEntry(Exception logMessage) {
        logFile = new File("/home/discord/logs/error-log.log");

        if (!doesLogFileExist()) {
            createNewLogFile("client-log");
        }

        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            bufferedWriter.append("\n[")
                    .append(Time.getCurrentDate())
                    .append(" ")
                    .append(Time.getCurrentTime())
                    .append("] ")
                    .append(logMessage.getMessage())
                    .append("\n");
            for (StackTraceElement e : logMessage.getStackTrace()) {
                bufferedWriter.append(String.valueOf(e)).append("\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createNewClientLogEntry(String logMessage) {
        logFile = new File("/home/discord/logs/client-log.log");

        if (!doesLogFileExist()) {
            createNewLogFile("client-log");
        }

        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            bufferedWriter.append("\n[")
                    .append(Time.getCurrentDate())
                    .append(" ")
                    .append(Time.getCurrentTime())
                    .append("] ")
                    .append(logMessage);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createNewLogFile(String logFileName) {
        logFile = new File("/home/discord/logs/" + logFileName + ".log");

        try {
            logFile.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean doesLogFolderExists() {
        return logFolder.exists();
    }

    private static boolean doesLogFileExist() {
        return logFile.exists();
    }
}
