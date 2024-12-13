package com.codek.panelinhadosabor.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codek.panelinhadosabor.R
import com.codek.panelinhadosabor.ui.state.CartState
import com.codek.panelinhadosabor.ui.theme.Orange
import com.codek.panelinhadosabor.ui.theme.Red
import com.codek.panelinhadosabor.ui.theme.Yellow

@Composable
fun SignInScreenTeste(
    cartState: CartState,
    onNavigateToHome: () -> Unit = {}
) {
    val colorPri = Yellow

    var email by remember { mutableStateOf("") }
    var onEmailChange: (String) -> Unit = { email = it }
    var password by remember { mutableStateOf("") }
    var onPasswordChange: (String) -> Unit = { password = it }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colorPri)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_panelinha),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(100))
                    .clickable( onClick = onNavigateToHome )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color.White,
                    RoundedCornerShape(45.dp, 45.dp, 0.dp, 0.dp)
                )
                .padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(30)
                    ),
                placeholder = {
                    Text(
                        text = "Email",
                        color = Color.LightGray,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 60.sp
                        )
                    )
                },
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Ícone de email",
                        colorFilter = ColorFilter.tint(Color.DarkGray)
                    )
                },
                shape = RoundedCornerShape(30),
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 60.sp
                )
            )
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(30)
                    ),
                placeholder = {
                    Text(
                        text = "Senha",
                        color = Color.LightGray,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 60.sp
                        )
                    )
                },
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Ícone de senha",
                        colorFilter = ColorFilter.tint(Color.DarkGray)
                    )
                },
                trailingIcon = {
                    Image(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = "Ícone de senha",
                        colorFilter = ColorFilter.tint(Color.DarkGray)
                    )
                },
                shape = RoundedCornerShape(30),
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 60.sp
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        colorPri,
                        RoundedCornerShape(30)
                    )
            ) {
                Text(
                    text = "Entrar",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Text(
                text = "Esqueceu a senha?",
                color = Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.End)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.LightGray)
                ) {}
                Text(
                    text = "Ou",
                    color = Color.DarkGray
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color.LightGray)
                ) {}
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        1.dp,
                        colorPri,
                        RoundedCornerShape(30)
                    )
            ) {
                Text(
                    text = "Criar conta",
                    color = colorPri,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    SignInScreenTeste(
        cartState = CartState()
    )
}