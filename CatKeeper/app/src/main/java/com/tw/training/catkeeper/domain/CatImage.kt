package com.tw.training.catkeeper.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by pchen on 06/11/2017.
 */
data class CatImage(@SerializedName("image")val imageUrl: String)