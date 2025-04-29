package com.example.khatabook.presentation.login

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khatabook.R
import com.example.khatabook.data.local.entity.User
import com.example.khatabook.presentation.viewmodal.UserViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginScreen(
    viewModel: UserViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit // Callback after successful login
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Launch effect when user is logged in
    LaunchedEffect(viewModel.user.value) {
        if (viewModel.user.value != null) {
            onLoginSuccess() // Trigger success callback
        }
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account: GoogleSignInAccount = task.getResult(Exception::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(context as Activity) { firebaseTask ->
                    if (firebaseTask.isSuccessful) {
                        val firebaseUser = FirebaseAuth.getInstance().currentUser
                        firebaseUser?.let {
                            viewModel.saveUser(
                                User(
                                    uid = it.uid,
                                    name = it.displayName ?: "",
                                    email = it.email ?: "",
                                    photoUrl = it.photoUrl?.toString() ?: ""
                                )
                            )
                        }
                    } else {
                        Log.e("FirebaseAuth", "Google sign-in failed", firebaseTask.exception)
                    }
                }
        } catch (e: Exception) {
            Log.e("GoogleSignIn", "Sign in failed", e)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login To Your Account",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 30.dp),
            fontWeight = FontWeight.Bold
        )

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )

        Text(
            text = "Continue With",
            modifier = Modifier.padding(top = 15.dp)
        )

        // Google Sign-In Button
        Button(
            onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.default_web_client_id)) // From google-services.json
                    .requestEmail()
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp),
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Logo",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(3f),
                contentScale = ContentScale.Fit
            )
        }
    }
}
