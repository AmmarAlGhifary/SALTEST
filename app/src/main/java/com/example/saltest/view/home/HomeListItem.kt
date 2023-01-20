package com.example.saltest.view.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.saltest.data.model.UserResponse


@Composable
fun HomeListItem(user: UserResponse) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        elevation = 10.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
            Toast.makeText(context, "This is ${user.first_name}", Toast.LENGTH_SHORT).show()
        }) {


            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .weight(1f, fill = true),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = user.first_name,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(0.dp, 2.dp),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = user.email,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(0.dp, 2.dp),
                    textAlign = TextAlign.Start
                )

            }
        }
    }
}

