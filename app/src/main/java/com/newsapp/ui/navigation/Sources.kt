package com.newsapp.ui.navigation

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.newsapp.R
import com.newsapp.models.Article
import com.newsapp.network.NewsManager

@Composable
fun Sources(newsManager: NewsManager) {
    val menuItems = listOf(
        "ABC News" to "ABC-NEWS",
        "TechCrunch" to "TechCrunch",
        "talkSPORT" to "TalkSport",
        "Reuters" to "Reuters",
        "The Verge" to "The-Verge"
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = newsManager.sourceName.value) },
            actions = {
                var menuExpanded by remember {
                    mutableStateOf(false)
                }
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                }
                MaterialTheme(
                    shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(16.dp))
                ){
                    DropdownMenu(expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }) {
                        menuItems.forEach {
                            DropdownMenuItem(onClick = {
                                newsManager.sourceName.value = it.second
                                menuExpanded = false
                            }) {
                                Text(text = it.first)
                            }
                        }
                    }
                }
            }
        )
    }) {
        newsManager.getArticlesBySources()
        val articlesList = newsManager.responseSources.value
        SourceContent(articles = articlesList.articles?: listOf())
    }
}

@Composable
fun SourceContent(articles: List<Article>) {
    val uriHandler = LocalUriHandler.current
    LazyColumn {
        items(articles) {
                article ->
            val annotatedString = buildAnnotatedString {
                pushStringAnnotation(
                    tag = "URL",
                    annotation = article.url ?: "newsapi.org"
                )
                withStyle(style = SpanStyle(color = colorResource(id = R.color.blue_sky),
                    textDecoration = TextDecoration.Underline)
                ) {
                    append("Read Full Article")
                }
            }
            Card(backgroundColor = colorResource(id = R.color.teal_700),
                elevation = 6.dp, modifier = Modifier.padding(8.dp)
            ) {
                Column(modifier = Modifier
                    .height(200.dp)
                    .padding(start = 8.dp, end = 8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly) {

                    Text(text = article.title!!,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = article.description!!,
                        color = colorResource(id = R.color.white),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Card(backgroundColor = Color.White, elevation = 6.dp) {
                        ClickableText(text = annotatedString,
                            modifier = Modifier.padding(8.dp),
                            onClick = {
                                annotatedString.getStringAnnotations(it, it).firstOrNull()?.let {
                                        result ->
                                    if (result.tag == "URL") {
                                        uriHandler.openUri(result.item)
                                    }
                                }
                            })
                    }
                }
            }
        }
    }
}