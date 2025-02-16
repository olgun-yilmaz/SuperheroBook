package com.olgunyilmaz.superherobook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.olgunyilmaz.superherobook.ui.theme.SuperheroBookTheme

class MainActivity : ComponentActivity() {

    private val superheroList = ArrayList<Superhero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun fetchData(){
        createSuperhero("Superman","DC",R.drawable.superman)
        createSuperhero("Batman","DC",R.drawable.batman)
        createSuperhero("Ironman","Marvel",R.drawable.ironman)
        createSuperhero("Spiderman","Marvel",R.drawable.spiderman)
        createSuperhero("The Joker","DC",R.drawable.the_joker)
        createSuperhero("Deadpool","Marvel",R.drawable.deadpool)
    }

    private fun createSuperhero(name : String, universe : String, imageRes : Int){
        val hero = Superhero(name,universe,imageRes)
        superheroList.add(hero)
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SuperheroBookTheme {
        Greeting("Android")
    }
}