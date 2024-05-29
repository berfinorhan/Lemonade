package com.beecoding.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beecoding.myapplication.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface {
                    LemonadeAppBar()
                    LemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(247, 223, 85),
            titleContentColor = Color.Black,
        ),
        title = {
            Text(
                text = stringResource(R.string.lemonade),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        },
    )
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    var currentStep by remember { mutableIntStateOf(1) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (currentStep) {
            1 -> {
                LemonadeButton(
                    imageResource = R.drawable.lemon_tree,
                    textResource = R.string.tap_the_lemon_tree_to_pick_a_lemon,
                    contentDesc = R.string.lemon_tree,
                    callback = { currentStep = 2 }
                )
            }

            2 -> {
                var taps = (2..4).random()
                LemonadeButton(
                    imageResource = R.drawable.lemon_squeeze,
                    textResource = R.string.keep_tapping_the_lemon_to_squeeze_it,
                    contentDesc = R.string.lemon,
                    callback = {
                        taps--
                        if (taps == 0) {
                            currentStep = 3
                        }
                    }
                )
            }

            3 -> {
                LemonadeButton(
                    imageResource = R.drawable.lemon_drink,
                    textResource = R.string.tap_the_lemonade_to_drink_it,
                    contentDesc = R.string.glass_of_lemonade,
                    callback = { currentStep = 4 }
                )
            }

            4 -> {
                LemonadeButton(
                    imageResource = R.drawable.lemon_restart,
                    textResource = R.string.tap_the_empty_glass_to_start_again,
                    contentDesc = R.string.empty_glass,
                    callback = { currentStep = 1 }
                )
            }

            else -> {

            }
        }
    }
}

@Composable
fun LemonadeButton(
    modifier: Modifier = Modifier,
    imageResource: Int,
    textResource: Int,
    contentDesc: Int,
    callback: () -> Unit
) {
    Button(
        onClick = callback,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(203, 235, 212)
        ),
        shape = RoundedCornerShape(20)
    ) {
        Image(
            painterResource(id = imageResource),
            contentDescription = stringResource(id = contentDesc)
        )
    }

    Spacer(modifier = Modifier.padding(16.dp))

    Text(
        text = stringResource(id = textResource),
        fontSize = 18.sp
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeAppBar()
        LemonadeApp()
    }
}