package com.balala.bootstrap.core;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/04
 *     desc   : 初始化APP
 *     version: 1.0
 * </pre>
 */
public class BootStrapProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        if (getContext() != null) {
            Application application = (Application) getContext().getApplicationContext();
            BootStrapUtils.init(application);
            BootStrapApplicationCore.invokeApplicaitonOnCreate(application);
        }
        return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}