package com.myjar.jarassignment.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myjar.jarassignment.JarApplication
import com.myjar.jarassignment.createRetrofit
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.data.repository.JarRepository
import com.myjar.jarassignment.data.repository.JarRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class JarViewModel : ViewModel() {

    private val _listStringData = MutableStateFlow<List<ComputerItem>>(emptyList())
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText
    val filteredList:StateFlow<List<ComputerItem>> = searchText
        .debounce(3000L)
        .combine(_listStringData) { text, list ->
            if (text.isBlank()) {
                list
            }
            list.filter { list ->
                list.name.contains(text, ignoreCase = true)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _listStringData.value
        )

    val dao = JarApplication.database.userDao()



    private val repository: JarRepository = JarRepositoryImpl(createRetrofit())

    fun fetchData() {
        viewModelScope.launch {
            repository.fetchResults().collect{
                _listStringData.value = it
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}