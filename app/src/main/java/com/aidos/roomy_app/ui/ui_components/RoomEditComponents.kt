package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    room: Room,
    roomSizesList: List<String>,
    roomTypesList: List<String>,
    onButtonClick: (Room) -> Unit
) {
    var newRoom = room
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

        Divider(thickness = 1.dp)
        val roomNumberTitle = stringResource(id = R.string.room_number)
        val typeTitle = stringResource(id = R.string.room_type)
        val residentsOfRoom = stringResource(id = R.string.residents_in_room)
        val roomSizeTitle = stringResource(id = R.string.room_size)

        EditableTextRow(
            label = roomNumberTitle,
            currentValue = room.roomNumber.toString(),
            onValueChanged = { newRoom = newRoom.copy(roomNumber = it.toInt()) } )

        EditableTextRow(
            label = roomSizeTitle,
            currentValue = room.roomSize.toString(),
            onValueChanged = {
                val size = when (it) {
                    "SMALL" -> RoomSize.SMALL
                    "BIG" -> RoomSize.BIG
                    "MEDIUM" -> RoomSize.MEDIUM
                    else -> RoomSize.SMALL
                }
                newRoom = newRoom.copy(roomSize = size)
            } )

        EditableTextRow(
            label = typeTitle,
            currentValue = room.roomType.toString(),
            onValueChanged = {
                val type = when (it) {
                    "SINGLE" -> RoomType.SINGLE
                    "DOUBLE" -> RoomType.DOUBLE
                    "TRIPLE" -> RoomType.TRIPLE
                    else -> RoomType.DOUBLE
                }

                newRoom = newRoom.copy(roomType = type)
            } )

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
            onClick = { onButtonClick(newRoom) }
        )
    }
}
@Composable
fun TextRowWithDropDownList(
    modifier: Modifier = Modifier,
    items: List<String>,
    label: String = "",
    onItemChosen: (String) -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expandedState != expandedState },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = modifier,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSurface
        )

        DropDownList(
            expanded = expandedState,
            items = items,
            onDismissRequest = { expandedState = it },
            onItemChosen = onItemChosen
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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
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
            singleLine = true,
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
            contentDescription = null,
            tint = Color.Red
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
    val roomSizesList = listOf("SMALL", "BIG")
    val roomTypesList = listOf("SINGLE", "DOUBLE")
    RoomyMainTheme {
        RoomEditForm(
            room = room,
            onButtonClick = { },
            roomSizesList = roomSizesList,
            roomTypesList = roomTypesList
        )
    }
}