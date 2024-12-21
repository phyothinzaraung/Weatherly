package com.phyo.weatherly.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phyo.weatherly.R

@Composable
fun SearchView(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = colorResource(R.color.grey), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = onSearchTextChanged,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (searchText.isNotEmpty()){
                        onSearch(searchText)
                    }
                }
            ),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (searchText.isEmpty()) {
                            Text(
                                text = "Search Location",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                    IconButton(onClick = {
                        if (searchText.isNotEmpty()){
                            onSearch(searchText)
                        }
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = "Search Icon",
                            tint = Color.Gray,
                            modifier = modifier.size(24.dp)
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchView() {
    SearchView(
        searchText = "London",
        onSearchTextChanged = {},
        onSearch = {}
    )
}
