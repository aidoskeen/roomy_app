package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.aidos.roomy_app.R
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

@Composable
fun MakeAnnouncementForm(
    text: String = "",
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    val title = stringResource(id = R.string.make_announcement)
    val announcementLabel = stringResource(id = R.string.announcement_text)
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (titleRef, inputRef, buttonRef) = createRefs()
            createVerticalChain(titleRef, inputRef, buttonRef, chainStyle = ChainStyle.SpreadInside)
            createEndBarrier(inputRef, margin = 20.dp)
            Text(
                modifier = Modifier
                    .constrainAs(titleRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(inputRef.top)
                    }
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface
            )

            TextField(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.surface)
                    .height(500.dp)
                    .fillMaxWidth()
                    .constrainAs(inputRef) {
                        top.linkTo(titleRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = 20.dp),
                value = text,
                label = { Text(text = announcementLabel) },
                onValueChange = { onValueChange(it) },
                textStyle = MaterialTheme.typography.caption,
            )

            RoomyButton(
                modifier = Modifier
                    .constrainAs(buttonRef) {
                        top.linkTo(inputRef.bottom)
                        bottom.linkTo(parent.bottom)
                    },
                text = stringResource(id = R.string.button_announce),
                onClick = onButtonClick
            )
        }
    }
}

@Composable
@Preview(name = "Announcement Form review light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Announcement Form review dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MakeAnnouncementPreview() {
    RoomyMainTheme {
        MakeAnnouncementForm(onValueChange = {}, onButtonClick = {}, text = "")
    }

}