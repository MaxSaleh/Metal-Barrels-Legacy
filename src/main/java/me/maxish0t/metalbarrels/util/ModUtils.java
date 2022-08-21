package me.maxish0t.metalbarrels.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.net.URL;

public class ModUtils {

    /**
     * Converts a UUID to a player minecraft name.
     * @param uuid Input the user UUID
     * @return Returns the players name
     */
    public static String convertUUID(String uuid){
        String url = "https://api.mojang.com/user/profiles/" + uuid.replace("-", "")+"/names";
        try {
            String json = IOUtils.toString(new URL(url));
            JsonElement element = JsonParser.parseString(json);
            JsonArray nameArray = element.getAsJsonArray();
            JsonObject nameElement = nameArray.get(nameArray.size()-1).getAsJsonObject();
            nameElement.get("name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
