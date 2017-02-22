/*
 * Apache License
 * Version 2.0, January 2004
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * TERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION
 *
 * Copyright (c) 2017 Flipkart Internet Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.flipkart.android.proteus.toolbox;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Aditya Sharat
 */
public class Utils {

    public static final String LIB_NAME = "proteus";
    public static final String VERSION = "5.0.0-SNAPSHOT";

    public static Result readJson(String path, JsonObject data, int index) {
        // replace INDEX reference with index value
        if (ProteusConstants.INDEX.equals(path)) {
            path = path.replace(ProteusConstants.INDEX, String.valueOf(index));
            return Result.success(new JsonPrimitive(path));
        } else {
            StringTokenizer tokenizer = new StringTokenizer(path, ProteusConstants.DATA_PATH_DELIMITERS);
            JsonElement elementToReturn = data;
            JsonElement tempElement;
            JsonArray tempArray;

            while (tokenizer.hasMoreTokens()) {
                String segment = tokenizer.nextToken();
                if (elementToReturn == null) {
                    return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                }
                if (elementToReturn.isJsonNull()) {
                    return Result.JSON_NULL_EXCEPTION;
                }
                if ("".equals(segment)) {
                    continue;
                }
                if (elementToReturn.isJsonArray()) {
                    tempArray = elementToReturn.getAsJsonArray();

                    if (ProteusConstants.INDEX.equals(segment)) {
                        if (index < tempArray.size()) {
                            elementToReturn = tempArray.get(index);
                        } else {
                            return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                        }
                    } else if (ProteusConstants.ARRAY_DATA_LENGTH_REFERENCE.equals(segment)) {
                        elementToReturn = new JsonPrimitive(tempArray.size());
                    } else if (ProteusConstants.ARRAY_DATA_LAST_INDEX_REFERENCE.equals(segment)) {
                        if (tempArray.size() == 0) {
                            return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                        }
                        elementToReturn = tempArray.get(tempArray.size() - 1);
                    } else {
                        try {
                            index = Integer.parseInt(segment);
                        } catch (NumberFormatException e) {
                            return Result.INVALID_DATA_PATH_EXCEPTION;
                        }
                        if (index < tempArray.size()) {
                            elementToReturn = tempArray.get(index);
                        } else {
                            return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                        }
                    }
                } else if (elementToReturn.isJsonObject()) {
                    tempElement = elementToReturn.getAsJsonObject().get(segment);
                    if (tempElement != null) {
                        elementToReturn = tempElement;
                    } else {
                        return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                    }
                } else if (elementToReturn.isJsonPrimitive()) {
                    return Result.INVALID_DATA_PATH_EXCEPTION;
                } else {
                    return Result.NO_SUCH_DATA_PATH_EXCEPTION;
                }
            }
            if (elementToReturn.isJsonNull()) {
                return Result.JSON_NULL_EXCEPTION;
            }
            return Result.success(elementToReturn);
        }
    }

    public static JsonElement merge(JsonElement oldJson, JsonElement newJson, boolean useCopy, Gson gson) {

        JsonElement newDataElement;
        JsonArray oldArray;
        JsonArray newArray;
        JsonElement oldArrayItem;
        JsonElement newArrayItem;
        JsonObject oldObject;

        if (oldJson == null || oldJson.isJsonNull()) {
            return useCopy ? gson.fromJson(newJson, JsonElement.class) : newJson;
        }

        if (newJson == null || newJson.isJsonNull()) {
            newJson = JsonNull.INSTANCE;
            return newJson;
        }

        if (newJson.isJsonPrimitive()) {
            JsonPrimitive value;
            if (!useCopy) {
                return newJson;
            }
            if (newJson.getAsJsonPrimitive().isBoolean()) {
                value = new JsonPrimitive(newJson.getAsBoolean());
            } else if (newJson.getAsJsonPrimitive().isNumber()) {
                value = new JsonPrimitive(newJson.getAsNumber());
            } else if (newJson.getAsJsonPrimitive().isString()) {
                value = new JsonPrimitive(newJson.getAsString());
            } else {
                value = newJson.getAsJsonPrimitive();
            }
            return value;
        }

        if (newJson.isJsonArray()) {
            if (!oldJson.isJsonArray()) {
                return useCopy ? gson.fromJson(newJson, JsonArray.class) : newJson;
            } else {
                oldArray = oldJson.getAsJsonArray();
                newArray = newJson.getAsJsonArray();

                if (oldArray.size() > newArray.size()) {
                    while (oldArray.size() > newArray.size()) {
                        oldArray.remove(oldArray.size() - 1);
                    }
                }

                for (int index = 0; index < newArray.size(); index++) {
                    if (index < oldArray.size()) {
                        oldArrayItem = oldArray.get(index);
                        newArrayItem = newArray.get(index);
                        oldArray.set(index, merge(oldArrayItem, newArrayItem, useCopy, gson));
                    } else {
                        oldArray.add(newArray.get(index));
                    }
                }
            }
        } else if (newJson.isJsonObject()) {
            if (!oldJson.isJsonObject()) {
                return useCopy ? gson.fromJson(newJson, JsonObject.class) : newJson;
            } else {
                oldObject = oldJson.getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : newJson.getAsJsonObject().entrySet()) {
                    newDataElement = merge(oldObject.get(entry.getKey()), entry.getValue(), useCopy, gson);
                    oldObject.add(entry.getKey(), newDataElement);
                }
            }
        } else {
            return useCopy ? gson.fromJson(newJson, JsonElement.class) : newJson;
        }

        return oldJson;
    }

    public static JsonObject addElements(JsonObject destination, JsonObject source, boolean override) {
        for (Map.Entry<String, JsonElement> entry : source.entrySet()) {
            if (!override && destination.get(entry.getKey()) != null) {
                continue;
            }
            destination.add(entry.getKey(), entry.getValue());
        }
        return destination;
    }

    public static String getStringFromArray(JsonArray array, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).isJsonPrimitive()) {
                sb.append(array.get(i).getAsString());
            } else {
                sb.append(array.get(i).toString());
            }
            if (i < array.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String getVersion() {
        return VERSION;
    }
}
