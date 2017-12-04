package com.google.android.gms.samples.vision.barcodereader.data;

import android.arch.persistence.db.SupportSQLiteQuery;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ioutd on 12/3/2017.
 */

public class PartContentProvider extends ContentProvider {

    public static final int PARTS = 100;
    public static final int PART_WITH_ID = 101;

    public static final UriMatcher sUriMatcher = buildUriMatcher();

    public static final String AUTHORITY = "com.google.android.gms.samples.vision.barcodereader";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_PARTS = "parts";

    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().appendPath(PATH_PARTS).build();

    private PartDatabase db;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, PATH_PARTS, PARTS);
        uriMatcher.addURI(AUTHORITY, PATH_PARTS + "/#", PART_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        return true;
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final PartDatabase db = PartDatabase.getPartDatabase(getContext());

        int match = sUriMatcher.match(uri);
        Cursor returnCursor;

        switch (match) {
            case PARTS:
                returnCursor = db.query((SupportSQLiteQuery) query(
                        uri,
                        projection,
                        selection,
                        selectionArgs,
                        sortOrder));
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final PartDatabase db = PartDatabase.getPartDatabase(getContext());
        final FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();   // TODO: 12/3/2017 insert() - is it bad practice to access firebase from content provider?

        int match = sUriMatcher.match(uri);
        Uri returnUri = null;

        switch (match){
            case PARTS:
                Part part = new Part(contentValues);
                String serial = part.getSerial();

                DatabaseReference reference = firebaseDB.getReference("picklist/FAC_20171203_1635/" + serial);
                reference.setValue(part);

                db.partDao().insertAll(part);   // TODO: 12/3/2017 insert() - make sure this doesn't create any errors
//                returnUri = ContentUris.withAppendedId(CONTENT_URI, serial);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String whereClause, @Nullable String[] args) {
        final PartDatabase db = PartDatabase.getPartDatabase(getContext());
        final FirebaseDatabase firebaseDB = FirebaseDatabase.getInstance();

        // TODO: 12/3/2017 delete() - implement

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }
}
