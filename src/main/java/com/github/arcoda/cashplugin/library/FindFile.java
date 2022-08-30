package com.github.arcoda.cashplugin.library;

import java.io.File;

public class FindFile
{
    public static File FindFile(String name, File file, Boolean recursive)
    {
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    if (recursive) {
                        FindFile(name,fil, true);
                    }
                    else {
                        continue;
                    }
                }
                else if (containsIgnoreCase(fil.getName(), name))
                {
                    return fil;
                }
            }
        return null;
    }
    private static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
}