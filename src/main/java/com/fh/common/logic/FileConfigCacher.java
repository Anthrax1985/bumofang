package com.fh.common.logic;

import com.fh.util.Tools;
import java.util.HashMap;
import java.util.Map;

public class FileConfigCacher
{
  private static Map<String, String> configMap = new HashMap<String,String>();

  public static String read(String file) {
    if (!configMap.containsKey(file)) {
      String content = Tools.readTxtFile(file);
      configMap.put(file, content);
    }
    return (String)configMap.get(file);
  }

  public static void write(String file, String content) {
    boolean rs = Tools.writeFile(file, content);
    if (rs)
      configMap.put(file, content);
  }
}