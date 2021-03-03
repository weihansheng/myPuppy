/*
 * Copyright 2020 The Android Open Source Project
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
package com.github.skydoves.landscapistdemo.model

import android.content.Context
import android.util.Log
import com.example.androiddevchallenge.Dog
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

object MockUtil {

    fun getFromAssets(mContext: Context, fileName: String): String? {
        try {
            val inputReader = InputStreamReader(mContext.getResources().getAssets().open(fileName))
            val bufReader = BufferedReader(inputReader)
            var line: String? = ""
            var Result: String? = ""
            while (bufReader.readLine().also { line = it } != null) Result += line
            return Result
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    fun getDogsFromJson(mContext: Context, fileName: String?): List<Dog> {
        var dogList = ArrayList<Dog>()
        var jsonStr = getFromAssets(mContext, "data.json")
        var jsonObject = JSONObject(jsonStr)
        val animals = jsonObject.getString("animals")
        val animalsArr = JSONArray(animals)
        val gsonObj = Gson()
        for (i in 0 until animalsArr.length()) {
            var animalStr = (animalsArr.get(i) as JSONObject).getString("animal")
            var dog = gsonObj.fromJson(animalStr, Dog::class.java)
            Log.d("johantest", "dog:" + dog.id)
            dogList.add(dog)
        }
        Log.d("johantest", "arr -----:" + animalsArr.length())
        Log.d("johantest", "result -----:" + animals)

        return dogList
    }
}
