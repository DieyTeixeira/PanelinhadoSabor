package com.codek.panelinhadosabor.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codek.panelinhadosabor.ui.complemento.SendMessageToWhatsApp
import com.codek.panelinhadosabor.ui.complemento.generateCartMessage
import com.codek.panelinhadosabor.ui.complemento.requestForegroundPermission
import com.codek.panelinhadosabor.ui.state.CartState
import com.codek.panelinhadosabor.ui.state.formatPrice
import com.codek.panelinhadosabor.ui.theme.Orange
import com.codek.panelinhadosabor.ui.theme.Red
import com.codek.panelinhadosabor.ui.theme.Yellow

@Composable
fun CartScreen(
    cartState: CartState,
    onNavigateToHome: () -> Unit = {},
    onNavigateToPayment: () -> Unit = {}
) {
    val colorPri = Yellow
    val colorSec = Orange
    val colorTer = Red

    val context = LocalContext.current
    requestForegroundPermission(context)
    val totalValueCart = cartState.items.sumOf { it.price * it.quantity }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.5f)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Header
        CartHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (cartState.items.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cartState.items) { item ->
                        CartItemRow(
                            item = item,
                            onDelete = { itemToDelete ->
                                cartState.removeItem(itemToDelete.id)
                            }
                        )
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
                            text = formatPrice(totalValueCart),
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = "Seu carrinho está vazio.",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))
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
                        ) { onNavigateToHome() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Continuar Comprando",
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
                            ) { onNavigateToPayment() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Finalizar Compra",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}