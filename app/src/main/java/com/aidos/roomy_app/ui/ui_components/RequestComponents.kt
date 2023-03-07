package com.aidos.roomy_app.ui.ui_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.R
import com.aidos.roomy_app.enums.RequestStatus
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.*
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

@Composable
fun RequestItem(
    modifier: Modifier = Modifier,
    request: Request,
    onAcceptClicked: () -> Unit,
    onRejectClicked: () -> Unit
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.request_item_label) + "\t" + request.requestId.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface
            )

            Text(
                text = stringResource(id = R.string.requester) + request.requester.getFullName(),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onSurface
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.dorm_item_label) + request.dormitory.university,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onSurface
                )

                Text(
                    text = stringResource(id = R.string.room_item_label) + request.room.roomNumber.toString(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.subtitle2,
                    color = MaterialTheme.colors.onSurface
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SmallButton(
                    text = stringResource(id = R.string.button_accept),
                    onClick = onAcceptClicked,
                    color = Color.Green
                )

                SmallButton(
                    text = stringResource(id = R.string.button_reject),
                    onClick = onRejectClicked,
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
@Preview(name = "Request review")
fun RequestItemPreview() {
    val place = Place(price = 100L)
    val room = Room(11, RoomType.SINGLE, RoomSize.SMALL, listOf(place))
    val resident = User.Resident("1", "Aidos", "Alimkhan")
    val dormitory = Dormitory(1)
    val request = Request(
        requestId = 1,
        requester = resident,
        room = room,
        dormitory = dormitory,
        requestStatus = RequestStatus.PENDING
    )
    RoomyMainTheme {
        RequestItem(
            request = request,
            onAcceptClicked = { },
            onRejectClicked = { }
        )

    }
}