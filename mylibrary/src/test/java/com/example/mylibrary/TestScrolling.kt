package com.example.mylibrary

import android.os.Build
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.R])
@LooperMode(LooperMode.Mode.PAUSED)
class TestScrolling {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `Check scrolling`() {
        composeTestRule.setContent {
            Column(
                Modifier
                    .fillMaxHeight()
                    .testTag("root")
                    .verticalScroll(state = rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(state = rememberScrollState())
                        .padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 400.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "text", modifier = Modifier.testTag("text_tag"))
                }
                Button(
                    modifier = Modifier
                        .testTag("button_tag"),
                    onClick = {}
                ) {
                    Text(text = "button")
                }
            }
        }
        composeTestRule.onNodeWithTag("button_tag").performScrollTo().assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("text_tag").performScrollTo().assertIsDisplayed()
    }
}