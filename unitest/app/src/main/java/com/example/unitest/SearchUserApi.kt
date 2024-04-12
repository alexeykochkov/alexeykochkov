
import com.example.unitest.User
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchUserApi {

    @GET(".")
    suspend fun getRandomUser(): User
}