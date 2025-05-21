package com.example.hockeyapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hockeyapp.Constant
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class NewsViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchNewsTopHeadlines()
    }

    fun fetchNewsTopHeadlines() {

        _isLoading.postValue(true)

        val newsApiClient = NewsApiClient(Constant.API_KEY)
        val requestHockey = EverythingRequest.Builder().q("hockey").build()

        newsApiClient.getEverything(requestHockey, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                _isLoading.postValue(false)
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                _isLoading.postValue(false)
                throwable?.localizedMessage?.let {
                    Log.i("NewsAPI Response Failed", it)
                }
            }
        })
    }
}
