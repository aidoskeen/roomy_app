package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.aidos.roomy_app.R
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

data class MessageItem(
    val textRes: Int,
    val iconRes: Int? = null
)

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
        onValueChange = { onValueChange(it) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions
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


@Composable
fun MessageBox(
    modifier: Modifier = Modifier,
    message: MessageItem
) {
    val textColor = MaterialTheme.colors.onSurface
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colors.surface)
            .requiredHeight(65.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
        ,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (message.iconRes != null) {
            Icon(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = message.iconRes),
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = message.textRes),
            style = MaterialTheme.typography.caption.copy(textColor)
        )

    }
}

@Composable
@Preview(name = "Messagebox night", uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 300, heightDp = 650)
@Preview(name = "messageBox day", uiMode = Configuration.UI_MODE_NIGHT_NO)
fun MessageBoxPreview() {
    val message = MessageItem(R.string.accepted, R.drawable.ic_mail)
    RoomyMainTheme {
        ConstraintLayout(
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .fillMaxSize()
        ) {
            MessageBox(message = message)
        }
    }
}
