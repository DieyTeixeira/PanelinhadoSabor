package com.codek.panelinhadosabor.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.codek.panelinhadosabor.ui.state.CartState
import com.codek.panelinhadosabor.ui.theme.Orange
import com.codek.panelinhadosabor.ui.theme.Red
import com.codek.panelinhadosabor.ui.theme.Yellow

@Composable
fun HomeScreen(
    cartState: CartState,
    onNavigateToCart: (Int) -> Unit = {}
) {
    val colorPri = Yellow
    val colorSec = Orange
    val colorTer = Red

    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(0) }
    val cartItemCount = cartState.items.sumOf { it.quantity }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    val filteredProducts = products.filter {
        (selectedCategory == 0 || it.categoryId == selectedCategory) &&
                it.name.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.5f))
    ) {
        // Header
        HomeHeader(
            searchText = searchText,
            onSearchTextChanged = { searchText = it },
            cartItemCount = cartItemCount,
            onClicked = { onNavigateToCart(1) }
        )

        // Seção do logo
        HomeLogo(
            colorSec = colorSec,
        )

        // Seção de Categorias
        HomeCategory(
            colorPri = colorPri,
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )

        // Carrossel de produtos
        HomeProducts(
            colorSec = colorSec,
            filteredProducts = filteredProducts,
            onProductSelected = { selectedProduct = it }
        )

        // Modal para iserir no carrinho
        selectedProduct?.let { product ->
            ProductDialog(
                product = product,
                colorSec = colorSec,
                onDismiss = { selectedProduct = null },
                onAddToCart = { quantity ->
                    cartState.addItem(product.id, product.name, product.price, product.imageRes, quantity)
                    selectedProduct = null
                }
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        cartState = CartState()
    )
}