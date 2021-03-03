/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

class DetailActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dog = intent.getSerializableExtra("dog") as Dog
        setContent {
            MyTheme {
                Detail(this, dog)
            }
        }
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@Composable
fun Detail(context: Context, dog: Dog) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier.padding(horizontal = 16.dp)
        ) {
            LazyColumn(

                content = {
//                    stickyHeader {
//                        Text(dog.description)
//                    }
                    items(dog.photo_urls) { url ->
                        val index = dog.photo_urls.indexOf(url)
                        if (index == 0) {
                            Text(dog.description)
                            Spacer(Modifier.height(10.dp))
                        }

                        GlideImage(
                            data = url,
                            contentDescription = "",
                            modifier = Modifier.width(500.dp).height(400.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
            )
        }
    }
}
