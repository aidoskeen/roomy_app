package com.aidos.roomy_app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.models.Room

//Top bar shown is most of the pages
@Composable
fun TabBar(
    modifier: Modifier = Modifier,
    onMenuClicked: () -> Unit,
    painter: Painter,
    description: String,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier) {
        Row(Modifier.padding(top = 8.dp)) {
            Image(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable(onClick = onMenuClicked),
                painter = painter,
                contentDescription = description
            )
            Spacer(Modifier.width(8.dp))
        }
        children(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun ImageOfRoom(
    item: Room,
    painter: Painter
) {
    Box {
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
            modifier = Modifier.size(64.dp).aspectRatio(1f),
            RoundedCornerShape(4.dp)
        ) {
            ImageOfRoom(item, painter)
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
private fun RoomItemRow(
    modifier: Modifier = Modifier,
    item: Room,
    painter: Painter,
    onItemClicked: (Room) -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(item) }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        Surface(
            modifier = Modifier.size(64.dp).aspectRatio(1f),
            RoundedCornerShape(4.dp)
        ) {
            ImageOfRoom(item, painter)
        }
        Spacer(Modifier.width(24.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = item.roomNumber.toString(),
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = item.roomNumber.toString(),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
private fun ImageContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(modifier.aspectRatio(1f), RoundedCornerShape(4.dp)) {
        content()
    }
}