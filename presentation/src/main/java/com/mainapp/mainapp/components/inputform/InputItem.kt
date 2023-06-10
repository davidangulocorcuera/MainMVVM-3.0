package com.mainapp.mainapp.components.inputform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import java.util.regex.Pattern

class InputItemData(
    val modifier: Modifier = Modifier,
    val mainText: CustomText = CustomText(text = ""),
    val onClick: (() -> Unit)? = null,
    val outsideLabel: Boolean = false,
    val label: CustomText? = null,
    val showLeadingIconOnWriteAction: Boolean = false,
    val showTrailingIconOnWriteAction: Boolean = false,
    val placeHolder: CustomText? = null,
    val trailingIcon: CustomIcon? = null,
    val leadingIcon: CustomIcon? = null,
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
    var showLeadingIconOnWriteAction by remember { mutableStateOf(text.isNotEmpty() && item.showLeadingIconOnWriteAction) }
    var showTrailingIconOnWriteAction by remember { mutableStateOf(text.isNotEmpty() && item.showTrailingIconOnWriteAction) }
    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                item.trailingIcon?.onClick?.invoke()
            },
        ) {
            item.trailingIcon?.let {
                Icon(
                    painter = painterResource(it.id),
                    contentDescription = it.contentDesc,
                    modifier = Modifier.clickable { item.onClick?.invoke() }
                )
            }
        }
    }
    val leadingIconView = @Composable {
        IconButton(
            onClick = {
                item.leadingIcon?.onClick?.invoke()
            },
        ) {
            item.leadingIcon?.let {
                Icon(
                    painter = painterResource(it.id),
                    contentDescription = it.contentDesc,
                    modifier = Modifier.clickable { item.onClick?.invoke() }
                )
            }
        }
    }
    Column(modifier = modifier) {
        if (item.outsideLabel) {
            item.label?.let { it ->
                Text(
                    text = it.text,
                    modifier = it.modifier,
                    fontSize = it.fontSize,
                    fontFamily = it.font,
                    color = if (isError) item.errorMessage?.textColor
                        ?: it.textColor else it.textColor
                )
            }
        }
        Row(
            modifier = Modifier
                .clickable {
                    item.onClick?.invoke()
                }
        ) {
            OutlinedTextField(
                isError = isError,
                value = text,
                onValueChange = {
                    text = it
                    item.regex?.pattern()?.let { pattern ->
                        isError = !validate(pattern, it)
                    }
                },
                leadingIcon = when{

                    else -> null
                } ,
                trailingIcon = if (item.trailingIcon != null) trailingIconView else null,
                label = {
                    if (!item.outsideLabel) {
                        item.label?.let { it ->
                            Text(
                                text = it.text,
                                modifier = it.modifier,
                                fontSize = it.fontSize,
                                fontFamily = it.font,
                                color = it.textColor
                            )
                        }
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
                        trailingIconColor = it.getTrailingIconColor(),
                        disabledIndicatorColor = it.getDisabledIndicatorColor()
                    )
                } ?: TextFieldDefaults.outlinedTextFieldColors()
            )
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

    val trailingIconView = @Composable {
        IconButton(
            onClick = {
                item.trailingIcon?.onClick?.invoke()
            },
        ) {
            item.trailingIcon?.let {
                Icon(
                    painter = painterResource(it.id),
                    contentDescription = it.contentDesc,
                    modifier = Modifier.clickable { item.onClick?.invoke() }
                )
            }
        }
    }
    val leadingIconView = @Composable {
        IconButton(
            onClick = {
                item.leadingIcon?.onClick?.invoke()
            },
        ) {
            item.leadingIcon?.let {
                Icon(
                    painter = painterResource(it.id),
                    contentDescription = it.contentDesc,
                    modifier = Modifier.clickable { item.onClick?.invoke() }
                )
            }
        }
    }
    Column(modifier = modifier) {
        if (item.outsideLabel) {
            item.label?.let { it ->
                Text(
                    text = it.text,
                    modifier = it.modifier,
                    fontSize = it.fontSize,
                    fontFamily = it.font,
                    color = if (isError) item.errorMessage?.textColor
                        ?: it.textColor else it.textColor
                )
            }
        }
        Row(
            modifier = Modifier
                .clickable {
                    item.onClick?.invoke()
                }
        ) {
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
                    if (!item.outsideLabel) {
                        item.label?.let { it ->
                            Text(
                                text = it.text,
                                modifier = it.modifier,
                                fontSize = it.fontSize,
                                fontFamily = it.font,
                                color = it.textColor
                            )
                        }
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
                leadingIcon = if (item.leadingIcon != null) leadingIconView else null,
                trailingIcon = if (item.trailingIcon != null) trailingIconView else null,
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
                        trailingIconColor = it.getTrailingIconColor(),
                        disabledIndicatorColor = it.getDisabledIndicatorColor()
                    )
                } ?: TextFieldDefaults.textFieldColors()
            )
        }
        if (isError) CustomText(item.errorMessage)

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
    val contentDesc: String,
    val onClick: (() -> Unit)? = null,
    val tint : Color? = null
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
    open fun getFocusedIndicatorColor(): Color =
        MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high)

    @Composable
    open fun getUnfocusedIndicatorColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity)

    @Composable
    open fun getDisabledTextColor(textColor: Color): Color = textColor.copy(ContentAlpha.disabled)

    @Composable
    open fun getCursorColor(): Color = MaterialTheme.colors.primary

    @Composable
    open fun getErrorCursorColor(): Color = MaterialTheme.colors.error

    @Composable
    open fun getErrorIndicatorColor(): Color = MaterialTheme.colors.error

    @Composable
    open fun getErrorLeadingColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = IconOpacity)

    @Composable
    open fun getLeadingIconColor(): Color = MaterialTheme.colors.onSurface.copy(alpha = IconOpacity)

    @Composable
    open fun getTrailingIconColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = IconOpacity)

    @Composable
    open fun getDisabledLeadingIconColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = IconOpacity).copy(alpha = ContentAlpha.disabled)

    @Composable
    open fun getDisabledIndicatorColor(): Color =
        MaterialTheme.colors.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity)
            .copy(alpha = ContentAlpha.disabled)
}

enum class StyleType { OUTLINED, FILLED }
