package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
    place: Place,
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
                text = stringResource(id = R.string.request_item_label) + "\t" + place.placeId,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface
            )

            Text(
                text = (stringResource(id = R.string.requester) + place.livingResident?.getFullName()),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = stringResource(id = R.string.room_item_label) + place.roomNumber,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onSurface
            )


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
fun TextRow(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            modifier = modifier,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )

        Text(
            text = value,
            modifier = modifier,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun RoomBookingForm(
    resident: User.Resident,
    room: Room,
    date: String? = null,
    onButtonClicked: (Place) -> Unit
) {
    var chosenPlace: Place? = null
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.room_booking_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )

        PlaceSelector(
            places = room.places,
            onPlaceClicked = { place ->  chosenPlace = place }
        )

        Divider(thickness = 1.dp)

        TextRow(
            label = stringResource(id = R.string.requester),
            value = resident.getFullName()
        )

        TextRow(
            label = stringResource(id = R.string.room_number),
            value = room.roomNumber.toString()
        )

        val sizeStringId = when (room.roomSize) {
            RoomSize.SMALL -> R.string.small_room
            RoomSize.MEDIUM -> R.string.medium_room
            RoomSize.BIG -> R.string.big_room
        }

        TextRow(
            label = stringResource(id = R.string.room_size),
            value = stringResource(id = sizeStringId)
        )

        val typeStringId = when (room.roomType) {
            RoomType.SINGLE -> R.string.single_room
            RoomType.DOUBLE -> R.string.double_room
            RoomType.TRIPLE -> R.string.triple_room
        }

        TextRow(
            label = stringResource(id = R.string.room_type),
            value = stringResource(id = typeStringId)
        )

        TextRow(
            label = stringResource(id = R.string.date_of_booking),
            value = date ?: ""
        )

        Divider(thickness = 1.dp)

        RoomyButton(
            text = stringResource(id = R.string.button_send_request),
            onClick = { chosenPlace?.let { onButtonClicked(it) } }
        )
    }
}

@Composable
fun PlaceSelector(
    places: List<Place>,
    onPlaceClicked: (Place) -> Unit
) {
    var iconClicked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .border(
                width = 4.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(4.dp)
            )
            .background(color = MaterialTheme.colors.surface)
            .heightIn(max = 150.dp)
            .widthIn(max = 300.dp)
            .aspectRatio(16f / 9f)
    ) {
        val chunkedList = places.chunked(2)
        Column(modifier = Modifier.fillMaxSize()) {
            chunkedList.forEach { childList ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    childList.forEach {
                        ClickableIcon(
                            painter = painterResource(id = R.drawable.ic_bed)
                        ) {
                            onPlaceClicked(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClickableIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit
) {
    var iconClicked by remember { mutableStateOf(false) }
    val color = if (iconClicked) Color.LightGray else MaterialTheme.colors.onSurface

    Icon(
        modifier = Modifier.clickable(
            onClick = {
                onClick()
                iconClicked = !iconClicked
            }
        ),
        painter = painter,
        tint = color,
        contentDescription = null
    )
}

@Composable
@Preview(name = "Request review", uiMode = UI_MODE_NIGHT_NO)
fun RequestItemPreview() {
    val place = Place(price = 100L)
    val room = Room(11, RoomType.SINGLE, RoomSize.SMALL, listOf(place))
    val resident = User.Resident(1, "Aidos", "Alimkhan")
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

@Composable
@Preview(name = "Booking Form review light", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Booking Form review dark", uiMode = UI_MODE_NIGHT_YES)
fun RoomBookingReview() {
    val place = Place(price = 100L)
    val room = Room(11, RoomType.SINGLE, RoomSize.SMALL, listOf(place))
    val resident = User.Resident(1, "Aidos", "Alimkhan")
    RoomyMainTheme {
        RoomBookingForm(
            resident = resident,
            room = room, 
            date = "2023/03/23"
        ) {}
    }

}

@Composable
@Preview(name = "Selector review light", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Selector review dark", uiMode = UI_MODE_NIGHT_YES)
fun PlaceSelectorPreview() {
    val place = Place(price = 100L)
    val place2 = Place(price = 200L)
    val place3 = Place(price = 300L)

    RoomyMainTheme {
        PlaceSelector(places = listOf(place, place2, place3), onPlaceClicked = {})
    }
}