package com.rishikeshwadkar.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_bitcoin.*

class BitcoinFragment : Fragment(), NewsItemClicked {

    lateinit var mAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bitcoin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bitcoinRecycler.layoutManager = LinearLayoutManager(context)
        fetchData(view)
        mAdapter = NewsAdapter(this)
        bitcoinRecycler.adapter = mAdapter
    }

    private fun fetchData(view: View) {
        val url = "http://newsapi.org/v2/everything?q=bitcoin&from=2020-11-16&sortBy=publishedAt&apiKey=d3bc3ef74e984ef1ac8f475a39d4c77d"
        // Request a string response from the provided URL.
        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET, url,null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()

                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {
                Snackbar.make(view,"Server is down",Snackbar.LENGTH_LONG).setAction("Action",null)
            }
        )
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        // Add the request to the RequestQueue.
        MySingleton.getInstance(activity?.applicationContext!!).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(item.url)
        startActivity(intent)
    }
}