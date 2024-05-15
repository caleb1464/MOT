package com.example.wazitoecommerce.ui.theme.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.ui.theme.WazitoECommerceTheme

@Composable
fun AboutScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    )
    {
        Text(
            text = "About.",
            fontSize = 50.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 150.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

            Text(text = "1.To see the available driver you can view from the our drivers page.",
                fontSize = 35.sp
                )
            Text(text = "2.For Drivers if you want to add your profile you can  press the add in home page.",
                fontSize = 35.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
    WazitoECommerceTheme {
        AboutScreen(navController = rememberNavController())
    }
}