package com.abspath.openweibo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.Set;

/**
 * Title: 对 SharedPreference 的二次封装
 * <p>Description:
 * <p>Author: Huajian Jiang
 * <br>Date: 2017/3/22
 * <br>Email: developer.huajianjiang@gmail.com
 */
public class Prefs {
    public static final String TAG = Prefs.class.getSimpleName();
    private static final int DEFAULT_INT_VALUE = -1;
    private static final long DEFAULT_LONG_VALUE = -1L;
    private static final float DEFAULT_FLOAT_VALUE = 0.0f;
    private static final boolean DEFAULT_BOLLEAN_VALUE = false;
    private static final String DEFAULT_STRING_VALUE = "";
    private static final Set<String> DEFAULT_STRINGSET_VALUE = null;
    private static Prefs INSTANCE;
    private static SharedPreferences prefs;
    private SharedPreferences.Editor mEditor;

    private Prefs() {}

    private Prefs(@NonNull Context ctxt) {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctxt.getApplicationContext());
    }

    private Prefs(@NonNull Context ctxt, @NonNull String name) {
        prefs = ctxt.getApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private Prefs(@NonNull Context ctxt, @NonNull String name, int mode) {
        prefs = ctxt.getApplicationContext().getSharedPreferences(name, mode);
    }

    public static Prefs get(@NonNull Context ctxt) {
        if (INSTANCE == null) {
            INSTANCE = new Prefs(ctxt);
        }
        return INSTANCE;
    }

    public static Prefs get(@NonNull Context ctxt, boolean newInstance) {
        if (INSTANCE == null || newInstance) {
            INSTANCE = new Prefs(ctxt);
        }
        return INSTANCE;
    }

    public static Prefs get(@NonNull Context ctxt, @NonNull String name) {
        if (INSTANCE == null) {
            INSTANCE = new Prefs(ctxt, name);
        }
        return INSTANCE;
    }

    public static Prefs get(@NonNull Context ctxt, @NonNull String name, boolean newInstance) {
        if (INSTANCE == null || newInstance) {
            INSTANCE = new Prefs(ctxt, name);
        }
        return INSTANCE;
    }

    public static Prefs get(@NonNull Context ctxt, @NonNull String name, int mode) {
        if (INSTANCE == null) {
            INSTANCE = new Prefs(ctxt, name, mode);
        }
        return INSTANCE;
    }

    public static Prefs get(@NonNull Context ctxt, @NonNull String name, int mode,
            boolean newInstance)
    {
        if (INSTANCE == null || newInstance) {
            INSTANCE = new Prefs(ctxt, name, mode);
        }
        return INSTANCE;
    }


    public int pullInt(@NonNull String where) {
        return prefs.getInt(where, DEFAULT_INT_VALUE);
    }

    public long pullLong(@NonNull String where) {
        return prefs.getLong(where, DEFAULT_LONG_VALUE);
    }

    public float pullFloat(@NonNull String where) {
        return prefs.getFloat(where, DEFAULT_FLOAT_VALUE);
    }

    public boolean pullBoolean(@NonNull String where) {
        return prefs.getBoolean(where, DEFAULT_BOLLEAN_VALUE);
    }

    public String pullString(@NonNull String where) {
        return prefs.getString(where, DEFAULT_STRING_VALUE);
    }

    public Set<String> pullStringSet(@NonNull String where) {
        return prefs.getStringSet(where, DEFAULT_STRINGSET_VALUE);
    }



    @SuppressLint("CommitPrefEdits")
    public Prefs pushInt(@NonNull String where, int what) {
        getEditor().putInt(where, what);
        return INSTANCE;
    }

    @SuppressLint("CommitPrefEdits")
    public Prefs pushLong(@NonNull String where, long what) {
        getEditor().putLong(where, what);
        return INSTANCE;
    }

    @SuppressLint("CommitPrefEdits")
    public Prefs pushFloat(@NonNull String where, float what) {
        getEditor().putFloat(where, what);
        return INSTANCE;
    }

    @SuppressLint("CommitPrefEdits")
    public Prefs pushBoolean(@NonNull String where, boolean what) {
        getEditor().putBoolean(where, what);
        return INSTANCE;
    }

    @SuppressLint("CommitPrefEdits")
    public Prefs pushString(@NonNull String where, @NonNull String what) {
        getEditor().putString(where, what);
        return INSTANCE;
    }

    @SuppressLint("CommitPrefEdits")
    public Prefs pushStringSet(@NonNull String where, @NonNull Set<String> what) {
        getEditor().putStringSet(where, what);
        return INSTANCE;
    }

    public Prefs remove(@NonNull String where) {
        getEditor().remove(where);
        return INSTANCE;
    }

    public void done() {
        if (mEditor != null) mEditor.apply();
        mEditor = null;
    }

    public void clear() {
        prefs.edit().clear().apply();
    }


    private SharedPreferences.Editor getEditor() {
        if (mEditor == null) mEditor = prefs.edit();
        return mEditor;
    }

}
