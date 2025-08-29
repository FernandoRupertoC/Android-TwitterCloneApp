package com.example.capacitacionandroid.data.datasource.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

//@Serializable
//data class NewsResponse(
//    val status: String,
//    val totalResults: Int,
//    val results: List<NewsResult>
//)

//@Serializable
//data class NewsResult(
//    val title: String,
//    val link: String,
//    @SerialName("image_url") val imageURL: String? = null,
//    @SerialName("source_icon") val sourceIcon: String? = null,
//    val description: String? = null,
//    val pubDate: String? = null,
//    @SerialName("source_name")
//    val sourceName: String,
//    @SerialName("source_priority") val sourcePriority: Long
//)




@Serializable
data class NewsResponse (
    val status: String,
    val totalResults: Long,
    val results: List<NewsResult>,
    val nextPage: String
)

@Serializable
data class NewsResult (
    @SerialName("article_id")
    val articleID: String,

    val title: String,
    val link: String,
    val keywords: List<String>? = null,
    val creator: List<String>,
    val description: String,
    val content: AISummary,
    val pubDate: String,
    val pubDateTZ: PubDateTZ,

    @SerializedName("image_url")
    val imageURL: String? = null,


    @SerializedName("video_url")
    val videoURL: JsonElement? = null,

    @SerialName("source_id")
    val sourceID: String,

    @SerialName("source_name")
    val sourceName: String,

    @SerialName("source_priority")
    val sourcePriority: Long,

    @SerialName("source_url")
    val sourceURL: String,

    //@SerialName("source_icon")
    //val sourceIcon: String? = null,
    val source_icon: String? = null,

    val language: Language,
    val country: List<String>,
    val category: List<Category>,
    val sentiment: AIContent,

    @SerialName("sentiment_stats")
    val sentimentStats: AIContent,

    @SerialName("ai_tag")
    val aiTag: AIContent,

    @SerialName("ai_region")
    val aiRegion: AI,

    @SerialName("ai_org")
    val aiOrg: AI,

    @SerialName("ai_summary")
    val aiSummary: AISummary,

    @SerialName("ai_content")
    val aiContent: AIContent,

    val duplicate: Boolean
)

@Serializable
enum class AIContent(val value: String) {
    @SerialName("ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLANS") OnlyAvailableInProfessionalAndCorporatePlans("ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLANS");
}

@Serializable
enum class AI(val value: String) {
    @SerialName("ONLY AVAILABLE IN CORPORATE PLANS") OnlyAvailableInCorporatePlans("ONLY AVAILABLE IN CORPORATE PLANS");
}

@Serializable
enum class AISummary(val value: String) {
    @SerialName("ONLY AVAILABLE IN PAID PLANS") OnlyAvailableInPaidPlans("ONLY AVAILABLE IN PAID PLANS");
}

@Serializable
enum class Category(val value: String) {
    @SerialName("business") Business("business"),
    @SerialName("top") Top("top");
}

@Serializable
enum class Language(val value: String) {
    @SerialName("english") English("english"),
    @SerialName("german") German("german"),
    @SerialName("lithuanian") Lithuanian("lithuanian");
}

@Serializable
enum class PubDateTZ(val value: String) {
    @SerialName("UTC") UTC("UTC");
}
