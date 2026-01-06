package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.border

import androidx.compose.material3.Surface
import com.example.composetutorial.ui.theme.ComposeTutorialTheme



import androidx.compose.ui.tooling.preview.Preview

data class Message(val author: String, val body: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Android", "Jetpack Compose"))
        }
    }
}

//@Composable
//fun MessageCard(msg: Message) {
//    Column {
//        Text(text = msg.author)
//        Text(text = msg.body)
//    }
//}
//

@Composable
fun MessageCard(msg: Message) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        modifier = Modifier.padding(all = 4.dp)
    ){Row(modifier = Modifier.padding(all = 8.dp).background(Color.Blue).padding(all = 6.dp)) {
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier.size(40.dp).clip(CircleShape).border(1.5.dp, MaterialTheme.colorScheme.tertiary, CircleShape)

        )

        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = msg.author , color = Color.White, style = MaterialTheme.typography.titleMedium )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body , color = Color.White, style = MaterialTheme.typography.bodyMedium)
        }

    }}


}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface {
            MessageCard(
                msg = Message("Sarah", "Take a look at Jetpack Compose!")
            )
        }
    }
}


//Styling with Modifiers
//Apply Theme Colors and Typography
//surface
