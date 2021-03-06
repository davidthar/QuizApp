package com.davidthar.quizapp.model

import android.app.Application
import androidx.room.Room
import com.davidthar.quizapp.model.database.RankingDatabase
import com.davidthar.quizapp.model.database.RankingEntity
import org.jetbrains.anko.doAsync

/*
** Coded by David Montes on 15/03/2022.
** https://github.com/davidthar
*/

class QuizApp : Application() {
    companion object{
        lateinit var database : RankingDatabase
        lateinit var ranking : MutableList<RankingEntity>
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, RankingDatabase::class.java, "ranking_table").build()

        doAsync {
            ranking = database.rankingDao().getTenRanking()

            if(ranking.isEmpty()){
                database.rankingDao().addToRanking(RankingEntity(name = "David", points = 270))
                database.rankingDao().addToRanking(RankingEntity(name = "Tania", points = 240))
                database.rankingDao().addToRanking(RankingEntity(name = "Raquel", points = 190))
                database.rankingDao().addToRanking(RankingEntity(name = "Jose", points = 167))
                database.rankingDao().addToRanking(RankingEntity(name = "Julián", points = 130))
                database.rankingDao().addToRanking(RankingEntity(name = "Duna", points = 99))
                database.rankingDao().addToRanking(RankingEntity(name = "Roberto", points = 87))
                database.rankingDao().addToRanking(RankingEntity(name = "Lucía", points = 74))
                database.rankingDao().addToRanking(RankingEntity(name = "Silvia", points = 55))
                database.rankingDao().addToRanking(RankingEntity(name = "Alejandro", points = 14))
                ranking = database.rankingDao().getTenRanking()
            }
        }
    }
}