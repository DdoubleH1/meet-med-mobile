package com.example.doctorapp.presentation.navigation

import android.os.Bundle
import com.example.chatapp.domain.core.navigation.BaseNavigator

interface AppNavigation: BaseNavigator {
    fun openSplashToSignInScreen(bundle: Bundle? = null)
    fun openSignInToSignUpScreen(bundle: Bundle? = null)
    fun openSignUpToSignInScreen(bundle: Bundle? = null)
}