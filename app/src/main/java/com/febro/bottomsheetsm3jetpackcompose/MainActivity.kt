package com.febro.bottomsheetsm3jetpackcompose

import android.media.Image
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.febro.bottomsheetsm3jetpackcompose.ui.theme.BottomSheetsM3JetpackComposeTheme
import kotlinx.coroutines.launch

/* SOME STASHED CODE GOES HERE */

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetsM3JetpackComposeTheme(
                darkTheme = false
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scaffoldState = rememberBottomSheetScaffoldState(
                        bottomSheetState = rememberStandardBottomSheetState(
                            skipHiddenState = false,
                        )
                    )
                    val scroll = rememberScrollState()
                    val scope = rememberCoroutineScope()

                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetContent = {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(.7f)
                                    .verticalScroll(scroll)
                            ) {
                                Column(
                                    modifier = Modifier
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.hello),
                                        contentDescription = null
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.hello),
                                        contentDescription = null
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.hello),
                                        contentDescription = null
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.hello),
                                        contentDescription = null
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.hello),
                                        contentDescription = null
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth().padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(onClick = {
                                        scope.launch {
                                            scaffoldState.bottomSheetState.hide()
                                        }
                                    }) {
                                        Text(text = "Open sheet")
                                    }
                                }


                            }

                        },
                        sheetPeekHeight = 0.dp,
                        sheetShadowElevation = 15.dp,
                        modifier = Modifier.pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                scope.launch {
                                    if (scaffoldState.bottomSheetState.isVisible) {
                                        scaffoldState.bottomSheetState.hide()
                                    }
                                }
                            })
                        }
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }) {
                                Text(text = "Open sheet")
                            }
                        }
                    }

                }
            }
        }
    }
}