package com.tw.training.catkeeper.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by pchen on 01/11/2017.
 */
data class CatsNearby(@SerializedName("id")val id: String,
                      @SerializedName("cat")val name: String,
                      @SerializedName("avatar")val avatar: CatImage,
                      @SerializedName("timestamp")val updateTime: String,
                      @SerializedName("thumbs")val thumbsList: List<CatImage>)