package cmin.quizapp

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class QuizViewModel : ViewModel() {
    var ranks = MutableLiveData<List<LeaderBoardRank>>()

    fun updateLeaderBoardRank(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val username = getUserName(context)
            val score = getScore(context)
            val repository = Repository()
            val response = repository.leaderBoardRank
            if (response is Response.Success<List<LeaderBoardRank>>) {
                if (username != null) {
                    for (i in response.data.indices) {
                        val leaderBoardRank = response.data.get(i)
                        if (leaderBoardRank.username == username) {
                            response.data.removeAt(i)
                            break
                        }
                    }
                    response.data.add(LeaderBoardRank(username, score))
                    Collections.sort(response.data,
                        Comparator { rank1: LeaderBoardRank, rank2: LeaderBoardRank -> rank2.score - rank1.score })
                    repository.updatePerson(response.data)
                }
                ranks.postValue(response.data)
            } else {
                ranks.postValue(ArrayList())
            }
        }
    }

    fun saveScore(context: Context, score: Int) {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putInt("score", score)
        myEdit.apply()
    }

    fun saveUserName(context: Context, userName: String?) {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

        // Creating an Editor object to edit(write to the file)
        val myEdit = sharedPreferences.edit()

        // Storing the key and its value as the data fetched from edittext
        myEdit.putString("username", userName)

        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        myEdit.apply()
    }

    fun getUserName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", null)
    }

    fun getScore(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("score", 0)
    }
}