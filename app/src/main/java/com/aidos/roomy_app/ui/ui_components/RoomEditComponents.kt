package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.R
import com.aidos.roomy_app.enums.PaymentStatus
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

@Composable
fun RoomEditForm(
    image: Painter,
    room: Room,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.edit_room),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )

        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .heightIn(max = 170.dp)
                .widthIn(max = 300.dp)
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(16.dp))
        )

        Divider(thickness = 1.dp)
        val roomNumberTitle = stringResource(id = R.string.room_number)
        val typeTitle = stringResource(id = R.string.room_type)
        val residentsOfRoom = stringResource(id = R.string.residents_in_room)
        val roomSizeTitle = stringResource(id = R.string.room_size)

        EditableTextRow(
            label = roomNumberTitle,
            currentValue = room.roomNumber.toString(),
            onValueChanged = { } )


        val sizeString = when (room.roomSize) {
            RoomSize.SMALL -> stringResource(R.string.small_room)
            RoomSize.MEDIUM -> stringResource(R.string.medium_room)
            RoomSize.BIG -> stringResource(R.string.big_room)
        }

        EditableTextRow(
            label = roomSizeTitle,
            currentValue = sizeString,
            onValueChanged = { } )

        val typeString = when (room.roomType) {
            RoomType.SINGLE -> stringResource(id = R.string.single_room)
            RoomType.DOUBLE -> stringResource(R.string.double_room)
            RoomType.TRIPLE -> stringResource(R.string.triple_room)
        }

        EditableTextRow(
            label = typeTitle,
            currentValue = typeString,
            onValueChanged = { } )

        Divider()

        Text(
            text = residentsOfRoom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface
        )

        room.places.forEach { place ->

            place.livingResident?.let {
                RemovableRow(value = it.getFullName()) {

                }
            }
        }

        RoomyButton(
            text = stringResource(id = R.string.apply_edit_button),
            onClick = { /*TODO*/ }
        )
    }
}

@Composable
fun EditableTextRow(
    modifier: Modifier = Modifier,
    label: String = "",
    currentValue: String,
    onValueChanged: (String) -> Unit
) {
    var value by remember {
        mutableStateOf("")
    }

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

        OutlinedTextField(
            value = value,
            label = { Text(text = currentValue) },
            onValueChange = {
                    newText: String -> value = newText
                    onValueChanged(value)
            }
        )
    }
}

@Composable
fun RemovableRow(
    modifier: Modifier = Modifier,
    value: String = "",
    onIconClicked: () -> Unit
) {
    var value by remember {
        mutableStateOf("")
    }
    val removeIcon = painterResource(id = R.drawable.ic_remove)
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = value,
            modifier = modifier.padding(start = 20.dp),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )

        Icon(
            modifier = Modifier
                .padding(end = 20.dp)
                .clickable(onClick = onIconClicked),
            painter = removeIcon,
            contentDescription = null
        )
    }
}

@Composable
@Preview(name = "Room edit form preview", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Room edit form preview day", uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewRoomEditForm() {
    val payment = MonthlyPayment(paymentId = "1","December", paymentStatus = PaymentStatus.PAID, dueDate = "2023/03/23")
    val resident = User.Resident(1, "Aidos", "Alimkhan")
    val place = Place(price = 100L, monthlyPayment = payment, livingResident = resident)
    val room = Room(11, RoomType.SINGLE, RoomSize.SMALL, listOf(place))
    val image = painterResource(id = R.drawable.ic_launcher_foreground)
    RoomyMainTheme {
        RoomEditForm(image = image, room = room, onValueChanged = { })
    }
}