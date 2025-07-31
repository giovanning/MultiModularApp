package br.com.siag.navigator.viewmodel

import androidx.lifecycle.ViewModel
import br.com.siag.navigator.core.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppNavigatorViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
) : ViewModel(), AppNavigator by appNavigator
