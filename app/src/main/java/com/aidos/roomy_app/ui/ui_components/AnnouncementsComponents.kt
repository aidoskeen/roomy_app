package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.aidos.roomy_app.R
import com.aidos.roomy_app.models.Announcement
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

@Composable
fun MakeAnnouncementForm(
    text: String = "",
    titleText: String = "",
    onTitleValueChange: (String) -> Unit,
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    val title = stringResource(id = R.string.make_announcement)
    val announcementLabel = stringResource(id = R.string.announcement_text)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (titleRef, titleInputRef, inputRef, buttonRef) = createRefs()
            createVerticalChain(titleRef, titleInputRef, inputRef, buttonRef, chainStyle = ChainStyle.SpreadInside)
            createEndBarrier(inputRef, margin = 20.dp)
            Text(
                modifier = Modifier
                    .constrainAs(titleRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(titleInputRef.top)
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
                    .heightIn(max = 50.dp)
                    .constrainAs(titleInputRef) {
                        top.linkTo(titleRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(inputRef.top)
                    },
                label = { Text(text = "Title") },
                value = titleText,
                onValueChange = { onTitleValueChange(it) },
            )

            TextField(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.surface)
                    .height(450.dp)
                    .fillMaxWidth()
                    .constrainAs(inputRef) {
                        top.linkTo(titleInputRef.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = 20.dp, top = 15.dp),
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
fun AnnouncementRow(
    modifier: Modifier = Modifier,
    announcement: Announcement
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val contentColor = MaterialTheme.colors.onSurface
    Column(
        modifier = modifier
            .clickable(onClick = { expanded = !expanded })
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier,
            text = announcement.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (!expanded)
            Text(
                modifier = Modifier,
                text = announcement.text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2.copy(
                    color = contentColor
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        else
            Text(
                modifier = Modifier,
                text = announcement.text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2.copy(
                    color = contentColor
                )
            )

        Divider(modifier = Modifier.background(contentColor))
    }
}

@Composable
@Preview(name = "Announcement Form review light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Announcement Form review dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun MakeAnnouncementPreview() {
    RoomyMainTheme {
        MakeAnnouncementForm(onValueChange = {}, onButtonClick = {}, text = "", onTitleValueChange = {})
    }

}

@Composable
@Preview(name = "Announcement Form review light", uiMode = Configuration.UI_MODE_NIGHT_NO, widthDp = 250, heightDp = 600)
@Preview(name = "Announcement Form review dark", uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 250, heightDp = 600)
fun MakeAnnouncementRowPreview() {
    val announcementList = listOf(
        Announcement(
            0,
            "Price will be increase",
            "bla bla bla bla bla bla bla bla bla bla bla bla bla bla"
        ),
        Announcement(
            0,
            "Dormitory will be renovated",
            "text text text text text text  text text text  text text text  text text text  text text text  text text text "
        ),
    )

    RoomyMainTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            announcementList.forEach { announcement ->
                AnnouncementRow(announcement = announcement)
            }
        }

    }

}