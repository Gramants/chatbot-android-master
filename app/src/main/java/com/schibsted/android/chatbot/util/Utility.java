package com.schibsted.android.chatbot.util;

public class Utility {

    public static boolean isNameValid(String name) {

        for (int i = 0; i < Constant.mInvalidNames.length; i++) {
            if (name.toLowerCase().contains(Constant.mInvalidNames[i].toLowerCase()))
                return false;
        }
        return true;

    }
}
