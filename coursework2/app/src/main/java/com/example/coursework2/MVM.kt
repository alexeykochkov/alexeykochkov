package com.example.coursework2

import Database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework2.popular.PopularInfo
import com.example.coursework2.premier.PremierFilm
import com.example.coursework2.staff.StaffInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class MVM : ViewModel() {

   var databaseMVM: Database? = null

    //получение всего списка фильмов
    fun getFilmInfo() {
        viewModelScope.launch {
            if (databaseMVM != null) {
                state4_.value = databaseMVM!!.tableDao().showAllPhoto()
            }
        }
    }

    fun changeLikeFilm(filmid: Int) {
        viewModelScope.launch {
            var film = databaseMVM?.tableDao()?.getFilm(filmid)
            if (film == null) {
                film = PhotoTable(filmid, true, false, "")
            } else {
                film.beLoved = !film.beLoved
            }
            databaseMVM?.tableDao()?.insertPhoto(film)
        }
    }

    enum class Month {
        JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
    }

    private val retrofitPremiersFilms = RetrofitInstance().premieresApi
    private val state_ = MutableStateFlow<List<PremierFilm>?>(null)
    val state = state_.asStateFlow()

    private val retrofitPopularFilms = RetrofitInstance().popularApi
    private val state2_ = MutableStateFlow<PopularInfo?>(null)
    val state2 = state2_.asStateFlow()

    private val retrofitStaffInfo = RetrofitInstance().staffApi
    private val state3_ = MutableStateFlow<ArrayList<StaffInfo>>(arrayListOf())
    val state3 = state3_.asStateFlow()

    //инициализация Лайв Даты для получения списка фильмов
    private val state4_ = MutableStateFlow<Flow<List<PhotoTable>>>(emptyFlow())
    val state4 = state4_.asStateFlow()

    fun requestPremiersFilms() {
        viewModelScope.launch {
            var random = (0 until Month.values().size).random()
            var randomMonthName = Month.values()[random].name
            val result = retrofitPremiersFilms.getInformationPremieres(year = 2019, randomMonthName)
            if (result.items.size <= 20) {
                state_.value = result.items
            } else {
                state_.value = result.items.take(20)
            }
            var retr = RetrofitInstance().staffApi.getInformationStaff(301)
            println("11111112222222223333333333444444444444444444444${retr}")
        }
    }

    fun requestPopularFilms() {
        viewModelScope.launch {
            val result =
                retrofitPopularFilms.getInformationPopular(type = "TOP_250_MOVIES", page = 1)
            state2_.value = result
        }
    }

    fun requestStaffInfo(filmid: Int) {
        viewModelScope.launch {
            var retr = RetrofitInstance().staffApi.getInformationStaff(filmid)
//            val result = retrofitStaffInfo.getInformationStaff(filmId = 301)
            state3_.value = retr
        }
    }

    fun requestByKey(key: Int) {
        when (key) {
            1 -> requestPremiersFilms()
            2 -> requestPopularFilms()
        }
    }


}