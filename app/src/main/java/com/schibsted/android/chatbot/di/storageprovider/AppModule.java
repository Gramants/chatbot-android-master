package com.schibsted.android.chatbot.di.storageprovider;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.schibsted.android.chatbot.persistence.PersistentStorageProxy;
import com.schibsted.android.chatbot.persistence.PersistentStorageProxyImpl;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    SharedPreferences providePreference() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    PersistentStorageProxy providePersistentStorageProxy(SharedPreferences preferences) {
        return new PersistentStorageProxyImpl(preferences);
    }
}