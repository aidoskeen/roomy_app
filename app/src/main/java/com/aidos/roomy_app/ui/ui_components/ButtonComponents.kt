package com.aidos.roomy_app.ui.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

// Button for login and registration
@Composable
fun BigButton(
    onClick: () -> Unit,
    text: String = ""
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .fillMaxWidth()
            .height(50.dp)
            .padding(40.dp, 0.dp, 40.dp, 0.dp)
            .clickable(
                onClick = { onClick() }
            )
    ) {
        Text(text = text)
    }
}