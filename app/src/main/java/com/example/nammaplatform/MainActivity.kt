```kotlin id="yzmq4r"
package com.example.nammaplatform

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Text To Speech Initialization
        tts = TextToSpeech(this) { status ->

            if (status == TextToSpeech.SUCCESS) {

                val result = tts.setLanguage(Locale("kn", "IN"))

                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED
                ) {

                    tts.language = Locale.US
                }
            }
        }

        setContent {

            // Removed NammaPlatformTheme error
            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainScreen(
                        onSpeakClick = {

                            tts.speak(
                                "ಮುಂದಿನ ರೈಲು ಪ್ಲಾಟ್‌ಫಾರ್ಮ್ ೨ಕ್ಕೆ ಬರುತ್ತದೆ",
                                TextToSpeech.QUEUE_FLUSH,
                                null,
                                "tts1"
                            )
                        }
                    )
                }
            }
        }
    }

    override fun onDestroy() {

        if (::tts.isInitialized) {

            tts.stop()
            tts.shutdown()
        }

        super.onDestroy()
    }
}

@Composable
fun MainScreen(onSpeakClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D47A1))
            .padding(16.dp)
    ) {

        // App Title
        Text(
            text = "Namma-Platform",
            color = Color.Yellow,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Station Name
        Text(
            text = "Station: Mysuru Junction",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Train Section
        Text(
            text = "Next 3 Trains",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        TrainCard("Mysuru Express", "Platform 2")
        TrainCard("Hampi Express", "Platform 1")
        TrainCard("Chamundi Express", "Platform 3")

        Spacer(modifier = Modifier.height(30.dp))

        // Coach Layout
        Text(
            text = "Coach Layout",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            CoachBox("Engine")
            CoachBox("General")
            CoachBox("Ladies")
            CoachBox("S1")
            CoachBox("S2")
            CoachBox("S3")
            CoachBox("S4")
            CoachBox("A1")
            CoachBox("B1")
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Kannada Speak Button
        Button(
            onClick = onSpeakClick,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow
            )
        ) {

            Text(
                text = "Speak in Kannada",
                color = Color.Black,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun CoachBox(name: String) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Yellow
        ),
        shape = RoundedCornerShape(10.dp)
    ) {

        Box(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 16.dp
            )
        ) {

            Text(
                text = name,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun TrainCard(trainName: String, platform: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Yellow
        ),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = trainName,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = platform,
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }
    }
}
```
