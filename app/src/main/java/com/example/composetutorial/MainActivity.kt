// kotlin
package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

// Simple data holder for a chat/message item.
data class Message(val author: String, val body: String)

// Main activity hosting the Compose UI.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent starts the Compose UI tree.
        setContent {
            // Apply the app theme.
            ComposeTutorialTheme {
                // Hoisted state: parent (activity/composition) owns expansion state.
                var isExpanded by remember { mutableStateOf(false) }

                // Pass the state and an onClick lambda down to the composable.
                MessageCard(
                    msg = Message("Android", "Jetpack Compose"),
                    isExpanded = isExpanded,
                    onClick = { isExpanded = !isExpanded } // toggle expansion
                )
            }
        }
    }
}

/*
 Composable that displays a single message item.
 - msg: the data to show
 - isExpanded: controls whether the message body shows full text or a single line
 - onClick: called when the message column is clicked (for toggling expansion)
*/
@Composable
fun MessageCard(
    msg: Message,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    // Row for avatar + content with outer padding.
    Row(modifier = Modifier.padding(8.dp)) {
        // Profile picture: circular image with border.
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = null, // decorative image
            modifier = Modifier
                .size(40.dp) // fixed avatar size
                .clip(CircleShape) // make it circular
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape) // outline
        )

        Spacer(modifier = Modifier.width(8.dp)) // spacing between avatar and text

        // Column containing author and message body.
        // The whole column is clickable to toggle expansion; weight fills remaining space.
        Column(
            modifier = Modifier
                .clickable { onClick() } // propagate click to parent-provided handler
                .weight(1f)
        ) {
            // Author name text styled by theme.
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Surface provides background, shape and elevation for the message body.
            // animateContentSize() smoothly animates when content size changes (expanding/collapsing).
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 4.dp,
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp) // small padding inside column
            ) {
                // Message text: padding inside the surface and maxLines controlled by isExpanded.
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(8.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1, // collapse to 1 line when not expanded
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

/*
 Preview composable to visualize the MessageCard in Android Studio.
 Two @Preview annotations show light and dark modes.
 The preview keeps its own local state to allow toggling in the preview panel.
*/
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
            // Local preview state for expansion toggle in preview.
            var previewExpanded by remember { mutableStateOf(false) }
            MessageCard(
                msg = Message(
                    "Sarah",
                    "Jetpack Compose simplifies UI development on Android. It combines a reactive programming model with the conciseness and ease of use of Kotlin."
                ),
                isExpanded = previewExpanded,
                onClick = { previewExpanded = !previewExpanded }
            )
        }
    }
}
