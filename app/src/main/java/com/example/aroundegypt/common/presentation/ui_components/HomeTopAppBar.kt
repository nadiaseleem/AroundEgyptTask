package com.example.aroundegypt.common.presentation.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.R
import com.example.aroundegypt.common.presentation.ui.theme.Gotham
import com.example.aroundegypt.common.presentation.ui.theme.grey
import com.example.aroundegypt.common.presentation.ui.theme.lightGray
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    drawerState: DrawerState,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit = {},
    onSearchClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    TopAppBar(
        title = {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onSearchClick = onSearchClick,
                hint = stringResource(R.string.search_placeholder),
            )
        },
        navigationIcon = {
            ImageIcon(
                icon = R.drawable.ic_menu,
                contentDescription = stringResource(R.string.menu),
                height = 20.dp,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
            )
        },
        actions = {
            ImageIcon(
                icon = R.drawable.ic_filter,
                contentDescription = stringResource(R.string.filter),
                height = 20.dp,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { onFilterClick() }
            )
        }
    )
}


@Composable
private fun SearchBar(
    hint: String,
    modifier: Modifier = Modifier,
    height: Dp = 40.dp,
    cornerShape: Shape = RoundedCornerShape(10.dp),
    backgroundColor: Color = grey,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .background(color = backgroundColor, shape = cornerShape)
            .clickable { onSearchClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        //Leading Icon: Search
        VectorResIcon(
            icon = R.drawable.ic_search,
            contentDescription = stringResource(R.string.search),
            height = 20.dp,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onSearchClick()
                })

        // Search TextField
        BasicTextField(
            value = searchQuery,
            onValueChange = { newValue -> onSearchQueryChange(newValue) },
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (searchQuery.isEmpty()) {
                        Text(
                            text = hint,
                            color = lightGray,
                            fontSize = 16.sp,
                            fontFamily = Gotham,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClick()
                focusManager.clearFocus()
            }),
            singleLine = true
        )

        // Trailing Icon: Close
        if (searchQuery.isNotEmpty())
            VectorIcon(icon = Icons.Default.Close,
                contentDescription = stringResource(R.string.close),
                size = 20.dp,
                color = lightGray,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        if (searchQuery.isNotEmpty())
                            onSearchQueryChange("")
                        focusManager.clearFocus()
                    })
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TopAppBarPreview() {
    HomeTopAppBar(
        drawerState = rememberDrawerState(
            initialValue = DrawerValue.Closed
        ),
        onFilterClick = {},
        searchQuery = "",
        onSearchQueryChange = {},
        onSearchClick = {}
    )
}