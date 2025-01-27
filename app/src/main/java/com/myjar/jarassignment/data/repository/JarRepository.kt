package com.myjar.jarassignment.data.repository

import android.content.Context
import com.myjar.jarassignment.data.remote.api.ApiService
import com.myjar.jarassignment.data.local.dao.ComputerItemDao
import com.myjar.jarassignment.data.remote.model.ComputerItem
import com.myjar.jarassignment.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface JarRepository {
    suspend fun fetchResults(): Flow<List<ComputerItem>>
}

class JarRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val dao: ComputerItemDao,
    private val context: Context
) : JarRepository {
    override suspend fun fetchResults(): Flow<List<ComputerItem>> = flow {
        if (NetworkUtils.hasInternetConnection(context)) {
            val response = apiService.fetchResults()
            dao.insertAll(response)
        }
        emitAll(dao.getAll())
    }
}