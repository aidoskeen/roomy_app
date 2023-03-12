package com.aidos.roomy_app.ui.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.R
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

@Composable
fun MenuOption(
    text: String,
    onClick: () -> Unit
) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
            )
            
            Icon(painter = painterResource(id = R.drawable.ic_forward_arrow), contentDescription = null)
        }
    
}

@Composable
@Preview(name = "Menu preview")
fun MenuOptionPreview() {
    val dividerModifer = Modifier.padding(start = 20.dp, end = 20.dp)
    RoomyMainTheme {
        Column {
            MenuOption(
                text = stringResource(id = R.string.make_announcement),
                onClick = { }
            )

            Divider(
                modifier = dividerModifer,
                color = MaterialTheme.colors.onSurface
            )

            MenuOption(
                text = stringResource(id = R.string.check_requests),
                onClick = { }
            )

            Divider(
                modifier = dividerModifer,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}