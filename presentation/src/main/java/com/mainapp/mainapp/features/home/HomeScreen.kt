package com.mainapp.mainapp.features.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.mainapp.mainapp.components.inputform.*
import com.mainapp.mainapp.components.ralewayBold
import com.mainapp.mainapp.components.ralewayRegular
import com.mainapp.mainapp.components.ralewayThin


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Column{
        InputItem(
            item = InputItemData(
                label = CustomText("Teléfono", textColor = Color.Blue, font = ralewayBold(), fontSize = 16.sp),
                maxLines = 2,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                placeHolder = CustomText("Introduce tu teléfono", textColor = Color.Blue, font = ralewayThin(), fontSize = 12.sp),
                errorMessage = CustomText("Error teléfono"),
                styleType = StyleType.OUTLINED,
                textFieldColors = object :TextFieldColors(){
                   @Composable override fun getBackgroundColor() = Color.Cyan
                   @Composable override fun getTextColor() = Color.Yellow
                }
            )
        )
    }
}