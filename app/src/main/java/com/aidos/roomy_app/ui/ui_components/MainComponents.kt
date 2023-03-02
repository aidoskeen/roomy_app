package com.aidos.roomy_app.ui.ui_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.R
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

//Top bar shown is most of the pages
@Composable
fun RoomyTopAppBar(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    painter: Painter,
    label: String
) {
    TopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
                    ){
                Image(
                    painter = painter,
                    contentDescription = null
                )
              Text(text = label)
            }
        },
        backgroundColor = backgroundColor,
        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = { /* TODO: Open search */ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = { /* TODO: Open account? */ }
                ) {
                    Icon(
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
            modifier = Modifier
                .size(64.dp)
                .aspectRatio(1f),
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
            modifier = Modifier
                .size(64.dp)
                .aspectRatio(1f),
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

@Composable
@Preview(name = "App Bar Preview")
fun AppBarPreview() {
    RoomyMainTheme {
        RoomyTopAppBar(
            backgroundColor = MaterialTheme.colors.primary,
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            label = "Roomy"
        )

    }
}