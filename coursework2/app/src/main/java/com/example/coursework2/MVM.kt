package com.example.coursework2


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coursework2.database.CollectionEntity
import com.example.coursework2.database.Database
import com.example.coursework2.database.FilmEntity
import com.example.coursework2.popular.PopularInfo
import com.example.coursework2.premier.PremierFilm
import com.example.coursework2.staff.StaffInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

const val MY_LOVE_COLLECTION = "Любимые"
const val MY_WANT_TO_SEE_COLLECTION = "Хочу Посмотреть"
class MVM : ViewModel() {


    var databaseMVM: Database? = null


    //получение всего списка фильмов
    fun getFilmInfo() {
        viewModelScope.launch {
            if (databaseMVM != null) {

                state4_.value = databaseMVM!!.tableDao().showAllFilms()

            }
        }
    }

    fun addRequiredCollectionInCollectionTable() {
        viewModelScope.launch {
            if (databaseMVM != null) {
                databaseMVM!!.tableDaoCollection().showAllCollection().collect {
                    if (it.firstOrNull{it.collectionName == MY_LOVE_COLLECTION} == null ) {
                        databaseMVM!!.tableDaoCollection().insertFilmInCollection(CollectionEntity(
                            MY_LOVE_COLLECTION, emptyList()
                        ))
                    }
                    if (it.firstOrNull{it.collectionName == MY_WANT_TO_SEE_COLLECTION} == null ) {
                        databaseMVM!!.tableDaoCollection().insertFilmInCollection(CollectionEntity(
                            MY_WANT_TO_SEE_COLLECTION, emptyList() ))
                    }
                }
            }
        }
    }


    fun changeLikeFilm(filmid: Int) {
        viewModelScope.launch {
            var film = databaseMVM?.tableDao()?.getFilm(filmid)
            if (film == null) {

                film = FilmEntity(filmid, beLoved = true)
            } else {
                film.beLoved = !film.beLoved
            }
            databaseMVM?.tableDao()?.insertFilm(film)
        }
    }

    fun changeWantToSee(filmid: Int) {
        viewModelScope.launch {
            var film = databaseMVM?.tableDao()?.getFilm(filmid)
            if (film == null) {
                film = FilmEntity(filmid, wantToSee = true)
            } else {
                film.wantToSee = !film.wantToSee
            }
            databaseMVM?.tableDao()?.insertFilm(film)
        }
    }

    fun changeViewed(filmid: Int) {
        viewModelScope.launch {
            var film = databaseMVM?.tableDao()?.getFilm(filmid)
            if (film == null) {
                film = FilmEntity(filmid, viewed = true)
            } else {
                film.viewed = !film.viewed
            }
            databaseMVM?.tableDao()?.insertFilm(film)
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


    //инициализация Лайв Даты для получения списка фильмов (присваивание)
    private val state4_ = MutableStateFlow<Flow<List<FilmEntity>>>(emptyFlow())

    //обработка рез-та
    val state4 = state4_.asStateFlow()

    //инициализация Лайв Даты для получения списка фильмов (присваивание)
    private val state5_ = MutableStateFlow<Flow<List<CollectionEntity>>>(emptyFlow())

    //обработка рез-та
    val state5 = state5_.asStateFlow()

    fun requestPremiersFilms() {
        viewModelScope.launch {
            var random = (0 until Month.values().size).random()
            var randomMonthName = Month.values()[random].name
            val result = retrofitPremiersFilms.getInformationPremieres(year = 2019, randomMonthName)
            val list = if (result.items.size <= 20) {
                result.items
            } else {
                result.items.take(20)
            }
            list.forEach {
                val film = databaseMVM?.tableDao()?.getFilm(it.kinopoiskId)
                if (film?.viewed == true) {
                    it.viewed = true
                }
            }
            state_.value = list
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