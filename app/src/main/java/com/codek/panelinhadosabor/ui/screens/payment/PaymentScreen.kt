package com.codek.panelinhadosabor.ui.screens.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codek.panelinhadosabor.ui.state.CartState
import com.codek.panelinhadosabor.ui.state.formatPrice
import com.codek.panelinhadosabor.ui.theme.Orange
import com.codek.panelinhadosabor.ui.theme.Red
import com.codek.panelinhadosabor.ui.theme.Yellow
import java.math.BigDecimal

@Composable
fun PaymentScreen(
    cartState: CartState,
    onConfirmPayment: () -> Unit = {},
    onCancel: (Int) -> Unit = {}
) {
    val colorPri = Yellow
    val colorSec = Orange
    val colorTer = Red

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var adress by remember { mutableStateOf("") }
    var numberHome by remember { mutableStateOf("") }
    var complement by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }

    val totalAmount = cartState.items.sumOf { it.price * it.quantity }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.5f)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        PaymentHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // Dados do Cliente
                PaymentClient(
                    name = name,
                    onNameChange = { newName -> name = newName },
                    phone = phone,
                    onPhoneChange = { newPhone -> phone = newPhone },
                    adress = adress,
                    onAdressChange = { newAdress -> adress = newAdress },
                    numberHome = numberHome,
                    onNumberHomeChange = { newNumberHome -> numberHome = newNumberHome },
                    complement = complement,
                    onComplementChange = { newComplement -> complement = newComplement },
                    district = district,
                    onDistrictChange = { newDistrict -> district = newDistrict },
                    city = city,
                    onCityChange = { newCity -> city = newCity }
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Escolha o método de pagamento:",
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.width(50.dp)) { PaymentOption("Cartão de Crédito", colorPri) }
                    Box(modifier = Modifier.width(50.dp)) { PaymentOption("PIX", colorPri) }
                    Box(modifier = Modifier.width(50.dp)) { PaymentOption("Dinheiro", colorPri) }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.LightGray,
                        RoundedCornerShape(16.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total do Carrinho:",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = formatPrice(totalAmount),
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))

            // Botões de Confirmar e Cancelar
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(175.dp)
                        .height(40.dp)
                        .background(
                            colorSec,
                            RoundedCornerShape(16.dp)
                        )
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { onCancel(0) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                if (cartState.items.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .width(175.dp)
                            .height(40.dp)
                            .background(
                                colorSec,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Confirmar",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PaymentOption(optionName: String, color: Color) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .background(color, RoundedCornerShape(16.dp))
            .clickable { /* Ação ao selecionar método */ },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = optionName,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}