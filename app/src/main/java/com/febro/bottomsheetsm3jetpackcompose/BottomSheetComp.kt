package com.febro.bottomsheetsm3jetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetComp(
    sheetContent: @Composable ColumnScope.() -> Unit,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false, // if true, error (Attempted to animate to hidden when skipHiddenState was enabled. Set skipHiddenState to false to use this func) is produced
        )
    ),
    content: @Composable (PaddingValues) -> Unit,

) {
    val scroll = rememberScrollState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight(.7f)
                    .verticalScroll(scroll),
                content = sheetContent,
            )
        },
        sheetPeekHeight = 0.dp,
        sheetShadowElevation = 15.dp,
        // close programmatically when user tap outside bottom sheet
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                scope.launch {
                    if (scaffoldState.bottomSheetState.isVisible) scaffoldState.bottomSheetState.hide()
                }
            })
        },
        content = content
    )
}