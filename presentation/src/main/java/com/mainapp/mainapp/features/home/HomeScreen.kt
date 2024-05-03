package com.mainapp.mainapp.features.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.mainapp.mainapp.R
import com.mainapp.mainapp.components.inputform.*
import com.mainapp.mainapp.components.ralewayBold
import com.mainapp.mainapp.components.ralewayThin


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    var phone = ""
    Column(Modifier.fillMaxSize()) {
        InputItem(
            item = InputItemData(
                mainText = CustomText(phone),
                outsideLabel = true,
                label = CustomText(
                    "Teléfono",
                    textColor = Color.Blue,
                    font = ralewayBold(),
                    fontSize = 16.sp
                ),
                maxLines = 2,
                trailingIcon = CustomIcon(
                    id = R.drawable.ic_arrow_right,
                    contentDesc = "clear",
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                placeHolder = CustomText(
                    "Introduce tu teléfono",
                    textColor = Color.Blue,
                    font = ralewayThin(),
                    fontSize = 12.sp
                ),
                errorMessage = CustomText("Error teléfono"),
                styleType = StyleType.OUTLINED,
                textFieldColors = object : TextFieldColors() {
                    @Composable
                    override fun getBackgroundColor() = Color.Cyan
                    @Composable
                    override fun getTextColor() = Color.Yellow
                }
            )
        )
        InputItem(
            item = InputItemData(
                outsideLabel = true,
                label = CustomText(
                    "Teléfono",
                    textColor = Color.Blue,
                    font = ralewayBold(),
                    fontSize = 16.sp
                ),
            )
        )
    }
}