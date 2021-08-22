package com.example.demoapp.di

import android.content.Context
import androidx.room.Room
import com.example.demoapp.BuildConfig
//import com.example.demoapp.api.ApiHelper
//import com.example.demoapp.api.ApiHelperImpl
import com.example.demoapp.api.ApiService
import com.example.demoapp.db.AppDatabase
import com.example.demoapp.db.UserDao
import com.example.demoapp.other.Constants
import com.example.demoapp.repository.MainRepository
import com.example.demoapp.ui.EmployeeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule{

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor =HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String , converterFactory: Converter.Factory): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

//    @Provides
//    @Singleton
//    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideAdapter(): EmployeeAdapter = EmployeeAdapter()

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context) : Context {
        return appContext
    }

    @Singleton
    @Provides
    fun providesMainRepository( apiService: ApiService, appContext: Context , appDatabase: AppDatabase) : MainRepository {
        return MainRepository(apiService,appContext,appDatabase)
    }

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, Constants.DBName).build()
    }
}