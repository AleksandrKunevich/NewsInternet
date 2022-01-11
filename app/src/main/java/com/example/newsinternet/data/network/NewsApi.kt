package com.example.newsinternet.data.network

import com.example.newsinternet.data.network.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    /**
    Everything /v2/everything

    Request parameters

    "q" -> query
    Keywords or phrases to search for in the article title and body.

    "qInTitle" ->qInTitle
    Keywords or phrases to search for in the article title only.

    "sources" -> sources
    A comma-seperated string of identifiers (maximum 20) for the news sources or blogs you want headlines from. Use the /sources endpoint to locate these programmatically or look at the sources index.

    "domains" -> domains
    A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to restrict the search to.

    "excludeDomains" -> excludeDomains
    A comma-seperated string of domains (eg bbc.co.uk, techcrunch.com, engadget.com) to remove from the results.

    "from" -> "fromDate"
    A date and optional time for the oldest article allowed. This should be in ISO 8601 format (e.g. 2021-12-18 or 2021-12-18T09:47:29)
    Default: the oldest according to your plan.

    "to" -> toDate
    A date and optional time for the newest article allowed. This should be in ISO 8601 format (e.g. 2021-12-18 or 2021-12-18T09:47:29)
    Default: the newest according to your plan.

    "sortBy" -> sortBy
    The order to sort the articles in. Possible options: relevancy, popularity, publishedAt.
    relevancy = articles more closely related to q come first.
    popularity = articles from popular sources and publishers come first.
    publishedAt = newest articles come first.
    Default: publishedAt

    "pageSize" -> pageSize
    int
    The number of results to return per page.
    Default: 100. Maximum: 100.

    "page" -> page
    int
    Use this to page through the results.
    Default: 1.

    "apiKey" -> apiKey
    REQUIRED
    Your API key. Alternatively you can provide this via the X-Api-Key HTTP header.

    "category" -> category
    Find sources that display news of this category. Possible options: business entertainment general health science sports technology. Default: all categories.

    "language" -> language
    Find sources that display news in a specific language. Possible options: ardeenesfrheitnlnoptruseudzh. Default: all languages.

    "country" -> country
    Find sources that display news in a specific country. Possible options: ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx myngnlnonzphplptrorsrusasesgsiskthtrtwuausveza. Default: all countries.
     **/

    @GET ("everything")
    suspend fun getEverything(
        @Query("q") query: String = "",
        @Query("qInTitle") qInTitle: String = "",
        @Query("sources") sources: String = "",
        @Query("domains") domains: String = "",
        @Query("excludeDomains") excludeDomains: String ="",
        @Query("from") fromDate: String = "",
        @Query("to") toDate: String = "",
        @Query("sortBy") sortBy: String = "",
        @Query("category") category: String = "",
        @Query("language") language: String = "",
        @Query("country") country: String = "",
        @Query("pageSize") pageSize: Int = 10,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = RetrofitClient.API_KEY,
    ): NewsResponse
}