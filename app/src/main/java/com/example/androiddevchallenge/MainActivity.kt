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
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.github.skydoves.landscapistdemo.model.MockUtil
import dev.chrisbanes.accompanist.glide.GlideImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(this)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(context: Context) {
    var dogList = MockUtil.getDogsFromJson(context, "data.json")
    Log.d("johantest", "hahahahhahah")
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            Modifier.padding(horizontal = 16.dp),
            content = {
                items(dogList) { dog ->
                    DogCard(dog, context)
                }
            }
        )
    }
}

@Composable
fun DogCard(dog: Dog, context: Context) {
    val padding = 16.dp
    Column(
        Modifier.clickable {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("dog", dog)
            context.startActivity(intent)
        }
            .padding(padding)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            GlideImage(
                data = dog.primary_photo_cropped_url,
                contentDescription = "",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(10.dp))
            Column() {
                Text(dog.name, fontSize = 15.sp)
                Text(dog.breeds_label)
            }
        }
        Spacer(Modifier.height(10.dp))
        Card(elevation = 4.dp) {
            GlideImage(
                data = dog.primary_photo_url,
                contentDescription = "",
                modifier = Modifier
                    .width(400.dp)
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

// @Preview("Light Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun LightPreview() {
//    MyTheme {
//        MyApp()
//    }
// }
//
// @Preview("Dark Theme", widthDp = 360, heightDp = 640)
// @Composable
// fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp(null)
//    }
// }
