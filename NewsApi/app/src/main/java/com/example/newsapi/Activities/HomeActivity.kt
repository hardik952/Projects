package com.example.newsapi.Activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.Adapter.CategoryAdapter
import com.example.newsapi.Adapter.NewsAdapter
import com.example.newsapi.Model.Article
import com.example.newsapi.Model.Category
import com.example.newsapi.Model.Country
import com.example.newsapi.Model.TopNewsResponse
import com.example.newsapi.Network.ApiClient
import com.example.newsapi.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(),  NewsAdapter.OnItemClickListener {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var countryAdapter: ArrayAdapter<String>
    private var countryList = mutableListOf<Country>()

    private lateinit var categoryAdapter: CategoryAdapter
    private var categoryList = mutableListOf<Category>()

    private lateinit var newsAdapter: NewsAdapter
    private var articleList = mutableListOf<Article>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareCountry()
        countryAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,countryList.map { it.name })
        binding.spinner.adapter = countryAdapter
        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var selectedCountry = countryList[position].code
                fetchTopNews(selectedCountry)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        prepareCategory()
        categoryAdapter = CategoryAdapter(this, categoryList)
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.adapter = categoryAdapter

        newsAdapter = NewsAdapter(this, articleList,this)
        binding.newsRecyclerView.adapter = newsAdapter
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchTopNews(countryList.first().code)


    }
    private fun fetchTopNews(countyCode: String) {
        ApiClient.init().getTopNews(countyCode).enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful) {
                    var news = response.body()?.articles
                    news?.let {
                        newsAdapter.articleList = it
                        newsAdapter.notifyDataSetChanged()

                        categoryAdapter.categoryClickListner= {position, category ->
                            fetchCategoryNews(countyCode,position,category)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }


    private fun fetchCategoryNews(countyCode: String,position:Int,category: Category) {
        ApiClient.init().getCategoryNews(countyCode,category.name).enqueue(object :
            Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    var news = response.body()?.articles
                    news?.let {
                        newsAdapter.articleList = it
                        newsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }

    private fun prepareCategory() {
        categoryList.add(Category(1, "business"))
        categoryList.add(Category(2, "entertainment"))
        categoryList.add(Category(3, "general"))
        categoryList.add(Category(4, "health"))
        categoryList.add(Category(5, "science"))
        categoryList.add(Category(6, "sports"))
        categoryList.add(Category(7, "technology"))

    }

    private fun prepareCountry() {
        countryList.add(Country(1, "India", "in"))
        countryList.add(Country(2, "Australia", "au"))
        countryList.add(Country(3, "China", "cn"))
        countryList.add(Country(4, "France", "fr"))
        countryList.add(Country(5, "Hong kong", "hk"))
        countryList.add(Country(6, "Switzerland", "ch"))
        countryList.add(Country(7, "Saudi Arabia", "sa"))
        countryList.add(Country(8, "Philippines", "ph"))
        countryList.add(Country(9, "Russia", "ru"))
        countryList.add(Country(10, "Germany", "de"))
    }

     override fun onItemClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}