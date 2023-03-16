package com.aidos.roomy_app.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

// Button for login and registration
@Composable
fun RoomyButton(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.secondary,
    onClick: () -> Unit,
    text: String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp))
            .height(50.dp)
            .clickable(
                onClick = { onClick() }
            )
            .background(color = color)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun SmallButton(
    onClick: () -> Unit,
    text: String = "",
    color: Color
) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .clip(RoundedCornerShape(30.dp))
            .height(30.dp)
            .clickable(
                onClick = { onClick() }
            )
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1
        )
    }
}
@Composable
@Preview(name = "Button preview")
fun PreviewRoomyButton() {
    RoomyMainTheme {
        RoomyButton(
            onClick = {},
            text = "Proceed"
        )
    }
}
