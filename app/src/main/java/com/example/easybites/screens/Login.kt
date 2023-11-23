package com.example.easybites.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.example.easybites.navigation.AppScreens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.example.easybites.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


@Composable
fun loginScreen(
    navController: NavController){
    contenido(navController)
}

@Composable
fun contenido(
    navController: NavController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    //True = Login; False = Crear
    val showLoginForm = rememberSaveable{
        mutableStateOf(true)

    }
    val token = "887288771317-l970hi4u120lflmp7nqtc5tt6dnla50c.apps.googleusercontent.com"
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()){
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try{
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken,null)
            viewModel.signInWithGoogleCredential(credential){
                navController.navigate(AppScreens.PrincipalScreen.ruta)

        }
        }
        catch (ex: Exception){
            Log.d("EasyBites", "GoogleSignIn ha fallado")
        }
    }


    Surface(
        modifier = Modifier
            .fillMaxSize()

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,



            ){

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "EasyBites",
                    fontSize = 70.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .background(color = Color(android.graphics.Color.parseColor("#F44336")))
                        .padding(8.dp)
                        .clip(RoundedCornerShape(4.dp))

                )
            }




            if (showLoginForm.value){
                Text(text = stringResource(id= R.string.IniciarSesion,"Iniciar Sesion"),
                    fontSize = 20.sp,)
                UseForm(
                    navController,
                    isCreateAccount = false
                ){
                        email, password ->
                    Log.d("EasyBites", "Logueando con $email y $password")
                    viewModel.signInWithEmailAndPassword(email, password){
                        navController.navigate(AppScreens.PrincipalScreen.ruta)                    }
                }

            }
            else{
                Text(text = stringResource(id= R.string.CreateAccount,"Crear una cuenta"),
                    fontSize = 20.sp,
                )
                UseForm(
                    navController,
                    isCreateAccount = true
                ){
                        email, password ->
                    Log.d("EasyBites", "Creando cuenta con $email y $password")
                    viewModel.createUserWithEmailAndPassword(email,password){
                        navController.navigate(AppScreens.PrincipalScreen.ruta)
                    }
                }

            }
            Spacer (
                modifier = Modifier
                    .height(15.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                val text1 =
                    if (showLoginForm.value)
                        stringResource(id= R.string.noCuenta,"¿No tienes cuenta aun?")
                    else stringResource(id= R.string.yaCuenta,"¿Ya tienes una cuenta?")
                val text2 =
                    if (showLoginForm.value)
                        stringResource(id= R.string.Registrate,"Registrate")
                    else stringResource(id= R.string.IniciarSesion,"Iniciar Sesion")
                Text(text = text1,

                    fontSize = 20.sp,)
                Text(text = text2,

                    fontSize = 20.sp,
                    modifier = Modifier
                        .clickable { showLoginForm.value = !showLoginForm.value }
                        .padding(start = 5.dp),
                    color = Color.Blue)


            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                             val opciones = GoogleSignInOptions.Builder(
                                 GoogleSignInOptions.DEFAULT_SIGN_IN
                             )
                                 .requestIdToken(token)
                                 .requestEmail()
                                 .build()
                        val googleSignInClient = GoogleSignIn.getClient(context,opciones)
                        launcher.launch(googleSignInClient.signInIntent)

                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center


            ){
                Image(painter = painterResource(id = R.drawable.google),
                    contentDescription = "Login con Google",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp))
                Text(text = "Inicio de sesión con Google",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal)

            }

        }

    }
}



@SuppressLint("RememberReturnType")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UseForm (
    navController: NavController,
    isCreateAccount: Boolean = false,
    onDone: (String,String)-> Unit = {email, pwd->})
{
    val email = rememberSaveable{
        mutableStateOf("")
    }
    val password = rememberSaveable{
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable{
        mutableStateOf(false)
    }
    val isEmailValid = remember(email.value){
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@(gmail|hotmail)\\.com$")
        email.value.matches(emailRegex)
    }
    val valido = remember(email.value,password.value){
        email.value.trim().isNotEmpty()&&
                password.value.trim().isNotEmpty()&&
                isEmailValid
    }
    val showEmailError = !isEmailValid && email.value.isNotEmpty()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        EmailInput(
            emailState = email
        )
        if (showEmailError){
            Text(
                text = "Por favor, ingrese una dirección de correo válida (ej. usuario@gmail.com)",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        SubmitButton(
            textId = if(isCreateAccount)"Crear cuenta" else "Iniciar sesión",
            inputValido = valido,

            ){
            onDone(email.value.trim(),password.value.trim())
        }
    }
}



@Composable
fun SubmitButton(
    textId: String,
    inputValido: Boolean,
    onClic: ()->Unit){
    Button(
        onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),


        shape = CircleShape,
        enabled = inputValido,


        ) {
        Text(
            text = textId,
            fontSize = 20.sp,

            modifier = Modifier
                .padding(5.dp)

        )



    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>)
{
    val visualTransformation = if (passwordVisible.value)
        VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value = it},
        label = {Text(
            text = labelId,

            fontSize = 20.sp,)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank()){
                PasswordVisibleIcon(passwordVisible)
            }
            else null
        }
    )

}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>)
{
    val image =

        if (passwordVisible.value)
            Icons.Default.VisibilityOff
        else
            Icons.Default.Visibility

    IconButton(onClick = { passwordVisible.value  =!passwordVisible.value}) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )

    }

}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId:String = "Email"
){
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(    valueState: MutableState<String>,
                   labelId: String,
                   isSingleLine: Boolean= true,
                   keyboardType: KeyboardType
){
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = {Text(
            text = labelId,

            fontSize = 20.sp,)},
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType

        )

    )
}


