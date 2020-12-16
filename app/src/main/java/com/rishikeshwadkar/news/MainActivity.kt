package com.rishikeshwadkar.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this,R.id.nav_host)
        NavigationUI.setupActionBarWithNavController(this,navController)

        bottomNavigationView.setupWithNavController(navController)
    }

//    private fun fetchData() {
//        val url = "http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=d3bc3ef74e984ef1ac8f475a39d4c77d"
//        mUrl = url
//        // Request a string response from the provided URL.
//        val jsonObjectRequest = object: JsonObjectRequest(Request.Method.GET, url,null,
//                Response.Listener {
//                    val newsJsonArray = it.getJSONArray("articles")
//                    val newsArray = ArrayList<News>()
//
//                    for(i in 0 until newsJsonArray.length()){
//                        val newsJsonObject = newsJsonArray.getJSONObject(i)
//                        val news = News(
//                                newsJsonObject.getString("title"),
//                                newsJsonObject.getString("author"),
//                                newsJsonObject.getString("url"),
//                                newsJsonObject.getString("urlToImage")
//                        )
//                        newsArray.add(news)
//                    }
//                    mAdapter.updateNews(newsArray)
//                },
//                Response.ErrorListener {
//
//                }
//        )
//        {
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                headers["User-Agent"] = "Mozilla/5.0"
//                return headers
//            }
//        }
//
//        // Add the request to the RequestQueue.
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
//    }
//
//    override fun onItemClicked(item: News) {
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.data = Uri.parse(item.url)
//        startActivity(intent)
//    }
}