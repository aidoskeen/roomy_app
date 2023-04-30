package com.aidos.roomy_app.ui.ui_components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.aidos.roomy_app.models.*
import com.aidos.roomy_app.ui.theme.RoomyMainTheme

@Composable
fun BookedRoomForm(
    image: Painter,
    room: Room,
    place: Place,
    onInvoiceClicked:() -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.my_room_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
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

        Divider()

        Text(
            text = stringResource(id = R.string.invoice),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )
        InvoiceItemRow(payment = place.monthlyPayment, painter = image, onItemClicked = onInvoiceClicked)
        RoomyButton(
            text = stringResource(id = R.string.make_payment),
            onClick = { /*TODO*/ }
        )

    }
}

@Composable
fun InvoiceItemRow(
    modifier: Modifier = Modifier,
    payment: MonthlyPayment?,
    painter: Painter,
    onItemClicked: () -> Unit
) {
    if (payment== null) return
    val textColor = MaterialTheme.colors.onSurface
    Row(
        modifier = modifier
            .clickable(onClick = onItemClicked)
            .padding(top = 12.dp, bottom = 12.dp)
            .background(
                color = MaterialTheme.colors.secondaryVariant,
                shape = RoundedCornerShape(5.dp)),
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

            Text(
                text = payment.month,
                style = MaterialTheme.typography.h6
                    .copy(color = textColor)
            )

            val paymentString = when(payment.paymentStatus) {
                PaymentStatus.PAID -> stringResource(id = R.string.paid_status)
                PaymentStatus.OVERDUE -> stringResource(id = R.string.overdue_status)
                else -> stringResource(id = R.string.not_paid_status)
            }
            Spacer(Modifier.height(4.dp))

            TextRow(
                label = stringResource(id = R.string.payment_title),
                value = paymentString
            )

            TextRow(
                label = stringResource(id = R.string.date_of_payment),
                value = payment.dueDate
            )
        }
    }
}

@Composable
fun InvoiceForm(
    payment: MonthlyPayment,
    resident: User.Resident,
    place: Place,
    room: Room
) {
    val invoiceContent: List<Pair<String, String>> = listOf(
        stringResource(id = R.string.resident_label) to resident.getFullName(),
        stringResource(id = R.string.payment_id_label) to payment.paymentId,
        stringResource(id = R.string.month) to payment.month,
        stringResource(id = R.string.due_date_label) to payment.dueDate,
        stringResource(id = R.string.rent_price_label) to place.price.toString()
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.invoice),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface
        )

        invoiceContent.forEach {
            TextRow(
                label = it.first,
                value = it.second
            )
        }
    }
}
@Composable
@Preview(name = "My Room Preview")
fun BookedRoomFormPreview() {
    val payment = MonthlyPayment(paymentId = "1","December", paymentStatus = PaymentStatus.PAID, dueDate = "2023/03/23")
    val place = Place(price = 100L, monthlyPayment = payment)
    val room = Room(11, RoomType.SINGLE, RoomSize.SMALL, listOf(place))
    val image = painterResource(id = R.drawable.ic_launcher_foreground)

    RoomyMainTheme {
        BookedRoomForm(image = image, room = room, place = place) {}
    }
}
@Composable
@Preview(name = "Invoice item preview", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Invoice item preview day", uiMode = UI_MODE_NIGHT_NO)
fun InvoiceItemPreview() {
    val payment = MonthlyPayment(paymentId = "1","December", paymentStatus = PaymentStatus.PAID, dueDate = "2023/03/23")
    val image = painterResource(id = R.drawable.ic_launcher_foreground)
    RoomyMainTheme() {
            InvoiceItemRow(payment = payment,
                painter = image
            ) {

            }
    }
}

@Composable
@Preview(name = "Invoice form preview", uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Invoice form preview day", uiMode = UI_MODE_NIGHT_NO)
fun InvoiceFormPreview() {
    val payment = MonthlyPayment(paymentId = "1","December", paymentStatus = PaymentStatus.PAID, dueDate = "2023/03/23")
    val place = Place(price = 100L, monthlyPayment = payment)
    val room = Room(11, RoomType.SINGLE, RoomSize.SMALL, listOf(place))
    val resident = User.Resident(1, "Aidos", "Alimkhan")

    RoomyMainTheme {
        InvoiceForm(payment = payment, resident = resident, place = place, room = room)
    }
}