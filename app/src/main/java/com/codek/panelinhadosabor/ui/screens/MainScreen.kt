package com.codek.panelinhadosabor.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.codek.panelinhadosabor.ui.screens.cart.CartScreen
import com.codek.panelinhadosabor.ui.screens.home.HomeScreen
import com.codek.panelinhadosabor.ui.screens.payment.PaymentScreen
import com.codek.panelinhadosabor.ui.state.CartState

@Composable
fun AppScreen() {
    val cartState = remember { CartState() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        var currentIndex by remember { mutableStateOf(0) }
        val navigateTo: (Int) -> Unit = { index -> currentIndex = index }

        Box(modifier = Modifier.fillMaxSize()) {
            BodyContent(
                currentIndex = currentIndex,
                cartState = cartState,
                navigateTo = navigateTo
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BodyContent(
    currentIndex: Int,
    cartState: CartState,
    navigateTo: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AnimatedContent(
            targetState = currentIndex,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) + fadeIn() with
                            slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) + fadeOut()
                } else {
                    slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth }) + fadeIn() with
                            slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }) + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { targetIndex ->
            screensIndex[targetIndex](cartState, navigateTo)
        }
    }
}

val screensIndex: List<@Composable (CartState, (Int) -> Unit) -> Unit> = listOf(
    { cartState, navigateTo ->
        HomeScreen(
            cartState = cartState,
            onNavigateToCart = { navigateTo(1) } // Navegar para o carrinho
        )
    },
    { cartState, navigateTo ->
        CartScreen(
            cartState = cartState,
            onNavigateToHome = { navigateTo(0) }, // Voltar para a tela inicial
            onNavigateToPayment = { navigateTo(2) } // Navegar para a tela de pagamento
        )
    },
    { cartState, navigateTo ->
        PaymentScreen(
            cartState = cartState,
            onConfirmPayment = {
                cartState.clearCart()
                navigateTo(0) // Voltar para a tela inicial após pagamento
            },
            onCancel = { navigateTo(1) } // Voltar para o carrinho
        )
    }
)

@Preview
@Composable
private fun PreviewApp() {
    AppScreen()
}