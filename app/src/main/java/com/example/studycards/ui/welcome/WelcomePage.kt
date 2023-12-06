package com.example.studycards.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NavigateNext
import androidx.compose.material.primarySurface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studycards.R
import com.example.studycards.ui.theme.YellowTheme
import com.example.studycards.ui.theme.firaSansFamily

@Composable
fun Welcome(welcomeComplete: () -> Unit) {
//Continue Click will navigate to Login and mark WelcomeComplete as done
    YellowTheme {
        Scaffold(
            backgroundColor = androidx.compose.material.MaterialTheme.colors.primarySurface,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = welcomeComplete,
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Rounded.NavigateNext,
                        contentDescription = stringResource(R.string.label_continue_to_login)
                    )
                }
            }
            //Create padding around the Icon
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(
                    text = stringResource(id = R.string.welcome),
                    //Will override default MaterialTheme font
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    //Set the Font size to large
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Visible,
                    //Some padding around the text
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 12.dp
                    )
                )
                Text(
                    text = stringResource(id = R.string.study),
                    fontFamily = firaSansFamily,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Start,
                    fontStyle = FontStyle.Normal,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 12.dp
                    )
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                // Image container
                //OBRAZEK NENI MOJE TVORBA
                Image(
                    painter = painterResource(id = R.drawable.ic_illustration_welcome),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(
                            horizontal = 10.dp,
                            vertical = 10.dp
                        )
                        .scale(1f) // this will zoom the image
                        .background(color = Color.Transparent) // Set the background color to transparent
                )
            }
        }
    }
}

@Preview(name = "Welcome")
@Composable
private fun WelcomePreview() {
    Welcome(welcomeComplete = { })
}