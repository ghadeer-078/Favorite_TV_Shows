package com.example.ghadeer_s.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ghadeer_s.API.APIClient
import com.example.ghadeer_s.API.APIInterface
import com.example.ghadeer_s.Database.TV
import com.example.ghadeer_s.Database.TVDao
import com.example.ghadeer_s.Database.TVDatabase
import com.example.ghadeer_s.Model.ShowResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModel(application: Application) : AndroidViewModel(application) {

    private val apiInterface by lazy { APIClient().getClient().create(APIInterface::class.java) }
    private val tvDao: TVDao by lazy { TVDatabase.getInstance(application).TVDao() }
    private val apiList: MutableLiveData<List<ShowResponseItem>> = MutableLiveData()
    private var dbList: LiveData<List<TV>> = tvDao.getShows()

    fun getList() = apiList

    fun getDBList() = dbList

    fun getData(show: String) {
        apiInterface.getShow(show).enqueue(
            object : Callback<ArrayList<ShowResponseItem>> {
                override fun onResponse(
                    call: Call<ArrayList<ShowResponseItem>>,
                    response: Response<ArrayList<ShowResponseItem>>
                ) {
                    Log.d("Retrofit", "onResponse: $response ")
                    apiList.postValue(response.body()!!)
                }

                override fun onFailure(
                    call: Call<ArrayList<ShowResponseItem>>,
                    t: Throwable
                ) {
                    Log.d("Retrofit", "onFailure: ${t.message}")
                }
            }
        )
    }

    private fun getDBData() {
        dbList = tvDao.getShows()
    }

    fun addToDB(tvShow: TV) {
        tvDao.addShow(tvShow)
        getDBData()
    }

    fun deleteFromDB(tvShow: TV) {
        tvDao.deleteShow(tvShow)
        getDBData()
    }
}