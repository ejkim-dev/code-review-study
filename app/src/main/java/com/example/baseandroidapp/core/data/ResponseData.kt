package com.example.baseandroidapp.core.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class DefaultResponse {
    @SerializedName("code")
    @Expose
    var code: Int = -1

    @SerializedName("msg")
    @Expose
    var msg: String? = null

    @SerializedName("success")
    @Expose
    var success: Boolean = false

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("errKey")
    var errKey: String? = null

    constructor()

    constructor(code: Int, msg: String?) {
        this.code = code
        this.msg = msg
    }
}


data class VodResponse(
    @SerializedName("data")
    val data: VodDetailEntity
) : DefaultResponse()

data class VodListResponse(
    @SerializedName("page")
    val list: TutorialListContents
) : DefaultResponse()

data class TutorialListContents(
    @SerializedName("content")
    val vodList: List<VodEntity>
)


// response wrapper test
sealed class DefaultRes<T>(
    @SerializedName("code")
    @Expose
    var code: Int = -1,

    @SerializedName("msg")
    @Expose
    var msg: String? = null,

    @SerializedName("success")
    @Expose
    var success: Boolean = false,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("errKey")
    var errKey: String? = null
) {

    fun isSuccessful() = success

    class SingleResult<T>(
        @SerializedName("data")
        val data: T
    ) : DefaultRes<T>()

    class ListResult<T>(
        @SerializedName("list")
        val list: T
    ) : DefaultRes<T>()

    class PageResult<T>(
        @SerializedName("page")
        val page: PageObject<T>
    ) : DefaultRes<T>()

}


class PageObject<T>(
    val content: T
)



