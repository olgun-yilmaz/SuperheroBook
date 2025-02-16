package com.olgunyilmaz.superherobook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.olgunyilmaz.superherobook.ui.theme.SuperheroBookTheme

class MainActivity : ComponentActivity() {

    private val superheroList = ArrayList<Superhero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            SuperheroBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = "list_screen"
                        ) {

                            composable("list_screen") {
                                fetchData(10)
                                SuperheroList(
                                    superheros = superheroList,
                                    navController = navController
                                )
                            }

                            composable("details_screen/{superhero}",
                                arguments = listOf(
                                    navArgument("superhero") {
                                        type = NavType.StringType
                                    }
                                )) {
                                val jsonSuperHero = remember {
                                    it.arguments?.getString("superhero")
                                }

                                val selectedSuperHero =
                                    Gson().fromJson(jsonSuperHero, Superhero::class.java)

                                DetailsScreen(superhero = selectedSuperHero)
                            }

                        }
                    }
                }
            }
        }
    }

    private fun fetchData(count: Int = 0) {
        for (i in 0..count) {
            createSuperhero("Superman", "DC", R.drawable.superman)
            createSuperhero("Batman", "DC", R.drawable.batman)
            createSuperhero("Ironman", "Marvel", R.drawable.ironman)
            createSuperhero("Spiderman", "Marvel", R.drawable.spiderman)
            createSuperhero("The Joker", "DC", R.drawable.the_joker)
            createSuperhero("Deadpool", "Marvel", R.drawable.deadpool)
        }

    }

    private fun createSuperhero(name: String, universe: String, imageRes: Int) {
        val hero = Superhero(name, universe, imageRes)
        superheroList.add(hero)
    }

}