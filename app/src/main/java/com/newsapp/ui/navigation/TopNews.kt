package com.newsapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.newsapp.DateFormat
import com.newsapp.DateFormat.getTimeAgo
import com.newsapp.R
import com.newsapp.models.Article
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun TopNews(navController: NavController, articles: List<Article>) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Headlines",
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(color = colorResource(id = R.color.blue_bg)))
        LazyColumn {
            items(articles.size) {
                    index ->
                NewsItem(article = articles[index],
                    onItemClick = {
                        navController.navigate("Detail/$index")
                    }
                )
            }
        }
    }
}

@Composable
fun NewsItem(article: Article, onItemClick: ()-> Unit = {}) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clickable {
                onItemClick()
            }) {

        GlideImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(id = R.drawable.news),
            placeHolder = ImageBitmap.imageResource(id = R.drawable.news)
        )
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable {
                onItemClick()
            }
    ) {
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 6.dp, start = 15.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = article.title!!,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Published " + DateFormat.stringToDate(article.publishedAt!!).getTimeAgo(),
                color = Color.Black, fontWeight = FontWeight.SemiBold)
        }
    }
}
