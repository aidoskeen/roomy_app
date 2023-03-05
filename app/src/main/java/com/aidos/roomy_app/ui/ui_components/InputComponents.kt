package com.aidos.roomy_app.ui.ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

//Field for getting input from user
@Composable
fun InputField(
    value: TextFieldValue,
    label: @Composable (() -> Unit)? = null,
    onValueChange: (TextFieldValue) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        label = label,
        value = value,
        onValueChange = { onValueChange(value) },
        visualTransformation = visualTransformation
    )
}

@Composable
fun DropDownList(
    expanded: Boolean = false,
    items: List<String>,
    onDismissRequest: (Boolean) -> Unit,
    onItemChosen: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = expanded,
        onDismissRequest = { onDismissRequest(false) },
    ) {
        items.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onDismissRequest(false)
                    onItemChosen(it)
                }
            ) {
                Text(it, modifier = Modifier.wrapContentWidth())
            }
        }
    }
}
