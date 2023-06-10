package com.mainapp.mainapp.components.inputform

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import java.util.regex.Pattern

@Composable
fun InputGroup(
    inputs: List<InputItemData>
) {

}
@Composable
fun TwoInputsInRow(
    leftInput : InputItemData,
    rightInput : InputItemData
) {
    Row(modifier = Modifier.wrapContentHeight().fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween){
        InputItem(item = leftInput, modifier = leftInput.modifier.weight(1f))
        InputItem(item = rightInput, modifier = rightInput.modifier.weight(1f))
    }
}

