package com.mainapp.mainapp.components.inputform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.TextFieldDefaults.IconOpacity
import androidx.compose.material.TextFieldDefaults.UnfocusedIndicatorLineOpacity
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.regex.Pattern

class InputItemData(
    val modifier: Modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth(),
    val mainText: CustomText = CustomText(text = ""),
    val onClick: (() -> Unit)? = null,
    val label: CustomText? = null,
    val placeHolder: CustomText? = null,
    val endIcon: CustomIcon? = null,
    val startIcon: CustomIcon? = null,
    val textFieldColors: TextFieldColors? = null,
    val maxLines: Int = Int.MAX_VALUE,
    val enabled: Boolean = true,
    val keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    val regex: Pattern? = null,
    val errorMessage: CustomText? = null,
    val styleType: StyleType = StyleType.FILLED
)

@Composable
fun InputItem(
    modifier: Modifier = Modifier,
    item: InputItemData
) {
    when (item.styleType) {
        StyleType.OUTLINED -> OutlinedInput(item = item, modifier = modifier)
        StyleType.FILLED -> FilledInput(item = item, modifier = modifier)
    }
}

@Composable
internal fun OutlinedInput(
    modifier: Modifier = Modifier,
    item: InputItemData,
) {
    var isError by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(item.mainText.text) }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable {
                    item.onClick?.invoke()
                }
                .fillMaxSize()
        ) {
            CustomIcon(item.startIcon)
            OutlinedTextField(
                isError = isError,
                value = text,
                onValueChange = {
                    text = it
                    item.regex?.pattern()?.let { pattern ->
                        isError = !validate(pattern, it)
                    }
                },
                label = {
                    item.label?.let { it ->
                        Text(
                            text = it.text,
                            modifier = it.modifier,
                            fontSize = it.fontSize,
                            fontFamily = it.font,
                            color = it.textColor
                        )
                    }
                },
                placeholder = {
                    item.placeHolder?.let { it ->
                        Text(
                            text = it.text,
                            modifier = it.modifier,
                            fontSize = it.fontSize,
                            fontFamily = it.font,
                            color = it.textColor
                        )
                    }
                },
                modifier = Modifier,
                enabled = item.enabled,
                maxLines = item.maxLines,
                singleLine = item.maxLines == 1,
                keyboardOptions = item.keyboardOptions,
                colors = item.textFieldColors?.let {
                    TextFieldDefaults.textFieldColors(
                        textColor = it.getTextColor(),
                        backgroundColor = it.getBackgroundColor(),
                        focusedIndicatorColor = it.getFocusedIndicatorColor(),
                        unfocusedIndicatorColor = it.getUnfocusedIndicatorColor(),
                        disabledTextColor = it.getDisabledTextColor(item.mainText.textColor),
                        cursorColor = it.getCursorColor(),
                        errorCursorColor = it.getErrorCursorColor(),
                        errorIndicatorColor = it.getErrorIndicatorColor(),
                        errorLeadingIconColor = it.getErrorLeadingColor(),
                        disabledLeadingIconColor = it.getDisabledLeadingIconColor(),
                        leadingIconColor = it.getLeadingIconColor(),
                        disabledIndicatorColor = it.getDisabledIndicatorColor())
                } ?: TextFieldDefaults.outlinedTextFieldColors()
            )
            CustomIcon(item.endIcon)
        }
        if (isError) CustomText(item.errorMessage)
    }
}

@Composable
internal fun FilledInput(
    modifier: Modifier = Modifier,
    item: InputItemData,
) {
    var isError by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(item.mainText.text) }
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable {
                    item.onClick?.invoke()
                }
                .fillMaxSize()
        ) {
            CustomIcon(item.startIcon)
            TextField(
                isError = isError,
                value = text,
                onValueChange = {
                    text = it
                    item.regex?.pattern()?.let { pattern ->
                        isError = !validate(pattern, it)
                    }
                },
                label = {
                    item.label?.let { it ->
                        Text(
                            text = it.text,
                            modifier = it.modifier,
                            fontSize = it.fontSize,
                            fontFamily = it.font,
                            color = it.textColor
                        )
                    }
                },
                placeholder = {
                    item.placeHolder?.let { it ->
                        Text(
                            text = it.text,
                            modifier = it.modifier,
                            fontSize = it.fontSize,
                            fontFamily = it.font,
                            color = it.textColor
                        )
                    }
                },
                modifier = Modifier,
                enabled = item.enabled,
                maxLines = item.maxLines,
                singleLine = item.maxLines == 1,
                keyboardOptions = item.keyboardOptions,
                colors = item.textFieldColors?.let {
                    TextFieldDefaults.textFieldColors(
                        textColor = it.getTextColor(),
                        backgroundColor = it.getBackgroundColor(),
                        focusedIndicatorColor = it.getFocusedIndicatorColor(),
                        unfocusedIndicatorColor = it.getUnfocusedIndicatorColor(),
                        disabledTextColor = it.getDisabledTextColor(item.mainText.textColor),
                        cursorColor = it.getCursorColor(),
                        errorCursorColor = it.getErrorCursorColor(),
                        errorIndicatorColor = it.getErrorIndicatorColor(),
                        errorLeadingIconColor = it.getErrorLeadingColor(),
                        disabledLeadingIconColor = it.getDisabledLeadingIconColor(),
                        leadingIconColor = it.getLeadingIconColor(),
                        disabledIndicatorColor = it.getDisabledIndicatorColor())
                } ?: TextFieldDefaults.textFieldColors()
            )
            CustomIcon(item.endIcon)
        }
        if (isError) CustomText(item.errorMessage)

    }
}

@Composable
internal fun CustomIcon(customIcon: CustomIcon?) {
    customIcon?.let {
        Icon(
            painterResource(id = it.id),
            contentDescription = it.contentDesc
        )
    }
}

@Composable
internal fun CustomText(customText: CustomText?) {
    customText?.let { it ->
        Text(
            text = it.text,
            modifier = it.modifier,
            fontSize = it.fontSize,
            fontFamily = it.font,
            color = it.textColor
        )
    }
}

internal fun validate(text: String, regex: String): Boolean = Pattern.matches(regex, text)

class CustomIcon(
    val id: Int,
    val contentDesc: String
)

class CustomText(
    val text: String,
    val textColor: Color = Color.Black,
    val font: FontFamily? = null,
    val fontSize: TextUnit = 12.sp,
    val modifier: Modifier = Modifier,
)

open class TextFieldColors {
    @Composable
    open fun getTextColor(): Color = LocalContentColor.current.copy(LocalContentAlpha.current)

    @Composable
    open fun getBackgroundColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity)

    @Composable
    fun getFocusedIndicatorColor(): Color =
        MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high)

    @Composable
    fun getUnfocusedIndicatorColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity)

    @Composable
    fun getDisabledTextColor(textColor: Color): Color = textColor.copy(ContentAlpha.disabled)

    @Composable
    fun getCursorColor(): Color = MaterialTheme.colors.primary

    @Composable
    fun getErrorCursorColor(): Color = MaterialTheme.colors.error

    @Composable
    fun getErrorIndicatorColor(): Color = MaterialTheme.colors.error

    @Composable
    fun getErrorLeadingColor(): Color = MaterialTheme.colors.onSurface.copy(alpha = IconOpacity)

    @Composable
    fun getLeadingIconColor(): Color = MaterialTheme.colors.onSurface.copy(alpha = IconOpacity)

    @Composable
    fun getDisabledLeadingIconColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = IconOpacity).copy(alpha = ContentAlpha.disabled)

    @Composable
    fun getDisabledIndicatorColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity)
            .copy(alpha = ContentAlpha.disabled)
}

enum class StyleType { OUTLINED, FILLED }
