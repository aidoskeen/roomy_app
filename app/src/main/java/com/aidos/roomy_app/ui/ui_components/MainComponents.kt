package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.R
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

//Top bar shown is most of the pages
@Composable
fun RoomyTopAppBar(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    painter: Painter,
    label: String,
    onUserIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painter,
                    contentDescription = null
                )
                Text(
                    text = label,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        },
        backgroundColor = backgroundColor,
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = { /* TODO: Open search */ }
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable(onClick = onUserIconClick),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                }

            }
        },
        modifier = modifier
    )
}

@Composable
fun ImageInBox(
    painter: Painter
) {
    Box (
        modifier = Modifier.padding(start = 15.dp)
            ){
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun RoomItemColumn(
    modifier: Modifier = Modifier,
    item: Room,
    painter: Painter,
    onItemClicked: (Room) -> Unit
) {
    Column(
        modifier = modifier
            .clickable { onItemClicked(item) }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(64.dp)
                .aspectRatio(1f),
            RoundedCornerShape(4.dp),
            color = MaterialTheme.colors.surface
        ) {
            ImageInBox(painter)
        }
        Spacer(Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.roomNumber.toString(),
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(Modifier.height(4.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.roomSize.toString(),
                style = MaterialTheme.typography.caption
            )
        }
    }
}
@Composable
fun DormitoryItemRow(
    modifier: Modifier = Modifier,
    item: Dormitory,
    painter: Painter,
    label: String,
    onItemClicked: (Dormitory) -> Unit
) {
    val textColor = MaterialTheme.colors.onSurface
    Row(
        modifier = modifier
            .clickable { onItemClicked(item) }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(64.dp)
                .aspectRatio(1f),
            RoundedCornerShape(4.dp),
            color = MaterialTheme.colors.surface
        ) {
            ImageInBox(painter)
        }
        Spacer(Modifier.width(24.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.h6
                        .copy(color = textColor)
                )

                Text(
                    text = item.dormitoryId.toString(),
                    style = MaterialTheme.typography.h6
                        .copy(color = textColor)

                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = item.address
                        + " " + item.university + " Rooms: ",
                style = MaterialTheme.typography.caption
                    .copy(color = textColor)
            )
        }
    }
}

@Composable
fun RoomItemRow(
    modifier: Modifier = Modifier,
    item: Room,
    painter: Painter,
    label: String = stringResource(id = R.string.room_item_label),
    onItemClicked: (Room) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(item) }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(64.dp)
                .aspectRatio(1f),
            RoundedCornerShape(4.dp),
            color = MaterialTheme.colors.surface
        ) {
            ImageInBox(painter)
        }

        Spacer(Modifier.width(24.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Start) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.h6)

                Text(
                    text = item.roomNumber.toString(),
                    style = MaterialTheme.typography.h6
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun RoomRow(
    modifier: Modifier = Modifier,
    room: Room,
    label: String,
    onItemClicked: (Room) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(room) }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {

        Spacer(Modifier.width(24.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.h6)

                Text(
                    text = room.roomNumber.toString(),
                    style = MaterialTheme.typography.h6
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = room.description,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
@Preview(name = "Dormitory row preview")
fun DormItemRowPreview() {
    RoomyMainTheme {

        val dormitory = Dormitory(
            dormitoryId = 11,
            address = "Sauletekio 25",
            rooms = listOf(),
        "VGTU"
        )
        val label = stringResource(id = R.string.dorm_item_label)
        val image = painterResource(id = R.drawable.ic_launcher_foreground)
        DormitoryItemRow(
            item = dormitory,
            painter = image,
            label = label,
            onItemClicked = { })
    }
}

@Composable
@Preview(name = "Room item row preview night", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Room item row preview day", uiMode = UI_MODE_NIGHT_NO)
fun RoomItemRowPreview() {
    RoomyMainTheme {
        val roomItem = Room(1, RoomType.DOUBLE, RoomSize.SMALL, listOf(), "Regular room")
        val label = stringResource(id = R.string.room_item_label)
        val image = painterResource(id = R.drawable.ic_launcher_foreground)
        RoomItemRow(
            item = roomItem,
            painter = image,
            label = label,
            onItemClicked = { })
    }
}

@Composable
@Preview(name = "Room row preview night", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Room row preview day", uiMode = UI_MODE_NIGHT_NO)
fun RoomRowPreview() {
    RoomyMainTheme {
        val roomItem = Room(1, RoomType.DOUBLE, RoomSize.SMALL, listOf(), "Regular room")
        val label = stringResource(id = R.string.room_item_label)
        RoomRow(
            room = roomItem,
            label = label,
            onItemClicked = { })
    }
}

@Composable
@Preview(name = "App Bar Preview day", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "App Bar Preview night", uiMode = UI_MODE_NIGHT_NO)
fun AppBarPreview() {
    val painter = painterResource(id = R.drawable.ic_launcher_foreground)
    RoomyMainTheme {
        RoomyTopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            painter = painter,
            label = "Roomy",
            onUserIconClick = { }
        )
    }
}

