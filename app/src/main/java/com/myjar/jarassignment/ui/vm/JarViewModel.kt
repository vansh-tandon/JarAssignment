package com.myjar.jarassignment.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myjar.jarassignment.data.remote.model.ComputerItem
import com.myjar.jarassignment.data.repository.JarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JarViewModel @Inject constructor(
    private val repository: JarRepository
) : ViewModel() {

    private val _listStringData = MutableStateFlow<List<ComputerItem>>(emptyList())

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText


    val filteredList: StateFlow<List<ComputerItem>> = searchText
        .debounce(300L)
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