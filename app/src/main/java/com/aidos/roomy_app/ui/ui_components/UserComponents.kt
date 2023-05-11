package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.R
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

@Composable
fun ProfileScreen(user: User) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.profile_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )
        val contentColor = MaterialTheme.colors.onSurface

        TextRow(
            label = stringResource(id = R.string.username),
            value = user.username
        )

        Divider(Modifier.background(contentColor))

        TextRow(
            label = stringResource(id = R.string.fullname),
            value = user.getFullName()
        )

        Divider(Modifier.background(contentColor))

        TextRow(
            label = stringResource(id = R.string.contract_expiry),
            value = "2023/07/30"
        )

        Divider(Modifier.background(contentColor))
        when (user) {
            is User.Resident -> {
                val value = if (user.roomNumber == null)
                    stringResource(id = R.string.no_room)
                else user.roomNumber.toString()

                TextRow(
                    label = stringResource(id = R.string.room_title),
                    value = value
                )
            }
            is User.Administrator -> {
                val dormitory = user.dormitory
                if (dormitory != null)
                    TextRow(
                        label = stringResource(id = R.string.dorm_item_label),
                        value = dormitory.university
                    )
            }
        }

        Divider(Modifier.background(contentColor))
        Row(
            modifier = Modifier
                .clickable(onClick = { })
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.edit_data),
                style = MaterialTheme.typography.body1.copy(
                    color = contentColor
                )
            )
        }
        Divider(Modifier.background(contentColor))

        RoomyButton(
            text = stringResource(id = R.string.logout_button),
            onClick = { /*TODO*/ })
    }
}

@Composable
@Preview(name = "Profile light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Profile dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewProfileForm() {
    val resident = User.Resident(
        id = 123,
        name = "Aidos",
        surname = "Alimkhan",
        roomNumber = 703
    )

    RoomyMainTheme {
        ProfileScreen(user = resident)
    }
}

@Composable
@Preview(name = "Admin profile light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Admin profile dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewProfileFormAdmin() {
    val dormitory = Dormitory(
        dormitoryId = 12
    )

    val admin = User.Administrator(
        id = 123,
        name = "Aidos",
        surname = "Alimkhan",
        dormitory = dormitory
    )

    RoomyMainTheme {
        ProfileScreen(user = admin)
    }
}