package com.example.wazitoecommerce.ui.theme.screens.products

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.wazitoecommerce.data.ProductViewModel
import com.example.wazitoecommerce.models.Product
import com.example.wazitoecommerce.navigation.HOME_URL
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme

@Composable
fun ViewProductsScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var productRepository = ProductViewModel(navController, context)


        val emptyProductState = remember { mutableStateOf(Product("","","","","")) }
        var emptyProductsListState = remember { mutableStateListOf<Product>() }

        var products = productRepository.allProducts(emptyProductState, emptyProductsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(text = "All products",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

           LazyColumn{
                items(products){
                    ProductItem(
                        name = it.name,
                        quantity = it.quantity,
                        price = it.price,
                         id = it.id,
                        navController = navController,
                        productRepository = productRepository,
                        productImage = it.imageUrl
                    )
                }
            }
        }
    }
}


@Composable
fun ProductItem(name:String, quantity:String, price:String, id:String,
                navController:NavHostController,
                productRepository:ProductViewModel, productImage:String) {

    Column(modifier = Modifier.fillMaxWidth()) {
        val mContext = LocalContext.current
        Text(text = name)
        Text(text = quantity)
        Text(text = price)
        Image(
            painter = rememberAsyncImagePainter(productImage),
            contentDescription = null,
            modifier = Modifier.size(210.dp)
        )
        Button(onClick = { navController.navigate(HOME_URL) },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Select")
        }
        Button(onClick = { productRepository.deleteProduct(id) },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Delete")
        }
        Button(
            onClick = {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = "tel:0757605094".toUri()
                mContext.startActivity(callIntent)
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "CALL",
            )
        }
        Button(
            onClick = {
                val smsIntent = Intent(Intent.ACTION_SENDTO)
                smsIntent.data = "smsto:0720245837".toUri()
                smsIntent.putExtra("sms_body", "Hello there ?,I need a ride.")
                mContext.startActivity(smsIntent)
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Text(
                text = "SMS",
            )
        }
        Button(
            onClick = {
                val simToolKitLaunchIntent =
                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                simToolKitLaunchIntent?.let { mContext.startActivity(it) }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Text(
                text = "MPESA"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ViewProductsScreenPreview(){
    WazitoECommerceTheme {
        ViewProductsScreen(navController = rememberNavController())
    }
}