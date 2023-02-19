package com.example.unipivetapp.util.auth

import com.example.domain.models.Result
import com.google.firebase.auth.FirebaseUser

/**
 * [AuthManager] is responsible for the authorization features inside the app.
 */

interface Authenticator {

    /**
     * Register feature
     */

    suspend fun register(email: String, pass: String): Result<FirebaseUser>

    /**
     * Login Feature
     */

    suspend fun login(email: String, pass: String): Result<FirebaseUser>

    /**
     * Returns whether or not there is a user active at the moment.
     */

    fun isUserActive(): Boolean

    /**
     * Returns the current user that is logged in.
     */

    fun getActiveUser(): FirebaseUser?

    /**
     * Logout feature
     */

    fun logout()
}