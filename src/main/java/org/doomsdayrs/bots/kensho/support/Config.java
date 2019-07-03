package org.doomsdayrs.bots.kensho.support;
/*
 * This file is part of Kensho.
 * Kensho is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with Kensho.  If not, see <https://www.gnu.org/licenses/>.
 * ====================================================================
 * Kensho
 * 03 / 07 / 2019
 *
 * @author github.com/doomsdayrs
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URLDecoder;

public class Config {
    enum PARSABLE {
        STRING(),
        BOOLEAN(),
        LONG(),
        INTEGER(),
        JSONOBJECT(),
        JSONARRAY()
    }

    private static final String configFile = "config.json";
    private static final String execDir = getExecDir();
    private static final String[] names = {"token", "prefix", "logEnable", "questions", "reportChannel"};
    private static final PARSABLE[] PARABLES = {PARSABLE.STRING, PARSABLE.STRING, PARSABLE.BOOLEAN, PARSABLE.JSONARRAY, PARSABLE.LONG};

    public static String token;
    public static String prefix;
    public static boolean logEnabled;
    public static JSONArray questions;
    public static long reportChannel;

    static {
        try {
            confirmConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Looks for a config.properties config file in the application root directory.
     * Will create one if none was found
     */
    public static void confirmConfig() throws IOException {
        File config = new File(execDir + configFile);
        System.out.println("\n" + execDir + configFile + " exists: " + config.exists());
        if (!config.exists()) {
            FileWriter fileWriter = new FileWriter(execDir + configFile);
            JSONObject jsonObject = new JSONObject();

            for (int x = 0; x < names.length; x++)
                switch (PARABLES[x]) {
                    case INTEGER:
                        jsonObject.put(names[x], 0);
                        break;
                    case BOOLEAN:
                        jsonObject.put(names[x], false);
                        break;
                    case LONG:
                        jsonObject.put(names[x], Long.parseLong("10000000000000"));
                        break;
                    case STRING:
                        jsonObject.put(names[x], "");
                        break;
                    case JSONARRAY:
                        jsonObject.put(names[x], new JSONObject());
                        break;
                    case JSONOBJECT:
                        jsonObject.put(names[x], new JSONArray());
                        break;
                }

            System.out.println("\nCreating new config file");
            System.out.println(jsonObject.toJSONString());

            if (config.canWrite())
                fileWriter.write(jsonObject.toJSONString());
            else {
                System.out.println("Cannot write");
                System.exit(1);
            }
            fileWriter.flush();
            System.out.println("Please fill out file fully");
            System.exit(0);

        } else {
            System.out.println("Config exists, Confirming legitimacy. . .");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject = (JSONObject) new JSONParser().parse(new FileReader(execDir + configFile));
            } catch (ParseException e) {
                System.out.println("Invalid config, Deleting");
                new File(execDir + configFile).delete();
                System.exit(1);
            }
            for (String key : names)
                if (!jsonObject.containsKey(key)) {
                    System.out.println(key + " is missing from config");
                }
            for (int x = 0; x <= 5; x++)
                switch (PARABLES[x]) {
                    case STRING:
                        switch (names[x]) {
                            case "token":
                                token = jsonObject.get(names[x]).toString();
                                break;
                            case "prefix":
                                prefix = jsonObject.get(names[x]).toString();
                                break;
                        }
                        break;
                    case BOOLEAN:
                        if (names[x].equalsIgnoreCase("logEnable"))
                            logEnabled = Boolean.parseBoolean(jsonObject.get(names[x]).toString());
                        break;
                    case LONG:
                        try {
                            reportChannel = Long.parseLong(jsonObject.get(names[x]).toString());
                        } catch (NumberFormatException e) {
                            System.out.println(names[x] + " is not proper" +
                                    "\n Error Message:\n" + e.getMessage());
                            System.exit(1);
                        }
                        break;
                    case JSONARRAY:
                        try {
                            questions = (JSONArray) new JSONParser().parse(jsonObject.get(names[x]).toString());
                        } catch (ParseException e) {
                            System.out.println(names[x] + " is not proper" +
                                    "\n Error Message:\n" + e.getMessage());
                            System.exit(1);
                        }
                        break;
                }
            System.out.println("Confirmed");
        }
    }


    /**
     * Returns the execution directory.
     * When the application is run from a .jar file this will remove .jar from its path
     *
     * @return the execution directory
     */
    public static String getExecDir() {
        String decodedPath = null;
        String runtimePath = Config.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath();
        System.out.println("\nGetting Execution direction...");
        System.out.println("Execution directory raw: " + runtimePath);

        try {
            decodedPath = URLDecoder.decode(runtimePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (decodedPath.toLowerCase().endsWith(".jar")) {
            System.out.println("Application was called from jar file");
            decodedPath = decodedPath.substring(0, decodedPath.lastIndexOf("/") + 1);
        }
        String output = "";

        if (System.getProperty("os.name").equals("Windows 10")) {
            output = decodedPath.substring(1);
        } else if (System.getProperty("os.name").equals("Linux")) {
            output = decodedPath;
        }
        System.out.println("Final Execution Directory: " + output);
        return output;
    }
}
