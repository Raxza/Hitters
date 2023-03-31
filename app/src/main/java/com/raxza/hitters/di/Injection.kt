package com.raxza.hitters.di

import android.content.Context
import com.raxza.hitters.data.HittersDatabase
import com.raxza.hitters.data.HittersRepository

object Injection {
    fun provideRepository(context: Context): HittersRepository {
        val database = HittersDatabase.getInstance(context)
        return HittersRepository.getInstance(database)
    }
}